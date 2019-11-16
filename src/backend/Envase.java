/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author Santiago
 */
public class Envase {

    private String nombre;
    private int id;
    private int vecesUsado;
    private Articulo.Tipo[] tipos;
    private int costeProduccion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVecesUsado() {
        return vecesUsado;
    }

    private void setVecesUsado(int vecesUsado) {
        this.vecesUsado = vecesUsado;
    }

    public Articulo.Tipo[] getTipos() {
        return tipos;
    }

    public void setTipos(Articulo.Tipo[] tipos) {
        this.tipos = tipos;
    }

    public int getCosteProduccion() {
        return costeProduccion;
    }

    public void setCosteProduccion(int costeProduccion) {
        this.costeProduccion = costeProduccion;
    }

    public Envase() {

    }

    public Envase(String nombre, int vecesUsado, Articulo.Tipo[] tipos, int costeProduccion) {
        this.setNombre(nombre);
        this.setId(-1);
        this.setVecesUsado(0);
        this.setTipos(tipos);
        this.setCosteProduccion(costeProduccion);
    }

    public void aumentarUso(int n) {
        this.setVecesUsado(this.getVecesUsado() + n);
    }

    public boolean admiteElTipo(Articulo.Tipo t) {
        for (Articulo.Tipo tipo : tipos) {
            if (t == tipo) {
                return true;
            }
        }
        return false;
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
        final Envase other = (Envase) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }

}
