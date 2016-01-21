/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.beans;

import beans.SlettAbonnementValg;
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
public class SlettAboValgTest {
    
    SlettAbonnementValg sA;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
        sA = new SlettAbonnementValg();
    }
    
    @Test
    public void test_alt(){
        sA.setResultat("TDAT2001");
        assertEquals(sA.getResultat(), "TDAT2001");
        
        sA.setResultat("TDAT2002");
        assertEquals(sA.getResultat(), "TDAT2002");
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
