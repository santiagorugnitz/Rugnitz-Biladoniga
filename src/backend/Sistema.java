/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

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
    private int ultimoId;
    
    public Sistema() {
        this.ventas = new ArrayList();
        this.envases = new ArrayList();
        this.articulos = new ArrayList();
        this.propuestas = new ArrayList();
        this.propuestasCliente = new ArrayList();
        this.ventasCliente = new ArrayList();
        this.ultimoId=0;

        this.beneficioAmbiental = 0;
        this.carrito = new Venta();
        this.esAdmin = true;
    }

    /**
     * Resetea el historial y la lista de propuestas del cliente a los valores
     * por defecto
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
        return ventasCliente;
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
    
    private int getUltimoId(){
        return this.ultimoId;
    }
    
    private void setUltimoId(int ultimoId){
        this.ultimoId=ultimoId;
    }

    /**
     * Se crea el articulo con los parametros recibidos y se agrega al sistema
     *
     * @param nombre Nombre del articulo
     * @param origen País de origen del articulo
     * @param descripcion Descripción del articulo
     * @param precio Precio del articulo, debe ser positivo
     * @param tipo Tipo del articulo
     * @param img Imagen del articulo, debe existir
     * @param categorias Categorias del articulo
     */
    public void agregarArticulo(String nombre, String origen, String descripcion, int precio,
            Articulo.Tipo tipo, Image img, Articulo.Categoria[] categorias) {
        Image image = img;
        Articulo a = new Articulo(nombre, origen, descripcion, precio, this.getUltimoId(), tipo, image, categorias);
        this.articulos.add(a);
        this.setUltimoId(this.getUltimoId()+1);
    }


    /**
     * Crea un envase con los parametros recibidos y lo agrega al sistema
     *
     * @param nombre Nombre del envase
     * @param tipos Tipos de articulos con los que se podría usar el envase
     * @param costeProduccion Coste ambiental de producción del envase
     */
    public void agregarEnvase(String nombre, Articulo.Tipo[] tipos, int costeProduccion) {
        Envase e = new Envase(nombre, this.envases.size(), tipos, costeProduccion);
        this.envases.add(e);
    }

    /**
     * Registra en el sistema la venta con los articulos del carrito, genera el
     * ticket y reinicia el carrito
     *
     * @return Ticket electrónico de la compra
     */
    public String registrarVenta() {
        ventas.add(carrito);
        ventasCliente.add(carrito);
        String ret = carrito.generarTicketDGI();
        for (Compra compra : carrito.getCompras()) {
            this.actualizarBeneficio(compra.getEnvase(), compra.getCantidad());
        }
        carrito = new Venta();
        actualizarListas();
        return ret;
    }

    /**
     * Reordena las listas según el orden predeterminado
     */
    private void actualizarListas() {
        envases.sort((Envase t, Envase t1) -> t.getVecesUsado() - t1.getVecesUsado());
        articulos.sort((Articulo a, Articulo a1) -> a.getVecesComprado() - a1.getVecesComprado());
        ventas.sort((Venta v, Venta v1) -> v.getFecha().compareTo(v1.getFecha()));
        ventasCliente.sort((Venta v, Venta v1) -> v.getFecha().compareTo(v1.getFecha()));

    }

    public void borrarArticulo(Articulo art) {
        for (int i = 0; i < articulos.size(); i++) {
            if (articulos.get(i).equals(art)) {
                this.articulos.remove(i);
            }
        }
    }

    public void borrarEnvase(Envase e) {
        envases.remove(e);
    }

    /**
     * Agrega al beneficioAmbiental el coste de produccion de n envases
     *
     * @param e Envase utilizado
     * @param n veces que se utilizó el envase
     */
    private void actualizarBeneficio(Envase e, int n) {
        this.beneficioAmbiental += e.getCosteProduccion() * n;
    }

    /**
     * Filtra la lista de articulos según las condiciones pasadas por parámetro
     * y disponiblidad
     *
     * @param precioDesde Precio mínimo
     * @param precioHasta Precio máximo
     * @param minValoracion Valoración mínima
     * @param categorias Categorias elegidas (debe cumplir al menos una)
     * @param nombre Nombre o parte del nombre del articulo
     * @return Lista de articulos filtrada
     */
    public List<Articulo> filtrarArticulos(int precioDesde, int precioHasta,
            double minValoracion, Articulo.Categoria[] categorias,
            String nombre) {
        List<Articulo> ret = new ArrayList();
        for (Articulo articulo : articulos) {
            boolean categoriaCorrecta = categorias.length == 0
                    ? true : estanTodos(categorias, articulo.getCategorias());
            boolean nombreContenido = nombre.length() == 0
                    ? true : articulo.getNombre().toLowerCase().contains(nombre.toLowerCase());
            if (articulo.getPrecio() <= precioHasta && articulo.getPrecio()
                    >= precioDesde && articulo.getValoracion() >= minValoracion
                    && categoriaCorrecta && nombreContenido) {
                ret.add(articulo);
            }
        }
        return ret;
    }

    /**
     * Método auxiliar que busca si todas las categorias del array de partida
     * estan presentes en el de llegada
     *
     * @param partida Array de categorías de partida
     * @param llegada Array de categorías de llegada
     * @return false si un elemento de partida no se encuentra en llegada, true
     * en caso contrario
     */
    private boolean estanTodos(Articulo.Categoria[] partida, Articulo.Categoria[] llegada) {
        boolean encontre = false;
        for (Articulo.Categoria c1 : partida) {
            encontre = false;
            for (int i = 0; i < llegada.length && !encontre; i++) {
                if (llegada[i].equals(c1)) {
                    encontre = true;
                }
            }
            if (!encontre) {
                return false;
            }
        }
        return true;
    }

    public void agregarAlCarrito(Articulo a, Envase e, int unidades) {
        carrito.agregarArticulo(a, e, unidades);
    }

    /**
     * Quita un articulo del carrito
     *
     * @param pos Posición del articulo a borrar
     */
    public void quitarDelCarrito(int pos) {
        carrito.quitarArticulo(pos);
    }

    /**
     * Actualiza las unidades a comprar de un articulo del carrito
     *
     * @param pos Posición del articulo dentro del carrito
     * @param cantNueva cantidad de unidades nueva
     */
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

    /**
     * Filtra la lista de propuestas buscando propuestas cuyo nombre contenga el
     * String pasado por parámetro
     *
     * @param nombre Nombre a buscar
     * @return Lista de propuestas filtrada
     */
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

    /**
     * Crea la lista de envases compatibles con el articulo dado
     *
     * @param a Articulo que se quiere comprar
     * @return Lista de envases compatibles
     */
    public List<Envase> envasesCompatibles(Articulo a) {
        List<Envase> ret = new ArrayList();
        for (Envase envase : this.envases) {
            if (envase.admiteElTipo(a.getTipo())) {
                ret.add(envase);
            }
        }
        return ret;
    }

    /**
     * Crea la propuesta con los parámetros recibidos y la agrega al sistema
     *
     * @param nombre Nombre de la propuesta
     * @param descripcion Descripción de la propuesta
     * @param cantidadVotos Cantidad de votos inicial de la propuesta
     * @param imagen Imagen de la propuesta, debe existir
     */
    public void agregarPropuesta(String nombre, String descripcion,
            int cantidadVotos,
            Image imagen) {

        Image image = imagen;

        Propuesta propuesta = new Propuesta(nombre, descripcion, cantidadVotos,
                image);
        this.propuestas.add(propuesta);
        this.propuestasCliente.add(propuesta);
    }

    /**
     * Agrega un voto a la propuesta y la borra de la lista de propuestas
     *
     * @param propuesta Propuesta a votar
     */
    public void agregarVotoPropuesta(Propuesta propuesta) {
        propuesta.agregarVoto();
        this.removerPropuesta(propuesta);
    }

    public void removerPropuesta(Propuesta propuesta) {
        this.propuestasCliente.remove(propuesta);
    }

    /**
     * Calcula la suma de todos los precios totales de cada venta
     *
     * @return suma de las ganancias de cada venta
     */
    public int gananciasTotales() {
        int ret = 0;
        for (Venta venta : ventas) {
            ret += venta.getTotal();
        }
        return ret;
    }

    /**
     * Dado un mes da la ganancia de las ventas de ese mes
     *
     * @param mes Mes a calcular las ventas
     * @return total de las ventas del mes
     */
    public int gananciaMes(int mes) {
        int ret = 0;
        for (Venta venta : ventas) {
            if (venta.getFecha().getMonth().getValue() == mes) {
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
     * Verifica si la fecha no es anterior a la de hoy y no es más de 14 días
     * después
     *
     * @param fecha Fecha a validar
     * @return true si la fecha esta dentro del rango, false en caso contrario
     */
    public boolean fechaPreVentaValida(LocalDate fecha) {
        LocalDate fechaLimite = LocalDate.now().plusDays(14);
        return !fecha.isAfter(fechaLimite) && !fecha.isBefore(LocalDate.now());
    }
    /**
     * Agrega la venta directamente al sistema sin pasar por ningún tipo de control.
     * Usado para agregar ventas artificialmente o cargar ventas iniciales
     * @param v Venta a agregar 
     */
    public void agregarVenta(Venta v){
        this.ventas.add(v);
        for (Compra compra : v.getCompras()) {
            this.actualizarBeneficio(compra.getEnvase(), compra.getCantidad());
        }
    }
}
