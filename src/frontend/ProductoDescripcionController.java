/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Articulo;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class ProductoDescripcionController implements Initializable {

    @FXML
    private Label lbl_valorar;
    @FXML
    private Label lbl_tipo;
    @FXML
    private Label lbl_nombre;
    @FXML
    private Label lbl_origen;
    @FXML
    private Label lbl_precio;
    @FXML
    private Label lbl_veces;
    @FXML
    private Label lbl_nota;
    @FXML
    private Slider notas;
    @FXML
    private Button btn_votar;
    @FXML
    private TextArea txt_categorias;
    @FXML
    private ImageView image;

    private Articulo articulo;
    private Sistema sistema;
    private int nota;
    private boolean esEditar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void inicializarDatos(Articulo art, Sistema sis) {
        this.articulo = art;
        this.sistema = sis;
        this.nota = 0;
        this.esEditar = sis.getEsAdmin();

        if (esEditar) {
            this.lbl_valorar.setVisible(false);
            this.notas.setVisible(false);
            this.btn_votar.setText("Editar");
        }

        //Cargar Ventana
        lbl_nombre.setText(art.getNombre());
        lbl_tipo.setText("Tipo: " + String.valueOf(art.getTipo()));
        lbl_origen.setText("Origen: " + String.valueOf(art.getOrigen()));
        lbl_precio.setText("Precio: $" + String.valueOf(art.getPrecio()));
        lbl_veces.setText("Veces comprado: " + String.valueOf(art.getVecesComprado()));
        lbl_nota.setText(String.valueOf(art.getValoracion()));
        Articulo.Categoria[] categorias = art.getCategorias();

        if (categorias.length != 0) {
            String cat = "";
            cat += categorias[0];
            for (int i = 1; i < categorias.length; i++) {
                cat += "\n" + categorias[i];
            }
            txt_categorias.setText(cat);
        }
        image.setImage(art.getImagen());
        centrarImagen(image);

    }

    @FXML
    public void volver(ActionEvent evento) {
        Stage window = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        window.close();
    }

    @FXML
    public void votar(ActionEvent evento) {
        Button boton = (Button) evento.getSource();
        if (this.esEditar) {
            Stage newstage = new Stage();

            FXMLLoader loader = new FXMLLoader(this.getClass().
                    getResource("/frontend/AgregarProducto.fxml"));

            try {

                Parent root = loader.load();
                Scene scene = new Scene(root);

                AgregarProductoController controlador = loader.getController();
                controlador.inicializarDatos(sistema, articulo);

                newstage.initStyle(StageStyle.UNDECORATED);
                newstage.setScene(scene);
                newstage.initModality(Modality.APPLICATION_MODAL);
                ((Stage) (newstage.getScene().getWindow())).centerOnScreen();

                newstage.showAndWait();

            } catch (IOException ex) {

                Logger.getLogger(ProductoController.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
            inicializarDatos(articulo, sistema);
            if (!sistema.getArticulos().contains(articulo)) {
                Stage window = (Stage) ((Node) evento.getSource()).getScene().getWindow();
                window.close();
            }
        } else {
            boton.setVisible(false);
            this.lbl_valorar.setText("Gracias por su opinion!");
            this.notas.setVisible(false);

            nota = (int) notas.getValue();
            articulo.agregarValoracion(nota);
        }
    }

}
