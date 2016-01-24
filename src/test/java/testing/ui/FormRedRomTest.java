/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.ui;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ui.FormRedRom;

/**
 *
 * @author Ane
 */
public class FormRedRomTest {
    
    FormRedRom frr;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
        frr = new FormRedRom();
        frr.setRomId("GR114");
        frr.setRomNavn("Grupperom 114");
        frr.setRomType(2);
        frr.setRomStr(8);
        frr.setAntSittePl(6);
        frr.setAntSkjermer(0);
        frr.setAntTavler(1);
        frr.setAntProsjektorer(0);
    }
    
    @Test
    public void tester(){
        assertEquals(frr.getRomID(), "GR114");
        assertEquals(frr.getRomNavn(), "Grupperom 114");
        assertEquals(frr.getRomStr(), 8);
        assertEquals(frr.getRomType(), 2);
        assertEquals(frr.getAntSittePl(), 6);
        assertEquals(frr.getAntTavler(), 1);
        assertEquals(frr.getAntSkjermer(), 0);
        assertEquals(frr.getAntProsjektorer(), 0);
                
    }
    
    @After
    public void tearDown() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
}
