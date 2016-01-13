/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verktøy;

import java.util.List;
import beans.*;

import java.util.ArrayList;
import service.ServiceImpl;

/**
 *
 * @author Sigrid
 */
public class Funksjoner {
  public List liste=new ArrayList();
  
  ServiceImpl si=new ServiceImpl();
  
  public ArrayList<Object> getAlleSokeTreff(String s){
        ArrayList<Object> liste=new ArrayList();
        ArrayList<Object> testListe=new ArrayList();
        ArrayList<Object> testOut=new ArrayList();
        testListe.add("Sigrid");
        testListe.add("Sigrid");
        testListe.add("Kasper");
        
      /*for(int i=0; i<si.getAlleBrukere().size();i++){
            liste.add(si.getAlleBrukere().get(i));
        }*/
        liste.add("heioghopp");
        
        String epost = "test1@aol.com";
        Bruker bruker = new Bruker();
        bruker.setEpost(epost);
        
        Bruker b = si.hentBruker(bruker);
        
        
       
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