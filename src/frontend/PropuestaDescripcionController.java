/**
 * @author Nahuel Biladoniga-211138
 * @author Santiago Rugnitz-215381
 */
package frontend;

import backend.Propuesta;
import static frontend.Utilitarios.centrarImagen;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * @author Nahuel Biladoniga-211138
 * @author Santiago Rugnitz-215381
 */
public class PropuestaDescripcionController implements Initializable {

    @FXML
    private Label lbl_nombre;
    @FXML
    private Label lbl_votos;
    @FXML
    private TextArea txt_desc;
    @FXML
    private ImageView image;

    private Propuesta prop;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void inicializarDatos(Propuesta prop) {
        this.prop = prop;

        //Cargar Ventana
        lbl_nombre.setText(String.valueOf(prop.getNombre()));
        lbl_votos.setText("Votos " + String.valueOf(prop.getCantidadVotos()));
        txt_desc.setText(String.valueOf(prop.getDescripcion()));
        image.setImage(prop.getImagen());
        centrarImagen(image);
    }

    @FXML
    public void volver(ActionEvent evento) {
        Stage window = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        window.close();
    }

}
