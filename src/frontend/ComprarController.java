/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Articulo;
import backend.Sistema;
import backend.Venta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class ComprarController implements Initializable {

    @FXML
    protected Label lbl_cantidad;
    @FXML
    protected Button btn_menos;
    @FXML
    protected Button btn_mas;

    protected Sistema sistema;
    protected Articulo articulo;
    protected Label lbl_cantidadCarrito;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_cantidad.setText("1");
        btn_menos.setDisable(true);

    }

    public void inicializarDatos(Sistema s, Articulo a, Label c) {
        this.sistema = s;
        this.articulo = a;
        this.lbl_cantidadCarrito = c;
    }

    @FXML
    public void volver(ActionEvent evento) {
        Stage window = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        window.close();
    }

    @FXML
    public void agregar(ActionEvent evento) {
        btn_menos.setDisable(false);

        Integer nuevaCantidad = Integer.valueOf(lbl_cantidad.getText()) + 1;
        if (nuevaCantidad == 10) {
            btn_mas.setDisable(true);
        }
        lbl_cantidad.setText(String.valueOf(nuevaCantidad));
    }

    @FXML
    public void quitar(ActionEvent evento) {
        btn_mas.setDisable(false);
        Integer nuevaCantidad = Integer.valueOf(lbl_cantidad.getText()) - 1;

        if (nuevaCantidad == 1) {
            btn_menos.setDisable(true);
        }
        lbl_cantidad.setText(String.valueOf(nuevaCantidad));
    }

    @FXML
    public void confirmar(ActionEvent evento) {

        Venta venta = this.sistema.getCarrito();
        Integer cantidad = Integer.valueOf(lbl_cantidad.getText());
        venta.agregarArticulo(articulo, null, cantidad);
        this.lbl_cantidadCarrito.setText(
                String.valueOf(this.sistema.getCarrito()
                        .getCompras().size()));
        Stage window = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        window.close();

    }
}
