
package backend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Nahuel Biladoniga-211138
 * @author Santiago Rugnitz-215381
 */
public class SistemaTest {

    public SistemaTest() {
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
     * Test of cerrarSesion method, of class Sistema.
     */
    @Test
    public void testCerrarSesion1() {
        System.out.println("cerrarSesion1");
        Sistema instance = new Sistema();

        //agrego propuestas
        instance.agregarPropuesta("Propuesta1", "Descripcion1", 8, null);
        instance.agregarPropuesta("Propuesta2", "Descripcion2", 0, null);
        Propuesta p = new Propuesta("Propuesta3", "Descripcion3", 0, null);
        instance.agregarPropuesta("Propuesta3", "Descripcion3", 0, null);

        //borro una propuesta para el cliente
        instance.agregarVotoPropuesta(p);

        //Cierro sesion
        instance.cerrarSesion();

        //Verifico si propuestas es igual a propuestasCliente
        assertEquals(instance.getPropuestas(), instance.getPropuestasCliente());
    }

    @Test
    public void testCerrarSesion2() {
        System.out.println("cerrarSesion2");
        Sistema instance = new Sistema();
        Articulo articulo = new Articulo();
        Envase envase = new Envase();

        //agrego ventas
        instance.agregarAlCarrito(articulo, envase, 4);
        instance.registrarVenta();
        instance.agregarAlCarrito(articulo, envase, 1);
        instance.registrarVenta();

        //Cierro sesion
        instance.cerrarSesion();

        //Verifico si ventasCliente esta vacio
        assertTrue(instance.getVentasCliente().isEmpty());
    }

    /**
     * Test of setEsAdmin method, of class Sistema.
     */
    @Test
    public void testSetEsAdmin() {
        System.out.println("setEsAdmin");
        boolean esAdmin = false;
        Sistema instance = new Sistema();
        instance.setEsAdmin(esAdmin);
        assertEquals(esAdmin, instance.getEsAdmin());
    }

    /**
     * Test of getEsAdmin method, of class Sistema.
     */
    @Test
    public void testGetEsAdmin() {
        System.out.println("getEsAdmin");
        Sistema instance = new Sistema();
        boolean expResult = true;
        boolean result = instance.getEsAdmin();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCarrito method, of class Sistema.
     */
    @Test
    public void testGetCarrito() {
        System.out.println("getCarrito");
        Sistema instance = new Sistema();
        Venta expResult = new Venta();
        Articulo articulo = new Articulo();
        Envase envase = new Envase();

        //Cargo en paralelo expResult y el carrito
        instance.agregarAlCarrito(articulo, envase, 3);
        expResult.agregarArticulo(articulo, envase, 3);
        instance.agregarAlCarrito(articulo, envase, 8);
        expResult.agregarArticulo(articulo, envase, 8);

        //Verifico si son iguales
        Venta result = instance.getCarrito();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetBeneficioAmbiental() {
        System.out.println("getBeneficioAmbiental");
        Sistema instance = new Sistema();
        int expResult = 8;
        instance.setBeneficioAmbiental(expResult);

        int result = instance.getBeneficioAmbiental();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBeneficioAmbiental method, of class Sistema.
     */
    @Test
    public void testSetBeneficioAmbiental() {
        System.out.println("setBeneficioAmbiental");
        int beneficioAmbiental = 3;
        Sistema instance = new Sistema();
        instance.setBeneficioAmbiental(beneficioAmbiental);
        assertEquals(beneficioAmbiental, instance.getBeneficioAmbiental());
    }

    /**
     * Test of agregarArticulo method, of class Sistema.
     */
    @Test
    public void testAgregarArticulo() {
        System.out.println("agregarArticulo");
        String nombre = "N";
        String origen = "O";
        int precio = 5;
        Articulo.Tipo tipo = Articulo.Tipo.SECO;
        Articulo.Categoria[] categorias = new Articulo.Categoria[0];
        Sistema instance = new Sistema();

        instance.agregarArticulo(nombre, origen, precio, tipo, null, categorias);
        Articulo expResult = new Articulo(nombre, origen, precio, 0, tipo, null, categorias);
        //Verifico si hay un solo articulo y si es el que estoy buscando
        assertEquals(1, instance.getArticulos().size());
        assertEquals(expResult, instance.getArticulos().get(0));
    }

    /**
     * Test of agregarEnvase method, of class Sistema.
     */
    @Test
    public void testAgregarEnvase() {
        System.out.println("agregarEnvase");
        String nombre = "Botella";
        Articulo.Tipo[] tipos = {Articulo.Tipo.BEBIDA};
        int costeProduccion = 5;
        Sistema instance = new Sistema();
        Envase expResult = new Envase(nombre, 0, tipos, costeProduccion);
        instance.agregarEnvase(nombre, tipos, costeProduccion);
        assertEquals(expResult, instance.getEnvases().get(0));
    }

    /**
     * Test of registrarVenta1 method, of class Sistema.
     */
    @Test
    public void testRegistrarVenta1() {
        System.out.println("registrarVenta");
        Sistema instance = new Sistema();
        Venta v = new Venta();
        Articulo a1 = new Articulo("", "", 1, 0, Articulo.Tipo.SECO, null, null);
        Articulo a2 = new Articulo("", "", 2, 1, Articulo.Tipo.SECO, null, null);
        Envase e = new Envase();

        v.agregarArticulo(a1, e, 8);
        v.agregarArticulo(a2, e, 5);
        instance.setCarrito(v);
        instance.registrarVenta();
        assertEquals(1, instance.getVentas().size());
        assertEquals(v, instance.getVentas().get(0));
    }

    /**
     * Test of registrarVenta2 method, of class Sistema.
     */
    @Test
    public void testRegistrarVenta2() {
        System.out.println("registrarVenta");
        Sistema instance = new Sistema();
        Venta v = new Venta();
        Articulo a1 = new Articulo("", "", 1, 0, Articulo.Tipo.SECO, null, null);
        Articulo a2 = new Articulo("", "", 2, 1, Articulo.Tipo.SECO, null, null);
        Envase e = new Envase();

        v.agregarArticulo(a1, e, 8);
        v.agregarArticulo(a2, e, 5);
        instance.setCarrito(v);
        instance.registrarVenta();
        assertEquals(1, instance.getVentasCliente().size());
        assertEquals(v, instance.getVentasCliente().get(0));
    }

    /**
     * Test of registrarVenta3 method, of class Sistema.
     */
    @Test
    public void testRegistrarVenta3() {
        System.out.println("registrarVenta");
        Sistema instance = new Sistema();
        Venta v = new Venta();
        Articulo a1 = new Articulo("", "", 1, 0, Articulo.Tipo.SECO, null, null);
        Articulo a2 = new Articulo("", "", 2, 1, Articulo.Tipo.SECO, null, null);
        Envase e = new Envase("", 0, null, 2);
        int expResult = 26;

        v.agregarArticulo(a1, e, 8);
        v.agregarArticulo(a2, e, 5);
        instance.setCarrito(v);
        instance.registrarVenta();
        assertEquals(expResult, instance.getBeneficioAmbiental());
    }

    /**
     * Test of registrarVenta4 method, of class Sistema.
     */
    @Test
    public void testRegistrarVenta4() {
        System.out.println("registrarVenta");
        Sistema instance = new Sistema();
        Venta v = new Venta();
        Articulo a1 = new Articulo("", "", 1, 0, Articulo.Tipo.SECO, null, null);
        Articulo a2 = new Articulo("", "", 2, 1, Articulo.Tipo.SECO, null, null);
        Envase e = new Envase();
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
                + "Fecha:" + LocalDate.now().toString() + "</p>\n"
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
                + "  </tr><tr> <td style=\"text-align:left\"><td>8</td><td>1</td><td>8</td></tr><tr> <td style=\"text-align:left\"><td>5</td><td>2</td><td>10</td></tr>  <tr id=\"total\">\n"
                + "  <td colspan=\"3\" id=\"total\">Total IVA incluido</td>\n"
                + "  <td>18</td>\n"
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

        v.agregarArticulo(a1, e, 8);
        v.agregarArticulo(a2, e, 5);
        instance.setCarrito(v);
        String result = instance.registrarVenta();

        assertEquals(expResult, result);
    }

    /**
     * Test of registrarVenta5 method, of class Sistema.
     */
    @Test
    public void testRegistrarVenta5() {
        System.out.println("registrarVenta");
        Sistema instance = new Sistema();

        instance.agregarArticulo("a1", "", 1, Articulo.Tipo.SECO, null, null);
        instance.agregarArticulo("a2", "", 2, Articulo.Tipo.SECO, null, null);
        instance.agregarEnvase("e1", null, 5);
        instance.agregarEnvase("e2", null, 3);
        Articulo a1 = instance.getArticulos().get(0);
        Articulo a2 = instance.getArticulos().get(1);
        Envase e1 = instance.getEnvases().get(0);
        Envase e2 = instance.getEnvases().get(1);

        //Creo la lista en el orden correcto
        List<Articulo> expResult = new ArrayList();
        expResult.add(a2);
        expResult.add(a1);

        instance.agregarAlCarrito(a1, e1, 8);
        instance.agregarAlCarrito(a2, e2, 5);
        instance.registrarVenta();
        instance.agregarAlCarrito(a2, e2, 5);
        instance.registrarVenta();
        List<Articulo> result = instance.getArticulos();
        assertEquals(expResult, result);
    }

    /**
     * Test of registrarVenta6 method, of class Sistema.
     */
    @Test
    public void testRegistrarVenta6() {
        System.out.println("registrarVenta");
        Sistema instance = new Sistema();

        instance.agregarArticulo("a1", "", 1, Articulo.Tipo.SECO, null, null);
        instance.agregarArticulo("a2", "", 2, Articulo.Tipo.SECO, null, null);
        instance.agregarEnvase("e1", null, 5);
        instance.agregarEnvase("e2", null, 3);
        Articulo a1 = instance.getArticulos().get(0);
        Articulo a2 = instance.getArticulos().get(1);
        Envase e1 = instance.getEnvases().get(0);
        Envase e2 = instance.getEnvases().get(1);

        //Creo la lista en el orden correcto
        List<Envase> expResult = new ArrayList();
        expResult.add(e2);
        expResult.add(e1);

        instance.agregarAlCarrito(a1, e2, 8);
        instance.agregarAlCarrito(a2, e1, 5);
        instance.registrarVenta();
        instance.agregarAlCarrito(a2, e2, 5);
        instance.registrarVenta();
        List<Envase> result = instance.getEnvases();
        assertEquals(expResult, result);
    }

    /**
     * Test of registrarVenta7 method, of class Sistema.
     */
    @Test
    public void testRegistrarVenta7() {
        System.out.println("registrarVenta");
        Sistema instance = new Sistema();
        Articulo a1 = new Articulo("a1", "", 1, 0, Articulo.Tipo.SECO, null, null);
        Articulo a2 = new Articulo("a2", "", 2, 1, Articulo.Tipo.SECO, null, null);
        Envase e1 = new Envase("e1", 0, null, 2);
        Envase e2 = new Envase("e2", 0, null, 2);

        //Creo la lista en el orden correcto
        List<Venta> expResult = new ArrayList();

        instance.agregarAlCarrito(a1, e1, 8);
        instance.agregarAlCarrito(a2, e2, 5);
        expResult.add(instance.getCarrito());
        instance.registrarVenta();
        instance.agregarAlCarrito(a2, e2, 5);
        expResult.add(instance.getCarrito());
        instance.registrarVenta();
        List<Venta> result = instance.getVentas();
        assertEquals(expResult, result);
    }

    /**
     * Test of registrarVenta8 method, of class Sistema.
     */
    @Test
    public void testRegistrarVenta8() {
        System.out.println("registrarVenta");
        Sistema instance = new Sistema();
        Articulo a1 = new Articulo("a1", "", 1, 0, Articulo.Tipo.SECO, null, null);
        Articulo a2 = new Articulo("a2", "", 2, 1, Articulo.Tipo.SECO, null, null);
        Envase e1 = new Envase("e1", 0, null, 2);
        Envase e2 = new Envase("e2", 0, null, 2);

        //Creo la lista en el orden correcto
        List<Venta> expResult = new ArrayList();

        instance.agregarAlCarrito(a1, e1, 8);
        instance.agregarAlCarrito(a2, e2, 5);
        expResult.add(instance.getCarrito());
        instance.registrarVenta();
        instance.agregarAlCarrito(a2, e2, 5);
        expResult.add(instance.getCarrito());
        instance.registrarVenta();
        List<Venta> result = instance.getVentasCliente();
        assertEquals(expResult, result);
    }

    /**
     * Test of registrarVenta9 method, of class Sistema.
     */
    @Test
    public void testRegistrarVenta9() {
        System.out.println("registrarVenta");
        Sistema instance = new Sistema();
        Articulo a1 = new Articulo("a1", "", 1, 0, Articulo.Tipo.SECO, null, null);
        Articulo a2 = new Articulo("a2", "", 2, 1, Articulo.Tipo.SECO, null, null);
        Envase e1 = new Envase("e1", 0, null, 2);
        Envase e2 = new Envase("e2", 0, null, 2);

        instance.agregarAlCarrito(a1, e1, 8);
        instance.agregarAlCarrito(a2, e2, 5);
        instance.registrarVenta();
        assertTrue(instance.getCarrito().getCompras().isEmpty());
    }

    /**
     * Test of borrarArticulo method, of class Sistema.
     */
    @Test
    public void testborrarArticulo() {
        System.out.println("borrarArticulo");
        Sistema instance = new Sistema();

        instance.agregarArticulo("a1", "", 300, Articulo.Tipo.SECO, null, new Articulo.Categoria[0]);
        instance.agregarArticulo("a2", "", 300, Articulo.Tipo.SECO, null, new Articulo.Categoria[0]);
        List<Articulo> expResult = new ArrayList();
        expResult.add(instance.getArticulos().get(1));

        Articulo a = instance.getArticulos().get(0);
        instance.borrarArticulo(a);
        List<Articulo> result = instance.getArticulos();
        assertEquals(expResult, result);
    }

    /**
     * Test of filtrarArticulos1 method, of class Sistema.
     */
    @Test
    public void testFiltrarArticulos1() {
        System.out.println("filtrarArticulos");
        int precioDesde = 0;
        int precioHasta = 100;
        double minValoracion = 0.0;
        Articulo.Categoria[] categorias = new Articulo.Categoria[0];
        String nombre = "";
        Sistema instance = new Sistema();

        //Cargo la lista resultado y la de instance con el articulo
        instance.agregarArticulo("", "", 100, Articulo.Tipo.SECO, null, new Articulo.Categoria[0]);
        List<Articulo> expResult = new ArrayList();
        expResult.add(instance.getArticulos().get(0));

        //Agrego articulos extra
        instance.agregarArticulo("", "", 101, Articulo.Tipo.SECO, null, new Articulo.Categoria[0]);
        instance.agregarArticulo("", "", 300, Articulo.Tipo.SECO, null, new Articulo.Categoria[0]);
        instance.agregarArticulo("", "", 300, Articulo.Tipo.SECO, null, new Articulo.Categoria[0]);

        List<Articulo> result = instance.filtrarArticulos(precioDesde, precioHasta, minValoracion, categorias, nombre);
        assertEquals(expResult, result);
    }

    /**
     * Test of filtrarArticulos1 method, of class Sistema.
     */
    @Test
    public void testFiltrarArticulos2() {
        System.out.println("filtrarArticulos");
        int precioDesde = 0;
        int precioHasta = 999;
        double minValoracion = 3.0;
        Articulo.Categoria[] categorias = new Articulo.Categoria[0];
        String nombre = "";
        Sistema instance = new Sistema();

        //Cargo la lista resultado y la de instance con el articulo
        instance.agregarArticulo("", "", 100, Articulo.Tipo.SECO, null, new Articulo.Categoria[0]);
        instance.getArticulos().get(0).agregarValoracion(3);
        List<Articulo> expResult = new ArrayList();
        expResult.add(instance.getArticulos().get(0));

        //Agrego articulos extra
        instance.agregarArticulo("", "", 101, Articulo.Tipo.SECO, null, new Articulo.Categoria[0]);
        instance.agregarArticulo("", "", 300, Articulo.Tipo.SECO, null, new Articulo.Categoria[0]);
        instance.agregarArticulo("", "", 300, Articulo.Tipo.SECO, null, new Articulo.Categoria[0]);

        List<Articulo> result = instance.filtrarArticulos(precioDesde, precioHasta, minValoracion, categorias, nombre);
        assertEquals(expResult, result);
    }

    /**
     * Test of filtrarArticulos3 method, of class Sistema.
     */
    @Test
    public void testFiltrarArticulos3() {
        System.out.println("filtrarArticulos");
        int precioDesde = 0;
        int precioHasta = 999;
        double minValoracion = 0.0;
        Articulo.Categoria[] categorias = {Articulo.Categoria.BAJAS_CALORIAS, Articulo.Categoria.LIBRE_DE_AZUCAR, Articulo.Categoria.LIBRE_DE_GLUTEN, Articulo.Categoria.ORGANICO, Articulo.Categoria.VEGANO};
        String nombre = "";
        Sistema instance = new Sistema();

        //Cargo la lista resultado y la de instance con el articulo
        instance.agregarArticulo("", "", 100, Articulo.Tipo.SECO, null, categorias);
        List<Articulo> expResult = new ArrayList();
        expResult.add(instance.getArticulos().get(0));

        //Agrego articulos extra
        Articulo.Categoria[] cat2 = {Articulo.Categoria.BAJAS_CALORIAS, Articulo.Categoria.LIBRE_DE_GLUTEN};
        instance.agregarArticulo("", "", 101, Articulo.Tipo.SECO, null, cat2);
        instance.agregarArticulo("", "", 300, Articulo.Tipo.SECO, null, new Articulo.Categoria[0]);
        instance.agregarArticulo("", "", 300, Articulo.Tipo.SECO, null, new Articulo.Categoria[0]);

        List<Articulo> result = instance.filtrarArticulos(precioDesde, precioHasta, minValoracion, categorias, nombre);
        assertEquals(expResult, result);
    }

    /**
     * Test of filtrarArticulos4 method, of class Sistema.
     */
    @Test
    public void testFiltrarArticulos4() {
        System.out.println("filtrarArticulos");
        int precioDesde = 0;
        int precioHasta = 999;
        double minValoracion = 0.0;
        Articulo.Categoria[] categorias = new Articulo.Categoria[0];
        String nombre = "aa";
        Sistema instance = new Sistema();

        //Cargo la lista resultado y la de instance con el articulo
        instance.agregarArticulo("aaa", "", 100, Articulo.Tipo.SECO, null, categorias);
        List<Articulo> expResult = new ArrayList();
        expResult.add(instance.getArticulos().get(0));

        //Agrego articulos extra
        instance.agregarArticulo("a", "", 101, Articulo.Tipo.SECO, null, new Articulo.Categoria[0]);
        instance.agregarArticulo("abba", "", 300, Articulo.Tipo.SECO, null, new Articulo.Categoria[0]);
        instance.agregarArticulo("baba", "", 300, Articulo.Tipo.SECO, null, new Articulo.Categoria[0]);

        List<Articulo> result = instance.filtrarArticulos(precioDesde, precioHasta, minValoracion, categorias, nombre);
        assertEquals(expResult, result);
    }

    /**
     * Test of agregarAlCarrito method, of class Sistema.
     */
    @Test
    public void testAgregarAlCarrito() {
        System.out.println("agregarAlCarrito");
        Articulo a = new Articulo("a", "", 1, 0, Articulo.Tipo.SECO, null, null);
        Envase e = new Envase("e", 0, null, 8);
        int unidades = 5;
        Venta v = new Venta();
        Sistema instance = new Sistema();

        instance.agregarAlCarrito(a, e, unidades);
        v.agregarArticulo(a, e, unidades);

        assertEquals(v, instance.getCarrito());
    }

    /**
     * Test of quitarDelCarrito method, of class Sistema.
     */
    @Test
    public void testQuitarDelCarrito() {
        System.out.println("quitarDelCarrito");
        int pos = 0;
        Sistema instance = new Sistema();
        Articulo a = new Articulo("a", "", 1, 0, Articulo.Tipo.SECO, null, null);
        Envase e = new Envase("e", 0, null, 8);

        instance.agregarAlCarrito(new Articulo(), e, 4);
        instance.agregarAlCarrito(a, e, 2);

        instance.quitarDelCarrito(pos);
        assertEquals(1, instance.getCarrito().getCompras().size());
        assertEquals(a, instance.getCarrito().getCompras().get(0).getArticulo());
    }

    /**
     * Test of editarCantCarrito method, of class Sistema.
     */
    @Test
    public void testEditarCantCarrito() {
        System.out.println("editarCantCarrito");
        int pos = 0;
        int cantNueva = 5;
        Sistema instance = new Sistema();

        instance.agregarAlCarrito(new Articulo(), new Envase(), 3);
        instance.editarCantCarrito(pos, cantNueva);

        assertEquals(cantNueva, instance.getCarrito().getCompras().get(0).getCantidad());
    }

    /**
     * Test of cantCarrito1 method, of class Sistema.
     */
    @Test
    public void testCantCarrito1() {
        System.out.println("cantCarrito");
        Sistema instance = new Sistema();
        int expResult = 0;
        int result = instance.cantCarrito();
        assertEquals(expResult, result);
    }

    /**
     * Test of cantCarrito2 method, of class Sistema.
     */
    @Test
    public void testCantCarrito2() {
        System.out.println("cantCarrito");
        Sistema instance = new Sistema();
        int expResult = 1;

        instance.agregarAlCarrito(new Articulo(), new Envase(), 1);
        instance.agregarAlCarrito(new Articulo(), new Envase(), 1);

        int result = instance.cantCarrito();
        assertEquals(expResult, result);
    }

    /**
     * Test of cantVentasCliente1 method, of class Sistema.
     */
    @Test
    public void testCantVentasCliente1() {
        System.out.println("cantVentasCliente");
        Sistema instance = new Sistema();
        int expResult = 0;
        int result = instance.cantVentasCliente();
        assertEquals(expResult, result);
    }

    /**
     * Test of cantVentasCliente2 method, of class Sistema.
     */
    @Test
    public void testCantVentasCliente2() {
        System.out.println("cantVentasCliente");
        Sistema instance = new Sistema();
        int expResult = 1;

        instance.agregarAlCarrito(new Articulo(), new Envase(), 1);
        instance.registrarVenta();

        int result = instance.cantVentasCliente();
        assertEquals(expResult, result);
    }

    /**
     * Test of filtrarPropuesta method, of class Sistema.
     */
    @Test
    public void testFiltrarPropuesta() {
        System.out.println("filtrarPropuesta");
        String nombre = "aa";
        Sistema instance = new Sistema();
        Propuesta p = new Propuesta("aaa", "", 0, null);
        List<Propuesta> expResult = new ArrayList();

        expResult.add(p);
        instance.agregarPropuesta("aaa", "", 0, null);
        instance.agregarPropuesta("a", "", 0, null);
        instance.agregarPropuesta("abba", "", 0, null);

        List<Propuesta> result = instance.filtrarPropuesta(nombre);
        assertEquals(expResult, result);
    }

    /**
     * Test of envasesCompatibles method, of class Sistema.
     */
    @Test
    public void testEnvasesCompatibles() {
        System.out.println("envasesCompatibles");
        Articulo a = new Articulo();
        a.setTipo(Articulo.Tipo.BEBIDA);
        Sistema instance = new Sistema();
        Articulo.Tipo[] tipos1 = {Articulo.Tipo.BEBIDA};
        Articulo.Tipo[] tipos2 = {Articulo.Tipo.HUMEDO, Articulo.Tipo.BEBIDA};

        Envase e1 = new Envase("e1", 0, tipos1, 5);
        Envase e2 = new Envase("e2", 1, tipos2, 4);
        instance.agregarEnvase("e1", tipos1, 5);
        instance.agregarEnvase("e2", tipos2, 4);

        List<Envase> expResult = new ArrayList();
        expResult.add(e1);
        expResult.add(e2);

        List<Envase> result = instance.envasesCompatibles(a);
        assertEquals(expResult, result);
    }

    /**
     * Test of agregarPropuesta method, of class Sistema.
     */
    @Test
    public void testAgregarPropuesta() {
        System.out.println("agregarPropuesta");
        String nombre = "p";
        String descripcion = "d";
        int cantidadVotos = 4;
        Image imagen = null;
        Sistema instance = new Sistema();
        instance.agregarPropuesta(nombre, descripcion, cantidadVotos, imagen);
        Propuesta p = new Propuesta(nombre, descripcion, cantidadVotos, imagen);
        assertEquals(1, instance.getPropuestas().size());
        assertEquals(p, instance.getPropuestas().get(0));
    }

    /**
     * Test of agregarVotoPropuesta method, of class Sistema.
     */
    @Test
    public void testAgregarVotoPropuesta() {
        System.out.println("agregarVotoPropuesta");
        Sistema instance = new Sistema();

        instance.agregarPropuesta("a", "", 5, null);
        Propuesta p = instance.getPropuestas().get(0);
        instance.agregarVotoPropuesta(p);

        assertEquals(6, p.getCantidadVotos());
    }

    /**
     * Test of removerPropuesta method, of class Sistema.
     */
    @Test
    public void testRemoverPropuesta() {
        System.out.println("removerPropuesta");
        Propuesta propuesta = new Propuesta("a", "", 5, null);
        Sistema instance = new Sistema();
        instance.agregarPropuesta("a", "", 5, null);
        instance.removerPropuesta(propuesta);
        assertFalse(instance.getPropuestasCliente().contains(propuesta));
        assertTrue(instance.getPropuestas().contains(propuesta));

    }

    /**
     * Test of gananciasTotales1 method, of class Sistema.
     */
    @Test
    public void testGananciasTotales1() {
        System.out.println("gananciasTotales");
        Sistema instance = new Sistema();
        int expResult = 0;
        int result = instance.gananciasTotales();
        assertEquals(expResult, result);
    }

    /**
     * Test of gananciasTotales2 method, of class Sistema.
     */
    @Test
    public void testGananciasTotales2() {
        System.out.println("gananciasTotales");
        Sistema instance = new Sistema();
        int expResult = 25;
        Articulo a = new Articulo("", "", 5, 0, Articulo.Tipo.SECO, null, new Articulo.Categoria[0]);

        instance.agregarAlCarrito(a, new Envase(), 3);
        instance.registrarVenta();
        instance.agregarAlCarrito(a, new Envase(), 2);
        instance.registrarVenta();

        int result = instance.gananciasTotales();
        assertEquals(expResult, result);
    }

    /**
     * Test of gananciaMes1 method, of class Sistema.
     */
    @Test
    public void testGananciaMes1() {
        System.out.println("gananciaMes");
        int mes = 2;
        Sistema instance = new Sistema();
        int expResult = 0;
        int result = instance.gananciaMes(mes);
        assertEquals(expResult, result);
    }

    /**
     * Test of gananciaMes2 method, of class Sistema.
     */
    @Test
    public void testGananciaMes2() {
        System.out.println("gananciaMes");
        int mes = 2;
        Sistema instance = new Sistema();
        int expResult = 15;
        Articulo a = new Articulo("", "", 5, 0, Articulo.Tipo.SECO, null, new Articulo.Categoria[0]);

        instance.agregarAlCarrito(a, new Envase(), 3);
        instance.getCarrito().setFecha(LocalDate.of(2019, mes, 12));
        instance.registrarVenta();
        instance.agregarAlCarrito(a, new Envase(), 2);
        instance.registrarVenta();

        int result = instance.gananciaMes(mes);
        assertEquals(expResult, result);
    }

    /**
     * Test of cantVentas1 method, of class Sistema.
     */
    @Test
    public void testCantVentas1() {
        System.out.println("cantVentas");
        Sistema instance = new Sistema();
        int expResult = 0;
        int result = instance.cantVentas();
        assertEquals(expResult, result);
    }

    /**
     * Test of cantVentas2 method, of class Sistema.
     */
    @Test
    public void testCantVentas2() {
        System.out.println("cantVentas");
        Sistema instance = new Sistema();
        int expResult = 3;

        instance.registrarVenta();
        instance.registrarVenta();
        instance.registrarVenta();

        int result = instance.cantVentas();
        assertEquals(expResult, result);
    }

    /**
     * Test of cantVentasHoy1 method, of class Sistema.
     */
    @Test
    public void testCantVentasHoy1() {
        System.out.println("cantVentasHoy");
        Sistema instance = new Sistema();
        int expResult = 0;
        int result = instance.cantVentasHoy();
        assertEquals(expResult, result);
    }

    /**
     * Test of cantVentasHoy2 method, of class Sistema.
     */
    @Test
    public void testCantVentasHoy2() {
        System.out.println("cantVentasHoy");
        Sistema instance = new Sistema();
        int expResult = 3;

        instance.registrarVenta();
        instance.registrarVenta();
        instance.registrarVenta();

        int result = instance.cantVentasHoy();
        assertEquals(expResult, result);
    }

    /**
     * Test of fechaPreVentaValida1 method, of class Sistema.
     */
    @Test
    public void testFechaPreVentaValida1() {
        System.out.println("fechaPreVentaValida");
        LocalDate fecha = LocalDate.now();
        Sistema instance = new Sistema();
        boolean expResult = true;
        boolean result = instance.fechaPreVentaValida(fecha);
        assertEquals(expResult, result);
    }

    /**
     * Test of fechaPreVentaValida2 method, of class Sistema.
     */
    @Test
    public void testFechaPreVentaValida2() {
        System.out.println("fechaPreVentaValida");
        LocalDate fecha = LocalDate.now();
        fecha = fecha.plusDays(14);
        Sistema instance = new Sistema();
        boolean expResult = true;
        boolean result = instance.fechaPreVentaValida(fecha);
        assertEquals(expResult, result);
    }

    /**
     * Test of fechaPreVentaValida3 method, of class Sistema.
     */
    @Test
    public void testFechaPreVentaValida3() {
        System.out.println("fechaPreVentaValida");
        LocalDate fecha = LocalDate.now();
        fecha = fecha.minusDays(1);
        Sistema instance = new Sistema();
        boolean expResult = false;
        boolean result = instance.fechaPreVentaValida(fecha);
        assertEquals(expResult, result);
    }

    /**
     * Test of fechaPreVentaValida4 method, of class Sistema.
     */
    @Test
    public void testFechaPreVentaValida4() {
        System.out.println("fechaPreVentaValida");
        LocalDate fecha = LocalDate.now();
        fecha = fecha.plusDays(15);
        Sistema instance = new Sistema();
        boolean expResult = false;
        boolean result = instance.fechaPreVentaValida(fecha);
        assertEquals(expResult, result);
    }

}
