package testing.beans;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import beans.BrukerB;
import beans.KalenderEvent;
import beans.Klasse;
import beans.Fag;
import beans.Bruker;
import java.util.Date;

/**
 *
 * @author Ingvild
 *
 * */

public class BrukerBTest {
    BrukerB bruker1;
    Klasse klasse1;
    Date fdato;
    private ArrayList kalenderEvents;
    private ArrayList fag;


    BrukerB brukeren;
    Bruker bruker;
    Klasse brukerKlasse;
    Date fdatoBruker;
    private ArrayList kalenderEventsBruker;
    private ArrayList fagBruker;
    
    BrukerB tombruker;
    
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Koden her eksekveres f�r f�rste test i klassen
        System.out.println("JUNIT: før FagTest-klassen.");
    }

    @Before
    public void setUp() throws Exception {
        // Koden her eksekveres før hver av testmetodene i klassen
        
        /** Bruker 1 **/
        
        bruker1 = new BrukerB();
        klasse1 = new Klasse();
        fdato = new Date(1991-1-2);
        kalenderEvents = new ArrayList();
        fag = new ArrayList();
        
        bruker1.setFornavn("Per");
        bruker1.setEtternavn("Aas");
        bruker1.setEpost("per@hotmail.no");
        bruker1.setNotat("Jeg liker fotball");
        bruker1.setTilgangsniva(3);
        bruker1.setInnlogget(true);
        bruker1.setTelefonnummer(91000000);
        bruker1.setFodedato(fdato);
        
        klasse1.setNavn("Data");
        klasse1.setFag(fag);
    
        bruker1.setKalenderEvents(kalenderEvents);
        bruker1.setKlasse(klasse1);
        
        /** Bruker 2 **/
        brukerKlasse = new Klasse();
        bruker = new Bruker();
        brukeren = new BrukerB(bruker);
        kalenderEventsBruker = new ArrayList();
        fagBruker = new ArrayList();
        fdatoBruker = new Date(1990-2-1);
        
        bruker.setFornavn("Roar");
        bruker.setEtternavn("Toresen");
        bruker.setPassord("123");
        bruker.setEpost("roar@hotmail.com");
        bruker.setNotat("Jeg liker musikk");
        bruker.setTilgangniva(2);
        bruker.setTelefonnummer(92000000);

        
        bruker.setFodedato(fdatoBruker);
        bruker.setKalenderEvents(kalenderEventsBruker);

        bruker.setKalenderEvents(kalenderEvents);
        bruker.setKlasse(klasse1);
        
    }

    @Test
    public void test1 (){
        /*Test set- og get-metoder*/
        assertEquals(bruker1.getFornavn(),"Per");
        
        assertEquals(bruker1.getEtternavn(),"Aas");
        
        assertEquals(bruker1.getEpost(),"per@hotmail.no");
        
        assertEquals(bruker1.getNotat(),"Jeg liker fotball");
       
        assertEquals(bruker1.getTilgangsniva(),3);
        
        assertEquals(bruker1.getTelefonnummer(),91000000);
                
        assertEquals(klasse1.getNavn(),"Data");
        
        assertEquals(klasse1.getFag(),fag);
        
        assertEquals(bruker1.getFodedato(), fdato);
        
        assertEquals(bruker1.getKlasse(),klasse1);
        
        assertEquals(bruker1.getKalenderEvents(),kalenderEvents);

    }
    
    @Test   
    public void testInnlogget(){
        assertTrue(bruker1.isInnlogget());
        
        bruker1.setInnlogget(false);
        assertFalse(bruker1.isInnlogget());
    }
    
    
    @Test
    public void testArrayList(){
        /* Tester ArrayList */
        bruker1.setKalenderEvents(kalenderEvents);
        assertTrue(bruker1.getKalenderEvents().isEmpty());
    }
        
    @Test
    public void testTomBruker(){
        assertNull("Brukeren er tom", tombruker);
                
    } 
    
    @Test
    public void testIkkeTomBruker(){
        tombruker = new BrukerB();
        assertNotNull("Brukeren er opprettet", tombruker);
    }

    @After
    public void tearDown() throws Exception {
        // Koden her kj�res etter hver test
        fag.clear();
        kalenderEvents.clear();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Koden her kj�rer etter alle testmetodene i klassen er ferdig
    }
}
