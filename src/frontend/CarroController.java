/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Compra;
import backend.Sistema;
import static frontend.Utilitarios.crearError;
import static frontend.Utilitarios.ir_historial;
import static frontend.Utilitarios.ir_propuestas;
import static frontend.Utilitarios.ir_puntosVenta;
import static frontend.Utilitarios.ir_tienda;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class CarroController implements Initializable {

    @FXML
    private Label lbl_cantidad_carro;
    @FXML
    private Label lbl_total;
    @FXML
    private Label lbl_subtotal;
    @FXML
    private VBox lista_compras;
    @FXML
    private Button productos;

    private Sistema sistema;

    public void inicializarDatos(Sistema sistema) {
        this.sistema = sistema;
        this.lbl_cantidad_carro.setText(String.valueOf(sistema.getCarrito()));
        this.cargarArticulos();
        Integer precioTotal = this.sistema.getCarrito().getTotal();
        this.lbl_subtotal.setText("$" + String.valueOf(precioTotal));
        this.lbl_total.setText("$" + String.valueOf(precioTotal));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        frontend.Utilitarios.cerrarSesion(this, event, this.sistema);
    }

    @FXML
    private void comprar(ActionEvent event) {
        if (sistema.cantCarrito() > 0) {
            String html = this.sistema.registrarVenta();
            mostrarFactura(html);
            productos.fire();

        } else {
            Utilitarios.crearError(this, "Carrito Vacío");
        }
    }

    public void cargarArticulos() {
        String cant = String.valueOf(sistema.cantCarrito());
        this.lbl_cantidad_carro.setText(cant);

        ArrayList<Compra> listCompras = this.sistema.getCarrito().getCompras();
        this.lista_compras.getChildren().clear();
        String precioTotal = "$" + String.valueOf(sistema.getCarrito().getTotal());
        lbl_total.setText(precioTotal);
        lbl_subtotal.setText(precioTotal);

        if (sistema.cantCarrito() == 0) {
            crearError(this, "Carrito Vacio");
            productos.fire();
        }

        for (int i = 0; i < listCompras.size(); i++) {
            try {

                //Cargar el objeto
                FXMLLoader fxml = new FXMLLoader(
                        getClass().getResource("/frontend/CarroItem.fxml"));
                Parent nodo = fxml.load();

                //Carga los datos
                CarroItemController controller = fxml.getController();

                controller.inicializarDatos(sistema, lbl_cantidad_carro,
                        this.lbl_total,
                        this.lbl_subtotal,
                        this,
                        i);

                //Cargo el nuevo objeto
                this.lista_compras.getChildren().add(nodo);

            } catch (IOException ex) {
                Logger.getLogger(TiendaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void mostrarFactura(String html) {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().
                getResource("/frontend/Factura.fxml"));

        try {

            Parent root = loader.load();
            Scene scene = new Scene(root);

            stage.initStyle(StageStyle.UNDECORATED);
            FacturaController controller = loader.getController();
            controller.inicializarDatos(html);
            loader.setController(controller);
            stage.centerOnScreen();
            stage.setTitle("Factura");
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException ex) {

            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tienda(ActionEvent event) {
        ir_tienda(this, event, sistema);
    }

    @FXML
    private void historial(ActionEvent event) {
        ir_historial(this, event, sistema);
    }

    @FXML
    private void puntosVenta(ActionEvent event) {
        ir_puntosVenta(this, event, sistema);
    }

    @FXML
    private void propuestas(ActionEvent event) {
        ir_propuestas(this, event, sistema);
    }
}
