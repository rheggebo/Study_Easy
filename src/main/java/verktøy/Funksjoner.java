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

  public ArrayList<Object> getAlleSokeTreff(String s, Service si, String[] checkBoxValues){
        
        ArrayList<Object> liste=new ArrayList();
        
        boolean ansatt = false;
        boolean student = false;
        boolean rom = false;
        boolean fag = false;
        boolean klasse = false;
        boolean checked = false;
        
        if(checkBoxValues != null) {
          for (String checkboxValue : checkBoxValues) {
          if(checkboxValue.equals("Ansatt")) ansatt = true; checked = true;
          if(checkboxValue.equals("Student")) student = true; checked = true;
          if(checkboxValue.equals("Fag")) fag = true; checked = true;
          if(checkboxValue.equals("Rom")) rom = true; checked = true;
          if(checkboxValue.equals("Klasse")) klasse = true; checked = true;
        }
    }
        
        if(!s.isEmpty()) {
            if(ansatt) {
                liste.addAll(sokAnsatt(si, s));
            }
            if(student) {
                liste.addAll(sokStudent(si, s));
            }
            if(rom) {
                liste.addAll(sokRom(si, s));
            }
            if(fag) {
                liste.addAll(sokFag(si, s));
            }
            if(klasse) {
                liste.addAll(sokKlasse(si, s));
            }
            if(!checked) {
                liste.addAll(sokAnsatt(si, s));
                liste.addAll(sokStudent(si, s));
                liste.addAll(sokRom(si, s));
                liste.addAll(sokFag(si, s));
                liste.addAll(sokKlasse(si, s));
            }
        }
        
        return liste; 

  }
  
  public ArrayList<Bruker> sokStudent(Service si, String s) {
      ArrayList<Bruker> studentListe = new ArrayList<Bruker>();
      studentListe.addAll(si.getStudentSok("%" + s + "%", "%" + s + "%", "%" + s + "%"));
      return studentListe;
  }
  
  public ArrayList<Bruker> sokAnsatt(Service si, String s) {
      ArrayList<Bruker> ansattListe = new ArrayList<Bruker>();
      ansattListe.addAll(si.getAnsattSok("%" + s + "%", "%" + s + "%", "%" + s + "%"));
      return ansattListe;
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
  
  public ArrayList<Klasse> sokKlasse(Service si, String s) {
      ArrayList<Klasse> klasseListe = new ArrayList<Klasse>();
      klasseListe.addAll(si.getKlasseSok("%" + s + "%"));
      return klasseListe;
  }
  public Object abonnement(String ab, Service si){
      
      return null;
  }
  
}
