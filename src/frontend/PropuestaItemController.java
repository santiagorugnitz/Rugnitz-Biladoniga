package frontend;

import backend.Propuesta;
import backend.Sistema;
import static frontend.Utilitarios.centrarImagen;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Nahuel Biladoniga-211138
 * @author Santiago Rugnitz-215381
 */
public class PropuestaItemController implements Initializable {

    @FXML
    Label lbl_nombre;
    @FXML
    ImageView image;
    @FXML
    Button btn_rechazar;
    @FXML
    Button btn_aceptar;

    private Propuesta propuesta;
    private Sistema sistema;
    private PropuestasController controlador;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void inicializarDatos(Sistema sistema, Propuesta propuesta,
            PropuestasController controlador) {
        this.controlador = controlador;
        this.propuesta = propuesta;
        this.sistema = sistema;
        this.lbl_nombre.setText(propuesta.getNombre());
        this.image.setImage(propuesta.getImagen());
        centrarImagen(image);

        if (sistema.getEsAdmin()) {
            btn_rechazar.setVisible(false);
            btn_aceptar.setVisible(false);
        } else {
            btn_rechazar.setVisible(true);
            btn_aceptar.setVisible(true);

        }
    }

    @FXML
    private void agregarVoto(ActionEvent evento) {
        this.sistema.agregarVotoPropuesta(propuesta);
        this.controlador.cargarArticulos(this.sistema.getPropuestasCliente());
    }

    @FXML
    private void rechazar(ActionEvent evento) {
        this.sistema.removerPropuesta(propuesta);
        this.controlador.cargarArticulos(this.sistema.getPropuestasCliente());
    }

    @FXML
    private void descripcion(MouseEvent evento) {

        Stage newstage = new Stage();

        FXMLLoader loader = new FXMLLoader(this.getClass().
                getResource("/frontend/PropuestaDescripcion.fxml"));

        try {

            Parent root = loader.load();
            Scene scene = new Scene(root);

            //Cargar Mensaje
            PropuestaDescripcionController controlador = loader.getController();
            controlador.inicializarDatos(this.propuesta);

            newstage.initStyle(StageStyle.UNDECORATED);
            newstage.setScene(scene);
            newstage.initModality(Modality.APPLICATION_MODAL);
            ((Stage) (newstage.getScene().getWindow())).centerOnScreen();

            newstage.showAndWait();

        } catch (IOException ex) {

            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
