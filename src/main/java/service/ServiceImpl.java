/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.Abonemennt;
import beans.Bruker;
import beans.BrukerB;
import beans.Fag;
import beans.KalenderEvent;
import beans.Klasse;
import beans.Rom;
import beans.RomBestilling;
import database.DBConnectionImpl;
import database.DBConnection;
import java.io.Console;
import java.util.ArrayList;
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
    public List<KalenderEvent> getKalenderEventEier(BrukerB b) {
        return dbc.getKalenderEventEier(b);
    }

    @Override
    public List<KalenderEvent> getKalenderEventRomID(Rom r) {
        return dbc.getKalenderEventRomID(r);
    }
    
   public List<KalenderEvent> getKalenderEventHidden(KalenderEvent ke) {
        return dbc.getKalenderEventHidden(ke);
    }

    @Override
    public List<Fag> getFagLaerer(Bruker b) {
        return dbc.getFagLaerer(b);
    }

    @Override
    public List<Rom> getRomFraNavn(Rom r) {
        return dbc.getRomFraNavn(r);
    }

    /*
    Ikke i bruk
    @Override
    public List<Rom> getRomFraType(Rom r) {
        return dbc.getRomFraType(r);
    }

    @Override
    public List<Rom> getRomFraStoerrelse(Rom r) {
        return dbc.getRomFraStoerrelse(r);
    }
    
    */

    @Override
    public List<KalenderEvent> getAlleEventsFraBruker(BrukerB b){
        return dbc.getAlleEventsFraBruker(b);
    }
    @Override
    public List<RomBestilling> getAlleBestillingerFraBruker(BrukerB b){
        return dbc.getAlleBestillingerFraBruker(b);
    }
    
    
    @Override
    public List<Rom> getRomTypeStorrelse(Rom r) {
        return dbc.getRomTypeStorrelse(r);
    }
    
    @Override
    public List<Abonemennt> getAbonemenntFraBruker(BrukerB b){
        return dbc.getAbonemenntFraBruker(b);
    }
    public boolean leggTilAbonemennt(Abonemennt ab){
        return dbc.leggTilAbonemennt(ab);
    }
    
    public boolean slettAbonemennt(Abonemennt ab) {
        return dbc.slettAbonemennt(ab);
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
    public List<Bruker> getStudentSok(String sokeord1, String sokeord2, String sokeord3) {
        return dbc.getStudentSok(sokeord1, sokeord2, sokeord3);
    }
    
    @Override
    public List<Bruker> getAnsattSok(String sokeord1, String sokeord2, String sokeord3) {
        return dbc.getAnsattSok(sokeord1, sokeord2, sokeord3);
    }
    
    @Override
    public List<Fag> getFagSok(String sokeord1, String sokeord2) {
        return dbc.getFagSok(sokeord1, sokeord2);
    }
    
    @Override
    public List<Rom> getRomSok(String sokeord1, String sokeord2) {
        return dbc.getRomSok(sokeord1, sokeord2);
    }

    @Override
    public List<Klasse> getKlasseSok(String sokeord1) {
        return dbc.getKlasseSok(sokeord1);
    }
    


    @Override
    public List<Rom> getRom(Rom r, KalenderEvent ke, boolean storrelse, boolean sitteplasser) {
        ArrayList<String> innhold = r.getInnhold();
        int antParam = innhold.size();
        if(antParam == 0){
            System.out.println("0"+r+ke+storrelse+sitteplasser);
            return dbc.getRom0Param(r, ke, storrelse, sitteplasser);
        }else if(antParam == 1){
            System.out.println("1"+r+ke+storrelse+sitteplasser);
            return dbc.getRom1Param(r, ke, storrelse, sitteplasser);
        }else if(antParam == 2){
            System.out.println("2"+r+ke+storrelse+sitteplasser);
            return dbc.getRom2Param(r, ke, storrelse, sitteplasser);
        }else if(antParam == 3){
            System.out.println("3"+r+ke+storrelse+sitteplasser);
            return dbc.getRom3Param(r, ke, storrelse, sitteplasser);
        }else{
            System.out.println("4"+r+   ke+storrelse+sitteplasser);
            return dbc.getRom4Param(r, ke, storrelse, sitteplasser);
        }
    }
    
    @Override
    public Rom getRom(Rom r){
        return dbc.getRom(r);
    }
    
    @Override
    public boolean leggTilBooking(KalenderEvent ke){
        return dbc.leggTilBooking(ke);
    }
    
    @Override
    public List<Rom> getRomSVG(Rom r, KalenderEvent ke){
        return dbc.getRomSVG(r, ke);
    }
    
    @Override
    public List<RomBestilling> getReserverteRom(KalenderEvent ke){
        return dbc.getReserverteRom(ke);
    }
    
    @Override
    public boolean slettBooking(KalenderEvent ke){
        return dbc.slettBooking(ke);
    }
    @Override
    public boolean leggTilEvent (KalenderEvent ke){
        return dbc.leggTilEvent(ke);
    }
    
    @Override
    public List<Abonemennt> getAbonnementDeltakere(String st){
       return dbc.getAbonnementDeltakere(st);
   }//i bruk?

    @Override
    public RomBestilling getRomBooking(KalenderEvent ke) {
        return dbc.getRomBooking(ke);
    }
    
    @Override
    public boolean slettKalenderEvent(RomBestilling r){
        return dbc.slettKalenderEvent(r);
    }
    
    @Override
    public List<Abonemennt> getBrukerAbonnement(String epost){
        return dbc.getBrukerAbonnement(epost);
    }
    
    @Override
    public List<Klasse> getAlleKlasser(){
        return dbc.getAlleKlasser();
    }

    @Override
    public boolean erRomLedig(KalenderEvent ke) {
        return dbc.erRomLedig(ke);
    }
    
    @Override
    public List<Fag> getFagKlasse(String klasse){
        return dbc.getFagKlasse(klasse);
    }
    
    @Override
    public boolean leggTilFagKlasse(String fag, String klasse){
        return dbc.leggTilFagKlasse(fag, klasse);
    }

    @Override
    public List<String> getAlleInnholdRom(Rom r) {
        return dbc.getAlleInnholdRom(r);
    }

    @Override
    public boolean oppdaterInnholdRom(String romID, String[] innhold) {
        for (int i = 0; i < innhold.length; i+=2) {
            String[] tab = new String[2];
            tab[0] = innhold[i];
            tab[1] = innhold[i+1];
            if(!dbc.oppdaterInnholdRom(romID, tab)){
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean leggTilKlasse(Fag f){
        return dbc.leggTilKlasse(f);
    }
    
    
}
