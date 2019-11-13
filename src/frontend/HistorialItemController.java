/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Sistema;
import backend.Venta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class HistorialItemController implements Initializable {

    @FXML
    private Label lbl_nombre;
    @FXML
    private Label lbl_cantidad;
    @FXML
    private Label lbl_precio;
    @FXML
    private Label lbl_nota;

    @FXML
    private Sistema sistema;
    //private Compra venta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void inicializarDatos(Sistema sistema,
            Label cantidad_carrito, Integer posicion) {

        this.sistema = sistema;
        this.venta = sistema.getVentas().get(posicion);

        //Cargo los labels de la compra
       this.lbl_nombre.setText(venta.get);
        this.lbl_nota.setText(String.valueOf(
                venta.getArticulo().getValoracion()));

    }

}
