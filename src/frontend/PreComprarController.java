/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Venta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class PreComprarController extends ComprarController {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }

    @FXML
    @Override
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
