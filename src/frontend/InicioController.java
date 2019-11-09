/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Sistema;
import static frontend.Utilitarios.cambiarVentana;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class InicioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Sistema sistema;
    
    public void setSistema(Sistema sistema){
        this.sistema = sistema;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void admin() {

    }

    @FXML
    public void consumidor(ActionEvent event) throws IOException {

        FXMLLoader fxml = Utilitarios.
                cambiarVentana(this, event, "/frontend/Tienda.fxml");
        
//Carga los datos
        TiendaController controller = fxml.getController();
        controller.inicializarDatos(sistema);
        fxml.setController(controller);
        
    }

    @FXML
    public void salir() {
        Platform.exit();
        System.exit(0);

    }
}
