/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Compra;
import backend.Sistema;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class CarroController implements Initializable {

    @FXML
    Label lbl_cantidad_carro;
    @FXML
    Label lbl_total;
    @FXML
    Label lbl_subtotal;
    @FXML
    VBox lista_compras;

    Sistema sistema;
    Label cantidad;

    public void inicializarDatos(Sistema sistema, Label cantidad) {
        this.sistema = sistema;
        this.cantidad = cantidad;
        this.lbl_cantidad_carro.setText(cantidad.getText());
        this.cargarArticulos();
        Integer precioTotal = this.sistema.getCarrito().getTotal();
        this.lbl_subtotal.setText("$" + String.valueOf(precioTotal));
        this.lbl_total.setText("$" + String.valueOf(precioTotal));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        System.out.println("haaa");
        frontend.Utilitarios.cerrarSesion(this, event, this.sistema);

    }

    @FXML
    private void comprar(ActionEvent event) {
        System.out.println("haaa");
        frontend.Utilitarios.cerrarSesion(this, event, this.sistema);

    }

    public void cargarArticulos() {
        String cant = String.valueOf(sistema.cantCarrito());
        this.cantidad.setText(cant);
        this.lbl_cantidad_carro.setText(cant);
        
        
        ArrayList<Compra> listCompras = this.sistema.getCarrito().getCompras();
        this.lista_compras.getChildren().clear();
        String precioTotal = "$" + String.valueOf(sistema.getCarrito().getTotal());
        lbl_total.setText(precioTotal);
        lbl_subtotal.setText(precioTotal);

        for (int i = 0; i < listCompras.size(); i++) {
            try {

                //Cargar el objeto
                FXMLLoader fxml = new FXMLLoader(
                        getClass().getResource("/frontend/CarroItem.fxml"));
                Parent nodo = fxml.load();

                //Carga los datos
                CarroItemController controller = fxml.getController();

                controller.inicializarDatos(sistema, lbl_cantidad_carro,
                        this.lbl_total,
                        this.lbl_subtotal,
                        this,
                        i);
                //fxml.setController(controller);

                //Cargo el nuevo objeto
                this.lista_compras.getChildren().add(nodo);

            } catch (IOException ex) {
                Logger.getLogger(TiendaController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
