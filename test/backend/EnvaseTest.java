/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

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
public class EnvaseTest {

    public EnvaseTest() {
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
     * Test of getNombre method, of class Envase.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Envase instance = new Envase();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNombre method, of class Envase.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "nombre";
        Envase instance = new Envase();
        instance.setNombre(nombre);
        assertEquals(nombre, instance.getNombre());
    }

    /**
     * Test of getId method, of class Envase.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Envase instance = new Envase();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVecesUsado method, of class Envase.
     */
    @Test
    public void testGetVecesUsado() {
        System.out.println("getVecesUsado");
        Envase instance = new Envase();
        int expResult = 0;
        int result = instance.getVecesUsado();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTipos method, of class Envase.
     */
    @Test
    public void testGetTipos() {
        System.out.println("getTipos");
        Envase instance = new Envase();
        Articulo.Tipo[] expResult = {};
        Articulo.Tipo[] result = instance.getTipos();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setTipos method, of class Envase.
     */
    @Test
    public void testSetTipos() {
        System.out.println("setTipos");
        Articulo.Tipo[] tipos = {Articulo.Tipo.BEBIDA};
        Envase instance = new Envase();
        instance.setTipos(tipos);
        assertArrayEquals(tipos, instance.getTipos());
    }

    /**
     * Test of getCosteProduccion method, of class Envase.
     */
    @Test
    public void testGetCosteProduccion() {
        System.out.println("getCosteProduccion");
        Envase instance = new Envase();
        int expResult = 0;
        int result = instance.getCosteProduccion();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCosteProduccion method, of class Envase.
     */
    @Test
    public void testSetCosteProduccion() {
        System.out.println("setCosteProduccion");
        int costeProduccion = 5;
        Envase instance = new Envase();
        instance.setCosteProduccion(costeProduccion);
        assertEquals(costeProduccion, instance.getCosteProduccion());
    }

    /**
     * Test of aumentarUso method, of class Envase.
     */
    @Test
    public void testAumentarUso() {
        System.out.println("aumentarUso");
        int n = 5;
        Envase instance = new Envase();
        instance.aumentarUso(n);
        instance.aumentarUso(n);
        assertEquals(2*n, instance.getVecesUsado());
    }

    /**
     * Test of admiteElTipo1 method, of class Envase.
     */
    @Test
    public void testAdmiteElTipo1() {
        System.out.println("admiteElTipo1");
        Articulo.Tipo t = Articulo.Tipo.SECO;
        Envase instance = new Envase();
        boolean expResult = false;
        boolean result = instance.admiteElTipo(t);
        assertEquals(expResult, result);
    }

    /**
     * Test of admiteElTipo2 method, of class Envase.
     */
    @Test
    public void testAdmiteElTipo2() {
        System.out.println("admiteElTipo2");
        Articulo.Tipo t = Articulo.Tipo.HUMEDO;
        Envase instance = new Envase();
        Articulo.Tipo[] tipos = {Articulo.Tipo.BEBIDA,Articulo.Tipo.HUMEDO};
        instance.setTipos(tipos);
        boolean expResult = true;
        boolean result = instance.admiteElTipo(t);
        assertEquals(expResult, result);
    }
}
