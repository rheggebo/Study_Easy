/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.verktøy;

import beans.Bruker;
import verktøy.Funksjoner;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import service.Service;

/**
 *
 * @author Sindre
 */
public class FunksjonerTest {
    Funksjoner test;
    Service si;
    String ord;
    String[] checkboxValues;
    Bruker bruker1;
    ArrayList<Bruker> studentListe;
    ArrayList<Bruker> ansattListe;
    ArrayList<Bruker> brukerListe;
    
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Koden her eksekveres f�r f�rste test i klassen
        System.out.println("JUNIT: før FunkjsonTest-klassen.");
    }
    
    @Before
    public void setUp() throws Exception {
        // Koden her eksekveres f�r hver av testmetodene i klassen
        test=new Funksjoner();
        si = mock(Service.class);
        ord="Per";
        checkboxValues=null;
        bruker1 = new Bruker();
        brukerListe = new ArrayList<Bruker>();
        brukerListe.add(bruker1);
        
        
        when(si.getStudentSok(ord, ord, ord)).thenReturn(brukerListe);
        when(si.getStudentSok(ord, ord, ord)).thenReturn(brukerListe);

        
    }
    
    @Test
    public void test_getAlleSokeTreff (){
        assertEquals(test.getAlleSokeTreff(ord, si, checkboxValues), true);
    }
    
    @Test
    public void test_sokStudent (){
        test.sokStudent(si, ord);
        assertEquals(test.sokStudent(si, ord), brukerListe);
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
