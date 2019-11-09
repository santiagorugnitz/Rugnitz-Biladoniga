/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Articulo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class ProductoController implements Initializable {

    @FXML
    VBox vbox;
    @FXML
    AnchorPane pane;
    @FXML
    Label lbl_precio;
    @FXML
    Label lbl_nombre;

    public void inicializarDatos(Articulo art) {
        lbl_precio.setText("$ "+String.valueOf(art.getPrecio()));
        lbl_nombre.setText(String.valueOf(art.getId()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
