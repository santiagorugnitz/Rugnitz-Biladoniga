package frontend;

import backend.Sistema;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

/**
 * @author Nahuel Biladoniga-211138
 * @author Santiago Rugnitz-215381
 */
public class InicioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Sistema sistema;

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void admin(ActionEvent event) {
        sistema.setEsAdmin(true);
        this.cargar(event);
    }

    @FXML
    public void consumidor(ActionEvent event) throws IOException {
        sistema.setEsAdmin(false);
        this.cargar(event);
    }

    private void cargar(ActionEvent event) {
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
