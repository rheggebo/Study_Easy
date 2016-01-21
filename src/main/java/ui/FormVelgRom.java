/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Sindre
 */
public class FormVelgRom {
    @NotNull
    private Date fraDato;
    private int fraTid;
    private int varighet;
    private String romId;

    public String getRomId() {
        return romId;
    }

    public void setRomId(String romId) {
        this.romId = romId;
    }

    public Date getFraDato() {
        return fraDato;
    }

    public void setFraDato(Date fraDato) {
        this.fraDato = fraDato;
    }

    public int getFraTid() {
        return fraTid;
    }

    public void setFraTid(int fraTid) {
        this.fraTid = fraTid;
    }

    public int getVarighet() {
        return varighet;
    }

    public void setVarighet(int varighet) {
        this.varighet = varighet;
    }
    
    public List getTiderList(){
        String[] s = {"0600","0700","0800","0900","1000","1100","1200","1300","1400","1500","1600","1700","1800","1900","2000","2100","2200","2300"};
        return Arrays.asList(s);
    }
    
    public List getVarighetList(){
        String[] s = {"1","2","3"};
        return Arrays.asList(s);
    }
}
