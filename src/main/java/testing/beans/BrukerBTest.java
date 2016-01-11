/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.beans;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;

import beans.BrukerB;
import beans.KalenderEvent;
/**
 *
 * @author Sindre
 */
public class BrukerBTest {


public class SearchEngineJUnitTest {
    //@InjectMocks private PageReader reader;
    KalenderEvent[] kalenderEvent; 
    
    @BeforeClass
    public void setUpClass() throws Exception {
        // Koden her eksekveres før første test i klassen
    }

    @Before
    public void setUp() throws Exception {
        // Koden her eksekveres før hver av testmetodene i klassen
        kalenderEvent=mock(KalenderEvent[].class);
    }

    @Test
    public void test_1 (){

    }
    
    public void test_2 (){
        
    }
 

    public void test_3 (){

    }
    /*
    @Test(expected = NullPointerException.class)
    public void test_at_koden_som kjøres_kaster_en_NullPointerException() {
        // Kode som gjør noe annet
    }
    */

    @After
    public void tearDown() throws Exception {
        // Koden her kjøres etter hver test
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        // Koden her kjører etter alle testmetodene i klassen er ferdig
    }
}

}
