/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Articulo;
import backend.Sistema;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class TiendaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    VBox vbox;
    @FXML
    AnchorPane pane;
    @FXML
    Label lbl_cantidad_carro;

    private Sistema sistema;

    public void inicializarDatos(Sistema s) {
        this.sistema = s;
        this.cargarArticulos(s.getArticulos());
        lbl_cantidad_carro.setText(String.valueOf(
                sistema.getCarrito().getTotal()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void carrito(ActionEvent event) {
        FXMLLoader fxml = frontend.Utilitarios.
                cambiarVentana(this, event, "/frontend/Carro.fxml");
        CarroController controller = fxml.getController();
        controller.inicializarDatos(sistema, lbl_cantidad_carro);
        fxml.setController(controller);

    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        frontend.Utilitarios.cerrarSesion(this, event);

    }

    private void cargarArticulos(ArrayList<Articulo> listArt) {
        this.vbox.getChildren().clear();

        int maxFila = 0;
        HBox filas = new HBox();
        filas.setSpacing(20);
        for (int i = 0; i < listArt.size(); i++) {
            try {

                Articulo art = listArt.get(i);
                //Cargarart el objeto

                FXMLLoader fxml = new FXMLLoader(
                        getClass().getResource("/frontend/Producto.fxml"));
                Parent nodo = fxml.load();

                //Carga los datos
                ProductoController controller = fxml.getController();
                controller.inicializarDatos(art, sistema, lbl_cantidad_carro);
                fxml.setController(controller);

                //Cargo el nuevo objeto
                filas.getChildren().add(nodo);
                maxFila++;

                //Cargar Filas
                if (maxFila == 3) {
                    this.vbox.getChildren().add(filas);
                    filas = new HBox();
                    filas.setSpacing(20);

                    maxFila = 0;
                }

            } catch (IOException ex) {
                Logger.getLogger(TiendaController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (maxFila != 0) {
            this.vbox.getChildren().add(filas);
        }

    }

}
