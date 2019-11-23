package backend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Nahuel Biladoniga-211138
 * @author Santiago Rugnitz-215381
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
        this.compras = new ArrayList();
        this.setFecha(LocalDate.now());
    }

    /**
     * Calcula el precio total de la venta sumando el precio total de cada
     * compra
     *
     * @return precio total de la venta
     */
    public int getTotal() {
        int total = 0;
        for (int i = 0; i < compras.size(); i++) {
            total += compras.get(i).total();
        }
        return total;
    }

    public void agregarArticulo(Articulo a, Envase e, int unidades) {
        for (int i = 0; i < this.compras.size(); i++) {
            if (compras.get(i).getArticulo().equals(a) && compras.get(i).getEnvase().equals(e)) {
                compras.get(i).setCantidad(unidades + compras.get(i).getCantidad());
                return;
            }
        }
        this.compras.add(new Compra(a, e, unidades));
    }

    public void quitarArticulo(int pos) {
        compras.remove(pos);
    }

    /**
     * Genera un html con el ticket electrónico de la venta siguiendo las normas
     * de la DGI
     *
     * @return codigo html del ticket de la venta
     */
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
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < compras.size(); i++) {
            Compra c = compras.get(i);
            String nombre = c.getArticulo().getNombre();
            int[] datos = new int[3];
            datos[0] = c.getCantidad();
            datos[1] = c.getArticulo().getPrecio();
            datos[2] = datos[0] * datos[1];
            buffer.append("<tr> <td style=\"text-align:left\">");
            buffer.append(nombre);
            for (int j = 0; j < datos.length; j++) {
                buffer.append("<td>");
                buffer.append(datos[j]);
                buffer.append("</td>");
            }
            buffer.append("</tr>");
        }
        ret+=buffer.toString();
        ret += "  <tr id=\"total\">\n"
                + "  <td colspan=\"3\" id=\"total\">Total IVA incluido</td>\n"
                + "  <td>" + this.getTotal() + "</td>\n"
                + "  </tr>\n"
                + "</table>\n"
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

    @Override
    public String toString() {
        return "Venta{" + "compras=" + compras + ", fecha=" + fecha + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Venta other = (Venta) obj;
        if (!Objects.equals(this.compras, other.compras)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return true;
    }

}
