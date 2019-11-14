/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import frontend.InicioController;
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

        s.agregarArticulo("agua", "china", 34, "cs", Articulo.Tipo.SECO, null,
                new String[]{"Vegano", "Libre de Gluten"});
        s.agregarArticulo("agdgdfua", "china", 33, "cs", Articulo.Tipo.SECO, null,
                new String[]{"Vegano", "Libre de Gluten"});
        s.agregarArticulo("sddg", "add", 1111, "cs", Articulo.Tipo.SECO, null,
                new String[]{"Organico", "Libre de Azucar"});
        s.agregarArticulo("dfg", "china", 3454, "cs", Articulo.Tipo.SECO, null,
                new String[]{"Libre de Gluten"});
        s.agregarArticulo("vbvc", "china", 566, "cs", Articulo.Tipo.SECO, null,
                new String[]{"Libre de Gluten"});
        s.agregarArticulo("tttt", "china", 3244, "cs", Articulo.Tipo.SECO, null,
                new String[]{"Libre de Gluten", "Vegano"});
        s.agregarArticulo("qwr", "china", 664, "cs", Articulo.Tipo.SECO, null,
                new String[]{"Libre de Gluten"});
        s.agregarArticulo("xcvxcv", "china", 77, "cs", Articulo.Tipo.SECO, null,
                new String[]{"Libre de Gluten"});
        s.agregarArticulo("awwf", "china", 5445, "cs", Articulo.Tipo.SECO, null,
                new String[]{"Libre de Gluten"});
        s.agregarArticulo("dffggf", "china", 455, "cs", Articulo.Tipo.SECO, null,
                new String[]{"Libre de Gluten"});
        s.agregarPropuesta("mejorrr", "aloaaa", 0, null, new String[]{"", "ddas"});
        s.agregarPropuesta("sddd", "aloaaa", 0, null, new String[]{"", "ddas"});
        s.agregarPropuesta("aasdcc", "aloaaa", 0, null, new String[]{"", "ddas"});
        s.agregarPropuesta("shola", "aloaaa", 0, null, new String[]{"", "ddas"});
        s.agregarPropuesta("xddd", "aloaaa", 0, null, new String[]{"", "ddas"});
        s.agregarPropuesta("loool", "aloaaa", 0, null, new String[]{"", "ddas"});
        s.agregarPropuesta("santi", "aloaaa", 0, null, new String[]{"", "ddas"});
        s.agregarPropuesta("mejorrr", "aloaaa", 0, null, new String[]{"", "ddas"});

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
