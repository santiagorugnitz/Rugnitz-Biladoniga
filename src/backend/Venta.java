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

    public ArrayList<Compra> getCompras() {
        return compras;
    }

    public int getTotal() {
        int total = 0;
        for (int i = 0; i < compras.size(); i++) {
            total += compras.get(i).getCantidad() * compras.get(i).getArticulo().getPrecio();
        }
        return total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Venta() {
        this.compras = new ArrayList();
        this.fecha = LocalDate.now();
    }

    public boolean cambiarFecha(int anio, int mes, int dia) {
        LocalDate d;
        try {
            d = LocalDate.of(anio, mes, dia);
        } catch (DateTimeException e) {
            return false;
        }
        this.fecha = d;
        return true;
    }

    public void agregarArticulo(Articulo a, Envase e, int unidades) {
        this.compras.add(new Compra(a, e, unidades));
    }
    
    public void quitarArticulo(int pos) {
        compras.remove(pos);
    }

    public String generarTicketDGI() {
        String ret = "codigo html principio";
        ret+=LocalDate.now().toString();
        ret+="codigo del medio";
        for (int i = 0; i < compras.size(); i++) {
            Compra c = compras.get(i);
            String nombre = c.getArticulo().getNombre();
            int[] datos = new int[4];
            datos[0] = c.getCantidad();
            datos[1] = c.getCantidad();
            datos[2] = c.getArticulo().getPrecio();
            datos[3] = datos[2] * datos[1];
            ret += "<tr> <td style=\"text-align:left\">";
            ret += nombre;
            for (int j = 0; j < datos.length; j++) {
                ret += "<td>";
                ret += datos[j];
                ret += "</td>";
            }
            ret += "</tr>";
        }
        ret+="codigo html final";
        return ret;
    }
    
    public int cantidadCarrito(){
        return this.compras.size();
    }
}
