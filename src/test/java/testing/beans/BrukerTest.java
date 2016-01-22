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
                
        test = new Bruker();
        test.setFornavn("Student en");
        test.setEtternavn("Pedersen");
        test.setEpost("pedersen@hotmail.com");
        test.setTilgangsniva(0);
        test.setPassord("passord");
        test.setKalenderEvents(liste);
        test.setPassord1("passord");
    }

    @Test
    public void test_getMetoder (){
        
        assertEquals(test.getFornavn(),"Student en");
        assertEquals(test.getEtternavn(),"Pedersen");
        assertEquals(test.getEpost(),"pedersen@hotmail.com");
        assertEquals(test.getTilgangsniva(),0);
        assertEquals(test.getPassord(),"passord");
        assertTrue(test.getKalenderEvents().isEmpty());
        assertEquals(test.getPassord1(), "passord");
    }
    
    @Test
    public void test_toString(){
        String bruker = "Student: Student en Pedersen,  Epost: pedersen@hotmail.com";
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

