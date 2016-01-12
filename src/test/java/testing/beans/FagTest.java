/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.beans;

import beans.Fag;
import beans.Bruker;
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
    
    Bruker brukerA;
    Bruker brukerB;
    
    private ArrayList laerere;
    private ArrayList kalenderE;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Koden her eksekveres før første test i klassen
       System.out.println("JUNIT: før FagTest-klassen.");
      
    }

    @Before
    public void setUp() throws Exception {
        // Koden her eksekveres før hver av testmetodene i klassen
        
        fag = new Fag();
        fag.setFagID("01234");
        fag.setNavn("AlgDat");
        
        fag1 = new Fag();
        fag1.setFagID("00123");
        fag1.setNavn("Matematikk 2");
                       
        laerere = new ArrayList();
        kalenderE = new ArrayList();
        
        
        
    }

    @Test
    public void testfag_1 (){
        /**
         * Tester set og get-metoder
         **/
        assertEquals(fag.getFagID(),"01234");
        assertEquals(fag.getNavn(),"AlgDat");
        
        fag.setFagID("01235");
        
        assertNotSame("01234", fag.getFagID());
        
        fag.setNavn("DataKom");
        
        assertNotSame("AlgDat", fag.getNavn());
                
    }
    
    @Test
    public void testfag_2 (){
        /**
         *Tester ArrayList for laerere. 
         */
               
        fag.setLaerere(laerere);
        assertTrue(fag.getLaerere().isEmpty());
        
        fag1.setKalenderEvents(kalenderE);
        assertTrue(fag1.getKalenderEvents().isEmpty());         
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
        laerere.clear();
        kalenderE.clear();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Koden her kjører etter alle testmetodene i klassen er ferdig
    }
}
