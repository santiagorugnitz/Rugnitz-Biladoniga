/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import frontend.InicioController;
import frontend.TiendaController;
import java.awt.Image;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Santiago
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/frontend/Inicio.fxml"));

        Parent root = loader.load();

        //Cargo el sistema
        Sistema s = new Sistema();
        //ARTICULOS POR DEFECTO

        s.agregarArticulo("agua", "china", 34, "cs", Articulo.Tipo.SECO, null, null);
        s.agregarArticulo("agdgdfua", "china", 33, "cs", Articulo.Tipo.SECO, null, null);
        s.agregarArticulo("sddg", "add", 1111, "cs", Articulo.Tipo.SECO, null, null);
        s.agregarArticulo("dfg", "china", 3454, "cs", Articulo.Tipo.SECO, null, null);
        s.agregarArticulo("vbvc", "china", 566, "cs", Articulo.Tipo.SECO, null, null);
        s.agregarArticulo("tttt", "china", 3244, "cs", Articulo.Tipo.SECO, null, null);
        s.agregarArticulo("qwr", "china", 664, "cs", Articulo.Tipo.SECO, null, null);
        s.agregarArticulo("xcvxcv", "china", 77, "cs", Articulo.Tipo.SECO, null, null);
        s.agregarArticulo("awwf", "china", 5445, "cs", Articulo.Tipo.SECO, null, null);
        s.agregarArticulo("dffggf", "china", 455, "cs", Articulo.Tipo.SECO, null, null);

        //FIN ARTICULOS POR DEFECTO
        InicioController controlador = loader.getController();
        controlador.setSistema(s);
        loader.setController(controlador);

        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.setTitle("EchoShop");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
