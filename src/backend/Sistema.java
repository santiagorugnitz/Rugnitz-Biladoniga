/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.awt.Image;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Santiago
 */
public class Sistema {

    private ArrayList<Venta> ventas;
    private ArrayList<Envase> envases;
    private ArrayList<Articulo> articulos;
    private ArrayList<Propuesta> propuestas;

    private int beneficioAmbiental;
    private Venta carrito;

    public Sistema() {
        this.ventas = new ArrayList();
        this.envases = new ArrayList();
        this.articulos = new ArrayList();
        this.propuestas = new ArrayList();

        this.beneficioAmbiental = 0;
        this.carrito = new Venta();
    }

    public Venta getCarrito() {
        return carrito;
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public ArrayList<Envase> getEnvases() {
        return envases;
    }

    public ArrayList<Articulo> getArticulos() {
        return articulos;
    }

    public int getBeneficioAmbiental() {
        return beneficioAmbiental;
    }

    public void setBeneficioAmbiental(int beneficioAmbiental) {
        this.beneficioAmbiental = beneficioAmbiental;
    }

    /**
     * PRE: La lista de articulos no debe contener el articulo a ingresar POS:
     * Si el precio es mayor o igual a 0 se agrega el articulo y retorna true,
     * de lo contrario retorna false
     *
     * @param origen Origen del articulo
     * @param precio Precio del articulo, debe ser mayor o igual a 0
     * @param material Material del articulo
     * @param tipo Tipo del articulo
     */
    public boolean agregarArticulo(String nombre, String origen, int precio, String material, Articulo.Tipo tipo, Image imagen, String[] categorias) {
        if (precio >= 0) {
            Articulo a = new Articulo(nombre, origen, precio, material, this.articulos.size() + 1, tipo, imagen, categorias);
            this.articulos.add(a);
            return true;
        } else {
            return false;
        }
    }

    public void agregarPropuesta(String nombre, String descripcion, int cantidadVotos,
            Image imagen, String[] categorias) {
        Propuesta propuesta = new Propuesta(nombre, descripcion, cantidadVotos,
                imagen, categorias);
        this.propuestas.add(propuesta);
    }

    /**
     * PRE: la lista de articulos debe tener al menos id-1 elementos POS: Se
     * editan los atributos del articulo
     *
     * @param id Identificador del articulo
     * @param origen Origen del articulo
     * @param precio Precio del articulo, debe ser mayor o igual a 0
     * @param material Material del articulo
     * @param tipo Tipo del articulo
     */
    public void actualizarArticulo(int id, String origen, int precio, String material, Articulo.Tipo tipo) {
        Articulo a = this.articulos.get(id);
        a.setOrigen(origen);
        a.setPrecio(precio);
        a.setMaterial(material);
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

    public ArrayList<Articulo> filtrarArticulos(int precioDesde, int precioHasta,
            double minValoracion, String[] categorias,
            String nombre) {
        ArrayList<Articulo> ret = new ArrayList();
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

    private boolean unoEnComun(String[] a1, String[] a2) {
        for (int i = 0; i < a1.length; i++) {
            for (int j = 0; j < a2.length; j++) {
                if (a1[i].equals(a2[j])) {
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

    public int cantVentas() {
        return ventas.size();
    }

    public ArrayList<Propuesta> getPropuestas() {
        return propuestas;
    }

    public ArrayList<Propuesta> filtrarPropuesta(String nombre) {
        ArrayList<Propuesta> ret = new ArrayList();
        for (Propuesta propuesta : propuestas) {
            boolean nombreContenido = nombre.length() == 0
                    ? true : propuesta.getNombre().contains(nombre);
            if (nombreContenido) {
                ret.add(propuesta);
            }
        }
        return ret;
    }

}
