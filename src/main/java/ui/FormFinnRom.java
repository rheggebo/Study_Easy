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
 * @author Henrik
 */
public class FormFinnRom {
    private String tittel;
    private String type;
    private Date fraDato;
    private Time fraTid;
    private int varighet;

    public List getTypeList(){
        String[] s = {"Privat","Forelesning","Møte"};
        return Arrays.asList(s);
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public void setFraDato(Date fraDato) {
        this.fraDato = fraDato;
    }

    public void setFraTid(Time fraTid) {
        this.fraTid = fraTid;
    }

    public void setVarighet(int varighet) {
        this.varighet = varighet;
    }
    
    

    public String getType() {
        return type;
    }

    public Date getFraDato() {
        return fraDato;
    }

    public Time getFraTid() {
        return fraTid;
    }

    public int getVarighet() {
        return varighet;
    }
    
    


}
