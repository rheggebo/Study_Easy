/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.beans;

import beans.Passord;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;

/**
 *
 * @author Sindre
 */
public class PassordTest {
    Passord pord;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Koden her eksekveres f�r f�rste test i klassen
        System.out.println("JUNIT: før PassordTest-klassen.");
        
    }
    
    @Before
    public void setUp() throws Exception {
        // Koden her eksekveres f�r hver av testmetodene i klassen
        pord = new Passord();
        pord.setPassord("Passord#£5");
        pord.setPassord1("Passord#£5");
        pord.setPassord2("Passord#£5");
        pord.setGenerert(true);
    }
    
    @Test
    public void tester_getmetoder (){
        assertSame(pord.getPassord(),pord.getPassord1());
        assertTrue(pord.isGenerert());
    }
    
    @Test
    public void tester_validering(){
        /*
        * Tester valideringsmetoden i passord-klassen
        */
        
        Passord pass = new Passord();
        
        
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
