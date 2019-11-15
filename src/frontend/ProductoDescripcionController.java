/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Articulo;
import backend.Sistema;
import static frontend.Utilitarios.centrarImagen;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class ProductoDescripcionController implements Initializable {

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
    private TextArea txt_categorias;
    @FXML
    private ImageView image;

    private Articulo art;
    private Sistema sis;
    private int nota;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void inicializarDatos(Articulo art, Sistema sis) {
        this.art = art;
        this.sis = sis;
        this.nota = 0;

        //Cargar Ventana
        lbl_nombre.setText(art.getNombre());
        lbl_tipo.setText("Tipo: " + String.valueOf(art.getTipo()));
        lbl_origen.setText("Origen: " + String.valueOf(art.getOrigen()));
        lbl_precio.setText("Precio: $" + String.valueOf(art.getPrecio()));
        lbl_veces.setText("Veces: " + String.valueOf(art.getVecesComprado()));
        lbl_nota.setText(String.valueOf(art.getValoracion()));
        String[] categorias = art.getCategorias();
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
        nota = (int) notas.getValue();
    }

}
