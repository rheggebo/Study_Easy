/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verktøy;

import java.util.List;
import beans.*;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import service.Service;

/**
 *
 * @author Sigrid
 */
public class Funksjoner {
  public List liste=new ArrayList();

  

  public ArrayList<Object> getAlleSokeTreff(String s, Service si){
        ArrayList<Object> liste=new ArrayList();
        ArrayList<Object> testListe=new ArrayList();
        ArrayList<Object> testOut=new ArrayList();
        testListe.add("Sigrid");
        testListe.add("Sigrid");
        testListe.add("Kasper");
        Bruker b = new Bruker();
        Bruker bruker = new Bruker();
        Rom r=new Rom();
        Rom rom=new Rom();
        
         
      /*for(int i=0; i<si.getAlleBrukere().size();i++){
            liste.add(si.getAlleBrukere().get(i));
        }*/
        
        String epost = "test1@aol.com";
        bruker.setEpost(epost);
        
        b = si.hentBruker(bruker);
        if(b.getFornavn() != null) {
            liste.add("Fant bruker " + b.getFornavn() + " på epost " + epost + " !");
        } else {
            liste.add("Fant ingen brukere på epost " + epost + " !");
        }
      if(s!=null){
          
      }
        
       
        return liste; 
        
       /* for(int i=0; i<testListe.size();i++){
            if(s.equalsIgnoreCase(testListe.get(i).toString())){
                     testOut.add(testListe.get(i));        
            }         
        }  
      return testOut;*/
    }
  
  
  /*public ArrayList<String> getListe(){
      ArrayList liste=new ArrayList();
      liste.add("Sigrid");
      liste.add("Silje");
      liste.add("Kasper");
      return liste;
  }*/
}
  /*for(int i=0;i<si.getAlleRom().size();i++){
            if(s.equalsIgnoreCase(si.getAlleRom().get(i).getRomNavn())){
                liste.add(si.getAlleRom().get(i));
            }
        }
        for(int i=0;i<si.getAlleFag().size();i++){
            if(s.equalsIgnoreCase(si.getAlleFag().get(i).getNavn())){
                liste.add(si.getAlleRom().get(i));
            }
        }
        for(int i=0;i<si.getAlleRom().size();i++){
            if(s.equalsIgnoreCase(si.getAlleRom().get(i).getRomNavn())){
                liste.add(si.getAlleRom().get(i));
            }     
        }
        */