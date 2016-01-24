/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.database;

import beans.Abonemennt;
import database.DBConnectionImpl;
import beans.Bruker;
import beans.BrukerB;
import beans.Fag;
import beans.KalenderEvent;
import beans.Klasse;
import beans.Rom;
import beans.RomBestilling;
import java.sql.Connection;
import java.sql.Timestamp;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Sindre
 */
public class DBConnectionImplTest {
    
    static DBConnectionImpl dbc;
    JdbcTemplate jdbc;
    
    String melding ="heu";
    BrukerB brukerB;
    Bruker brukerC;
    static Bruker b;
    Bruker bruker;
    Abonemennt ab;
    Abonemennt ac;
    Klasse klasse;
    static RomBestilling rb1;
    Rom rom1; 
    Rom rom2;
    Rom rom3;
    Rom rom4;
    Fag fag1;
    Fag fag2;
    Timestamp fraDato;
    Timestamp tilDato;
    List<KalenderEvent> listkEvent; 
    List<Fag> listFag;
    List<Rom> listRom;
    List<Rom> listeRom;
    List<Rom> listRomTest;
    List<Abonemennt> listAbo;
    List<RomBestilling> listRB;
    List<Klasse> listKl;
    static List<KalenderEvent> a;
    static List<RomBestilling> rb; 
    static int tall;
        
    static KalenderEvent kEvent;
    static KalenderEvent kEvent1;
    ArrayList<String> tab;
    
    static DataSource dummyDataSource(){
        String url = "jdbc:mysql://mysql.stud.iie.ntnu.no/g_scrum_t1_test"; //mysql.stud.iie.ntnu.no/g_scrum_t1_test
        String username = "g_scrum_t1_test"; //TestDatabase:"g_scrum_t1_test"  Database:"g_scrum_t1" 
        String password = "nYHZ2FnB"; // TestDatabasePassord:"nYHZ2FnB" DatabasePassord:"0GPgcC6H"
        DriverManagerDataSource dmds = new DriverManagerDataSource(url, username, password);
        dmds.setDriverClassName("com.mysql.jdbc.Driver");
        try{
            Connection con = dmds.getConnection();
            System.out.println(" *********  Konfig " + con );
            //getAllePersoner(con); //brukes for testing av oppkobling
        }catch(Exception e){
            System.out.println(" Konfig.Feil ved henting av conncetion() " + e);
        }
        return dmds;
    }
    
    static int inkrementere(){
        a = new ArrayList<KalenderEvent>();
        a = dbc.getKalenderEventEier(b);
        int bare = a.get(0).getId();
        //System.out.println(bare);
        return bare;
    }
      
    @BeforeClass
    public static void setUpClass() throws Exception{
        dbc = new DBConnectionImpl();
        dbc.setDatabaseSource(dummyDataSource());
       
    }
    
    @Before
    public void setUp() {
        jdbc = mock(JdbcTemplate.class);
        
        listRomTest = new ArrayList<Rom>();
        listRomTest.add(rom1);
       // tab = new ArrayList<String>(10);
       // tab.add("hey");tab.add("hey");tab.add("hey");tab.add("hey");tab.add("hey");
        
        fraDato = Timestamp.valueOf("2016-1-26 09:00:00.0");
        tilDato = Timestamp.valueOf("2016-1-26 12:30:00.0");
        
        ab = new Abonemennt("pedersen@aol.com","test2@aol.com", 0);
        ac = new Abonemennt("ola@hotmail.com", "TDAT2001", 1);
        rom1 = new Rom();
        rom1.setRomID("KAUD");
        rom1.setRomNavn("KAUD");
        rom1.setEtasje(3);
        rom1.setType(3);
        rom1.setStorrelse(100);
        rom1.setAntStolplasser(102);
        
        rom2 = new Rom();
        rom2.setRomID("KAUD2");
        rom2.setRomNavn("KAUD2");
        rom2.setEtasje(3);
        rom2.setType(3);
        rom2.setStorrelse(100);
        rom2.setAntStolplasser(102);
        
        rom3 = new Rom();
        rb1 = new RomBestilling();
             
        fag1 = new Fag();
        fag1.setFagID("TDAT2004");
        fag1.setNavn("Algdat");
        
        fag2 = new Fag();
        fag2.setFagID("TDAT2001");
        
        kEvent1 = new KalenderEvent();
        kEvent = new KalenderEvent();
        kEvent.setEierNavn("Ola Nilsson");
        kEvent.setEpost("ola@hotmail.com");
        kEvent.setType(2);
        kEvent.setStartTid(fraDato);
        kEvent.setSluttTid(tilDato);
        kEvent.setTittel("Lekser");
        kEvent.setRom("GR114");
        kEvent.setFag("");
        kEvent.setNotat("Må gjøre");
        kEvent.setPrivat(true);
        kEvent.setTilhorerEvent(2);
        
        klasse = new Klasse();
        klasse.setNavn("2.ing");
        
        brukerB = new BrukerB();
        b = new Bruker(); 
        b.setFornavn("Ola");
        b.setEtternavn("Aas");
        b.setEpost("ola@hotmail.com");
        b.setTilgangsniva(0);
        b.setPassord("Passord79##");
        
        bruker = new Bruker();
        bruker.setFornavn("Per");
        bruker.setEtternavn("Aas");
        bruker.setEpost("per@hotmail.com");
        bruker.setNotat("Jeg liker fotball");
        bruker.setTilgangsniva(3);
        bruker.setPassord("Passord89##");
        
    }
    
     @Test
    public void test_getBruker(){
        String epost = "henrik_bjorkheim@hotmail.com";
        String brukar = "Ansatt: Henrik Pus,  Epost: henrik_bjorkheim@hotmail.com";
               
        try{
           b = dbc.getBruker(epost);
           assertEquals(b.toString(),brukar);
        } catch(NullPointerException e){
            System.out.println("Nullpointer..");
        }        
    }
        
    @Test
    public void test_leggTilBruker(){
        assertTrue(dbc.leggTilBruker(bruker));
    }
    
    @Test
    public void test_leggTilBrukerFalse(){
        assertFalse(dbc.leggTilBruker(b));
    }
    
    @Test
    public void test_slettBruker(){
        assertTrue(dbc.slettBruker(bruker));
    }
    
    @Test
    public void test_slettBrukerFalse(){
        assertFalse(dbc.slettBruker(bruker));
    }
    
    @Test
    public void test_oppdaterBruker(){
        b.setEtternavn("Nilsson");
        assertTrue(dbc.oppdaterBruker(b));
    }
    
    @Test
    public void test_oppdaterBrukerFalse(){
        assertFalse(dbc.oppdaterBruker(bruker));
    }
     
    @Test
    public void test_sjekkPassord(){
        String passord = "Passord79##";
        assertTrue(dbc.sjekkPassord("ola@hotmail.com", passord));
    }
    
    @Test
    public void test_sjekkPassordFalse(){
        String passord = "Passord78##";
        assertFalse(dbc.sjekkPassord("ola@hotmail.com", passord));
    }
      
    @Test
    public void test_getAlle(){
        assertEquals(dbc.getAlleRom().size(), 16);
        assertEquals(dbc.getAlleFag().size(), 5);
    
        int ant = 16;
        assertEquals(dbc.getAlleBrukere().size(), ant);
    }
    
    @Test
    public void test_sok(){
        assertEquals(dbc.getAnsattSok("test", "test", "test").size(), 0);
        
        assertEquals(dbc.getFagSok("TDAT2001", "TDAT2001").size(), 1);
        
        assertEquals(dbc.getRomSok("KAUD", "KAUD").size(), 1);
        
        assertEquals(dbc.getStudentSok("Ola", "Ola", "Ola").size(),1);
    
        assertEquals(dbc.getKlasseSok("TDAT").size(), 0);
        
        assertEquals(dbc.getKlasseSok("TADT").size(), 0);
    }
    
    @Test
    public void testFjernKalenderEvent(){
        kEvent.setId(inkrementere());
        assertTrue(dbc.fjernKalenderEvent(kEvent));
    }
    
    @Test
    public void testleggTilKalenderEvent(){
        assertTrue(dbc.leggTilEvent(kEvent));
    }
  
    @Test
    public void test_AbonnementAB(){
        assertTrue(dbc.leggTilAbonemennt(ab));
        //assertTrue(dbc.slettAbonemennt(ab));
    }
    @Test
    public void test_slettAbonnement1(){
        assertTrue(dbc.slettAbonemennt(ab));
    }
    
    @Test
    public void test_slettAbonnementFalse(){
        assertFalse(dbc.slettAbonemennt(ac));
    }
    
    @Test
    public void test_abonnementAC(){
        assertTrue(dbc.leggTilAbonemennt(ac));
        assertTrue(dbc.slettAbonemennt(ac));
    }
    
    @Test
    public void test_getRomFraNavn(){
        assertEquals(dbc.getRomFraNavn(rom1).size(), 1);
        assertEquals(dbc.getRomFraNavn("KAUD").size(), 1);
        
    }
    
    @Test
    public void test_slettRom(){
        //lærer tilknyttet fag
        assertTrue(dbc.slettRom(rom2));
    }
    
    @Test
    public void test_leggTilOppdater(){
        //lærer tilknyttet fag
        assertTrue(dbc.leggTilRom(rom2));
       
    }
 
    @Test
    public void testRomBestillingMapper(){
        brukerB.setEpost("test1@aol.com");
        listRB = new ArrayList<RomBestilling>();
        listRB = dbc.getAlleBestillingerFraBruker(brukerB);
        listRB = dbc.getReserverteRom(kEvent);
        //System.out.println(listRB.get(0).getTilhorerEvent());
        //rb1 = dbc.getRomBooking(kEvent);
        //inkrementere2();
        //System.out.println(rb1.toString());
        
    }
    
    @Test
    public void testAbonemenntMapper(){
        brukerB.setEpost("test2@aol.com");
        listAbo = new ArrayList<Abonemennt>();
        listAbo = dbc.getAbonemenntFraBruker(brukerB);
        listAbo = dbc.getBrukerAbonnement("test1@aol.com");
    }
    
    @Test
    public void testRomMapper(){
        rom3.setRomID("KAUD");
        ArrayList<String> innholdRom1 = new ArrayList<String>();
        innholdRom1.add("prosjekt tor");
        innholdRom1.add("ape katter");
        innholdRom1.add("tv tv");
        innholdRom1.add("tv tv");
        innholdRom1.add("tv tv");
        innholdRom1.add("tv tv");
        innholdRom1.add("tv tv");
        innholdRom1.add("tv tv");
        innholdRom1.add("tv tv");
        innholdRom1.add("tv tv");
        
        rom1.setInnhold(innholdRom1);
        listRom = new ArrayList<Rom>();
        listRom = dbc.getRomTypeStorrelse(rom3);
        
        listRom = dbc.getRom0Param(rom1, kEvent, true, true);
        listRom = dbc.getRom0Param(rom1, kEvent, true, false);
        listRom = dbc.getRom0Param(rom1, kEvent, false, true);
        listRom = dbc.getRom0Param(rom1, kEvent, false, false);
        
        listRom = dbc.getRom1Param(rom1, kEvent, true, true);
        listRom = dbc.getRom1Param(rom1, kEvent, true, false);
        listRom = dbc.getRom1Param(rom1, kEvent, false, true);
        listRom = dbc.getRom1Param(rom1, kEvent, false, false);
        
        listRom = dbc.getRom2Param(rom1, kEvent, true, true);
        listRom = dbc.getRom2Param(rom1, kEvent, true, false);
        listRom = dbc.getRom2Param(rom1, kEvent, false, true);
        listRom = dbc.getRom2Param(rom1, kEvent, false, false);
        
        listRom = dbc.getRom3Param(rom1, kEvent, true, true);
        listRom = dbc.getRom3Param(rom1, kEvent, true, false);
        listRom = dbc.getRom3Param(rom1, kEvent, false, true);
        listRom = dbc.getRom3Param(rom1, kEvent, false, false);
        
        listRom = dbc.getRom4Param(rom1, kEvent, true, true);
        listRom = dbc.getRom4Param(rom1, kEvent, true, false);
        listRom = dbc.getRom4Param(rom1, kEvent, false, true);
        listRom = dbc.getRom4Param(rom1, kEvent, false, false);
                
   
    }
    
    @Test
    public void testKalenderEventMapper(){
        rom3.setRomID("KA-SA235");
        brukerB.setEpost("ola@hotmail.com");
        listkEvent = new ArrayList<KalenderEvent>();
        listkEvent = dbc.getKalenderEventEier(brukerB);
        listkEvent = dbc.getKalenderEventRomID(rom3);
        listkEvent = dbc.getKalenderEventHidden(kEvent);
        listkEvent = dbc.getAlleEventsFraBruker(brukerB);
        assertEquals(listkEvent.size(), 1);
    }
    
    @Test
    public void testKlasseMapper(){
        listKl = new ArrayList<Klasse>();
        listKl = dbc.getAlleKlasser();
    }
    
    @Test
    public void testRom(){
        rom4 = new Rom();
        rom4 = dbc.getRom(rom1);
        //assertEquals(dbc.getRom(rom1), rom4);
        assertTrue(dbc.erRomLedig(kEvent));
        
        
    }
    
    @Test
    public void testOpptattRom(){
        assertTrue(dbc.erRomLedig(kEvent1));
    }
    
    @Test
    public void testLeggTilBooking(){
        kEvent1.setRom("KAUD");
        kEvent1.setStartTid(fraDato);
        kEvent1.setSluttTid(tilDato);
        kEvent1.setEpost("ola@hotmail.com");
        kEvent1.setTilhorerEvent(2);
        //assertTrue(dbc.leggTilBooking(kEvent1));
    }
    
    @After
    public void tearDown() {
        
    }
           
    @AfterClass
    public static void tearDownClass() {
                       
    }
      
}
