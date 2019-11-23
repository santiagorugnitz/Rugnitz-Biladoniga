package frontend;

import backend.Sistema;
import com.jfoenix.controls.JFXButton;
import static frontend.Utilitarios.ir_propuestas;
import static frontend.Utilitarios.ir_tienda;
import java.io.File;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.web.WebView;

/**
 * @author Nahuel Biladoniga-211138
 * @author Santiago Rugnitz-215381
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

    public void inicializarDatos(Sistema s) {
        this.sistema = s;
        File f = new File("leaflet\\mapa.html");
        mapa.getEngine().load(f.toURI().toString());
    }

    @FXML
    private void tienda(ActionEvent event) {
        ir_tienda(this, event, sistema);
    }

    @FXML
    private void propuestas(ActionEvent event) {
        ir_propuestas(this, event, sistema);
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        frontend.Utilitarios.cerrarSesion(this, event, this.sistema);
    }

}
