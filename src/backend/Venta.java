/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Santiago
 */
public class Venta {
    private ArrayList<Articulo> productos;
    private ArrayList<Envase> envases;
    private ArrayList<Integer> cantidades;
    private LocalDate fecha;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Articulo> getProductos() {
        return productos;
    }

    public ArrayList<Envase> getEnvases() {
        return envases;
    }

    public Venta() {
        this.productos=new ArrayList();
        this.envases= new ArrayList();
        this.cantidades= new ArrayList();
        this.fecha= LocalDate.now();
    }
    
    public boolean cambiarFecha(int anio,int mes,int dia) {
        LocalDate d;
        try{
         d = LocalDate.of(anio,mes,dia);
        }
        catch(DateTimeException e){
            return false;
        }
        this.fecha= d;
        return true;
    }
    
    public void agregarArticulo(Articulo a,Envase e,int unidades){
        this.productos.add(a);
        this.envases.add(e);
        this.cantidades.add(unidades);
    }
    public void quitarArticulo(int pos){
        if(pos<productos.size())return;
        this.productos.remove(pos);
        this.envases.remove(pos);
    }

    

    
    
    
}
