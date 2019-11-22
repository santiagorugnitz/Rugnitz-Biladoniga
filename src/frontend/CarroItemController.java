/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Compra;
import backend.Sistema;
import static frontend.Utilitarios.centrarImagen;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class CarroItemController implements Initializable {

    @FXML
    private Label lbl_nombre;
    @FXML
    private Label lbl_nota;
    @FXML
    private Label lbl_cantidad;
    @FXML
    private Label lbl_precio;
    @FXML
    private Button btn_sumar;
    @FXML
    private Button btn_restar;
    @FXML
    private ImageView image;

    private Compra compra;
    private Label total;
    private Label subtotal;
    private CarroController carro_controlador;
    private Sistema sistema;
    private Integer posicion;

    public void inicializarDatos(Sistema sistema,
            Label total,
            Label subtotal,
            CarroController carro_controlador,
            Integer posicion) {

        this.carro_controlador = carro_controlador;
        this.sistema = sistema;
        this.compra = sistema.getCarrito().getCompras().get(posicion);

        //Cargo los labels de la compra
        this.lbl_nombre.setText(compra.getArticulo().getNombre());
        this.lbl_nota.setText(String.valueOf(
                compra.getArticulo().getValoracion()));

        //Se pasan los labels
        this.total = total;
        this.subtotal = subtotal;
        this.posicion = posicion;
        this.image.setImage(compra.getArticulo().getImagen());
        centrarImagen(image);

        //Actualiza los datos
        actualizar();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void sumar_item(ActionEvent evento) {
        btn_restar.setDisable(false);

        Integer nuevaCantidad = compra.getCantidad() + 1;
        compra.setCantidad(nuevaCantidad);

        actualizar();

        if (nuevaCantidad == 10) {
            this.btn_sumar.setDisable(true);
        }
    }

    private void actualizar() {
        //Cargo el item de compra
        this.lbl_precio.setText("$" + String.valueOf(compra.total()));
        this.lbl_cantidad.setText(String.valueOf(compra.getCantidad()));

        if (compra.getCantidad() == 1) {
            btn_restar.setDisable(true);
        }
        if (compra.getCantidad() == 10) {
            this.btn_sumar.setDisable(true);
        }
        //Cargo el total y subtotal
        String precioTotal = "$" + String.valueOf(sistema.getCarrito().getTotal());
        this.total.setText(precioTotal);
        this.subtotal.setText(precioTotal);
    }

    @FXML
    public void eliminarElemento(ActionEvent evento) {
        //Integer posArt = this.venta.getArticulo();
        this.sistema.quitarDelCarrito(this.posicion);
        this.carro_controlador.cargarArticulos();
    }

    @FXML
    public void restar_item(ActionEvent evento) {
        this.btn_sumar.setDisable(false);

        Integer nuevaCantidad = compra.getCantidad() - 1;
        compra.setCantidad(nuevaCantidad);

        actualizar();

        if (nuevaCantidad == 1) {
            btn_restar.setDisable(true);
        }
    }
}
