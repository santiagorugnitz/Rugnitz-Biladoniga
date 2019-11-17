/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Articulo;
import backend.Sistema;
import static frontend.Utilitarios.crearError;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class AgregarProductoController implements Initializable {

    @FXML
    private TextField txt_nombre;
    @FXML
    private TextField txt_origen;
    @FXML
    private TextField txt_precio;
    @FXML
    private ComboBox lst_tipo;
    @FXML
    private ListView lst_categorias;

    private Sistema sistema;
    private File imagen;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lst_categorias.getItems().addAll(
                (Object[]) Articulo.Categoria.values());
        lst_categorias.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        lst_tipo.getItems().setAll((Object[]) Articulo.Tipo.values());

        //lst_tipo.setValue(Articulo.Tipo.values()[0]);
        txt_precio.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    txt_precio.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

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
    private void confirmar(ActionEvent evento) throws MalformedURLException, IOException {
        String nombre = this.txt_nombre.getText();
        String origen = this.txt_origen.getText();
        String precio = this.txt_origen.getText();
        Articulo.Tipo tipo = (Articulo.Tipo) lst_tipo.getValue();

        if (nombre.trim().isEmpty() || origen.trim().isEmpty()
                || precio.trim().isEmpty() || imagen == null || tipo == null) {
            crearError(this, "Datos Incorrectos");
        } else {
            ObservableList selectedIndices = lst_categorias.
                    getSelectionModel().getSelectedIndices();
            ArrayList<Articulo.Categoria> categorias = new ArrayList<>();

            for (Object o : selectedIndices) {
                Articulo.Categoria cat = Articulo.Categoria.values()[(Integer) o];
                categorias.add(cat);
            }

            Articulo.Categoria[] cats = categorias.toArray(
                    new Articulo.Categoria[categorias.size()]);

            sistema.agregarArticulo(nombre, origen, Integer.parseInt(precio),
                    tipo, imagen, cats);

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
