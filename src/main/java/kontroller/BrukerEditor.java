/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kontroller;

import service.Brukerservice;
import beans.Bruker;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author Stein-Erik
 */ 
public class BrukerEditor extends PropertyEditorSupport{
    private Brukerservice brukerservice;

    public BrukerEditor(Brukerservice brukerservice) {
        this.brukerservice = brukerservice;
    }
    
    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        String[] t = text.split(" ");
        Bruker v = brukerservice.hentBruker((t[0]));
        setValue(v);
    }
    
    public String getAsString(){
        Bruker b = (Bruker)getValue();
        return b.getEpost();
    }
}