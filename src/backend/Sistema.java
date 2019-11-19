/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author Santiago
 */
public class Sistema {

    private List<Venta> ventas;
    private List<Envase> envases;
    private List<Articulo> articulos;
    private List<Propuesta> propuestas;

    private int beneficioAmbiental;

    private List<Venta> ventasCliente;
    private List<Propuesta> propuestasCliente;
    private Venta carrito;
    private boolean esAdmin;

    public Sistema() {
        this.ventas = new ArrayList();
        this.envases = new ArrayList();
        this.articulos = new ArrayList();
        this.propuestas = new ArrayList();
        this.propuestasCliente = new ArrayList();
        this.ventasCliente = new ArrayList();

        this.beneficioAmbiental = 0;
        this.carrito = new Venta();
        this.esAdmin = true;
    }

    /**
     * Resetea el historial y la lista de propuestas del cliente a los valores por defecto
     */
    public void cerrarSesion() {
        this.propuestasCliente = new ArrayList(propuestas);
        this.ventasCliente = new ArrayList();
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public boolean getEsAdmin() {
        return this.esAdmin;
    }

    public Venta getCarrito() {
        return carrito;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public List<Venta> getVentasCliente() {
        return ventas;
    }

    public List<Envase> getEnvases() {
        return envases;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public int getBeneficioAmbiental() {
        return beneficioAmbiental;
    }

    public void setBeneficioAmbiental(int beneficioAmbiental) {
        this.beneficioAmbiental = beneficioAmbiental;
    }

    /**
     * Se crea el articulo con los parametros recibidos y se agrega al sistema
     * @param nombre Nombre del articulo
     * @param origen País de origen del articulo
     * @param descripcion Descripción del articulo
     * @param precio Precio del articulo, debe ser positivo
     * @param tipo Tipo del articulo
     * @param img Imagen del articulo, debe existir
     * @param categorias Categorias del articulo
     * @throws IOException 
     */
    public void agregarArticulo(String nombre, String origen, String descripcion, int precio,
            Articulo.Tipo tipo, Image img, Articulo.Categoria[] categorias) throws IOException {
            Image image = img;
            Articulo a = new Articulo(nombre, origen, descripcion, precio, this.articulos.size() + 1, tipo, image, categorias);
            this.articulos.add(a);
    }

    /**
     * 
     * @param id
     * @param origen
     * @param precio
     * @param tipo 
     */
    public void actualizarArticulo(int id, String origen, int precio, Articulo.Tipo tipo) {
        Articulo a = this.articulos.get(id);
        a.setOrigen(origen);
        a.setPrecio(precio);
        a.setTipo(tipo);
    }

    /**
     * PRE:- POS: Si costeProduccion>0 se agrega el envase y retorna true, de lo
     * contrario retorna false
     *
     * @param nombre Nombre del envase
     * @param tipos tipos de alimento/bebida aceptados por el envase
     * @param costeProduccion coste ambiental de la produccion del envase debe
     * ser positivo
     */
    public boolean agregarEnvase(String nombre, Articulo.Tipo[] tipos, int costeProduccion) {
        if (costeProduccion > 0) {
            Envase e = new Envase(nombre, this.envases.size() + 1, tipos, costeProduccion);
            this.envases.add(e);
            return true;
        } else {
            return false;
        }
    }

    /**
     * PRE:- POS: Se agrega la venta a la lista y se actualizan los usos de los
     * elementos y el beneficio global
     */
    public String registrarVenta() {
        //TODO descomentar cuando las compras tengan envases no nulos
        ventas.add(carrito);
        ventasCliente.add(carrito);

        /*
        for (int i = 0; i < carrito.getCompras().size(); i++) {
            Compra c = carrito.getCompras().get(i);
            c.getArticulo().aumentarUso(c.getCantidad());
            c.getEnvase().aumentarUso(c.getCantidad());
            this.actualizarBeneficio(c.getEnvase(), c.getCantidad());
        }
         */
        String ret = carrito.generarTicketDGI();
        carrito = new Venta();
        actualizarListas();
        return ret;
    }

    /**
     * PRE:- POS: Se ordenan las listas del sistema segun el ordenamiento
     * predeterminado
     */
    private void actualizarListas() {
        envases.sort((Envase t, Envase t1) -> t.getVecesUsado() - t1.getVecesUsado());
        articulos.sort((Articulo a, Articulo a1) -> a.getVecesComprado() - a1.getVecesComprado());
        ventas.sort((Venta v, Venta v1) -> v.getFecha().compareTo(v1.getFecha()));
        ventasCliente.sort((Venta v, Venta v1) -> v.getFecha().compareTo(v1.getFecha()));

    }

    public void borrarArticulo(int id) {
        this.articulos.get(id).setDisponible(false);
    }

    public void borrarEnvase(Envase e) {
        envases.remove(e);
    }

    /**
     * PRE: - POS: retorna una lista de ventas cuya fecha sea mayor a la fecha
     * actual
     *
     */
    public ArrayList<Venta> ventasPendientes() {
        ArrayList<Venta> ret = new ArrayList();
        for (int i = ventas.size() - 1; i > 0; i++) {
            if (ventas.get(i).getFecha().compareTo(LocalDate.now()) > 0) {
                ret.add(ventas.get(i));
            } else {
                return ret;
            }

        }
        return ret;
    }

    /**
     * PRE: - POS: Agrega el coste de produccion del envase recibido al
     *
     * @param e Envase reutilizado
     */
    public void actualizarBeneficio(Envase e, int n) {
        this.beneficioAmbiental += e.getCosteProduccion() * n;
    }

    public List<Articulo> filtrarArticulos(int precioDesde, int precioHasta,
            double minValoracion, Articulo.Categoria[] categorias,
            String nombre) {
        List<Articulo> ret = new ArrayList();
        for (Articulo articulo : articulos) {
            boolean categoriaCorrecta = categorias.length == 0
                    ? true : unoEnComun(categorias, articulo.getCategorias());
            boolean nombreContenido = nombre.length() == 0
                    ? true : articulo.getNombre().contains(nombre);
            if (articulo.getPrecio() <= precioHasta && articulo.getPrecio()
                    >= precioDesde && articulo.getValoracion() >= minValoracion
                    && categoriaCorrecta && nombreContenido) {
                ret.add(articulo);
            }
        }
        return ret;
    }

    private boolean unoEnComun(Articulo.Categoria[] a1, Articulo.Categoria[] a2) {
        for (Articulo.Categoria c1 : a1) {
            for (Articulo.Categoria c2 : a2) {
                if (c2.equals(c1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void agregarAlCarrito(Articulo a, Envase e, int unidades) {
        carrito.agregarArticulo(a, e, unidades);
    }

    public void quitarDelCarrito(int pos) {
        carrito.quitarArticulo(pos);
    }

    public void editarCantCarrito(int pos, int cantNueva) {
        carrito.getCompras().get(pos).setCantidad(cantNueva);
    }

    public void borrarCarrito() {
        carrito = new Venta();
    }

    public int cantCarrito() {
        return carrito.getCompras().size();
    }

    public int cantVentasCliente() {
        return ventasCliente.size();
    }

    public List<Propuesta> getPropuestas() {
        return propuestas;
    }

    public List<Propuesta> getPropuestasCliente() {
        return this.propuestasCliente;
    }

    public List<Propuesta> filtrarPropuesta(String nombre) {
        List<Propuesta> ret = new ArrayList();
        for (Propuesta propuesta : propuestas) {
            boolean nombreContenido = nombre.length() == 0
                    ? true : propuesta.getNombre().contains(nombre);
            if (nombreContenido) {
                ret.add(propuesta);
            }
        }
        return ret;
    }

    public List<Envase> envasesCompatibles(Articulo a) {
        List<Envase> ret = new ArrayList();
        for (Envase envase : this.envases) {
            if (envase.admiteElTipo(a.getTipo())) {
                ret.add(envase);
            }
        }
        return ret;
    }

    public void agregarPropuesta(String nombre, String descripcion,
            int cantidadVotos,
            Image imagen) throws MalformedURLException {

        Image image = imagen;

        Propuesta propuesta = new Propuesta(nombre, descripcion, cantidadVotos,
                image);
        this.propuestas.add(propuesta);
        this.propuestasCliente.add(propuesta);
    }

    public void agregarVotoPropuesta(Propuesta propuesta) {
        propuesta.agregarVoto();
        this.removerPropuesta(propuesta);
    }

    public void removerPropuesta(Propuesta propuesta) {
        this.propuestasCliente.remove(propuesta);
    }

    public int gananciasTotales() {
        int ret = 0;
        for (Venta venta : ventas) {
            ret += venta.getTotal();
        }
        return ret;
    }

    public int gananciaMes(int mes) {
        int ret = 0;
        for (Venta venta : ventas) {
            if (venta.getFecha().getMonth().getValue() == mes) {
                ret += venta.getTotal();
            }
        }
        return ret;
    }

    public int gananciaHoy() {
        int ret = 0;
        for (Venta venta : ventas) {
            if (venta.getFecha().equals(LocalDate.now())) {
                ret += venta.getTotal();
            }
        }
        return ret;
    }

    public int cantVentas() {
        return ventas.size();
    }

    public int cantVentasHoy() {
        int ret = 0;
        for (Venta venta : ventas) {
            if (venta.getFecha().equals(LocalDate.now())) {
                ret++;
            }
        }
        return ret;
    }

    /**
     *
     * @param f
     * @return
     */
    public boolean fechaPreVentaValida(LocalDate f) {
        LocalDate fechaLimite = LocalDate.now().plusDays(14);
        return !f.isAfter(fechaLimite) && !f.isBefore(LocalDate.now());
    }
}
