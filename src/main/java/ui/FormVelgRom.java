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

/**
 *
 * @author Sindre
 */
public class FormVelgRom {
    private Date fraDato;
    private Time fraTid;
    private int varighet;

    public Date getFraDato() {
        return fraDato;
    }

    public void setFraDato(Date fraDato) {
        this.fraDato = fraDato;
    }

    public Time getFraTid() {
        return fraTid;
    }

    public void setFraTid(Time fraTid) {
        this.fraTid = fraTid;
    }

    public int getVarighet() {
        return varighet;
    }

    public void setVarighet(int varighet) {
        this.varighet = varighet;
    }
    
    public List getTiderList(){
        String[] s = {"06:00","07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};
        return Arrays.asList(s);
    }
    
    public List getVarighetList(){
        String[] s = {"06:00","07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};
        return Arrays.asList(s);
    }
}
