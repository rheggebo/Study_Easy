/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.Bruker;
import beans.Fag;
import beans.KalenderEvent;
import beans.Klasse;
import beans.Rom;
import database.DBConnection;
import database.DBInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Stein-Erik
 */
public class BrukerserviceImpl implements Brukerservice{
    
    private DBInterface dbc;
    
    @Autowired
    public void setDBC(DBInterface dbc){
        this.dbc = dbc;
    }
    
    @Override
    public Bruker hentBruker(String email) {
        Bruker bruker = dbc.getBruker(email);
        if(bruker != null){
            return bruker;
        }
        return new Bruker();
    }
    
    @Override
    public boolean sjekkPassord(String brukernavn, String passord) {
        return dbc.loggInn(brukernavn, passord);
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
    public KalenderEvent getKalenderEventEier(Bruker b) {
        return dbc.getKalenderEventEier(b);
    }

    @Override
    public KalenderEvent getKalenderEventRomID(Rom r) {
        return dbc.getKalenderEventRomID(r);
    }

    @Override
    public Fag getFagLaerer(Bruker b) {
        return dbc.getFagLaerer(b);
    }

    @Override
    public Rom getRombestilling() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rom getRomFraNavn(Rom r) {
        return dbc.getRomFraNavn(r);
    }

    @Override
    public Rom getRomFraInnhold(Rom r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rom getRomFraType(Rom r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rom getRomFraStoerrelse(Rom r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Klasse getLaererKlasse(Bruker b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
