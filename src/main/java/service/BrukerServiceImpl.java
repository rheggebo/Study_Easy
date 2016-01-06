/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.Bruker;
import database.DBConnection;
import database.DBInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Stein-Erik
 */
public class BrukerServiceImpl implements BrukerService{
    
    private DBInterface dbc;
    
    @Autowired
    public void setDBC(DBInterface dbc){
        this.dbc = dbc;
    }
    
    @Override
    public Bruker hentBruker(String brukernavn, String email) {
        System.out.println("Kobler til DB");
        if(brukernavn != null && !brukernavn.isEmpty()){
            System.out.println("Går i db etter brukernavn");
            return dbc.getBrukerN(brukernavn);
        }else if(email != null && !email.isEmpty()){
            return dbc.getBrukerE(email);
        }
        return new Bruker();
    }
    
    @Override
    public boolean sjekkPassord(String brukernavn, String passord) {
        System.out.println("Sender inn til DB: "+brukernavn + " "+passord);
        return dbc.loggInn(brukernavn, passord);
    }

    @Override
    public Bruker hentBruker(Bruker bruker) {
        return dbc.getBrukerN(bruker.getBrukernavn());
    }

    @Override
    public boolean endreBruker(Bruker bruker) {
        return dbc.oppdaterBruker(bruker);
    }

    @Override
    public boolean slettBruker(Bruker bruker) {
        return dbc.slettBruker(bruker);
    }

    @Override
    public boolean nyBruker(Bruker bruker) {
        return dbc.leggTilBruker(bruker);
    }

    @Override
    public boolean slettBrukere(List<Bruker> brukere) {
        boolean sjekk = true;
        for (int i = 0; i < brukere.size(); i++) {
            if(!slettBruker(brukere.get(i))){
                sjekk = false;
            }
            
        }
        return sjekk;
    }

    @Override
    public boolean endreBrukere(List<Bruker> brukere) {
        boolean sjekk = true;
        for (int i = 0; i < brukere.size(); i++) {
            if(!endreBruker(brukere.get(i))){
                sjekk = false;
            }
            
        }
        return sjekk;
    } 
    
    @Override
    public List<Bruker> hentAlleBrukere(){
        return dbc.hentAlleBrukere();
    }
}
