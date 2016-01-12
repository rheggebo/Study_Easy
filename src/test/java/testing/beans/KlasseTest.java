/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.beans;

/**
 *
 * @author Sindre
 */

import beans.Klasse;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;


public class KlasseTest {
    
    Klasse klasse;
    private ArrayList fag;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Koden her eksekveres f�r f�rste test i klassen
        System.out.println("JUNIT: før KlasseTest-klassen.");
        
    }
    
    @Before
    public void setUp() throws Exception {
        // Koden her eksekveres f�r hver av testmetodene i klassen
        klasse = new Klasse();
        klasse.setNavn("2.ing");
        
        fag = new ArrayList();
        
    }
    
    @Test
    public void tester_getmetoder (){
        assertEquals(klasse.getNavn(), "2.ing");
    }
    
    @Test
    public void tester_Arrays (){
        
        klasse.setFag(fag);
        assertTrue(klasse.getFag().isEmpty());
    
    }
    
     @Test
    public void tester_toString (){
        assertEquals(klasse.toString(), "2.ing");
    
    }
    
    @After
    public void tearDown() throws Exception {
        // Koden her kj�res etter hver test
      
        
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Koden her kj�rer etter alle testmetodene i klassen er ferdig
        
    }
   
}
