package backend;

import java.util.Objects;

/**
 * @author Nahuel Biladoniga-211138
 * @author Santiago Rugnitz-215381
 */
public class Compra {

    private Articulo articulo;
    private Envase envase;
    private int cantidad;

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Envase getEnvase() {
        return envase;
    }

    public void setEnvase(Envase envase) {
        this.envase = envase;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Compra() {
        this.setArticulo(new Articulo());
        this.setEnvase(new Envase());
        this.setCantidad(0);
    }

    public Compra(Articulo articulo, Envase envase, int cantidad) {
        this.setArticulo(articulo);
        this.setEnvase(envase);
        this.setCantidad(cantidad);
    }

    /**
     * Retorna el precio total de la compra
     *
     * @return precio del articulo multiplicado por la cantidad de articulos
     */
    public int total() {
        return articulo.getPrecio() * cantidad;
    }

    @Override
    public String toString() {
        return "Compra{" + "articulo=" + articulo + ", envase=" + envase + ", cantidad=" + cantidad + '}';

    }    

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Compra other = (Compra) obj;
        if (this.cantidad != other.cantidad) {
            return false;
        }
        if (!Objects.equals(this.articulo, other.articulo)) {
            return false;
        }
        if (!Objects.equals(this.envase, other.envase)) {
            return false;
        }
        return true;
    }

}
