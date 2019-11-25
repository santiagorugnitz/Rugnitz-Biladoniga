
package backend;

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
public class ArticuloTest {

    public ArticuloTest() {
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
     * Test of getNombre method, of class Articulo.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Articulo instance = new Articulo();
        String expResult = "nombre";
        instance.setNombre(expResult);
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNombre method, of class Articulo.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "nombre";
        Articulo instance = new Articulo();
        instance.setNombre(nombre);
        assertEquals(nombre, instance.getNombre());
    }

    /**
     * Test of getOrigen method, of class Articulo.
     */
    @Test
    public void testGetOrigen() {
        System.out.println("getOrigen");
        Articulo instance = new Articulo();
        String expResult = "Uruguay";
        instance.setOrigen(expResult);
        String result = instance.getOrigen();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrigen method, of class Articulo.
     */
    @Test
    public void testSetOrigen() {
        System.out.println("setOrigen");
        String origen = "Uruguay";
        Articulo instance = new Articulo();
        instance.setOrigen(origen);
        assertEquals(origen, instance.getOrigen());
    }

    /**
     * Test of getPrecio method, of class Articulo.
     */
    @Test
    public void testGetPrecio() {
        System.out.println("getPrecio");
        Articulo instance = new Articulo();
        int expResult = 3;
        instance.setPrecio(expResult);
        int result = instance.getPrecio();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPrecio method, of class Articulo.
     */
    @Test
    public void testSetPrecio() {
        System.out.println("setPrecio");
        int precio = 0;
        Articulo instance = new Articulo();
        instance.setPrecio(precio);
        assertEquals(precio, instance.getPrecio());
    }

    /**
     * Test of getCategorias method, of class Articulo.
     */
    @Test
    public void testGetCategorias() {
        System.out.println("getCategorias");
        Articulo instance = new Articulo();
        Articulo.Categoria[] expResult = {Articulo.Categoria.BAJAS_CALORIAS};
        instance.setCategorias(expResult);
        Articulo.Categoria[] result = instance.getCategorias();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setCategorias method, of class Articulo.
     */
    @Test
    public void testSetCategorias() {
        System.out.println("setCategorias");
        Articulo.Categoria[] categorias = {Articulo.Categoria.VEGANO};
        Articulo instance = new Articulo();
        instance.setCategorias(categorias);
        assertArrayEquals(categorias, instance.getCategorias());
    }

    /**
     * Test of getId method, of class Articulo.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Articulo instance = new Articulo();
        int expResult = -1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTipo method, of class Articulo.
     */
    @Test
    public void testGetTipo() {
        System.out.println("getTipo");
        Articulo instance = new Articulo();
        Articulo.Tipo expResult = Articulo.Tipo.SECO;
        Articulo.Tipo result = instance.getTipo();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTipo method, of class Articulo.
     */
    @Test
    public void testSetTipo() {
        System.out.println("setTipo");
        Articulo.Tipo tipo = Articulo.Tipo.BEBIDA;
        Articulo instance = new Articulo();
        instance.setTipo(tipo);
        assertEquals(tipo, instance.getTipo());
    }

    /**
     * Test of getVecesComprado method, of class Articulo.
     */
    @Test
    public void testGetVecesComprado() {
        System.out.println("getVecesComprado");
        Articulo instance = new Articulo();
        int expResult = 0;
        int result = instance.getVecesComprado();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImagen method, of class Articulo.
     */
    @Test
    public void testGetImagen() {
        System.out.println("getImagen");
        Articulo instance = new Articulo();
        Image expResult = null;
        Image result = instance.getImagen();
        assertEquals(expResult, result);
    }

    /**
     * Test of setImagen method, of class Articulo.
     */
    @Test
    public void testSetImagen() {
        System.out.println("setImagen");
        Image imagen = null;
        Articulo instance = new Articulo();
        instance.setImagen(imagen);
        assertEquals(imagen, instance.getImagen());
    }

    /**
     * Test of getValoracion method, of class Articulo.
     */
    @Test
    public void testGetValoracion() {
        System.out.println("getValoracion");
        Articulo instance = new Articulo();
        double expResult = 0.0;
        double result = instance.getValoracion();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of aumentarUso method, of class Articulo.
     */
    @Test
    public void testAumentarUso() {
        System.out.println("aumentarUso");
        int expResult = 8;
        Articulo instance = new Articulo();
        instance.aumentarUso(3);
        instance.aumentarUso(5);
        assertEquals(expResult,instance.getVecesComprado());

    }

    /**
     * Test of agregarValoracion1 method, of class Articulo.
     */
    @Test
    public void testAgregarValoracion1() {
        System.out.println("agregarValoracion");
        double valoracion = 3.0;
        Articulo instance = new Articulo();
        instance.agregarValoracion(valoracion);
        assertEquals(valoracion,instance.getValoracion(),0);
    }

    /**
     * Test of agregarValoracion2 method, of class Articulo.
     */
    @Test
    public void testAgregarValoracion2() {
        System.out.println("agregarValoracion");
        double expResult = 4.0;
        Articulo instance = new Articulo();
        instance.agregarValoracion(3);
        instance.agregarValoracion(5);
        assertEquals(expResult,instance.getValoracion(),0);
    }
}
