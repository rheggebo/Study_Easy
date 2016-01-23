/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.email;

import email.Email;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import javax.mail.MessagingException;

/**
 *
 * @author Sindre
 */
public class EmailTest {
    
    Email test;
    
    String mottaker;
    String tema;
    String melding;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Koden her eksekveres f�r f�rste test i klassen
        System.out.println("JUNIT: før EmailTest-klassen.");
        
    }
    
    @Before
    public void setUp() throws Exception {
        // Koden her eksekveres f�r hver av testmetodene i klassen
        test = new Email();
        
        mottaker = "studyeasytest@gmail.com";
        tema = "test";
        melding = "spamspamspam";
    }
    
    @Test
    public void tester_email (){
        assertEquals(test.sendEpost(mottaker, tema, melding), true);  
    }
    
    @Test
    public void tester_email2() throws Exception{
        assertEquals(test.sendEpost(null, null, melding), false);
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
