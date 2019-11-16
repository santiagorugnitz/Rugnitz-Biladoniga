/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Articulo;
import backend.Sistema;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Nahuel
 */
public class Utilitarios {

    public static FXMLLoader cambiarVentana(Object win, Event event, String fxml) {
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

    public static void cerrarSesion(Object win, Event event, Sistema sistema) {
        sistema.cerrarSesion();

        FXMLLoader loader = cambiarVentana(win, event, "/frontend/Inicio.fxml");
        InicioController controlador = loader.getController();
        controlador.setSistema(sistema);

    }

    public static void crearError(Object win, String mensaje) {

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
    }

    public static void productoDescripcion(Object win, Event event,
            Sistema sistema, Articulo art) {

        Stage newstage = new Stage();

        FXMLLoader loader = new FXMLLoader(win.getClass().
                getResource("/frontend/ProductoDescripcion.fxml"));

        try {

            Parent root = loader.load();
            Scene scene = new Scene(root);

            //Cargar Mensaje
            ProductoDescripcionController controlador = loader.getController();
            controlador.inicializarDatos(art, sistema);

            newstage.initStyle(StageStyle.UNDECORATED);
            newstage.setScene(scene);
            newstage.initModality(Modality.APPLICATION_MODAL);
            ((Stage) (newstage.getScene().getWindow())).centerOnScreen();

            newstage.showAndWait();

        } catch (IOException ex) {

            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void ir_carrito(Object win, Event event, Sistema sistema) {

        if (sistema.cantCarrito() != 0) {
            FXMLLoader fxml = frontend.Utilitarios.
                    cambiarVentana(win, event, "/frontend/Carro.fxml");

            CarroController controller = fxml.getController();
            controller.inicializarDatos(sistema);

        } else {
            crearError(win, "Carrito Vacio");
        }
    }

    public static void ir_tienda(Object win, Event event, Sistema sistema) {

        FXMLLoader fxml = frontend.Utilitarios.
                cambiarVentana(win, event, "/frontend/Tienda.fxml");

        TiendaController controller = fxml.getController();

        controller.inicializarDatos(sistema);
    }

    public static void ir_historial(Object win, Event event, Sistema sistema) {

        boolean esVacio = sistema.getEsAdmin()
                ? sistema.cantVentas() == 0 : sistema.cantVentasCliente() == 0;

        if (!esVacio) {
            FXMLLoader fxml = frontend.Utilitarios.
                    cambiarVentana(win, event, "/frontend/Historial.fxml");

            HistorialController controller = fxml.getController();

            controller.inicializarDatos(sistema);

        } else {
            crearError(win, "Historial Vacio");
        }
    }

    public static void ir_puntosVenta(Object win, Event event, Sistema sistema) {
        FXMLLoader fxml = Utilitarios.cambiarVentana(
                win, event, "/frontend/Mapa.fxml");
        //Carga los datos
        MapaController controller = fxml.getController();
        controller.inicializarDatos(sistema);
    }

    public static void ir_propuestas(Object win, Event event, Sistema sistema) {
        FXMLLoader fxml = Utilitarios.cambiarVentana(
                win, event, "/frontend/Propuestas.fxml");
        //Carga los datos
        PropuestasController controller = fxml.getController();
        controller.inicializarDatos(sistema);
    }

    public static void centrarImagen(ImageView img) {
        Image imagen = img.getImage();

        double w = 0;
        double h = 0;

        double ratioX = img.getFitWidth() / imagen.getWidth();
        double ratioY = img.getFitHeight() / imagen.getHeight();

        double reducCoeff = 0;
        if (ratioX >= ratioY) {
            reducCoeff = ratioY;
        } else {
            reducCoeff = ratioX;
        }

        w = imagen.getWidth() * reducCoeff;
        h = imagen.getHeight() * reducCoeff;

        img.setX((img.getFitWidth() - w) / 2);
        img.setY((img.getFitHeight() - h) / 2);

    }
}
