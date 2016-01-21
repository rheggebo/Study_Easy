/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.beans;

import beans.NyEvent;
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
public class NyEventTest {
    
    NyEvent ne;
    
    @BeforeClass
    public static void setUpClass() {
    }
     
    @Before
    public void setUp() {
        ne = new NyEvent();
        ne.setTittel("Matpause");
        ne.setRom("GR115");
        ne.setFag("");
        ne.setType(2);
        ne.setPrivat(false);
        ne.setNotat("Sulten");
    }
    
    @Test
    public void testGetMetoder(){
        
        assertEquals(ne.getTittel(),"Matpause");
        assertEquals(ne.getRom(),"GR115");
    
        ne.setRom("GR114");
        assertEquals(ne.getRom(),"GR114");
        assertEquals(ne.getFag(),"");
        assertEquals(ne.getType(),2);
        
        assertEquals(ne.isPrivat(),false);
        
        ne.setPrivat(true);
        assertEquals(ne.isPrivat(),true);
        assertEquals(ne.getNotat(),"Sulten");
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
