/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.beans;

import beans.KalenderEvent;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.sql.Timestamp;

/**
 *
 * @author Sindre
 */
public class KalenderEventTest {
    
    KalenderEvent kEvent;
    Timestamp fraTid;
    Timestamp tilTid;
    
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Koden her eksekveres f�r f�rste test i klassen
        System.out.println("JUNIT: før KalenderEventTest-klassen.");
        
    }
    
    @Before
    public void setUp() throws Exception {
        // Koden her eksekveres f�r hver av testmetodene i klassen
       
        fraTid = Timestamp.valueOf("2016-1-22 09:15:00.0");
        tilTid = Timestamp.valueOf("2014-1-22 12:00:00.0");
        
        kEvent = new KalenderEvent();
        kEvent.setId(0001);
        kEvent.setEpost("test1@aol.com"); //eierID som i e-post
        kEvent.setEierNavn("Per Hansen");
        kEvent.setStartTid(fraTid);
        kEvent.setSluttTid(tilTid);
        kEvent.setRom("KAUD");
        kEvent.setFag("AlgDat");
        kEvent.setType(2);
        kEvent.setPrivat(false);
        kEvent.setNotat("Hallaballa :) ");
        kEvent.setTittel("Stakk");
        kEvent.setTilhorerEvent(16);
        
        
    }
    
    @Test
    public void tester_getmetoder (){
        assertEquals(kEvent.getId(), 0001);
        assertEquals(kEvent.getEpost(), "test1@aol.com");
        assertEquals(kEvent.getEierNavn(), "Per Hansen");
        assertNotSame(kEvent.getStartTid(), kEvent.getSluttTid());
        assertEquals(kEvent.getRom(),"KAUD");
        assertEquals(kEvent.getFag(),"AlgDat");
        assertEquals(kEvent.getType(),2);
        assertFalse(kEvent.isPrivat());
        assertEquals(kEvent.getNotat(),"Hallaballa :) ");
        assertEquals(kEvent.getTittel(), "Stakk");
        assertEquals(kEvent.getTilhorerEvent(), 16);
        
        String str = "1 Stakk " + kEvent.getStartTid().toString();
        assertEquals(kEvent.toString(), str);
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
