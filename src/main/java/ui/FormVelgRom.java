/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.sql.Time;
import java.util.Arrays;
import java.util.Calendar;
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
    private String opptatt;

    public String getOpptatt() {
        return opptatt;
    }

    public void setOpptatt(String opptatt) {
        this.opptatt = opptatt;
    }

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
        int tidMin = 6;
        int tidMax = 23;
        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY)+1;
        if (hour<tidMin){
            hour = tidMin;
        }
        String[] s = new String[tidMax-hour+1];
        int time;
        for (int i = 0; i <= tidMax-hour; i++) {
            time = hour+i;
            if (time<10){
                s[i] = "0"+ time+"00"; 
                System.out.println("--------------------"+s[i]);
            } else{
                s[i] = time+"00"; 
            }
        }
        return Arrays.asList(s);
    }
    
    public List getVarighetList(){
        String[] s = {"1","2","3"};
        return Arrays.asList(s);
    }
}
