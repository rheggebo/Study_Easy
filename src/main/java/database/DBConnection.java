/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Abonemennt;
import beans.Bruker;
import beans.BrukerB;
import beans.Fag;
import beans.KalenderEvent;
import beans.Klasse;
import beans.Rom;
import beans.RomBestilling;
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
    
    public List<Rom> getRomTypeStorrelse(Rom r);
    
    public List<Rom> getRom0Param(Rom r, KalenderEvent ke, boolean storrelse, boolean sitteplasser);
    
    public List<Rom> getRom1Param(Rom r, KalenderEvent ke, boolean storrelse, boolean sitteplasser);
    
    public List<Rom> getRom2Param(Rom r, KalenderEvent ke, boolean storrelse, boolean sitteplasser);
    
    public List<Rom> getRom3Param(Rom r, KalenderEvent ke, boolean storrelse, boolean sitteplasser);
    
    public List<Rom> getRom4Param(Rom r, KalenderEvent ke, boolean storrelse, boolean sitteplasser);
    
    public Rom getRom(Rom r);
    
    public List<RomBestilling> getAlleBestillingerFraBruker(BrukerB b);
    
    public List<Abonemennt> getAbonemenntFraBruker(BrukerB b);
    
    public boolean leggTilAbonemennt(Abonemennt ab);
    
    /*** Søkefunksjonsmetoder:  */
    
    public List<Bruker> getStudentSok(String sokeord1, String sokeord2, String sokeord3);
    
    public List<Bruker> getAnsattSok(String sokeord1, String sokeord2, String sokeord3);

    public List<Fag> getFagSok(String sokeord1, String sokeord2);
    
    public List<Rom> getRomSok(String sokeord1, String sokeord2);
    

    public List<Klasse> getKlasseSok(String sokeord1);


}
