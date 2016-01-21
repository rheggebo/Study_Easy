/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.beans;

import beans.Abonemennt;
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
public class AbonemenntTest {
    
    Abonemennt ab; 
    
    @BeforeClass
    public static void setUpClass() {
    }
            
    @Before
    public void setUp() {
        ab = new Abonemennt("ola@hotmail.com", "TDAT2002", 1);
        
    }
    
    @Test
    public void testGetMetoder(){
        assertEquals(ab.getType(), 1);
        assertEquals(ab.getEierid(), "ola@hotmail.com");
        assertEquals(ab.getAbonererId(), "TDAT2002");
        
    }
    
    @Test
    public void testing(){
        ab.setEierId("test1@aol.com");
        
        assertEquals(ab.getEierid(), "test1@aol.com");
        
        ab.setAbonererId("TDAT2001");
        
        assertEquals(ab.toString(), "TDAT2001");
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
