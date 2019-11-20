/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Compra;
import backend.Venta;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
public class HistorialOrdenController implements Initializable {

    @FXML
    private Label lbl_total;
    @FXML
    private Label lbl_fecha;
    @FXML
    private Label lbl_numero;

    private Venta venta;
    private VBox lista_ventas;

    public void inicializarDatos(Venta venta, int numeroVenta, VBox lista_ventas) {
        this.venta = venta;
        this.lbl_numero.setText(String.valueOf(numeroVenta + 1));
        this.lbl_fecha.setText(venta.getFecha().toString());
        this.lbl_total.setText("$" + String.valueOf(venta.getTotal()));
        this.lista_ventas = lista_ventas;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void seleccionado(ActionEvent seleccionado) {
        List<Compra> listCompras = this.venta.getCompras();
        this.lista_ventas.getChildren().clear();

        for (int i = 0; i < listCompras.size(); i++) {
            try {

                //Cargar el objeto
                FXMLLoader fxml = new FXMLLoader(
                        getClass().getResource("/frontend/HistorialItem.fxml"));
                Parent nodo = fxml.load();

                //Carga los datos
                HistorialItemController controller = fxml.getController();

                controller.inicializarDatos(listCompras.get(i));

                //Cargo el nuevo objeto
                this.lista_ventas.getChildren().add(nodo);

            } catch (IOException ex) {
                Logger.getLogger(TiendaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
