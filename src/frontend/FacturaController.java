/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class FacturaController {

    @FXML
    private WebView factura;

        public void inicializarDatos(String html){
            
        factura.getEngine().loadContent(html);
    }
    
    @FXML
    void volver(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }
}
