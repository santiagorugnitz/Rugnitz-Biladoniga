/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import static frontend.Utilitarios.cambiarVentana;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class InicioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void admin() {

    }

    @FXML
    public void consumidor(ActionEvent event) throws IOException {
        cambiarVentana(this,event,"/frontend/Tienda.fxml");
    }

    @FXML
    public void salir() {
        Platform.exit();
        System.exit(0);

    }
}
