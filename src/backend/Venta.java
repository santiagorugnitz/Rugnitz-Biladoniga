/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Santiago
 */
public class Venta {

    private List<Compra> compras;
    private LocalDate fecha;

    public List<Compra> getCompras() {
        return compras;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Venta() {
        this.compras=new ArrayList();
        this.setFecha(LocalDate.now());
    }
    
    public int getTotal() {
        int total = 0;
        for (int i = 0; i < compras.size(); i++) {
            total += compras.get(i).getCantidad() * compras.get(i).getArticulo().getPrecio();
        }
        return total;
    }

    public boolean cambiarFecha(int anio, int mes, int dia) {
        LocalDate d;
        try {
            d = LocalDate.of(anio, mes, dia);
        } catch (DateTimeException e) {
            return false;
        }
        this.setFecha(d);
        return true;
    }

    public void agregarArticulo(Articulo a, Envase e, int unidades) {
        this.compras.add(new Compra(a, e, unidades));
    }

    public void quitarArticulo(int pos) {
        compras.remove(pos);
    }

    public String generarTicketDGI() {
        String ret = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "	<title>e-Ticket</title>\n"
                + "\n"
                + "	<meta charset=\"utf-8\" />\n"
                + "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "	<style>\n"
                + "    table, th, td{\n"
                + "	border: 2px solid black;\n"
                + "	border-collapse: collapse;\n"
                + "	margin-left: auto;\n"
                + "	margin-right: auto;\n"
                + "	text-align:right;\n"
                + "	\n"
                + "}\n"
                + "\n"
                + "#total{\n"
                + "	border-bottom: 0;\n"
                + "	border-left: 0;\n"
                + "}\n"
                + "\n"
                + "\n"
                + "#top{\n"
                + "	border:0;\n"
                + "	width:100%;\n"
                + "}\n"
                + "\n"
                + "#esqizq{\n"
                + "	border:0;\n"
                + "	font-style: italic;\n"
                + "	text-align:left;\n"
                + "}\n"
                + "\n"
                + "#esqder{\n"
                + "	border:0;\n"
                + "	margin:10;\n"
                + "}\n"
                + "\n"
                + "div#consumoFinal{\n"
                + "	border: 2px solid black;\n"
                + "	\n"
                + "}\n"
                + "    </style>\n"
                + "	\n"
                + "</head>\n"
                + "<body style=\"font-family:arial;margin-right:2%\">\n"
                + "\n"
                + "<table id=\"top\">\n"
                + "<tr id=\"top\">\n"
                + "<td id=\"esqizq\">\n"
                + "Echo Shop <br>\n"
                + "Ejido 1351 \n"
                + "</td>\n"
                + "<td id=\"esqder\">\n"
                + "98724 <br>\n"
                + "<b>e-TICKET</b><br>\n"
                + "A 0000001 <br>\n"
                + "CONTADO <br>\n"
                + "</td>\n"
                + "</tr>\n"
                + "</table>\n"
                + "\n"
                + "<p></p>\n"
                + "\n"
                + "<table>\n"
                + "<col width=\"50%\">\n"
                + "<tr>\n"
                + "<td style=\"text-align:center\">\n"
                + "CONSUMO FINAL\n"
                + "</td>\n"
                + "</tr>\n"
                + "</table>\n"
                + "\n"
                + "<p></p>\n"
                + "\n"
                + "<p style=\"text-align:right;margin-right:1%\">\n"
                + "Fecha:";
        ret += LocalDate.now().toString();
        ret += "</p>\n"
                + "<table id=\"total\">\n"
                + "	\n"
                + "	<col width=\"58%\">\n"
                + "	<col width=\"14%\">\n"
                + "	<col width=\"14%\">\n"
                + "	<col width=\"14%\">\n"
                + "  <tr>\n"
                + "    <td style=\"text-align:center\">Detalle</td>\n"
                + "    <td style=\"text-align:center\">Cantidad</td>\n"
                + "    <td style=\"text-align:center\">Precio Unitario</td>\n"
                + "	<td style=\"text-align:center\">Total</td>\n"
                + "  </tr>";
        for (int i = 0; i < compras.size(); i++) {
            Compra c = compras.get(i);
            String nombre = c.getArticulo().getNombre();
            int[] datos = new int[3];
            datos[0] = c.getCantidad();
            datos[1] = c.getArticulo().getPrecio();
            datos[2] = datos[0] * datos[1];
            ret += "<tr> <td style=\"text-align:left\">";
            ret += nombre;
            for (int j = 0; j < datos.length; j++) {
                ret += "<td>";
                ret += datos[j];
                ret += "</td>";
            }
            ret += "</tr>";
        }
        ret += "  <tr id=\"total\">\n"
                + "  <td colspan=\"3\" id=\"total\">Total IVA incluido</td>\n"
                + "  <td>" + this.getTotal() + "</td>\n"
                + "  </tr>\n"
                + "</table>\n"
                + "<img src=\"D:\\Admin\\Google Drive\\Java\\Es el mapa es el mapa\\QRCode.png\" width=\"128\" height=\"128\" style=\"margin-left:2%\">\n"
                + "\n"
                + "<p></p>\n"
                + "\n"
                + "<b>Código de Seguridad: </b>281736940<br>\n"
                + "<b>Res. </b>Nov/2019<br>\n"
                + "<b>Puede verificar comprobante en www.echoshop.com.uy </b><br>\n"
                + "<b>IVA al día</b><br>\n"
                + "\n"
                + "<p></p>\n"
                + "\n"
                + "<table width=\"100%\">\n"
                + "<col width=\"100%\">\n"
                + "<tr>\n"
                + "<td style=\"text-align:center\">\n"
                + "ADENDA\n"
                + "<p></p>\n"
                + "<br>\n"
                + "<br>\n"
                + "</td>\n"
                + "</tr>\n"
                + "</table>\n"
                + "\n"
                + "\n"
                + "</body>\n"
                + "</html>";
        return ret;
    }

}
