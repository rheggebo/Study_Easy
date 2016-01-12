/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.beans;

import beans.Sok;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;

/**
 *
 * @author Ane
 */
public class SokTest {
    
    Sok sok;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Koden her eksekveres f�r f�rste test i klassen
        System.out.println("JUNIT: før SokTest-klassen.");
        
    }
    
    @Before
    public void setUp() throws Exception {
        // Koden her eksekveres f�r hver av testmetodene i klassen
        sok = new Sok();
        sok.setSokeord("KAUD");
    }
    
    @Test
    public void tester_getmetoder (){
        assertEquals(sok.getSokeord(), "KAUD");
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
