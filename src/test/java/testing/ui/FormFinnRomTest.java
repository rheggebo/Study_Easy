/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.ui;

import beans.KalenderEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ui.FormFinnRom;

/**
 *
 * @author Ane
 */
public class FormFinnRomTest {
    
    FormFinnRom ffr;
    Date fDato; 
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
        
        SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy-mm-dd");
        String strdate1 = "2016-01-28";
  
        
        try {
            fDato = dateformat2.parse(strdate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        
        ffr = new FormFinnRom();
        ffr.setSkjerm(2);
        ffr.setTavle(1);
        ffr.setProjektor(0);
        ffr.setSitteplasser(8);
        ffr.setStorrelse(10);
        ffr.setTittel("GR114");
        ffr.setType("1");
        ffr.setRomtype("0");
        ffr.setFraDato(fDato);
        ffr.setFraTid(1120);
        ffr.setNotat("hæ");
        ffr.setLegHendelse(false);
        
    }
    
    
    @Test
    public void testGet(){
        assertEquals(ffr.getRomtype(), "0");
        assertEquals(ffr.getRomtypeList().size(), 3);
        assertEquals(ffr.getVarighetList().size(), 3);
        ffr.setVarighet(1);
        assertEquals(ffr.getVarighet(), 1);
        assertEquals(ffr.getFagList().size(), 0);
        
        ffr.addFagListe("TDAT2004");
        assertEquals(ffr.getFagList().size(), 1);
        
        assertEquals(ffr.getProjektor(), 0);
        assertEquals(ffr.getTavle(), 1);
        assertEquals(ffr.getSkjerm(), 2);
        assertEquals(ffr.getSitteplasser(), 8);
        assertEquals(ffr.getStorrelse(), 10);
        assertEquals(ffr.getTittel(), "GR114");
        assertEquals(ffr.getFraDato(), fDato);
        assertEquals(ffr.getType(), "1");
        ffr.setFag("Fag 1");
        assertEquals(ffr.getFag(), "Fag 1");
        assertEquals(ffr.getFraTid(), 1120);
        assertEquals(ffr.getNotat(), "hæ");
        assertEquals(ffr.getTypeList().size(), 4);
        assertEquals(ffr.getUtstyrList().size(), 4);
        assertEquals(ffr.getTiderList().size(), 18);
        assertEquals(ffr.getRomTypeListScrub().size(), 1);
        assertEquals(ffr.isLagHendelse(), false);
         
    }
    
    @After
    public void tearDown() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
}
