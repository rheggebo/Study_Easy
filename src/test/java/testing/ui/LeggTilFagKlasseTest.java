/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.ui;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ui.LeggTilFagKlasse;

/**
 *
 * @author Ane
 */
public class LeggTilFagKlasseTest {
    
    LeggTilFagKlasse lfk;
    ArrayList kListe;
    ArrayList fListe;
    
    @BeforeClass
    public static void setUpClass() {
    }
        
    @Before
    public void setUp() {
        lfk = new LeggTilFagKlasse();
        lfk.setFagID("TDAT2001");
        lfk.setKlasseID("TDATH13");
        
        kListe = new ArrayList();
        fListe = new ArrayList();
        
        String fag = lfk.getFagID();
        String klasse = lfk.getKlasseID();
        
        lfk.addFagListe(fag);
        lfk.addKlasseListe(klasse);
        
    }
    
    @Test
    public void testMetoder(){
        assertEquals(lfk.getFagID(), "TDAT2001");
        assertEquals(lfk.getKlasseID(), "TDATH13");
        assertEquals(lfk.getFagListe().size(), 1);
        assertEquals(lfk.getKlasseListe().size(), 1);
        
    }
    
    @After
    public void tearDown() {
        kListe.clear();
        fListe.clear();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
}
