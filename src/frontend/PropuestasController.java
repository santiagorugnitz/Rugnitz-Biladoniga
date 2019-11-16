/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Articulo;
import backend.Propuesta;
import backend.Sistema;
import static frontend.Utilitarios.cambiarVentana;
import static frontend.Utilitarios.ir_propuestas;
import static frontend.Utilitarios.ir_puntosVenta;
import static frontend.Utilitarios.ir_tienda;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class PropuestasController implements Initializable {

    @FXML
    private VBox lista_propuestas;
    @FXML
    private Button btn_propuesta;

    private Sistema sistema;
    private boolean esAdmin;

    public void inicializarDatos(Sistema sistema) {
        this.sistema = sistema;
        this.esAdmin = esAdmin;
        boolean esAdmin = sistema.getEsAdmin();
        cargarArticulos(esAdmin
                ? sistema.getPropuestas() : sistema.getPropuestasCliente());
        btn_propuesta.setVisible(!esAdmin);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void cargarArticulos(List<Propuesta> listaProps) {
        this.lista_propuestas.getChildren().clear();

        int maxFila = 0;
        HBox filas = new HBox();
        filas.setSpacing(20);
        for (int i = 0; i < listaProps.size(); i++) {
            try {

                Propuesta prop = listaProps.get(i);
                //Cargarart el objeto

                FXMLLoader fxml = new FXMLLoader(
                        getClass().getResource("/frontend/PropuestaItem.fxml"));
                Parent nodo = fxml.load();

                //Carga los datos
                PropuestaItemController controller = fxml.getController();
                controller.inicializarDatos(sistema, prop, this);
                fxml.setController(controller);

                //Cargo el nuevo objeto
                filas.getChildren().add(nodo);
                maxFila++;

                //Cargar Filas
                if (maxFila == 4) {
                    this.lista_propuestas.getChildren().add(filas);
                    filas = new HBox();
                    filas.setSpacing(20);

                    maxFila = 0;
                }

            } catch (IOException ex) {
                Logger.getLogger(TiendaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (maxFila != 0) {
            this.lista_propuestas.getChildren().add(filas);
        }

    }

    @FXML
    private void buscar(ActionEvent evento) {
        TextField boton = (TextField) evento.getSource();
        String texto = boton.getText();
        List<Propuesta> listaProps = this.sistema.filtrarPropuesta(texto);
        cargarArticulos(listaProps);
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        frontend.Utilitarios.cerrarSesion(this, event, this.sistema);
    }

    @FXML
    private void puntosVenta(ActionEvent event) {
        ir_puntosVenta(this, event, sistema);
    }

    @FXML
    private void propuestas(ActionEvent event) {
        ir_propuestas(this, event, sistema);
    }

    @FXML
    private void tienda(ActionEvent event) {
        ir_tienda(this, event, sistema);
    }

    @FXML
    private void agregarPropuesta(ActionEvent event) {

        Stage newstage = new Stage();

        FXMLLoader loader = new FXMLLoader(this.getClass().
                getResource("/frontend/AgregarPropuesta.fxml"));

        try {

            Parent root = loader.load();
            Scene scene = new Scene(root);

            //Cargar Mensaje
            AgregarPropuestaController controlador = loader.getController();
            controlador.inicializarDatos(sistema);

            newstage.initStyle(StageStyle.UNDECORATED);
            newstage.setScene(scene);
            newstage.initModality(Modality.APPLICATION_MODAL);
            ((Stage) (newstage.getScene().getWindow())).centerOnScreen();

            newstage.showAndWait();

        } catch (IOException ex) {

            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        cargarArticulos(esAdmin
                ? sistema.getPropuestas() : sistema.getPropuestasCliente());
    }
}
