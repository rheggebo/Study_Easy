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
import java.util.List;

/**
 *
 * @author Stein-Erik
 */
public interface Brukerservice {
    
    public boolean sjekkPassord(String brukernavn, String passord);
    
    public Bruker hentBruker(String epost);
    
    public Bruker hentBruker(Bruker bruker);
    
    public boolean endreBruker(Bruker bruker);
    
    public boolean slettBruker(Bruker bruker);
    
    public boolean nyBruker(Bruker bruker);
    
    public boolean slettBrukere(List<Bruker> brukere);
    
    public boolean endreBrukere(List<Bruker> brukere);
    
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
    
    public List<Rom> getRomFraInnhold(Rom r);
    
    public List<Rom> getRomFraType(Rom r);
    
    public List<Rom> getRomFraStoerrelse(Rom r);
    
    public List<Klasse> getLaererKlasse(Bruker b);
}
