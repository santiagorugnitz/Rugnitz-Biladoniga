/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.awt.Image;

/**
 *
 * @author Nahuel
 */
public class Propuesta {

    private String nombre;
    private String descripcion;
    private int cantidadVotos;
    private Image imagen;

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    private void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadVotos() {
        return cantidadVotos;
    }

    private void setCantidadVotos(int cantidadVotos) {
        this.cantidadVotos = cantidadVotos;
    }

    public Image getImagen() {
        return imagen;
    }

    private void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    
    
    public Propuesta(){
        this.setNombre("");
        this.setDescripcion("");
        this.setCantidadVotos(0);
        this.setImagen(null);
    
    }
    
    public Propuesta(String nombre, String descripcion, int cantidadVotos, Image imagen, String[] categorias) {
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setCantidadVotos(cantidadVotos);
        this.setImagen(imagen);
    }

    public void agregarVoto() {
        this.setCantidadVotos(this.getCantidadVotos()+1);
    }

}