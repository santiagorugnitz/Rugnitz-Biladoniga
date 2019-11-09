/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author Santiago
 */
public class Articulo {

    //TODO: agregar mas supongo
    public enum Tipo{BEBIDA,SECO,HUMEDO};
    
    private String nombre;
    private String origen;
    private int precio;
    private String material;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void aumentarUso(int n) {
        this.vecesComprado+=n;
    }
    
    public void setDisponible(boolean disponible){
        this.disponible=disponible;
    }
    
    public int getVecesComprado() {
        return this.vecesComprado;
    }

    public Articulo(String nombre,String origen, int precio, String material, int id, Tipo tipo,Image imagen) {
        this.nombre=nombre;
        this.origen = origen;
        this.precio = precio;
        this.material = material;
        this.id = id;
        this.disponible = true;
        this.tipo = tipo;
        this.vecesComprado = 0;
        this.imagen=imagen;
        this.valoracion=0;
    }
    
    public Articulo(double v){
        this.valoracion=v;
    }
    public Articulo(int p){
        this.precio=p;
    }
    
    
}
