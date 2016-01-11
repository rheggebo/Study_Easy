/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.beans;

import beans.Fag;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;


/**
 *
 * @author Sindre
 */
public class FagTest {
    Fag fag;
    Fag fag1;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Koden her eksekveres før første test i klassen
       
        
    }

    @Before
    public void setUp() throws Exception {
        // Koden her eksekveres før hver av testmetodene i klassen
        fag = new Fag();
        fag.setFagID("Data");
        fag.setNavn("Per");
    }

    @Test
    public void testfag_1 (){
        /**
         * Test set- og get-metoder
         **/
        assertEquals(fag.getFagID(),"Data");
        assertEquals(fag.getNavn(),"Per");
        
        fag1.setFagID("ALgDat");
        
        
        
    }
    
    @Test
    public void testfag_2 (){
        /**
         *tester med forandring, 
         * eks: finn en str på et rom for å så forandre det
         * og så finne den forandra størrelsen 
         */
        
 
    }
    
    @Test
    public void test_3 (){

    }
    /*
    @Test(expected = NullPointerException.class)
    public void test_at_koden_som kjøres_kaster_en_NullPointerException() {
        // Kode som gjør noe annet
    }
    */

    @After
    public void tearDown() throws Exception {
        // Koden her kjøres etter hver test
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Koden her kjører etter alle testmetodene i klassen er ferdig
    }
}
