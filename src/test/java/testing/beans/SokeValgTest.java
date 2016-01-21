/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.beans;

import beans.SokeValg;
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
public class SokeValgTest {
    
    SokeValg sV;
    
    @BeforeClass
    public static void setUpClass() {
    }
       
    @Before
    public void setUp() {
        sV = new SokeValg();
    }
    
    @Test
    public void test_alt(){
        sV.setResultat("hei");
        assertEquals(sV.getResultat(), "hei");
        
        sV.setResultat("mamma");
        assertEquals(sV.getResultat(), "mamma");
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
