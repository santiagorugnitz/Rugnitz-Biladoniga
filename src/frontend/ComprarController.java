package frontend;

import backend.Articulo;
import backend.Envase;
import backend.Sistema;
import backend.Venta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * @author Nahuel Biladoniga-211138
 * @author Santiago Rugnitz-215381
 */
public class ComprarController implements Initializable {

    @FXML
    private Label lbl_cantidad;
    @FXML
    private Button btn_menos;
    @FXML
    private Button btn_mas;
    @FXML
    private ListView listEnvases;

    private Sistema sistema;
    private Articulo articulo;
    private Label lbl_cantidadCarrito;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_cantidad.setText("1");
        btn_menos.setDisable(true);

    }

    public void inicializarDatos(Sistema s, Articulo a, Label c) {
        this.sistema = s;
        this.articulo = a;
        this.lbl_cantidadCarrito = c;
        ObservableList<Envase> items = FXCollections.
                observableArrayList(s.envasesCompatibles(a));

        this.listEnvases.setItems(items);
        this.listEnvases.getSelectionModel().selectFirst();
    }

    @FXML
    public void volver(ActionEvent evento) {
        Stage window = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        window.close();
    }

    @FXML
    public void agregar(ActionEvent evento) {
        btn_menos.setDisable(false);

        Integer nuevaCantidad = Integer.valueOf(lbl_cantidad.getText()) + 1;
        if (nuevaCantidad == 10) {
            btn_mas.setDisable(true);
        }
        lbl_cantidad.setText(String.valueOf(nuevaCantidad));
    }

    @FXML
    public void quitar(ActionEvent evento) {
        btn_mas.setDisable(false);
        Integer nuevaCantidad = Integer.valueOf(lbl_cantidad.getText()) - 1;

        if (nuevaCantidad == 1) {
            btn_menos.setDisable(true);
        }
        lbl_cantidad.setText(String.valueOf(nuevaCantidad));
    }

    @FXML
    public void confirmar(ActionEvent evento) {

        Venta venta = this.sistema.getCarrito();
        Envase e = (Envase) this.listEnvases.getSelectionModel().getSelectedItem();
        Integer cantidad = Integer.valueOf(lbl_cantidad.getText());

        venta.agregarArticulo(articulo, e, cantidad);

        this.lbl_cantidadCarrito.setText(
                String.valueOf(this.sistema.getCarrito()
                        .getCompras().size()));
        Stage window = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        window.close();

    }
}
