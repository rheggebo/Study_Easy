/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.service;

import beans.Abonemennt;
import beans.Bruker;
import beans.BrukerB;
import beans.Fag;
import beans.KalenderEvent;
import beans.Klasse;
import beans.Rom;
import beans.RomBestilling;
import database.DBConnection;
import java.util.ArrayList;
import java.util.List;
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
    | tatt
        |ikke tatt
*/
public class ServiceImplTest {
    ServiceImpl test;
    DBConnection dBConnection;
    Abonemennt ab;
    Bruker sindre;
    Bruker henrik;
    Bruker b;
    BrukerB sindreB;
    Rom rom272;
    Rom rom1;
    RomBestilling romB;
    List<Bruker> liste;
    List<Bruker> liste1;
    Klasse k;
    Fag f;
    KalenderEvent ke;
    ArrayList<String> innhold;
    List<KalenderEvent> liste2;
    List<Fag> liste3;
    List<Rom> liste4;
    List<Fag> listeF;
    List<Rom> listeR;
    List<Klasse> listeK;
    List<Bruker> listeB;        
    List<RomBestilling> listeRB;
    List<Abonemennt> listeAB;
    String[] innhold1 = new String[2];
    String[] innhold2 = new String[2];
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Koden her eksekveres for første test i klassen
        System.out.println("JUNIT: før ServiceImplTest-klassen.");
    }

    @Before
    public void setUp() throws Exception {
        // Koden her eksekveres for hver av testmetodene i klassen
        dBConnection=mock(DBConnection.class);
        test = new ServiceImpl();
        test.setDBC(dBConnection);
        sindre = new Bruker();
        henrik = new Bruker();
        b = new Bruker();
        rom272 = new Rom();
        ke= new KalenderEvent();
        f = new Fag();
        k = new Klasse();
        ab = new Abonemennt();
        innhold = new ArrayList();
        innhold.add("Prosjektor");
        liste= new ArrayList<>();
        liste1 = new ArrayList<>();
        liste2 = new ArrayList<>();
        liste3 = new ArrayList<>();
        liste4 = new ArrayList<>();
        liste1.add(b);
        liste2.add(ke);
        liste.add(henrik);
        liste.add(sindre);
        liste3.add(f);
        liste4.add(rom272);
        innhold1[0] = "stol";
        innhold1[1] = "mamma";
        innhold2[0] = "stol";
        innhold2[1] = "mamma";
        
        when(dBConnection.sjekkPassord("sindre@gmail.com","passord")).thenReturn(true);
        when(dBConnection.getBruker("sindre@gmail.com")).thenReturn(sindre);
        when(dBConnection.getBruker("test1@aol.com")).thenReturn(b);
        when(dBConnection.oppdaterBruker(sindre)).thenReturn(true);
        when(dBConnection.oppdaterBruker(henrik)).thenReturn(true);
        when(dBConnection.slettBruker(sindre)).thenReturn(true);
        when(dBConnection.slettBruker(henrik)).thenReturn(true);
        when(dBConnection.slettBruker(b)).thenReturn(false);
        when(dBConnection.oppdaterBruker(b)).thenReturn(false);
        when(dBConnection.leggTilBruker(sindre)).thenReturn(true);
        when(dBConnection.leggTilBruker(sindre)).thenReturn(true);
        when(dBConnection.getAlleBrukere()).thenReturn(liste);
        when(dBConnection.oppdaterRom(rom272)).thenReturn(true);
        when(dBConnection.slettRom(rom272)).thenReturn(true);
        when(dBConnection.oppdaterKlasseFag(k, f)).thenReturn(true);
        when(dBConnection.slettRomInnhold(rom272, "tavle")).thenReturn(true);
        when(dBConnection.leggTilInnhold(rom272, "tavle")).thenReturn(true);
        when(dBConnection.slettBrukerFag(sindre, f)).thenReturn(true);
        when(dBConnection.leggTilFag(f)).thenReturn(true);
        when(dBConnection.leggTilRom(rom272)).thenReturn(false);
        when(dBConnection.leggTilEvent(ke)).thenReturn(true);
        when(dBConnection.fjernKalenderEvent(ke)).thenReturn(true);
        when(dBConnection.getKalenderEventDeltakere(ke)).thenReturn(liste);
        when(dBConnection.getKalenderEventDeltaker(ke,sindre)).thenReturn(sindre);
        when(dBConnection.getKalenderEventEier(sindre)).thenReturn(liste2);
        when(dBConnection.getKalenderEventRomID(rom272)).thenReturn(liste2);
        when(dBConnection.getKalenderEventHidden(ke)).thenReturn(liste2);
        when(dBConnection.getFagLaerer(sindre)).thenReturn(liste3);
        when(dBConnection.getRomFraNavn(rom272)).thenReturn(liste4);
        /*Ikke i bruk
        when(dBConnection.getRomFraType(rom272)).thenReturn(liste4);
        when(dBConnection.getRomFraStoerrelse(rom272)).thenReturn(liste4);
        */
        when(dBConnection.getAlleRom()).thenReturn(liste4);
        when(dBConnection.getAlleFag()).thenReturn(liste3);
        when(dBConnection.getAlleEventsFraBruker(sindreB)).thenReturn(liste2);
        when(dBConnection.getAlleBestillingerFraBruker(sindreB)).thenReturn(listeRB);
        when(dBConnection.getRomTypeStorrelse(rom272)).thenReturn(liste4);
        when(dBConnection.getAbonemenntFraBruker(sindreB)).thenReturn(listeAB);
        when(dBConnection.leggTilAbonemennt(ab)).thenReturn(true);
        when(dBConnection.slettAbonemennt(ab)).thenReturn(true);
        when(dBConnection.getStudentSok("test1", "test1", "test1")).thenReturn(listeB);
        when(dBConnection.getAnsattSok("hei","hei","hei")).thenReturn(listeB);
        when(dBConnection.getFagSok("Fysikk","Fysikk")).thenReturn(listeF);
        when(dBConnection.getRomSok("GR114","GR114")).thenReturn(listeR);
        when(dBConnection.getKlasseSok("TDAT")).thenReturn(listeK);
        when(dBConnection.getRom(rom1)).thenReturn(rom1);
        when(dBConnection.getRomSVG(rom1, ke)).thenReturn(listeR);
        when(dBConnection.leggTilBooking(ke)).thenReturn(true);
        when(dBConnection.slettBooking(ke)).thenReturn(true);
        when(dBConnection.getReserverteRom(ke)).thenReturn(listeRB);
        when(dBConnection.getRomBooking(ke)).thenReturn(romB);
        when(dBConnection.slettKalenderEvent(romB)).thenReturn(true);
        when(dBConnection.getBrukerAbonnement("test1@aol.com")).thenReturn(listeAB);
        when(dBConnection.getAlleKlasser()).thenReturn(listeK);
        when(dBConnection.erRomLedig(ke)).thenReturn(true);
        when(dBConnection.oppdaterInnholdRom("KAUD", innhold1)).thenReturn(true);
        when(dBConnection.oppdaterInnholdRom("KAUD", innhold2)).thenReturn(false);
        when(dBConnection.getAlleInnholdRom(rom1)).thenReturn(innhold);
        when(dBConnection.getFagKlasse("TDATh14")).thenReturn(listeF);
        when(dBConnection.leggTilFagKlasse("TDAT2001", "TDATH14")).thenReturn(true);
        
        
    }
    
    @Test
    public void test_hentBruker (){
        
        sindre.setEpost("sindre@gmail.com");
        
        assertEquals(test.hentBruker("sindre@gmail.com"),sindre);        
        assertEquals(test.hentBruker(sindre), sindre);
    }
    
    @Test
    public void testHentBruker2(){
        assertEquals(test.hentBruker("test1@aol.com"), b);
    }
    
    @Test
    public void test_setPassord (){
        ServiceImpl test = new ServiceImpl();
        test.setDBC(dBConnection);
        
        //Ser på passordet:
        assertEquals(test.sjekkPassord("sindre@gmail.com","passord"),true);
    }
    
    @Test
    public void test_EndreBruker (){
        sindre.setPassord("Passord");
        assertEquals(test.endreBruker(sindre),true);
    }
    
    @Test
    public void test_slettBruker (){
        assertEquals(test.slettBruker(sindre),true);
    }
        
    @Test
    public void test_NyBruker (){
        assertEquals(test.nyBruker(sindre),true);
        assertFalse(test.slettBrukere(liste1));
    }
    
    @Test
    public void test_SlettListe (){
        //Slett liste med brukere:
        assertEquals(test.slettBrukere(liste),true);
    }
    
    @Test
    public void test_EndreBrukere (){
        //Slett liste med bruere:
        assertEquals(test.endreBrukere(liste),true);
        assertFalse(test.endreBrukere(liste1));
    }
    
    @Test
    public void test_FåAlleBrukere (){
        //Slett liste med bruere:
        assertEquals(test.getAlleBrukere(),liste);
    }
    
    @Test
    public void test_OppdatereRom (){
        //Slett liste med bruere:
        assertEquals(test.oppdaterRom(rom272),true);
    }
    
    @Test
    public void test_SlettRom (){
        //Slett liste med bruere:
        assertEquals(test.slettRom(rom272),true);
    }
    
    @Test
    public void test_OppdaterKlasseFag (){
        
        //Slett liste med bruere:
        assertEquals(test.oppdaterKlasseFag(k, f),true);
    }
    
    @Test
    public void test_SlettRomInnhold (){
        //Slett liste med bruere:
        assertEquals(test.slettRomInnhold(rom272, "tavle"),true);
    }
    
    @Test
    public void test_LeggTilInnhold (){
        //Slett liste med bruere:
        assertEquals(test.leggTilInnhold(rom272, "tavle"),true);
    }
    
    @Test
    public void test_SlettBrukerFag (){
        //Slett liste med bruere:
        assertEquals(test.slettBrukerFag(sindre, f),true);
    }
    
    @Test
    public void test_LeggTilFag (){
        //Slett liste med bruere:
        assertEquals(test.leggTilFag(f),true);
    }
    
    @Test
    public void test_LeggTilRom (){
        //Slett liste med bruere:
        assertEquals(test.leggTilRom(rom272),false);
    }
    
    @Test
    public void test_FjernTilKalenderEvent (){
        //Slett liste med bruere:
        assertEquals(test.fjernKalenderEvent(ke),true);
    }
    
    @Test
    public void test_GetKalenderEventDeltakere (){
        //Slett liste med bruere:
        assertEquals(test.getKalenderEventDeltakere(ke),liste);
    }
    
    @Test
    public void test_GetKalenderEventDeltaker (){
        //Slett liste med bruere:
        assertEquals(test.getKalenderEventDeltaker(ke,sindre),sindre);
    }
    
    @Test
    public void test_GetKalenderEventEier (){
        //Slett liste med bruere:
        assertEquals(test.getKalenderEventEier(sindre),liste2);
    }
    
    @Test
    public void test_GetKalenderEventRomID (){
        //Slett liste med bruere:
        assertEquals(test.getKalenderEventRomID(rom272),liste2);
    }
    
    @Test
    public void test_GetKalenderEventHidden (){
        //Slett liste med bruere:
        assertEquals(test.getKalenderEventHidden(ke), liste2);
    }
    
    @Test
    public void test_GetFagLaerer (){
        //Slett liste med bruere:
        assertEquals(test.getFagLaerer(sindre),liste3);
    }
    
    @Test
    public void test_GetRomFraNavn(){
        //Slett liste med bruere:
        assertEquals(test.getRomFraNavn(rom272),liste4);
    }
    
    @Test
    public void test_GtAlleRom(){
        //Slett liste med bruere:
        assertEquals(test.getAlleRom(),liste4);
    }
    
    @Test
    public void test_GetAlleFag(){
        //Slett liste med bruere:
        assertEquals(test.getAlleFag(),liste3);
    }
    
    @Test
    public void test_getAlleEventsFraBruker(){
        //Slett liste med bruere:
        assertEquals(test.getAlleEventsFraBruker(sindreB),liste2);
    }
    
    @Test
    public void test_getAlleBestillingerFraBruker(){
        //Slett liste med brukere:
        assertEquals(test.getAlleBestillingerFraBruker(sindreB),listeRB);
    }
    
    @Test
    public void test_getRomTypeStorrelse(){
        //Slett liste med brukere:
        assertEquals(test.getRomTypeStorrelse(rom272),liste4);
    }
    
    @Test
    public void test_getAbonemenntFraBruker(){
        //Slett liste med brukere:
        assertEquals(test.getAbonemenntFraBruker(sindreB), listeAB);
        assertEquals(test.getBrukerAbonnement("test1@aol.com"), listeAB);
    }
    
    @Test
    public void test_getAbonemennt(){
        assertTrue(test.leggTilAbonemennt(ab));
        assertTrue(test.slettAbonemennt(ab));
        //assertEquals(test.getAbonnementDeltakere(null))
    }
    
    @Test
    public void test_getSok(){
        assertEquals(test.getStudentSok("test1", "test1", "test1"), listeB);
        assertEquals(test.getAnsattSok("hei","hei","hei"), listeB);
        assertEquals(test.getFagSok("Fysikk","Fysikk"), listeF);
        assertEquals(test.getRomSok("GR114","GR114"), listeR);
        assertEquals(test.getKlasseSok("TDAT"), listeK);
    }
    
    @Test
    public void test_Rom(){
        assertEquals(test.getRom(rom1), rom1);
        assertEquals(test.getRomSVG(rom1, ke), listeR);
        assertEquals(test.getReserverteRom(ke), listeRB);
        assertTrue(test.erRomLedig(ke));
        assertFalse(test.oppdaterInnholdRom("KAUD", innhold1));
        assertFalse(test.oppdaterInnholdRom("KAUD", innhold2));
        assertEquals(test.getAlleInnholdRom(rom1), innhold);
    }
    
    @Test
    public void test_booking(){
        assertTrue(test.leggTilBooking(ke));
        assertTrue(test.slettBooking(ke));
        assertEquals(test.getRomBooking(ke), romB);
    }
    
    
    @Test
    public void test_kalenderEvent(){
        assertTrue(test.leggTilEvent(ke));
        assertTrue(test.slettKalenderEvent(romB)); 
    }
    
    @Test
    public void test_div(){
        assertEquals(test.getAlleKlasser(), listeK);
        assertTrue(test.slettKalenderEvent(romB));
        assertEquals(test.getFagKlasse("TDATh14"), listeF);
        assertTrue(test.leggTilFagKlasse("TDAT2001", "TDATH14"));
    }
    
    /*
    @Test
    public void test_SlettL (){
        ServiceImpl test = new ServiceImpl();
        Bruker sindre = new Bruker();
        Bruker henrik = new Bruker();
        ArrayList<Bruker> liste = new ArrayList<>();
        liste.add(henrik);
        liste.add(sindre);
        test.setDBC(dBConnection);
        
        //Få alle brureke:
        //when(dBConnection.getAlleBrukere().get(0).thenReturn(sindre));
        
        
        //assertEquals(test.getTilgangsniva(),3);
    }
    */
        
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
