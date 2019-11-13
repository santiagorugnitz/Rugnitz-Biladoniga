/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Sistema;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Nahuel
 */
public class Utilitarios {

    public static FXMLLoader cambiarVentana(Object win, ActionEvent event, String fxml) {
        FXMLLoader loader = new FXMLLoader(win.getClass().getResource(fxml));

        try {
            Parent tableViewParent = loader.load();
            Scene tableViewScene = new Scene(tableViewParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            ((Stage) (window.getScene().getWindow())).centerOnScreen();

            window.show();
        } catch (IOException ex) {
            Logger.getLogger(Utilitarios.class.getName()).log(Level.SEVERE, null, ex);
        }

        return loader;
    }

    public static void cerrarSesion(Object win, ActionEvent event, Sistema sistema) {
        FXMLLoader loader = cambiarVentana(win, event, "/frontend/Inicio.fxml");
        InicioController controlador = loader.getController();
        controlador.setSistema(sistema);

    }

    public static FXMLLoader crearError(Object win, String mensaje) {

        Stage newstage = new Stage();

        FXMLLoader loader = new FXMLLoader(win.getClass().
                getResource("/frontend/ErrorPopup.fxml"));

        try {

            Parent root = loader.load();
            Scene scene = new Scene(root);

            //Cargar Mensaje
            ErrorPopupController errorControlador = loader.getController();
            errorControlador.setMensaje(mensaje);

            newstage.initStyle(StageStyle.UNDECORATED);
            newstage.setScene(scene);
            newstage.initModality(Modality.APPLICATION_MODAL);
            ((Stage) (newstage.getScene().getWindow())).centerOnScreen();

            newstage.showAndWait();

        } catch (IOException ex) {

            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loader;
    }
}
