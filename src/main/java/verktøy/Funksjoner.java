/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verktøy;

import java.util.List;
import beans.*;

import java.util.ArrayList;
import java.util.Arrays;
import javax.faces.application.ProjectStage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import service.Service;

/**
 *
 * @author Sigrid
 */
public class Funksjoner {
  public List liste=new ArrayList();

  

  public ArrayList<Object> getAlleSokeTreff(String s, Service si, String melding){
        
        ArrayList<Object> liste=new ArrayList();
        ArrayList<Boolean> checkboxValues = new ArrayList<Boolean>();
        
        boolean ansatt = false;
        boolean student = false;
        boolean rom = false;
        boolean fag = false;
        boolean klasse = false;
        boolean checked = false;
        
        for (Boolean checkboxValue : checkboxValues) {
          if(checkboxValue.toString().equals(ansatt)) ansatt = true;
          if(checkboxValue.toString().equals(student)) student = true;
          if(checkboxValue.toString().equals(rom)) rom = true;
          if(checkboxValue.toString().equals(klasse)) klasse = true;
          if(checkboxValue.toString().equals(checked)) checked = true;
        }
        
        if(!s.isEmpty()) {
            if(ansatt) {
                liste.addAll(sokBruker(si, s));
            }
            if(student) {
                liste.addAll(sokBruker(si, s));
            }
            if(rom) {
                liste.addAll(sokRom(si, s));
            }
            if(fag) {
                liste.addAll(sokFag(si, s));
            }
            if(klasse) {
                liste.addAll(sokFag(si, s));
            }
            if(!checked) {
                liste.addAll(sokBruker(si, s));
                liste.addAll(sokRom(si, s));
                liste.addAll(sokFag(si, s));
            }
        }
 
        return liste; 

  }
  
  public ArrayList<Bruker> sokBruker(Service si, String s) {
      ArrayList<Bruker> brukerListe = new ArrayList<Bruker>();
      brukerListe.addAll(si.getBrukerSok("%" + s + "%", "%" + s + "%", "%" + s + "%"));
      return brukerListe;
  }
  
  public ArrayList<Rom> sokRom(Service si, String s) {
      ArrayList<Rom> romListe = new ArrayList<Rom>();
      romListe.addAll(si.getRomSok("%" + s + "%", "%" + s + "%"));
      return romListe;
      
  } 
  
  public ArrayList<Fag> sokFag(Service si, String s) {
      ArrayList<Fag> fagListe = new ArrayList<Fag>();
      fagListe.addAll(si.getFagSok("%" + s + "%", "%" + s + "%"));
      return fagListe;
  }
  
}
