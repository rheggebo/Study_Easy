/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.ui;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ui.FormVelgRom;

/**
 *
 * @author Ane
 */
public class FormVelgRomTest {
    
    FormVelgRom fVR;
    Date fraDato;
    int fraTid;
    
    @BeforeClass
    public static void setUpClass() {
    }
       
    @Before
    public void setUp(){ 
        
        SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strdate1 = "2016-01-23";
        fraTid = 1200;
        
        try {
            fraDato = dateformat2.parse(strdate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }  
        
        fVR = new FormVelgRom();
        fVR.setFraDato(fraDato);
        fVR.setFraTid(fraTid);
        fVR.setRomId("GR108");
        fVR.setVarighet(2);
        
    }
    
    @Test
    public void testMetoder(){
        assertEquals(fVR.getRomId(),"GR108");
        assertEquals(fVR.getFraDato(), fraDato);
        assertEquals(fVR.getFraTid(), fraTid);
        assertEquals(fVR.getVarighet(),2);
        assertEquals(fVR.getTiderList().size(), 18);
        assertEquals(fVR.getVarighetList().size(), 3);
    }
    
    @After
    public void tearDown() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    // @Test
    // public void hello() {}
}
