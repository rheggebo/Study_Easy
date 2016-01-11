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
    Rom rom1;
    private ArrayList innhold;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Koden her eksekveres før første test i klassen
        System.out.println("JUNIT: før klassen.");
        
    }

    @Before
    public void setUp() throws Exception {
        // Koden her eksekveres før hver av testmetodene i klassen
     
        rom = new Rom();
        rom.setRomID("KAUD");
        rom.setRomNavn("KAUD");
        rom.setEtasje(3);
        rom.setType(3);
        rom.setStorrelse(100);
        
        rom1 = rom; 
        
        innhold = new ArrayList();
                     
    }

    @Test
    public void tester_getmetoder (){
        /**
         * Test set- og get-metoder
         **/
        assertEquals(rom.getRomID(),"KAUD");
        assertEquals(rom.getRomNavn(),"KAUD");
        assertEquals(rom.getEtasje(), 3);
        assertEquals(rom.getType(), 3);
        assertEquals(rom.getStorrelse(),100);
        
    }
    
    @Test
    public void tester_Arraylist(){
        assertTrue(innhold.isEmpty());
    }
    
    @Test
    public void tester_Liste(){
        innhold.add("prosjektor");
        assertEquals(1, innhold.size());
        assertEquals(innhold.get(0), "prosjektor");
        
    }
    
    @Test
    public void test_sammenligning(){
        /**
         *tester med forandring, 
         * eks: finn en str på et rom for å så forandre det
         * og så finne den forandra størrelsen 
         */
       rom1.setStorrelse(100);
       
       assertSame(rom1.getStorrelse(), rom.getStorrelse());
       assertSame(rom.getType(), rom.getEtasje());
       
    }
    
 
    @Test
    public void test_3 (){
        //rom1.setEtasje(2);
        assertSame(rom.getEtasje(), rom1.getEtasje());
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
