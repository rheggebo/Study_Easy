/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.beans;

import beans.Rom;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;


/**
 *
 * @author Sindre
 */
public class RomTest {
    
    Rom rom;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Koden her eksekveres før første test i klassen
    }

    @Before
    public void setUp() throws Exception {
        // Koden her eksekveres før hver av testmetodene i klassen
     
        rom = new Rom();
        
    }

    @Test
    public void testrom_1 (){
        /**
         * Test set- og get-metoder
         **/
        Rom rom = new Rom();
        rom.setRomID("KAUD");
        assertEquals(rom.getRomID(),"KAUD");
        
        rom.setRomNavn("KAUD");
        assertEquals(rom.getRomNavn(),"KAUD");
        
        rom.setEtasje(3);
        assertEquals(rom.getEtasje(), 3);
        
        rom.setType(3);
        assertEquals(rom.getType(), 3);
        
        rom.setStorrelse(100);
        assertEquals(rom.getStorrelse(),100);        
        
    }
    
    public void testrom_2 (){
        /**
         *tester med forandring, 
         * eks: finn en str på et rom for å så forandre det
         * og så finne den forandra størrelsen 
         */
        
      
    }
    
 

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
