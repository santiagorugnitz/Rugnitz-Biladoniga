package frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * @author Nahuel Biladoniga-211138
 * @author Santiago Rugnitz-215381
 */
public class FacturaController {

    @FXML
    private WebView factura;

    public void inicializarDatos(String html) {

        factura.getEngine().loadContent(html);
    }

    @FXML
    void volver(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }
}
