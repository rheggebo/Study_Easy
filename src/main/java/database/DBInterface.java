/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Bruker;
import beans.Fag;
import beans.KalenderEvent;
import beans.Klasse;
import beans.Rom;
import java.util.List;

/**
 *
 * @author stein.erik.bjornnes
 */
public interface DBInterface {
    
    public Bruker getBruker(String epost);
    
    public boolean loggInn(String epost, String passord);
    
    public boolean oppdaterBruker(Bruker b);
    
    public boolean oppdaterBrukerFag(Bruker b, Fag f);
    
    public boolean oppdaterBrukerKlasse(Bruker b, Klasse k);
    
    public boolean slettBruker(Bruker b);
    
    public boolean leggTilBruker(Bruker b);
    
    public List<Bruker> getAlleBrukere();
    
    public boolean oppdaterRom(Rom r);
    
    public boolean slettRom(Rom r);
    
    public boolean oppdaterKlasseFag(Klasse k, Fag f);
    
    public boolean slettRomInnhold(Rom r, String innholdNavn);
    
    public boolean leggTilInnhold(Rom r, String innholdNavn);
    
    public boolean slettBrukerFag(Bruker b, Fag f);
    
    public boolean leggTilFag(Fag f);
    
    public boolean leggTilRom(Rom r);
    
    public boolean leggTilKalenderEvent(KalenderEvent ke);
    
    public boolean fjernKalenderEvent(KalenderEvent ke);
    
    public KalenderEvent getKalenderDeltakere(KalenderEvent ke);
    
    public Bruker getKalenderDeltaker(KalenderEvent ke, Bruker b);
    
    public KalenderEvent getKalenderEventEier(Bruker b);
    
    public KalenderEvent getKalenderEventRomID(Rom r);
    
    public Fag getFagLaerer(Bruker b);
    
    public Rom getRombestilling();
    
    public Rom getRomFraNavn(Rom r);
    
    public Rom getRomFraInnhold(Rom r);
    
    public Rom getRomFraType(Rom r);
    
    public Rom getRomFraStoerrelse(Rom r);
    
    public Klasse getLaererKlasse(Bruker b);
}
