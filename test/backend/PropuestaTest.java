/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import javafx.scene.image.Image;
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
public class PropuestaTest {
    
    public PropuestaTest() {
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
     * Test of getNombre method, of class Propuesta.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Propuesta instance = new Propuesta();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescripcion method, of class Propuesta.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        Propuesta instance = new Propuesta();
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCantidadVotos method, of class Propuesta.
     */
    @Test
    public void testGetCantidadVotos() {
        System.out.println("getCantidadVotos");
        Propuesta instance = new Propuesta();
        int expResult = 0;
        int result = instance.getCantidadVotos();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImagen method, of class Propuesta.
     */
    @Test
    public void testGetImagen() {
        System.out.println("getImagen");
        Propuesta instance = new Propuesta();
        Image expResult = null;
        Image result = instance.getImagen();
        assertEquals(expResult, result);
    }

    /**
     * Test of agregarVoto method, of class Propuesta.
     */
    @Test
    public void testAgregarVoto() {
        System.out.println("agregarVoto");
        Propuesta instance = new Propuesta();
        int anterior = instance.getCantidadVotos();
        instance.agregarVoto();
        assertEquals(anterior+1,instance.getCantidadVotos());
    }

    
}
