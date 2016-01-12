/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.service;

import beans.Bruker;
import database.DBConnection;
import service.ServiceImpl;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sindre
 */
/*
    Funksjoner som skal testes
    public boolean sjekkPassord(String brukernavn, String passord);
    public Bruker hentBruker(String epost);
    public Bruker hentBruker(Bruker bruker);
    public boolean endreBruker(Bruker bruker);
    public boolean slettBruker(Bruker bruker);
    public boolean nyBruker(Bruker bruker);
    public boolean slettBrukere(List<Bruker> brukere);
    public boolean endreBrukere(List<Bruker> brukere);
    public List<Bruker> getAlleBrukere();
    public boolean oppdaterRom(Rom r);
    public boolean slettRom(Rom r);
    public boolean oppdaterKlasseFag(Klasse k, Fag f);
    public boolean slettRomInnhold(Rom r, String innholdNavn);
    public boolean leggTilInnhold(Rom r, String innholdNavn);
    public boolean slettBrukerFag(Bruker b, Fag f);
    public boolean leggTilFag(Fag f);
    public boolean leggTilRom(Rom r);
    public boolean leggTilKalenderEvent(KalenderEvent ke);
    public boolean fjernKalenderEvent(KalenderEvent ke);
    public List<Bruker> getKalenderEventDeltakere(KalenderEvent ke);
    public Bruker getKalenderEventDeltaker(KalenderEvent ke, Bruker b);
    public List<KalenderEvent> getKalenderEventEier(Bruker b);
    public List<KalenderEvent> getKalenderEventRomID(Rom r);
    public List<Fag> getFagLaerer(Bruker b);
    public Rom getRombestilling();
    public List<Rom> getRomFraNavn(Rom r);
    public List<Rom> getRomFraInnhold(Rom r);
    public List<Rom> getRomFraType(Rom r);
    public List<Rom> getRomFraStoerrelse(Rom r);
    public List<Klasse> getLaererKlasse(Bruker b);
    public List<Rom> getAlleRom();
    public List<Fag> getAlleFag();
*/
public class ServiceImplTest {
    DBConnection dBConnection;
    
    //@InjectMocks private PageReader reader;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Koden her eksekveres for første test i klassen
    }

    @Before
    public void setUp() throws Exception {
        // Koden her eksekveres for hver av testmetodene i klassen
        dBConnection=mock(DBConnection.class);

    
    }
    
    
    @Test
    public void test_hentBruker (){
        ServiceImpl test = new ServiceImpl();
        Bruker sindre = new Bruker();
        when(dBConnection.getBruker("sindre@gmail.com")).thenReturn(sindre);
        assertEquals(test.hentBruker("sindre@gmail.com"),sindre);        
        
        //assertEquals(test.getTilgangsniva(),3);
    }
    
    @Test
    public void test_Mange_Funksjoener_Til_DBConnection (){
        ServiceImpl test = new ServiceImpl();
        Bruker sindre = new Bruker();
        
        when(dBConnection.sjekkPassord("sindre@gmail.com","passord")).thenReturn(true);
        assertEquals(test.sjekkPassord("sindre@gmail.com","passord"),true); 
        
        when(dBConnection.sjekkPassord("sindre@gmail.com","passord")).thenReturn(true);
        assertEquals(test.sjekkPassord("sindre@gmail.com","passord"),true); 
        
        //assertEquals(test.getTilgangsniva(),3);
    }
        
    /**@Test
    public void objektTesting(){
        assertSame(objektTest.getKlasse(), objektTest.getKlasse());
        
        //assertNotSame(objektTest.getKlasse(), objektTest.getKlasse());
                
    } **/
        /** EKSEMPEL PÅ MOCKITO-TESTING
         * 
         * //Create mock
        BrukerB test = Mockito.mock(BrukerB.class);
        
        when(test.getNavn()).thenReturn("Ingvild");
        
        assertEquals(test.getNavn(),"Ingvild");
        **/
        
        /**
         Tester enkle get-metoder som:
         getId(), getType(), getEier(), getRom()
         **/

    /*
    @Test(expected = NullPointerException.class)
    public void test_at_koden_som kj�res_kaster_en_NullPointerException() {
        // Kode som gj�r noe annet
    }
    */

    @After
    public void tearDown() throws Exception {
        // Koden her kj�res etter hver test
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Koden her kj�rer etter alle testmetodene i klassen er ferdig
    }
}
