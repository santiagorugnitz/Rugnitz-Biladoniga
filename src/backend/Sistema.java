/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Santiago
 */
public class Sistema {
    private ArrayList<Venta> ventas;
    private ArrayList<Envase> envases;
    private ArrayList<Articulo> articulos;
    private int[] ventasMes;
    
    public void agregarArticulo(){
        
    }
    
    public void agregarEnvase(){
        
    }
    
    public void registrarVenta(Venta v){
        ventas.add(v);
        for (int i = 0; i <v.getProductos().size(); i++) {
            v.getProductos().get(i).aumentarUso();
        }
        for (int i = 0; i < v.getEnvases().size(); i++) {
            v.getEnvases().get(i).aumentarUso();
            
        }
        actualizarListas();
    }
    
    private void actualizarListas(){
       envases.sort((Envase t, Envase t1) -> t.getVecesUsado()-t1.getVecesUsado());
       articulos.sort((Articulo a, Articulo a1) -> a.getVecesComprado()-a1.getVecesComprado());
    }
    
    public void borrarArticulo(){
        
    }
    
    public void borrarEnvase(){
    
    }
    
    public void borrarVenta(){
        
    }
}
