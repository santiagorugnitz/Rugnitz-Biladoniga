
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template img, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import frontend.InicioController;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
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
        //DATOS POR DEFECTO
        Image img = new Image(new File("src/frontend/img/default.png").toURI().toURL().toExternalForm());

        s.agregarEnvase("Caja de cartón", new Articulo.Tipo[]{Articulo.Tipo.SECO}, 3);
        s.agregarEnvase("Contenedor de plástico", new Articulo.Tipo[]{Articulo.Tipo.SECO, Articulo.Tipo.HUMEDO}, 70);
        s.agregarEnvase("Botella de vidrio", new Articulo.Tipo[]{Articulo.Tipo.BEBIDA}, 5);
        s.agregarEnvase("Botella de plástico", new Articulo.Tipo[]{Articulo.Tipo.BEBIDA}, 50);
        s.agregarEnvase("Cantimplora", new Articulo.Tipo[]{Articulo.Tipo.BEBIDA}, 28);

        s.agregarArticulo("Jugo de Maracuya", "Brasil", 32, Articulo.Tipo.BEBIDA, new Image(new File("src/frontend/img/jugo.jpg").toURI().toURL().toExternalForm()), new Articulo.Categoria[]{Articulo.Categoria.LIBRE_DE_GLUTEN, Articulo.Categoria.ORGANICO, Articulo.Categoria.VEGANO});
        s.getArticulos().get(0).agregarValoracion(5);
        s.agregarArticulo("Cerveza Artesanal", "Alemania", 150, Articulo.Tipo.BEBIDA, new Image(new File("src/frontend/img/cerveza.jpg").toURI().toURL().toExternalForm()), new Articulo.Categoria[]{Articulo.Categoria.ORGANICO, Articulo.Categoria.VEGANO});
        s.getArticulos().get(1).agregarValoracion(3);
        s.agregarArticulo("Arroz con leche", "Uruguay", 60, Articulo.Tipo.HUMEDO, new Image(new File("src/frontend/img/arrozConLeche.jpg").toURI().toURL().toExternalForm()), new Articulo.Categoria[]{Articulo.Categoria.ORGANICO});
        s.getArticulos().get(2).agregarValoracion(2);
        s.agregarArticulo("Hamburguesa completa", "Uruguay", 330, Articulo.Tipo.SECO, new Image(new File("src/frontend/img/burgerComun.jpg").toURI().toURL().toExternalForm()), new Articulo.Categoria[]{});
        s.getArticulos().get(3).agregarValoracion(5);
        s.agregarArticulo("Hamburguesa vegana", "Uruguay", 360, Articulo.Tipo.SECO, new Image(new File("src/frontend/img/burgerVegana.jpg").toURI().toURL().toExternalForm()), new Articulo.Categoria[]{Articulo.Categoria.VEGANO});
        s.getArticulos().get(4).agregarValoracion(1);
        s.agregarArticulo("Ensalada de frutas", "Brasil",  30, Articulo.Tipo.HUMEDO, new Image(new File("src/frontend/img/ensaladaFrutas.jpg").toURI().toURL().toExternalForm()), new Articulo.Categoria[]{Articulo.Categoria.ORGANICO, Articulo.Categoria.VEGANO, Articulo.Categoria.LIBRE_DE_GLUTEN});
        s.getArticulos().get(5).agregarValoracion(2);
        s.agregarArticulo("CocaCola", "Uruguay",  32, Articulo.Tipo.BEBIDA, new Image(new File("src/frontend/img/coca.jpg").toURI().toURL().toExternalForm()), new Articulo.Categoria[]{Articulo.Categoria.LIBRE_DE_GLUTEN});
        s.getArticulos().get(6).agregarValoracion(1);
        s.agregarArticulo("Yogurt Light", "Uruguay", 26, Articulo.Tipo.HUMEDO, new Image(new File("src/frontend/img/yogurt.jpg").toURI().toURL().toExternalForm()), new Articulo.Categoria[]{Articulo.Categoria.BAJAS_CALORIAS, Articulo.Categoria.LIBRE_DE_AZUCAR, Articulo.Categoria.LIBRE_DE_GLUTEN, Articulo.Categoria.ORGANICO});
        s.getArticulos().get(7).agregarValoracion(5);
        s.agregarArticulo("Sushi", "Japón", 600, Articulo.Tipo.SECO, new Image(new File("src/frontend/img/sushi.jpg").toURI().toURL().toExternalForm()), new Articulo.Categoria[]{Articulo.Categoria.LIBRE_DE_GLUTEN, Articulo.Categoria.ORGANICO});
        s.getArticulos().get(8).agregarValoracion(4);

        s.agregarPropuesta("Más jugos", "Agreguen más jugos por favor", 8, new Image(new File("src/frontend/img/jugos.jpg").toURI().toURL().toExternalForm()));
        s.agregarPropuesta("Más opciones veganas", "Agreguen más opciones veganas por favor", 1, new Image(new File("src/frontend/img/vegetales.jpg").toURI().toURL().toExternalForm()));
        s.agregarPropuesta("Elegir rango de precios", "Creo que estaria bueno poder elegir los rangos de precios en vez de tener que elegir los predeterminados", 45, new Image(new File("src/frontend/img/dollar.jpg").toURI().toURL().toExternalForm()));

        //3 ventas al azar en cada mes
        for (int i = 1; i <= 12; i++) {
            for (int j = 0; j < 3; j++) {
                //numero random dentro del intervalo [0,cantArticulos) 
                double random = Math.random() * s.getArticulos().size();
                int pos = (int) random;
                int cant = (int) random + 1;
                Articulo a = s.getArticulos().get(pos);
                Envase e = s.envasesCompatibles(a).get(0);
                Venta v = new Venta();
                v.agregarArticulo(a, e, cant);
                LocalDate fecha = LocalDate.of(LocalDate.now().getYear(), i, 21);
                v.setFecha(fecha);
                s.setCarrito(v);
                s.registrarVenta();
            }
        }

        //FIN DATOS POR DEFECTO
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
