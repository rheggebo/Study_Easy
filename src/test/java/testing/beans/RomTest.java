/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.beans;

import beans.Rom;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;


/**
 *
 * @author Sindre
 */
public class RomTest {
    
    Rom rom;
    Rom rom1;
    private ArrayList innhold;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Koden her eksekveres f�r f�rste test i klassen
        System.out.println("JUNIT: før RomTest-klassen.");
        
    }

    @Before
    public void setUp() throws Exception {
        // Koden her eksekveres f�r hver av testmetodene i klassen
     
        rom = new Rom();
        rom.setRomID("KAUD");
        rom.setRomNavn("KAUD");
        rom.setEtasje(3);
        rom.setType(3);
        rom.setStorrelse(100);
        rom.setAntStolplasser(40);
        
        rom1 = new Rom();
        rom1.setRomID("Labben");
        rom1.setRomNavn("Labben");
        rom1.setEtasje(2);
        rom1.setType(2);
        rom1.setStorrelse(200);
        
        innhold = new ArrayList();
        innhold.add("Prosjektor");
        innhold.add("Tavle");
        rom.setInnhold(innhold);
        
                  
    }

    @Test
    public void tester_getmetoder (){
        /**
         * Test set- og get-metoder
         **/
        assertEquals(rom.getRomID(),"KAUD");
        assertEquals(rom.getRomNavn(),"KAUD");
        assertEquals(rom.getEtasje(), 3);
        assertEquals(rom.getType(), 3);
        assertEquals(rom.getStorrelse(),100);
        assertEquals(rom.getAntStolplasser(),40);
        
    }
            
    @Test
    public void tester_Liste(){
        assertEquals(rom.getInnhold(), innhold);
        
    }
    
    @Test
    public void test_Sammenligning(){
        /**
         *tester med forandring, 
         * eks: finn en str p� et rom for � s� forandre det
         * og s� finne den forandra st�rrelsen 
         */
       rom1.setStorrelse(100);
       
       assertSame(rom1.getStorrelse(), rom.getStorrelse());
       assertSame(rom.getType(), rom.getEtasje());
       
    }
    
    @Test
    public void test_RomObjekter (){
        /*
        *Tester at de to rom-objektene ikke er like.
        */
               
       /* System.out.println(rom1.getEtasje());
        System.out.println(rom.getEtasje());
        */
        
        assertThat(rom.getEtasje(), not(rom1.getEtasje())); //tester at resultatet blir forskjellig
        
        rom1.setRomID("KAUD");
        
        assertEquals(rom.getRomID(), rom1.getRomID());
        
        rom.setType(1);
        
        assertThat(rom.getType(), not(rom1.getType()));
        
    }
    /*
    @Test(expected = NullPointerException.class)
    public void test_at_koden_som kj�res_kaster_en_NullPointerException() {
        // Kode som gj�r noe annet
    }
    */
    @Test
    public void test_toString(){
        String rommet = "Rom: KAUD KAUD etasje: 3 størrelse: 100 m2 sitteplasser: 40";
        assertEquals(rom.toString(), rommet);
    }

    @After
    public void tearDown() throws Exception {
        // Koden her kj�res etter hver test
      
        innhold.clear();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Koden her kj�rer etter alle testmetodene i klassen er ferdig
        
    }
}
