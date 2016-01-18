/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.beans;

import beans.Bruker;
import beans.Klasse;
import beans.KalenderEvent;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author Sindre
 */
public class BrukerTest {
    
    Klasse klasse;
    Bruker test;
    Date fDato;
    KalenderEvent kEvent;
    private ArrayList<KalenderEvent> liste;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Koden her eksekveres før første test i klassen
    }

    @Before
    public void setUp() throws Exception {
        // Koden her eksekveres før hver av testmetodene i klassen
        
        liste = new ArrayList();
        
        klasse = new Klasse();
        klasse.setNavn("2.ing");
        
        fDato = new Date(1992-1-3);
        
        test = new Bruker();
        test.setFornavn("Per");
        test.setEtternavn("Aas");
        test.setEpost("per@hotmail.com");
        test.setNotat("Jeg liker fotball");
        test.setTilgangsniva(3);
        test.setPassord("123");
        test.setFodedato(fDato);
        test.setKlasse(klasse);
        test.setTelefonnummer(12345678);
        test.setKalenderEvents(liste);
    }

    @Test
    public void test_getMetoder (){
        
        assertEquals(test.getFornavn(),"Per");
        assertEquals(test.getEtternavn(),"Aas");
        assertEquals(test.getEpost(),"per@hotmail.com");
        assertEquals(test.getNotat(),"Jeg liker fotball");
        assertEquals(test.getTilgangsniva(),3);
        assertEquals(test.getPassord(),"123");
        assertEquals(test.getFodedato(), fDato);
        assertEquals(test.getKlasse(), klasse);
        assertEquals(test.getTelefonnummer(),12345678);
        assertTrue(test.getKalenderEvents().isEmpty());
        
    }
    
    @Test
    public void test_toString(){
        String bruker = "Per Aas,  Epost: per@hotmail.com";
        assertEquals(test.toString(), bruker);
    }
        
    @After
    public void tearDown() throws Exception {
        // Koden her kjøres etter hver test
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Koden her kjører etter alle testmetodene i klassen er ferdig
    }
}

