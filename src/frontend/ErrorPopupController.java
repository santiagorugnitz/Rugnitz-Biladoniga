package frontend;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * @author Nahuel Biladoniga-211138
 * @author Santiago Rugnitz-215381
 */
public class ErrorPopupController implements Initializable {

    @FXML
    private Label mensaje;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Label a = new Label();
    }

    public void setMensaje(String men) {
        this.mensaje.setText(men);
    }

    public void volver(ActionEvent evento) {
        Stage window = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        window.close();
    }

}
