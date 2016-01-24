/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.verktøy;

import beans.Bruker;
import beans.Fag;
import beans.Klasse;
import beans.Rom;
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
    ArrayList<Bruker> brukerListe;
    ArrayList<Fag> fagListe;
    ArrayList<Rom> romListe;
    ArrayList<Klasse> klasseListe;
    ArrayList<Object> sokeKlasse;
    
    
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Koden her eksekveres f�r f�rste test i klassen
        System.out.println("JUNIT: før FunkjsonTest-klassen.");
    }
    
    @Before
    public void setUp() throws Exception {
        // Koden her eksekveres f�r hver av testmetodene i klassen
        test=mock(Funksjoner.class);
        si = mock(Service.class);
        
        ord="Per";
        checkboxValues= new String[5];
        bruker1 = new Bruker();
        brukerListe = new ArrayList<Bruker>();
        brukerListe.add(bruker1);
        sokeKlasse = new ArrayList<Object>();
        
        
        
        when(test.sokStudent(si, ord)).thenReturn(brukerListe);
        when(test.sokAnsatt(si,ord)).thenReturn(brukerListe);
        when(test.sokRom(si, ord)).thenReturn(romListe);
        when(test.sokFag(si,ord)).thenReturn(fagListe);
        when(test.sokKlasse(si,ord)).thenReturn(klasseListe);
        when(test.getAlleSokeTreff(ord, si, checkboxValues)).thenReturn(sokeKlasse);
        
    }
    
    @Test
    public void test_getAlleSokeTreff() {
        assertEquals(test.getAlleSokeTreff(ord, si, checkboxValues), sokeKlasse);
    }
    
    @Test
    public void test_sokStudent(){
        assertEquals(test.sokStudent(si, ord), brukerListe);
    }
    
    @Test
    public void test_sokAnsatt(){
        assertEquals(test.sokAnsatt(si, ord), brukerListe);
    }
    
    @Test
    public void test_sokRom(){
        assertEquals(test.sokRom(si, ord), romListe);
    }
    
    @Test
    public void test_sokFag(){
        assertEquals(test.sokFag(si, ord), fagListe);
    }
    
    @Test
    public void test_sokKlasse(){
        assertEquals(test.sokKlasse(si, ord), klasseListe);
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
