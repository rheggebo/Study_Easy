/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Bruker;
import java.util.List;

/**
 *
 * @author stein.erik.bjornnes
 */
public interface DBInterface {
    
    public Bruker getBrukerN(String brukernavn);
    
    public Bruker getBrukerE(String email);
    
    public boolean loggInn(String brukernavn, String passord);
    
    public boolean oppdaterBruker(Bruker b);
    
    public boolean slettBruker(Bruker b);
    
    public boolean leggTilBruker(Bruker b);
    
    public List<Bruker> hentAlleBrukere();
    
}
