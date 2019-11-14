/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Propuesta;
import backend.Sistema;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class PropuestaItemController implements Initializable {

    @FXML
    Label lbl_nombre;

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
}
