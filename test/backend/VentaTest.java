/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Santiago
 */
public class VentaTest {

    public VentaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getFecha method, of class Venta.
     */
    @Test
    public void testGetFecha() {
        System.out.println("getFecha");
        Venta instance = new Venta();
        LocalDate expResult = LocalDate.now();
        LocalDate result = instance.getFecha();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFecha method, of class Venta.
     */
    @Test
    public void testSetFecha() {
        System.out.println("setFecha");
        LocalDate fecha = LocalDate.of(2008, Month.MARCH, 15);
        Venta instance = new Venta();
        instance.setFecha(fecha);
        assertEquals(fecha, instance.getFecha());
    }

    /**
     * Test of getTotal1 method, of class Venta.
     */
    @Test
    public void testGetTotal1() {
        System.out.println("getTotal");
        Venta instance = new Venta();
        int expResult = 0;
        int result = instance.getTotal();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotal2 method, of class Venta.
     */
    @Test
    public void testGetTotal2() {
        System.out.println("getTotal");
        Venta instance = new Venta();
        Articulo a1 = new Articulo("", "", 1, 0, Articulo.Tipo.SECO, null, null);
        Articulo a2 = new Articulo("", "", 2, 1, Articulo.Tipo.SECO, null, null);
        Envase e = new Envase();
        instance.agregarArticulo(a1, e, 5);
        instance.agregarArticulo(a2, e, 10);
        int expResult = 25;
        int result = instance.getTotal();
        assertEquals(expResult, result);
    }

    /**
     * Test of agregarArticulo1 method, of class Venta.
     */
    @Test
    public void testAgregarArticulo1() {
        System.out.println("agregarArticulo1");
        Articulo a = new Articulo();
        Envase e = new Envase();
        int unidades = 5;
        Compra expResult = new Compra(a, e, unidades);
        Venta instance = new Venta();
        instance.agregarArticulo(a, e, unidades);
        assertEquals(expResult, instance.getCompras().get(0));
    }

    /**
     * Test of agregarArticulo2 method, of class Venta.
     */
    @Test
    public void testAgregarArticulo2() {
        System.out.println("agregarArticulo2");
        Articulo a = new Articulo();
        Envase e = new Envase();
        int unidades = 5;
        Compra expResult = new Compra(a, e, 10);
        Venta instance = new Venta();
        instance.agregarArticulo(a, e, unidades);
        instance.agregarArticulo(a, e, unidades);
        assertEquals(expResult, instance.getCompras().get(0));
    }

    /**
     * Test of quitarArticulo method, of class Venta.
     */
    @Test
    public void testQuitarArticulo() {
        System.out.println("quitarArticulo");
        int pos = 1;
        Venta instance = new Venta();

        instance.agregarArticulo(new Articulo(), new Envase(), 3);
        instance.agregarArticulo(new Articulo("", "", 12, 1, Articulo.Tipo.SECO, null, null), new Envase(), 5);
        instance.quitarArticulo(pos);
        assertEquals(1, instance.getCompras().size());
        assertEquals(3, instance.getCompras().get(0).getCantidad());
    }

    /**
     * Test of generarTicketDGI method, of class Venta.
     */
    @Test
    public void testGenerarTicketDGI() {
        System.out.println("generarTicketDGI");
        Venta instance = new Venta();
        Articulo a = new Articulo();
        Envase e = new Envase();
        int cant = 5;
        a.setNombre("Manzana");
        a.setPrecio(5);
        instance.agregarArticulo(a, e, cant);
        String expResult = "<!DOCTYPE html>\n"
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
                + "Fecha:"+LocalDate.now().toString()+"</p>\n"
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
                + "  </tr><tr> <td style=\"text-align:left\">Manzana<td>5</td><td>5</td><td>25</td></tr>  <tr id=\"total\">\n"
                + "  <td colspan=\"3\" id=\"total\">Total IVA incluido</td>\n"
                + "  <td>25</td>\n"
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
        String result = instance.generarTicketDGI();
        assertEquals(expResult, result);
    }

}
