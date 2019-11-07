/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.util.ArrayList;

/**
 *
 * @author Santiago
 */
public class Articulo {

    //TODO: agregar mas supongo
    public enum Tipo{BEBIDA,SECO,HUMEDO};
    
    private String origen;
    private int precio;
    private String material;
    private int id;
    private boolean disponible;
    private Tipo tipo;
    private int vecesComprado;

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

    public void aumentarUso() {
        this.vecesComprado++;
    }
    
    public void setDisponible(boolean disponible){
        this.disponible=disponible;
    }
    
    public int getVecesComprado() {
        return this.vecesComprado;
    }

    public Articulo(String origen, int precio, String material, int id, Tipo tipo) {
        this.origen = origen;
        this.precio = precio;
        this.material = material;
        this.id = id;
        this.disponible = true;
        this.tipo = tipo;
        this.vecesComprado = 0;
    }
    
    
    
}
