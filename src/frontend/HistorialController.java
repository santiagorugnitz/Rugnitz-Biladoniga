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
public class HistorialController implements Initializable {

    @FXML
    private VBox listaVentas;
    @FXML
    private Label lbl_cantidad_carro;

    private Sistema sistema;
    private Label cantidad;

    public void inicializarDatos(Sistema sistema, Label cantidad) {
        this.sistema = sistema;
        this.cantidad = cantidad;
        this.lbl_cantidad_carro.setText(cantidad.getText());
        this.cargarHistorial();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void cargarHistorial() {
        String cant = String.valueOf(sistema.cantCarrito());
        this.cantidad.setText(cant);
        this.lbl_cantidad_carro.setText(cant);

        ArrayList<Compra> listCompras = this.sistema.getCarrito().getCompras();
        this.listaVentas.getChildren().clear();

        for (int i = 0; i < listCompras.size(); i++) {
            try {

                //Cargar el objeto
                FXMLLoader fxml = new FXMLLoader(
                        getClass().getResource("/frontend/HistorialItem.fxml"));
                Parent nodo = fxml.load();

                //Carga los datos
                CarroItemController controller = fxml.getController();
/*
                controller.inicializarDatos(sistema, lbl_cantidad_carro,
                        this.lbl_total,
                        this.lbl_subtotal,
                        this,
                        i);
*/
                //Cargo el nuevo objeto
                this.listaVentas.getChildren().add(nodo);

            } catch (IOException ex) {
                Logger.getLogger(TiendaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
