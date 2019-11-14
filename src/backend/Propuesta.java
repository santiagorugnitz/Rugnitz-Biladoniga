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

    private final String nombre;
    private final String descripcion;
    private int cantidadVotos;
    private final Image imagen;

    public Propuesta(String nombre, String descripcion, int cantidadVotos,
            Image imagen, String[] categorias) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadVotos = cantidadVotos;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidadVotos() {
        return cantidadVotos;
    }

    public Image getImagen() {
        return imagen;
    }

    public void agregarVoto() {
        this.cantidadVotos += 1;
    }

}
