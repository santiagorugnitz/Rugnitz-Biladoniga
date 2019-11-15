/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Articulo;
import backend.Sistema;
import static frontend.Utilitarios.ir_carrito;
import static frontend.Utilitarios.ir_historial;
import static frontend.Utilitarios.ir_propuestas;
import static frontend.Utilitarios.ir_puntosVenta;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class TiendaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox vbox;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label lbl_cantidad_carro;
    @FXML
    private ToggleGroup g_precios;
    @FXML
    private RadioButton sinLimite;
    @FXML
    private Button btn_articulos;
    @FXML
    private Group gpr_cantidad;

    //Atributos
    private int desde;
    private int hasta;
    private double valoracion;
    private List<String> categorias;
    private String nombreABuscar;
    //Sistema
    private Sistema sistema;
    private boolean esAdmin;

    public void inicializarDatos(Sistema s) {
        this.sistema = s;
        this.cargarArticulos(s.getArticulos());
        lbl_cantidad_carro.setText(String.valueOf(
                sistema.cantCarrito()));
        eliminarFiltros(null);
        this.esAdmin = s.getEsAdmin();

        btn_articulos.setVisible(s.getEsAdmin());
        gpr_cantidad.setVisible(!s.getEsAdmin());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        g_precios.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) -> {
            RadioButton rb1 = (RadioButton) g_precios.getSelectedToggle();
            if (rb1 != null) {
                String rango = rb1.getText();
                switch (rango) {
                    case "Sin Limite":
                        this.desde = 0;
                        this.hasta = Integer.MAX_VALUE;
                        break;
                    case "$ 0 a $ 100":
                        this.desde = 0;
                        this.hasta = 100;

                        break;
                    case "$ 100 a $ 300":
                        this.desde = 100;
                        this.hasta = 300;

                        break;
                    case "$ 300 a $ 500":
                        this.desde = 300;
                        this.hasta = 500;

                        break;
                    case "$ 500 o mas":
                        this.desde = 500;
                        this.hasta = Integer.MAX_VALUE;
                        break;
                }
                actualizarListaArticulos();
            }
        });

    }

    private void actualizarListaArticulos() {
        List<Articulo> listaArt = this.sistema.
                filtrarArticulos(desde, hasta, valoracion,
                        categorias.toArray(new String[categorias.size()]),
                        nombreABuscar);
        cargarArticulos(listaArt);
    }

    private void cargarArticulos(List<Articulo> listArt) {
        this.vbox.getChildren().clear();

        int maxFila = 0;
        HBox filas = new HBox();
        filas.setSpacing(20);
        for (int i = 0; i < listArt.size(); i++) {
            try {

                Articulo art = listArt.get(i);
                //Cargarart el objeto

                FXMLLoader fxml = new FXMLLoader(
                        getClass().getResource("/frontend/Producto.fxml"));
                Parent nodo = fxml.load();

                //Carga los datos
                ProductoController controller = fxml.getController();
                controller.inicializarDatos(art, sistema, lbl_cantidad_carro);
                fxml.setController(controller);

                //Cargo el nuevo objeto
                filas.getChildren().add(nodo);
                maxFila++;

                //Cargar Filas
                if (maxFila == 3) {
                    this.vbox.getChildren().add(filas);
                    filas = new HBox();
                    filas.setSpacing(20);

                    maxFila = 0;
                }

            } catch (IOException ex) {
                Logger.getLogger(TiendaController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (maxFila != 0) {
            this.vbox.getChildren().add(filas);
        }

    }

    @FXML
    private void busquedaNombre(ActionEvent event) {
        TextField field = (TextField) event.getSource();
        this.nombreABuscar = field.getText();
        this.actualizarListaArticulos();
    }

    @FXML
    private void busquedaCategoria(ActionEvent event) {
        CheckBox field = (CheckBox) event.getSource();
        String nombre = field.getText();

        if (field.isSelected()) {
            categorias.add(nombre);
        } else {
            categorias.remove(nombre);
        }
        this.actualizarListaArticulos();
    }

    @FXML
    private void busquedaValoracion(ActionEvent event) {
        Button field = (Button) event.getSource();
        String nombre = field.getText();

        this.valoracion = Double.parseDouble(nombre);
        this.actualizarListaArticulos();
    }

    @FXML
    private void eliminarFiltros(ActionEvent event) {
        //Atributos
        this.desde = 0;
        this.hasta = Integer.MAX_VALUE;
        this.valoracion = 0.0;
        this.categorias = new ArrayList<>();
        this.nombreABuscar = "";

        //Restablecer Labels
        //this.sinLimite.setSelected(true);
        actualizarListaArticulos();
    }

    @FXML
    private void carrito(ActionEvent event) {
        ir_carrito(this, event, sistema);
    }

    @FXML
    private void historial(ActionEvent event) {
        ir_historial(this, event, sistema);
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        frontend.Utilitarios.cerrarSesion(this, event, this.sistema);
    }

    @FXML
    private void puntosVenta(ActionEvent event) {
        ir_puntosVenta(this, event, sistema);
    }

    @FXML
    private void propuestas(ActionEvent event) {
        ir_propuestas(this, event, sistema);
    }

    @FXML
    private void agregarArticulo(ActionEvent event) {

        Stage newstage = new Stage();

        FXMLLoader loader = new FXMLLoader(this.getClass().
                getResource("/frontend/AgregarProducto.fxml"));

        try {

            Parent root = loader.load();
            Scene scene = new Scene(root);

            //Cargar Mensaje
            AgregarProductoController controlador = loader.getController();
            controlador.inicializarDatos(sistema);

            newstage.initStyle(StageStyle.UNDECORATED);
            newstage.setScene(scene);
            newstage.initModality(Modality.APPLICATION_MODAL);
            ((Stage) (newstage.getScene().getWindow())).centerOnScreen();

            newstage.showAndWait();

        } catch (IOException ex) {

            Logger.getLogger(ProductoController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        this.cargarArticulos(this.sistema.getArticulos());

    }

}
