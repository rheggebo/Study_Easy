/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.Bruker;
import beans.BrukerB;
import beans.Fag;
import beans.KalenderEvent;
import beans.Klasse;
import beans.Rom;
import database.DBConnectionImpl;
import database.DBConnection;
import java.io.Console;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import verktøy.PasswordHasher;

/**
 *
 * @author Stein-Erik
 */ 
public class ServiceImpl implements Service{
    
    private DBConnection dbc;
    
    @Autowired
    public void setDBC(DBConnection dbc){
        this.dbc = dbc;
    }
    
    @Override
    public Bruker hentBruker(String epost) {
        Bruker bruker = dbc.getBruker(epost);
        if(bruker != null){
            return bruker;
        }
        return new Bruker();
    }

    @Override
    public boolean sjekkPassord(String brukernavn, String passord) {
        return dbc.sjekkPassord(brukernavn, passord);
    }

    @Override
    public Bruker hentBruker(Bruker bruker) {
        return dbc.getBruker(bruker.getEpost());
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
        return   dbc.leggTilBruker(bruker);
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
    public List<Bruker> getAlleBrukere(){
        return dbc.getAlleBrukere();
    }

    @Override
    public boolean oppdaterRom(Rom r) {
        return dbc.oppdaterRom(r);
    }

    @Override
    public boolean slettRom(Rom r) {
        return dbc.slettRom(r);
    }

    @Override
    public boolean oppdaterKlasseFag(Klasse k, Fag f) {
        return dbc.oppdaterKlasseFag(k, f);
    }

    @Override
    public boolean slettRomInnhold(Rom r, String innholdNavn) {
        return dbc.slettRomInnhold(r, innholdNavn);
    }

    @Override
    public boolean leggTilInnhold(Rom r, String innholdNavn) {
        return dbc.leggTilInnhold(r, innholdNavn);
    }

    @Override
    public boolean slettBrukerFag(Bruker b, Fag f) {
        return dbc.slettBrukerFag(b, f);
    }

    @Override
    public boolean leggTilFag(Fag f) {
        return dbc.leggTilFag(f);
    }

    @Override
    public boolean leggTilRom(Rom r) {
        return dbc.leggTilRom(r);
    }

    @Override
    public boolean leggTilKalenderEvent(KalenderEvent ke) {
        return dbc.leggTilKalenderEvent(ke);
    }

    @Override
    public boolean fjernKalenderEvent(KalenderEvent ke) {
        return dbc.fjernKalenderEvent(ke);
    }

    @Override
    public List<Bruker> getKalenderEventDeltakere(KalenderEvent ke) {
        return dbc.getKalenderEventDeltakere(ke);
    }

    @Override
    public Bruker getKalenderEventDeltaker(KalenderEvent ke, Bruker b) {
        return dbc.getKalenderEventDeltaker(ke, b);
    }

    @Override
    public List<KalenderEvent> getKalenderEventEier(Bruker b) {
        return dbc.getKalenderEventEier(b);
    }

    @Override
    public List<KalenderEvent> getKalenderEventRomID(Rom r) {
        return dbc.getKalenderEventRomID(r);
    }

    @Override
    public List<Fag> getFagLaerer(Bruker b) {
        return dbc.getFagLaerer(b);
    }

    @Override
    public Rom getRombestilling() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rom> getRomFraNavn(Rom r) {
        return dbc.getRomFraNavn(r);
    }

    @Override
    public List<Rom> getRomFraInnhold(Rom r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rom> getRomFraType(Rom r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rom> getRomFraStoerrelse(Rom r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Klasse> getLaererKlasse(Bruker b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<KalenderEvent> getAlleEventsFraBruker(BrukerB b){
        return dbc.getAlleEventsFraBruker(b);
    }
    
    @Override
    public List<Rom> finnRomTypeStorrelse(Rom r) {
        return dbc.finnRomTypeStorrelse(r);
    }
    
    /***Søkefunksjon metoder --- **/
    @Override
    public List<Rom> getAlleRom(){
        return dbc.getAlleRom();
    }
    
    @Override
    public List<Fag> getAlleFag(){
        return dbc.getAlleFag();
    }
    
    @Override
    public List<Bruker> getBrukerSok(String sokeord) {
        return dbc.getBrukerSok(sokeord);
    }
    
    @Override
    public List<Rom> getRomFraNavn(String navn) {
        return dbc.getRomFraNavn(navn);
    }
    
    @Override
    public List<Fag> getFagID(String fagID) {
        return dbc.getFagID(fagID);
    }
    
    @Override
    public List<Fag> getFagNavn(String fagNavn) {
        return dbc.getFagNavn(fagNavn);
    }
    
    @Override
    public List<Bruker> getBrukerFornavn(String fornavn) {
        return dbc.getBrukerFornavn(fornavn);
    }
    
    @Override
    public List<Bruker> getBrukerEtternavn(String etternavn) {
        return dbc.getBrukerEtternavn(etternavn);
    }
    
    @Override
    public List<Bruker> getBrukerEpost(String epost) {
        return dbc.getBrukerEpost(epost);
    }
    
    @Override
    public List<Rom> getRomNavn(String romnavn) {
        return dbc.getRomNavn(romnavn);
    }
    
    @Override
    public List<Rom> getRomID(String romID) {
        return dbc.getRomID(romID);
    }
}
