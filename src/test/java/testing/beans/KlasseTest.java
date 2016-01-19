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

import beans.Fag;
import beans.Klasse;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;


public class KlasseTest {
    
    Klasse klasse;
    private ArrayList<Fag> fag;
    
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
        
        Fag fag1 = new Fag();
        fag1.setFagID("TDAT2001");
        fag1.setNavn("Matematikk 1");
        
        Fag fag2 = new Fag();
        fag2.setFagID("TDAT2002");
        fag2.setNavn("Fysikk 1");
        
        Fag fag3 = new Fag();
        fag3.setFagID("TDAT2003");
        fag3.setNavn("Matematikk 2");
        
        fag.add(fag1);
        fag.add(fag2);
        fag.add(fag3);
        
         klasse.setFag(fag);
        
    }
    
    @Test
    public void tester_getmetoder (){
        assertEquals(klasse.getNavn(), "2.ing");
    }
    
    @Test
    public void tester_Arrays (){
        assertEquals(klasse.getFag(), fag);
    
    }
    
     @Test
    public void tester_toString (){
        System.out.println(klasse.toString());
        String str = "Klasse: " + klasse.getNavn() + ", Fag: ";
        for (int i = 0; i < fag.size(); i++) {
            str += fag.get(i).getFagID() + " ";
        }
        assertEquals(klasse.toString(), str);
                 
    }
    
    @After
    public void tearDown() throws Exception {
        // Koden her kj�res etter hver test
      
        fag.clear();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Koden her kj�rer etter alle testmetodene i klassen er ferdig
        
    }
   
}
