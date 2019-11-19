/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import frontend.InicioController;
import java.io.File;
import java.net.URL;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        URL url = getClass().getResource("/frontend/img/logo.png");
        File file = new File("src/frontend/img/default.png");

        s.agregarEnvase("Caja de cartón", new Articulo.Tipo[]{Articulo.Tipo.SECO}, 3);
        s.agregarEnvase("Tupper", new Articulo.Tipo[]{Articulo.Tipo.SECO, Articulo.Tipo.HUMEDO}, 20);
        s.agregarEnvase("Botella de vidrio", new Articulo.Tipo[]{Articulo.Tipo.BEBIDA}, 5);
        s.agregarEnvase("Botella de plástico", new Articulo.Tipo[]{Articulo.Tipo.BEBIDA}, 50);
        s.agregarEnvase("Cantimplora", new Articulo.Tipo[]{Articulo.Tipo.BEBIDA}, 28);

        s.agregarArticulo("Jugo de Maracuya", "Brasil","Jugo natural de maracuya", 32, Articulo.Tipo.BEBIDA, new File("src/frontend/img/jugo.jpg"), new Articulo.Categoria[]{Articulo.Categoria.LIBRE_DE_GLUTEN, Articulo.Categoria.ORGANICO, Articulo.Categoria.VEGANO});
        s.agregarArticulo("Cerveza Artesanal", "Alemania","Cerveza artesanal importada de un pequeño pueblo aleman", 150, Articulo.Tipo.BEBIDA, new File("src/frontend/img/cerveza.jpg"), new Articulo.Categoria[]{Articulo.Categoria.ORGANICO, Articulo.Categoria.VEGANO});
        s.agregarArticulo("Arroz con leche", "Uruguay","", 60, Articulo.Tipo.HUMEDO, file, new Articulo.Categoria[]{Articulo.Categoria.ORGANICO});
        s.agregarArticulo("Hamburguesa completa", "Uruguay","", 330, Articulo.Tipo.SECO, new File("src/frontend/img/burgerComun.jpg"), new Articulo.Categoria[]{});
        s.agregarArticulo("Hamburguesa vegana", "Uruguay","", 360, Articulo.Tipo.SECO, new File("src/frontend/img/burgerVegana.jpg"), new Articulo.Categoria[]{Articulo.Categoria.VEGANO});
        s.agregarArticulo("Ensalada de frutas", "Brasil","", 30, Articulo.Tipo.HUMEDO, file, new Articulo.Categoria[]{Articulo.Categoria.ORGANICO,Articulo.Categoria.VEGANO,Articulo.Categoria.LIBRE_DE_GLUTEN});
        s.agregarArticulo("CocaCola", "Uruguay","", 32, Articulo.Tipo.BEBIDA, file, new Articulo.Categoria[]{Articulo.Categoria.LIBRE_DE_GLUTEN});
        s.agregarArticulo("comidita", "china","", 77, Articulo.Tipo.SECO, file, new Articulo.Categoria[]{Articulo.Categoria.BAJAS_CALORIAS});
        s.agregarArticulo("comidonga", "china","", 5445, Articulo.Tipo.SECO, file, new Articulo.Categoria[]{Articulo.Categoria.BAJAS_CALORIAS});
        s.agregarArticulo("comidota", "china","", 455, Articulo.Tipo.SECO, file, new Articulo.Categoria[]{Articulo.Categoria.BAJAS_CALORIAS});

        s.agregarPropuesta("Más jugos", "Agreguen más jugos por favor", 0, file);
        s.agregarPropuesta("sddd", "aloaaa", 0, file);
        s.agregarPropuesta("aasdcc", "aloaaa", 0, file);
        s.agregarPropuesta("shola", "aloaaa", 0, file);
        s.agregarPropuesta("xddd", "aloaaa", 0, file);
        s.agregarPropuesta("loool", "aloaaa", 0, file);
        s.agregarPropuesta("santi", "aloaaa", 0, file);
        s.agregarPropuesta("mejorrr", "aloaaa", 0, file);

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
