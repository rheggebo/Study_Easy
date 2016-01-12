/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verktøy;

import java.util.List;
import beans.Sok;
import java.util.ArrayList;
import service.ServiceImpl;

/**
 *
 * @author Sigrid
 */
public class Funksjoner {
  ServiceImpl si=new ServiceImpl();
  
  public ArrayList<Object> getAlleSokeTreff(String s){
        ArrayList<Object> liste=new ArrayList<Object>();
        for(int i=0; i<si.getAlleBrukere().size();i++){
            if(s.equals(si.getAlleBrukere().get(i).getNavn())){
                     liste.add(si.getAlleBrukere().get(i));        
            }
        }
        for(int i=0;i<si.getAlleRom().size();i++){
            if(s.equals(si.getAlleRom().get(i).getRomNavn())){
                liste.add(si.getAlleRom().get(i));
            }
        }
        for(int i=0;i<si.getAlleFag().size();i++){
            if(s.equals(si.getAlleFag().get(i).getNavn())){
                liste.add(si.getAlleRom().get(i));
            }
        }
        for(int i=0;i<si.getAlleRom().size();i++){
            if(s.equals(si.getAlleRom().get(i).getRomNavn())){
                liste.add(si.getAlleRom().get(i));
            }     
        }
        
     
      return liste;
    }
  public ArrayList<Object> getListe(){
      ArrayList liste=new ArrayList();
      liste.add("Sigrid");
      liste.add("Kasper");
      return liste;
  }
}
