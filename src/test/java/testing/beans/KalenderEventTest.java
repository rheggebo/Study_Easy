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
import java.util.Date;

/**
 *
 * @author Sindre
 */
public class KalenderEventTest {
    
    KalenderEvent kEvent;
    Date fraDato;
    Date tilDato;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Koden her eksekveres f�r f�rste test i klassen
        System.out.println("JUNIT: før KalenderEventTest-klassen.");
        
    }
    
    @Before
    public void setUp() throws Exception {
        // Koden her eksekveres f�r hver av testmetodene i klassen
       
        fraDato = new Date(2016-1-1);
        tilDato = new Date(2014-1-22);
        
        kEvent = new KalenderEvent();
        kEvent.setId(0001);
        kEvent.setEier("test1@aol.com"); //eierID som i e-post
        kEvent.setEierNavn("Per Hansen");
        kEvent.setStartDato(fraDato);
        kEvent.setSluttDato(tilDato);
        kEvent.setRom("KAUD");
        kEvent.setFag("AlgDat");
        kEvent.setType(2);
        kEvent.setPrivat(false);
        kEvent.setNotat("Hallaballa :) ");
        
        
    }
    
    @Test
    public void tester_getmetoder (){
        assertEquals(kEvent.getId(), 0001);
        assertEquals(kEvent.getEier(), "test1@aol.com");
        assertEquals(kEvent.getEierNavn(), "Per Hansen");
        assertNotSame(kEvent.getStartDato(), kEvent.getSluttDato());
        assertEquals(kEvent.getRom(),"KAUD");
        assertEquals(kEvent.getFag(),"AlgDat");
        assertEquals(kEvent.getType(),2);
        assertFalse(kEvent.isPrivat());
        assertEquals(kEvent.getNotat(),"Hallaballa :) ");
        
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
