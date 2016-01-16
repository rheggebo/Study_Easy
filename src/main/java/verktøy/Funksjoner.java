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

  

  public ArrayList<Object> getAlleSokeTreff(String s, Service si){
        
        HttpServletRequest request = null;
        HttpServletRequest response;
        
        ArrayList<Object> liste=new ArrayList();
        ArrayList<Boolean> checkboxValues = new ArrayList<Boolean>();
        for (Boolean checkboxValue : checkboxValues) {
            
        }
        
        /* midlertidig lister med objekter */
        ArrayList<Bruker> brukerListe = new ArrayList<Bruker>();
        ArrayList<Fag> fagListe = new ArrayList<Fag>();
        ArrayList<Rom> romListe = new ArrayList<Rom>();
        
        if(!s.isEmpty()) {
        /* henter data fra databasen utifra søkeord s */

        brukerListe.addAll(si.getBrukerSok("%" + s + "%", "%" + s + "%", "%" + s + "%"));
        fagListe.addAll(si.getFagSok("%" + s + "%", "%" + s + "%"));
        romListe.addAll(si.getRomSok("%" + s + "%", "%" + s + "%"));
        
        }
 
        /* legger til alle lister i objekt hoved listen og returnerer til siden */
        liste.addAll(brukerListe);
        liste.addAll(fagListe);
        liste.addAll(romListe);
        return liste; 

    }
  
}
