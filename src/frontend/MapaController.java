/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Sistema;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class MapaController implements Initializable {

    @FXML
    private WebView mapa;
    
    private Sistema sistema;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
    
    public void inicializarDatos(Sistema s){
        this.sistema=s;
        System.out.println(new File(".").getAbsolutePath());
        File f = new File("leaflet\\mapa.html");
        System.out.println(f.getAbsolutePath());
        mapa.getEngine().load(f.toURI().toString());
    }


    @FXML
    void propuestas(ActionEvent event) {
        //soon

    }

    @FXML
    void tienda(ActionEvent event) {
        FXMLLoader fxml =Utilitarios.cambiarVentana(this, event, "/frontend/Tienda.fxml");
            
        //Carga los datos
        TiendaController controller = fxml.getController();
        controller.inicializarDatos(sistema);
        fxml.setController(controller);
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        frontend.Utilitarios.cerrarSesion(this, event, this.sistema);
    }
    
}
