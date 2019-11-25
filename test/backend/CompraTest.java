
package backend;

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
public class CompraTest {
    
    public CompraTest() {
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
     * Test of getArticulo method, of class Compra.
     */
    @Test
    public void testGetArticulo() {
        System.out.println("getArticulo");
        Articulo a = new Articulo();
        Compra instance = new Compra(a,new Envase(),5);
        Articulo expResult = a;
        Articulo result = instance.getArticulo();
        assertEquals(expResult, result);
    }

    /**
     * Test of setArticulo method, of class Compra.
     */
    @Test
    public void testSetArticulo() {
        System.out.println("setArticulo");
        Articulo articulo = new Articulo();
        Compra instance = new Compra();
        instance.setArticulo(articulo);
        assertEquals(articulo,instance.getArticulo());
    }

    /**
     * Test of getEnvase method, of class Compra.
     */
    @Test
    public void testGetEnvase() {
        System.out.println("getEnvase");
        Envase e = new Envase("", 0, null, 5);
        Compra instance = new Compra(new Articulo(),e,3);
        Envase expResult =e;
        Envase result = instance.getEnvase();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEnvase method, of class Compra.
     */
    @Test
    public void testSetEnvase() {
        System.out.println("setEnvase");
        Envase envase = new Envase();
        Compra instance = new Compra();
        instance.setEnvase(envase);
        assertEquals(envase,instance.getEnvase());
    }

    /**
     * Test of getCantidad method, of class Compra.
     */
    @Test
    public void testGetCantidad() {
        System.out.println("getCantidad");
        Compra instance = new Compra();
        int expResult = 0;
        int result = instance.getCantidad();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCantidad method, of class Compra.
     */
    @Test
    public void testSetCantidad() {
        System.out.println("setCantidad");
        int cantidad = 4;
        Compra instance = new Compra();
        instance.setCantidad(cantidad);
        assertEquals(cantidad,instance.getCantidad());
    }

    /**
     * Test of total method, of class Compra.
     */
    @Test
    public void testTotal() {
        System.out.println("total");
        Compra instance = new Compra(new Articulo(),new Envase(),5);
        instance.getArticulo().setPrecio(9);
        int expResult = 45;
        int result = instance.total();
        assertEquals(expResult, result);
    }
    
}
