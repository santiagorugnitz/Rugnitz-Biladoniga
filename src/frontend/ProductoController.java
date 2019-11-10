/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Articulo;
import backend.Sistema;
import backend.Venta;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class ProductoController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label lbl_precio;
    @FXML
    private Label lbl_nombre;

    private Sistema sistema;
    private Articulo articulo;
    private Label lbl_cantidadCarrito;

    public void inicializarDatos(Articulo articulo, Sistema sistema, Label cantidad) {
        lbl_precio.setText("$" + String.valueOf(articulo.getPrecio()));
        lbl_nombre.setText(articulo.getNombre());
        this.sistema = sistema;
        this.articulo = articulo;
        this.lbl_cantidadCarrito = cantidad;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void comprar(ActionEvent evento) {
        Stage newstage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/Comprar.fxml"));
        Parent root;

        try {
            root = loader.load();
            Scene scene = new Scene(root);

            ComprarController controlador = loader.getController();
            controlador.inicializarDatos(sistema, articulo,lbl_cantidadCarrito);
            loader.setController(controlador);

            newstage.setScene(scene);
            newstage.initModality(Modality.APPLICATION_MODAL);
            newstage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void preComprar(ActionEvent evento) {
        System.out.println("holaaa");
    }

}
