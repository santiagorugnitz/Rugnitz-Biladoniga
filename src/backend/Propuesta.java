/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.util.Objects;
import javafx.scene.image.Image;

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

    public Propuesta() {
        this.setNombre("");
        this.setDescripcion("");
        this.setCantidadVotos(0);
        this.setImagen(null);

    }

    public Propuesta(String nombre, String descripcion, int cantidadVotos, Image imagen) {
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setCantidadVotos(cantidadVotos);
        this.setImagen(imagen);
    }

    public void agregarVoto() {
        this.setCantidadVotos(this.getCantidadVotos() + 1);
    }

    @Override
    public String toString() {
        return "Propuesta{" + "nombre=" + nombre + ", cantidadVotos=" + cantidadVotos + '}';
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
        final Propuesta other = (Propuesta) obj;
        if (this.cantidadVotos != other.cantidadVotos) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        return true;
    }
    
    

}
