package frontend;

import backend.Compra;
import backend.Sistema;
import backend.Venta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * @author Nahuel Biladoniga-211138
 * @author Santiago Rugnitz-215381
 */
public class HistorialItemController implements Initializable {

    @FXML
    private Label lbl_nombre;
    @FXML
    private Label lbl_cantidad;
    @FXML
    private Label lbl_precio;
    @FXML
    private Label lbl_nota;
    @FXML
    private ImageView imagen;

    private Compra venta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void inicializarDatos(Compra venta) {
        this.venta = venta;

        //Cargo los labels de la compra
        this.lbl_nombre.setText(venta.getArticulo().getNombre());
        this.lbl_nota.setText(String.valueOf(
                venta.getArticulo().getValoracion()));
        this.lbl_precio.setText("$" + String.valueOf(venta.total()));
        this.lbl_cantidad.setText(String.valueOf(venta.getCantidad()));
        this.imagen.setImage(venta.getArticulo().getImagen());
    }

}
