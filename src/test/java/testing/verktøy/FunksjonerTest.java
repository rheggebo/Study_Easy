/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.verktøy;

import verktøy.Funksjoner;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import service.Service;

/**
 *
 * @author Sindre
 */
public class FunksjonerTest {
    Funksjoner test;
    Service si;
    String ord;
    String[] checkboxValues;
    
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Koden her eksekveres f�r f�rste test i klassen
        System.out.println("JUNIT: før FunkjsonTest-klassen.");
    }
    
    @Before
    public void setUp() throws Exception {
        // Koden her eksekveres f�r hver av testmetodene i klassen
        test=new Funksjoner();
        si = mock(Service.class);
        ord="Per";
        checkboxValues=null;
        

        
    }
    
    @Test
    public void tester_getAlleSokeTreff (){
        assertEquals(test.getAlleSokeTreff(ord, si, checkboxValues), true);
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
