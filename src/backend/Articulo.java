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
public class Articulo {
    private String origen;
    private int precio;
    private String material;
    private int vecesComprado;

    int getVecesComprado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void aumentarUso(){
        this.vecesComprado++;
    }
    
    
}
