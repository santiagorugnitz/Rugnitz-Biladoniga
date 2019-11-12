/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Compra;
import backend.Sistema;
import backend.Venta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class CarroItemController implements Initializable {

    @FXML
    Label lbl_nombre;
    @FXML
    Label lbl_nota;
    @FXML
    Label lbl_cantidad;
    @FXML
    Label lbl_precio;

    @FXML
    Button btn_sumar;
    @FXML
    Button btn_restar;

    Compra venta;
    Label cantidad_carrito;
    Label total;
    Label subtotal;
    CarroController carro_controlador;
    Sistema sistema;
    Integer posicion;

    public void inicializarDatos(Compra venta,
            Sistema sistema,
            Label cantidad_carrito,
            Label total,
            Label subtotal,
            CarroController carro_controlador,
            Integer posicion) {

        this.venta = venta;
        this.cantidad_carrito = cantidad_carrito;
        this.carro_controlador = carro_controlador;
        this.lbl_nombre.setText(venta.getArticulo().getNombre());
        this.lbl_nota.setText(String.valueOf(
                venta.getArticulo().getValoracion()));
        this.sistema = sistema;
        this.total = total;
        this.subtotal = subtotal;
        this.posicion = posicion;
        actualizar();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void sumar_item(ActionEvent evento) {
        btn_restar.setDisable(false);

        Integer nuevaCantidad = this.venta.getCantidad() + 1;
        this.venta.setCantidad(nuevaCantidad);

        actualizar();

        if (nuevaCantidad == 10) {
            this.btn_sumar.setDisable(true);
        }
    }

    private void actualizar() {
        this.lbl_precio.setText("$" + String.valueOf(venta.total()));
        this.lbl_cantidad.setText(String.valueOf(this.venta.getCantidad()));
        String precioTotal = "$"+String.valueOf(sistema.getCarrito().getTotal());
        this.total.setText(precioTotal);
        this.subtotal.setText(precioTotal);
    }

    @FXML
    public void eliminarElemento(ActionEvent evento) {
        //Integer posArt = this.venta.getArticulo();
        this.sistema.getCarrito().quitarArticulo(posicion);
        this.carro_controlador.cargarArticulos();
    }

    @FXML
    public void restar_item(ActionEvent evento) {
        this.btn_sumar.setDisable(false);

        Integer nuevaCantidad = this.venta.getCantidad() - 1;
        this.venta.setCantidad(nuevaCantidad);

        actualizar();

        if (nuevaCantidad == 1) {
            btn_restar.setDisable(true);
        }
    }
}
