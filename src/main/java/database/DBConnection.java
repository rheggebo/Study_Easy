/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Bruker;
import beans.BrukerB;
import beans.Fag;
import beans.KalenderEvent;
import beans.Klasse;
import beans.Rom;
import java.util.List;

/**
 *
 * @author stein.erik.bjornnes
 */
public interface DBConnection {
    
    public Bruker getBruker(String epost);
    
    public boolean sjekkPassord(String epost, String passord);
    
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
    
    public List<Bruker> getKalenderEventDeltakere(KalenderEvent ke);
    
    public Bruker getKalenderEventDeltaker(KalenderEvent ke, Bruker b);
    
    public List<KalenderEvent> getKalenderEventEier(Bruker b);
    
    public List<KalenderEvent> getKalenderEventRomID(Rom r);
    
    public List<Fag> getFagLaerer(Bruker b);
    
    public Rom getRombestilling();
    
    public List<Rom> getRomFraNavn(Rom r);
    
    public List<Rom> getRomFraNavn(String navn);
    
    public List<Rom> getRomFraInnhold(String[] innhold);
    
    public List<Rom> getRomFraType(Rom r);
    
    public List<Rom> getRomFraStoerrelse(Rom r);
    
    public Klasse getLaererKlasse(Bruker b);
    
    public List<Rom> getAlleRom();
    
    public List<Fag> getAlleFag();
    
    public List<KalenderEvent> getAlleEventsFraBruker(BrukerB b);
    
    /*** Søkefunksjonsmetoder:  */
    
    public List<Bruker> getBrukerSok(String sokeord);
    
    public List<Fag> getFag(String fag);
    
    public List<Bruker> getBrukerFornavn(String fornavn);
    
    public List<Bruker> getBrukerEtternavn(String etternavn);
    
    public List<Rom> getRomNavn(String romnavn);
    
    public List<Rom> getRomID(String romID);
}
