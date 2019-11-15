/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import javafx.scene.image.Image;

/**
 *
 * @author Santiago
 */
public class Articulo {

    //TODO: agregar mas supongo
    public enum Tipo {
        BEBIDA, SECO, HUMEDO
    };

    private String nombre;
    private String origen;
    private int precio;
    private String[] categorias;
    private int id;
    private boolean disponible;
    private Tipo tipo;
    private int vecesComprado;
    private Image imagen;
    private double valoracion;

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

    public String[] getCategorias() {
        return categorias;
    }

    public void setCategorias(String[] categorias) {
        this.categorias = categorias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
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

    public void setVecesComprado(int vecesComprado) {
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

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    public Articulo() {
        this.setNombre("");
        this.setOrigen("");
        this.setPrecio(0);
        this.setId(-1);
        this.setDisponible(false);
        this.setTipo(Tipo.SECO);
        this.setVecesComprado(0);
        this.setImagen(null);
        this.setValoracion(0);
        this.setCategorias(new String[0]);

    }

    public Articulo(String nombre, String origen, int precio, String material, int id, Tipo tipo, Image imagen, String[] categorias) {
        this.setNombre(nombre);
        this.setOrigen(origen);
        this.setPrecio(precio);
        this.setId(id);
        this.setDisponible(true);
        this.setTipo(tipo);
        this.setVecesComprado(0);
        this.setImagen(imagen);
        this.setValoracion(0);
        this.setCategorias(categorias);
    }

    public void aumentarUso(int n) {
        this.setVecesComprado(this.getVecesComprado() + n);
    }

}
