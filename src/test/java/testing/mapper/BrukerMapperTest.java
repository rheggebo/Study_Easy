/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.mapper;

import email.Email;
import mapper.BrukerMapper;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Sindre
 */
public class BrukerMapperTest {
    String epost;
    String passord;
    String type;
    String fornavn;
    String etternavn;
    
    BrukerMapper test;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Koden her eksekveres f�r f�rste test i klassen
        System.out.println("JUNIT: før BrukerMapperTest.");
        
    }
    
    @Before
    public void setUp() throws Exception {
        // Koden her eksekveres f�r hver av testmetodene i klassen
        test = new BrukerMapper();
        epost= "studyeasytest@gmail.com";
        passord = "test";
        type = "spamspamspam";
        fornavn="Per";
        etternavn="Normann";    
       
    }
    
    @Test
    public void tester_brukermapper (){
                       
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
