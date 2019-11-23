package backend;

import javafx.scene.image.Image;

/**
 * @author Nahuel Biladoniga-211138
 * @author Santiago Rugnitz-215381
 */
public class Articulo {

    public enum Tipo {
        BEBIDA, SECO, HUMEDO
    };

    public enum Categoria {
        LIBRE_DE_AZUCAR, LIBRE_DE_GLUTEN, VEGANO, ORGANICO, BAJAS_CALORIAS;

    };

    private String nombre;
    private String origen;
    private int precio;
    private Categoria[] categorias;
    private int id;
    private Tipo tipo;
    private int vecesComprado;
    private Image imagen;
    private double valoracion;
    private int vecesValorado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Categoria[] getCategorias() {
        return categorias;
    }

    public void setCategorias(Categoria[] categorias) {
        this.categorias = categorias;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getVecesComprado() {
        return vecesComprado;
    }

    private void setVecesComprado(int vecesComprado) {
        this.vecesComprado = vecesComprado;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public double getValoracion() {
        return valoracion;
    }

    private void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    private int getVecesValorado() {
        return vecesValorado;
    }

    private void setVecesValorado(int vecesValorado) {
        this.vecesValorado = vecesValorado;
    }

    public Articulo() {
        this.setNombre("");
        this.setOrigen("");
        this.setPrecio(10);
        this.setId(-1);
        this.setTipo(Tipo.SECO);
        this.setVecesComprado(0);
        this.setImagen(null);
        this.setValoracion(0);
        this.setCategorias(new Categoria[0]);
        this.setVecesValorado(0);

    }

    public Articulo(String nombre, String origen, int precio, int id, Tipo tipo, Image imagen, Categoria[] categorias) {
        this.setNombre(nombre);
        this.setOrigen(origen);
        this.setPrecio(precio);
        this.setId(id);
        this.setTipo(tipo);
        this.setVecesComprado(0);
        this.setImagen(imagen);
        this.setValoracion(0);
        this.setCategorias(categorias);
        this.setVecesValorado(0);

    }

    public void aumentarUso(int n) {
        this.setVecesComprado(this.getVecesComprado() + n);
    }

    /**
     * Actualiza la valoracion y la cantidad de valoraciones según la valoracion
     * recibida. valoracion es un promedio de todas las valoraciones realizadas
     * 1- Se divide entre la cantidad de valoraciones para obtener la sumatoria
     * de valoraciones 2- Se agrega la valoracion nueva y se divide entre la
     * nueva cant de valoraciones
     *
     * @param valoracion valoracion nueva a agregar
     */
    public void agregarValoracion(double valoracion) {
        this.setValoracion(this.getValoracion() * this.getVecesValorado() + valoracion);
        this.setVecesValorado(this.getVecesValorado() + 1);
        this.setValoracion(this.getValoracion() / this.getVecesValorado());
    }

    @Override
    public String toString() {
        return "Articulo{" + "nombre=" + nombre + ", precio=" + precio + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Articulo other = (Articulo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
