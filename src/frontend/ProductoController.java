/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class ProductoController extends AnchorPane {

    @FXML
    VBox vbox;
    @FXML
    AnchorPane pane;

    public ProductoController() {
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                    "/frontend/Producto.fxml"));
            fxmlLoader.setController(this);
            this.getChildren().add((Node) fxmlLoader.load());
            
        } catch (IOException e) {

        }
    }

}
