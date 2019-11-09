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
    private ArrayList<Compra> compras;
    private LocalDate fecha;
    private int total;

    public ArrayList<Compra> getCompras() {
        return compras;
    }

    public int getTotal() {
        return total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Venta() {
        this.compras=new ArrayList();
        this.fecha= LocalDate.now();
        this.total=0;
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
        this.compras.add(new Compra(a,e,unidades));
        this.total+=a.getPrecio()*unidades;
    }
    public void quitarArticulo(int pos){
        this.total-=compras.get(pos).total();
        compras.remove(pos);
    }
}
