/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.beans;

import beans.RomBestilling;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ane
 */
public class RomBestillingTest {
    
    RomBestilling romB;
    Timestamp startDato;
    Timestamp sluttDato;
        
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
        startDato = Timestamp.valueOf("2016-01-22 10:15:00.0");
        sluttDato = Timestamp.valueOf("2016-01-22 12:00:00.0");
       
        romB = new RomBestilling();        
        romB.setEierId("test4@aol.com");
        romB.setRomId("GR113");
        romB.setTilhorerEvent(2);
        romB.setStartDato(startDato);
        romB.setSluttDato(sluttDato);
        romB.setBestillingsID(16);
        romB.setKlokkesjekk(true);
        romB.setSjekketInn(false);
        
    }
    
    @Test
    public void testing(){
        assertEquals(romB.getEierId(), "test4@aol.com");
        assertEquals(romB.getRomId(), "GR113");
        assertEquals(romB.getTilhorerEvent(), 2);
        assertEquals(romB.getStartDato(), startDato);
        assertEquals(romB.getSluttDato(), sluttDato);
        assertEquals(romB.isSjekketInn(), false);
        assertEquals(romB.getBestillingsID(), 16);
        assertEquals(romB.isKlokkesjekk(), true);
    }
    
    @Test
    public void testToString(){
        String str = "Rom: GR113 Start-tid: 2016-01-22 10:15:00.0 Slutt-tid: 2016-01-22 12:00:00.0";
        assertEquals(romB.toString(), str);
    }
    
    @After
    public void tearDown() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
