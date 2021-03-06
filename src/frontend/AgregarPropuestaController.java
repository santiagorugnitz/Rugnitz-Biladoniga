package frontend;

import backend.Sistema;
import static frontend.Utilitarios.crearError;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author Nahuel Biladoniga-211138
 * @author Santiago Rugnitz-215381
 */
public class AgregarPropuestaController implements Initializable {

    @FXML
    private TextArea txt_desc;
    @FXML
    private TextField txt_nombre;

    private Sistema sistema;
    private File imagen;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void inicializarDatos(Sistema sistema) {
        this.sistema = sistema;
    }

    @FXML
    private void elegirImagen(ActionEvent evento) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Imagenes (*.png,*.jpg)", "*.png", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);

        imagen = fileChooser.showOpenDialog(
                ((Node) evento.getTarget()).getScene().getWindow());

    }

    @FXML
    private void confirmar(ActionEvent evento) throws MalformedURLException {
        String nombre = this.txt_nombre.getText();
        String descripcion = this.txt_desc.getText();
        if (nombre.trim().isEmpty() || descripcion.trim().isEmpty()
                || imagen == null) {
            crearError(this, "Datos Incorrectos");
        } else {
            sistema.agregarPropuesta(nombre, descripcion, 0,
                    new Image(imagen.toURI().toURL().toExternalForm()));
            Stage window = (Stage) ((Node) evento.getSource()).getScene().getWindow();
            window.close();

        }

    }

    @FXML
    private void volver(ActionEvent evento) {
        Stage window = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        window.close();

    }
}
