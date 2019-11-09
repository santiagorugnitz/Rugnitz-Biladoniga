/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import frontend.InicioController;
import frontend.TiendaController;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/Inicio.fxml"));
        Parent root = loader.load();

        //Cargo el sistema
        Sistema s = new Sistema();
        //ARTICULOS POR DEFECTO
        s.agregarArticulo("uru", 20, "caucho", Articulo.Tipo.SECO);
        s.agregarArticulo("uru", 123, "caucho", Articulo.Tipo.SECO);
        s.agregarArticulo("uru", 42, "caucho", Articulo.Tipo.SECO);
        s.agregarArticulo("uru", 220, "caucho", Articulo.Tipo.SECO);
        s.agregarArticulo("uru", 1, "caucho", Articulo.Tipo.SECO);
        s.agregarArticulo("uru", 55, "caucho", Articulo.Tipo.SECO);
        s.agregarArticulo("uru", 12, "caucho", Articulo.Tipo.SECO);
        s.agregarArticulo("uru", 556, "caucho", Articulo.Tipo.SECO);
        s.agregarArticulo("uru", 556, "caucho", Articulo.Tipo.SECO);

        //FIN ARTICULOS POR DEFECTO
        InicioController controlador = loader.getController();
        controlador.setSistema(s);
        loader.setController(controlador);

        Scene scene = new Scene(root);
        stage.setTitle("JavaFX and Maven");
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
