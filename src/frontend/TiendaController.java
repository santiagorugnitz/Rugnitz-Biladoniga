/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class TiendaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    VBox vbox;
    @FXML
    AnchorPane pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void carrito(ActionEvent event) {

        frontend.Utilitarios.cambiarVentana(this, event, "/frontend/Carro.fxml");

    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        frontend.Utilitarios.cerrarSesion(this, event);

    }

}
