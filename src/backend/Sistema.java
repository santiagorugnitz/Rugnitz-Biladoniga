/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Santiago
 */
public class Sistema {

    private ArrayList<Venta> ventas;
    private ArrayList<Envase> envases;
    private ArrayList<Articulo> articulos;
    private int beneficioAmbiental;

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public ArrayList<Envase> getEnvases() {
        return envases;
    }

    public ArrayList<Articulo> getArticulos() {
        return articulos;
    }

    public int getBeneficioAmbiental() {
        return beneficioAmbiental;
    }

    public void setBeneficioAmbiental(int beneficioAmbiental) {
        this.beneficioAmbiental = beneficioAmbiental;
    }

    public boolean agregarArticulo(String origen, int precio, String material, Articulo.Tipo tipo) {
        if (precio >= 0) {
            Articulo a = new Articulo(origen, precio, material, this.articulos.size() + 1, tipo);
            this.articulos.add(a);
            return true;
        }
        else return false;
    }
    
    public void actualizarArticulo(int id,String origen,int precio,String material,Articulo.Tipo tipo){
        Articulo a=this.articulos.get(id);
        a.setOrigen(origen);
        a.setPrecio(precio);
        a.setMaterial(material);
        a.setTipo(tipo);
    }

    public boolean agregarEnvase(String nombre, Articulo.Tipo[] tipos, int costeProduccion) {
        if (costeProduccion > 0) {
            Envase e = new Envase(nombre, this.envases.size() + 1, tipos,costeProduccion);
            this.envases.add(e);
            return true;
        } else {
            return false;
        }
    }

    public void registrarVenta(Venta v) {
        ventas.add(v);
        for (int i = 0; i < v.getProductos().size(); i++) {
            v.getProductos().get(i).aumentarUso();
        }
        for (int i = 0; i < v.getEnvases().size(); i++) {
            v.getEnvases().get(i).aumentarUso();
            this.actualizarBeneficio(v.getEnvases().get(i));

        }
        actualizarListas();
    }

    private void actualizarListas() {
        envases.sort((Envase t, Envase t1) -> t.getVecesUsado() - t1.getVecesUsado());
        articulos.sort((Articulo a, Articulo a1) -> a.getVecesComprado() - a1.getVecesComprado());
        ventas.sort((Venta v, Venta v1) -> v.getFecha().compareTo(v1.getFecha()));
    }

    public void borrarArticulo(int id) {
        this.articulos.get(id).setDisponible(false);
    }

    public void borrarEnvase(Envase e) {
        envases.remove(e);
    }

    public ArrayList<Venta> ventasPendientes() {
        ArrayList<Venta> ret = new ArrayList();
        for (int i = ventas.size() - 1; i > 0; i++) {
            if (ventas.get(i).getFecha().compareTo(LocalDate.now()) > 0) {
                ret.add(ventas.get(i));
            } else {
                return ret;
            }

        }
        return ret;
    }

    public void actualizarBeneficio(Envase e) {
        this.beneficioAmbiental += e.getCosteProduccion();
    }

}
