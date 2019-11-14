/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Articulo;
import backend.Propuesta;
import backend.Sistema;
import static frontend.Utilitarios.ir_propuestas;
import static frontend.Utilitarios.ir_puntosVenta;
import static frontend.Utilitarios.ir_tienda;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class PropuestasController implements Initializable {

    @FXML
    private VBox lista_propuestas;

    private Sistema sistema;

    public void inicializarDatos(Sistema sistema) {
        this.sistema = sistema;
        cargarArticulos(sistema.getPropuestasCliente());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void cargarArticulos(ArrayList<Propuesta> listaProps) {
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
        ArrayList<Propuesta> listaProps = this.sistema.filtrarPropuesta(texto);
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

}
