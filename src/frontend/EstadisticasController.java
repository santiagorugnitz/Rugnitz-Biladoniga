/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Articulo;
import backend.Sistema;
import static frontend.Utilitarios.ir_historial;
import static frontend.Utilitarios.ir_propuestas;
import static frontend.Utilitarios.ir_puntosVenta;
import static frontend.Utilitarios.ir_tienda;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class EstadisticasController implements Initializable {

    @FXML
    private Label lbl_ventas;
    @FXML
    private TableView valoraciones;
    @FXML
    private Label lbl_ventas_hoy;
    @FXML
    private Label lbl_ganancias;
    @FXML
    private Label lbl_beneficio;
    @FXML
    private LineChart<Number, Number> grafica;

    private Sistema sistema;

    public void inicializarDatos(Sistema sistema) {
        this.sistema = sistema;
        //Cargar Estadisticas
        this.lbl_ganancias.setText("$"+String.valueOf(sistema.gananciasTotales()));
        this.lbl_beneficio.setText(String.valueOf(sistema.getBeneficioAmbiental()));
        this.lbl_ventas.setText(String.valueOf(sistema.cantVentas()));
        this.lbl_ventas_hoy.setText(String.valueOf(sistema.cantVentasHoy()));

        //Cargar Tabla
        TableColumn<String, Articulo> column1 = new TableColumn<>("Nombre");
        column1.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<String, Articulo> column2 = new TableColumn<>("Valoracion");
        column2.setCellValueFactory(new PropertyValueFactory<>("valoracion"));

        valoraciones.getColumns().add(column1);
        valoraciones.getColumns().add(column2);

        for (Articulo art : sistema.getArticulos()) {
            valoraciones.getItems().add(art);
        }

        //Cargar grafica
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        XYChart.Series series = new XYChart.Series();
        series.setName("Ganancias por mes");

        for (int i = 1; i <= 12; i++) {
            series.getData().add(new XYChart.Data(i, sistema.gananciaMes(i)));
        }

        grafica.getData().add(series);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        frontend.Utilitarios.cerrarSesion(this, event, sistema);
    }

    @FXML
    void historial(ActionEvent event) {
        ir_historial(this, event, sistema);

    }

    @FXML
    void productos(ActionEvent event) {
        ir_tienda(this, event, sistema);

    }

    @FXML
    void propuestas(ActionEvent event) {
        ir_propuestas(this, event, sistema);
    }

    @FXML
    void puntosVenta(ActionEvent event) {
        ir_puntosVenta(this, event, sistema);
    }

}
