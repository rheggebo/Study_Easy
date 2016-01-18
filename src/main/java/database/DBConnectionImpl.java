/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import mapper.BrukerMapper;
import mapper.RomMapper;
import beans.Bruker;
import beans.BrukerB;
import beans.Fag;
import beans.KalenderEvent;
import beans.Klasse;
import beans.Rom;
import beans.RomBestilling;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import mapper.FagMapper;
import mapper.KalenderEventMapper;
import mapper.RomBestillingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import verktøy.PasswordHasher;

/**
 *
 * @author stein.erik.bjornnes
 */
public class DBConnectionImpl implements DBConnection{
    
    private final String getBrukerEpost = "SELECT * FROM brukere WHERE EPOST=?";
    private final String endreBruker = "UPDATE brukere SET PASSORD=?, TYPE=?, FORNAVN=?, ETTERNAVN=? WHERE EPOST=?";
    private final String nyBruker = "INSERT INTO brukere VALUES(?,?,?,?,?)";
    private final String slettBruker = "DELETE FROM brukere WHERE EPOST=?";
    private final String alleBrukere = "SELECT * FROM brukere";
    private final String endreBrukerFag = "UPDATE FAG_LÆRER SET FAGID=? WHERE BRUKERID=?";
    private final String endreBrukerKlasse = "UPDATE KLASSE_DELTAKER SET KLASSEID=? WHERE BRUKERID=?";
    private final String endreKlasseFag = "UPDATE KLASSE_FAG SET FAGID=? WHERE KLASSEID=?";
    private final String endreRom = "UPDATE ROM SET ROMNAVN=?, TYPE=?, ETASJE=?, STØRRELSE=? WHERE ROMID=?";
    private final String slettRom = "DELETE FROM ROM WHERE ROMID=?";
    private final String slettRomInnhold = "DELETE FROM ROM_INNHOLD WHERE ROMID=? AND INNHOLDID=?";
    private final String leggTilInnhold = "INSERT INTO INNHOLD VALUES(?)";
    private final String slettInnhold = "DELETE FROM INNHOLD WHERE INNHOLDID=?";
    private final String leggTilRomInnhold = "INSERT INTO ROM_INNHOLD VALUES(?,?)";
    private final String slettBrukerFag = "DELETE FROM FAG_LÆERER WHERE FAGID=? AND BRUKERID=?";
    private final String leggTilKalenderDeltaker = "INSERT INTO KALENDER_DELTAKER VALUES(?,?)";
    private final String fjernKalenderDeltaker = "DELETE FROM KALENDER_DELTAKER WHERE EVENTID=? AND DELTAKERID=?";
    private final String leggTilFag = "INSERT INTO FAG VALUES(?)";
    private final String leggTilRom = "INSERT INTO ROM VALUES(?,?,?,?,?)";
    private final String leggTilKalenderEvent = "INSERT INTO KALENDER_EVENT VALUES(?,?,?,?,?,?,?,?)";
    private final String fjernKalenderEvent = "DELETE FROM KALENDER_EVENT WHERE ID=?";
    private final String getKalenderEventDeltakere = "SELECT DELTAKERID FROM KLANEDER_DELTAKER WHERE EVENTID=?";
    private final String getKalenderEventDeltaker = "SELECT * FROM KALENDER_DELTAKER WHERE EVENTID=? AND DELTAKERID=?";
    private final String getKalenderEventEier = "SELECT * FROM KALENDER_EVENT WHERE EIER=?";
    private final String getKalenderEventRomID = "SELECT * FROM KALENDER_EVENT WHERE ROMID=?";
    private final String getFagLaerer = "SELECT FAGID FROM FAG_LÆRER WHERE BRUKERID=?";
    private final String getRombestilling = "";
    private final String getRomFraNavn = "SELECT * FROM rom WHERE romnavn=?";
    private final String getRomFraInnhold = "";
    private final String getRomFraType = "SELECT * FROM ROM WHERE TYPE=?";
    private final String getRomFraStoerrelse = "SELECT * FROM ROM WHERE STØRRELSE=?";
    private final String getLaererKlasse = "";
    private final String getKlasseDeltaker = "";
    private final String leggTilAbonnement = "";
    private final String slettAbonnement = "";
    private final String getAbonnement = "";
    private final String finnRomTypeStorrelse = "SELECT type, størrelse FROM rom WHERE type=? AND størrelse=?;";
    
    private final String getAlleBestillingerFraBruker = "SELECT rom_bestilling.eierID, rom_bestilling.romID, rom_bestilling.dato_start, rom_bestilling.dato_slutt FROM rom_bestilling WHERE rom_bestilling.eierID =?";
    private final String getAlleEventsFraBruker = 
            "SELECT DISTINCT kalender_event.id, kalender_event.tittel, kalender_event.dato_start, kalender_event.dato_slutt, kalender_event.eier, kalender_event.eier_navn, kalender_event.romID, kalender_event.fagID, kalender_event.type, kalender_event.descr, kalender_event.hidden "
            + "FROM kalender_event, brukere WHERE kalender_event.eier =? UNION "
            + "SELECT DISTINCT kalender_event.id, kalender_event.tittel, kalender_event.dato_start, kalender_event.dato_slutt, kalender_event.eier, kalender_event.eier_navn, kalender_event.romID, "
            + "kalender_event.fagID, kalender_event.type, kalender_event.descr, kalender_event.hidden FROM kalender_event, brukere, abonemennt_bruker "
            + "WHERE kalender_event.eier = abonemennt_bruker.brukerID  AND abonemennt_bruker.eierID =?  AND kalender_event.hidden = 0 UNION "
            + "SELECT DISTINCT kalender_event.id, kalender_event.tittel, kalender_event.dato_start, kalender_event.dato_slutt, kalender_event.eier, "
            + "kalender_event.eier_navn, kalender_event.romID, kalender_event.fagID, kalender_event.type, kalender_event.descr, kalender_event.hidden FROM kalender_event, brukere, abonemennt_fag WHERE kalender_event.fagID = abonemennt_fag.fagID AND abonemennt_fag.eierID =?;";

    /**Søkefunksjon**/
    private final String alleRom="SELECT * FROM rom";
    private final String alleFag="SELECT * FROM fag";
    
    private final String getBrukerSok = "SELECT * FROM brukere WHERE fornavn LIKE ? OR etternavn LIKE ? or epost LIKE ?";
    private final String getFagSok = "SELECT * FROM fag WHERE fagID LIKE ? OR fagnavn LIKE ?";
    private final String getRomSok = "SELECT * FROM rom WHERE romID LIKE ? OR romnavn LIKE ?";
    
    
    private DataSource dS;
    private JdbcTemplate jT;
    
    @Autowired
    private void setDatabaseSource(DataSource dS){
        this.dS = dS;
        jT = new JdbcTemplate(dS);
    }

    @Override
    public Bruker getBruker(String epost) {
        return (Bruker) jT.queryForObject(getBrukerEpost, new Object[]{epost},new BrukerMapper());
    }
    
    
    @Override
    public boolean sjekkPassord(String epost, String passord) {
        Bruker bruker = (Bruker) jT.queryForObject(getBrukerEpost, new Object[]{epost},new BrukerMapper());
        try {
            return PasswordHasher.check(passord, bruker.getPassord());
        } catch (Exception ex) {
            Logger.getLogger(DBConnectionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean oppdaterBruker(Bruker b) {
        try{
            System.out.println(PasswordHasher.getSaltedHash(b.getPassord())+ " "
            +b.getTilgangsniva()+" "
            +b.getFornavn()+" "
            +b.getEtternavn()+ " "
            +b.getEpost());
        int antallRader = jT.update(endreBruker, new Object[]{
            PasswordHasher.getSaltedHash(b.getPassord()),
            b.getTilgangsniva(),
            b.getFornavn(),
            b.getEtternavn(),
            b.getEpost()
        });
        if(antallRader>0){
            return true;
        }
        }catch(Exception e){}
        return false;   
    }

    @Override
    public boolean slettBruker(Bruker b) {
        int antallRader = jT.update(slettBruker,new Object[]{b.getEpost()});
        if(antallRader > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean leggTilBruker(Bruker b) {
        try{
        int antallRader = jT.update(nyBruker,new Object[]{
            b.getEpost(),
            PasswordHasher.getSaltedHash(b.getPassord()),
            b.getTilgangsniva(),
            b.getFornavn(),
            b.getEtternavn()
        });
        if(antallRader > 0){
            return true;
        }
        }catch(Exception e){}
        return false;
    }
    
    @Override
    public List<Rom> getAlleRom(){
        return jT.query(alleRom, new RomMapper());
    }
    @Override
    public List<Fag> getAlleFag(){
        return jT.query(alleFag, new FagMapper());
    }
    @Override
    public List<Bruker> getAlleBrukere() {
        return jT.query(alleBrukere, new BrukerMapper());
    }
    
    
    
    /***Søkefunksjon metoder:   **/
    
    @Override
    public List<Bruker> getBrukerSok(String sokeord1, String sokeord2, String sokeord3) {
        return jT.query(getBrukerSok, new Object[]{sokeord1, sokeord2, sokeord3}, new BrukerMapper());
    }
    
    @Override
    public List<Fag> getFagSok(String sokeord1, String sokeord2) {
        return jT.query(getFagSok, new Object[]{sokeord1, sokeord2}, new FagMapper());
    }
    
    @Override
    public List<Rom> getRomSok(String sokeord1, String sokeord2) {
        return jT.query(getRomSok, new Object[]{sokeord1, sokeord2}, new RomMapper());
    }
    

    /***Søkefunksjon metoder slutt***/

    @Override
    public boolean oppdaterBrukerFag(Bruker b, Fag f) {
        int antallRader = jT.update(endreBrukerFag,new Object[]{
            f.getFagID(),
            b.getEpost()
        });
        if(antallRader > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean oppdaterBrukerKlasse(Bruker b, Klasse k) {
        int antallRader = jT.update(endreBrukerKlasse,new Object[]{
            k.getNavn(),
            b.getEpost()
        });
        if(antallRader > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean oppdaterRom(Rom r) {
        int antallRader = jT.update(endreRom,new Object[]{
            r.getRomNavn(),
            r.getType(),
            r.getEtasje(),
            r.getStorrelse(),
            r.getRomID()
        });
        if(antallRader > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean slettRom(Rom r) {
        int antallRader = jT.update(slettRom,new Object[]{
            r.getRomID()
        });
        if(antallRader > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean oppdaterKlasseFag(Klasse k, Fag f) {
        int antallRader = jT.update(endreKlasseFag,new Object[]{
            f.getFagID(),
            k.getNavn()
        });
        if(antallRader > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean slettRomInnhold(Rom r, String innholdNavn) {
        int antallRader = jT.update(slettRomInnhold,new Object[]{
            r.getRomID(),
            innholdNavn
        });
        if(antallRader > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean leggTilInnhold(Rom r, String innholdNavn) {
        int antallRader = jT.update(leggTilInnhold,new Object[]{
            r.getRomID(),
            innholdNavn
        });
        if(antallRader > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean slettBrukerFag(Bruker b, Fag f) {
        int antallRader = jT.update(slettBrukerFag,new Object[]{
            f.getFagID(),
            b.getEpost()
        });
        if(antallRader > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean leggTilFag(Fag f) {
        int antallRader = jT.update(leggTilFag,new Object[]{
            f.getFagID()
        });
        if(antallRader > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean leggTilRom(Rom r) {
        int antallRader = jT.update(leggTilRom,new Object[]{
            r.getRomID(),
            r.getRomNavn(),
            r.getType(),
            r.getEtasje(),
            r.getStorrelse()
        });
        if(antallRader > 0){
            return true;
        }
        return false;
    }
    
    @Override
    public boolean leggTilKalenderEvent(KalenderEvent ke) {
        int antallRader = jT.update(leggTilKalenderEvent,new Object[]{
            ke.getId(),
            ke.getStartTid(),
            ke.getSluttTid(),
            ke.getEpost(),
            ke.isPrivat(),
            ke.getType(),
            ke.getFag()
        });
        if(antallRader > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean fjernKalenderEvent(KalenderEvent ke) {
        int antallRader = jT.update(fjernKalenderEvent,new Object[]{
            ke.getId()
        });
        if(antallRader > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<Bruker> getKalenderEventDeltakere(KalenderEvent ke) {
        return jT.query(getKalenderEventDeltakere, new Object[]{
            ke.getId()
        }, new BrukerMapper());
    }

    @Override
    public Bruker getKalenderEventDeltaker(KalenderEvent ke, Bruker b) {
        return (Bruker) jT.queryForObject(getKalenderEventDeltaker, new Object[]{
            ke.getId(),
            b.getEpost()
        }, new BrukerMapper());
    }

    @Override
    public List<KalenderEvent> getKalenderEventEier(Bruker b) {
        return jT.query(getKalenderEventEier, new Object[]{
            b.getEpost()
        }, new KalenderEventMapper());
    }

    @Override
    public List<KalenderEvent> getKalenderEventRomID(Rom r) {
        return jT.query(getKalenderEventRomID, new Object[]{
            r.getRomID()
        }, new KalenderEventMapper());
    }

    @Override
    public List<Fag> getFagLaerer(Bruker b) {
        return jT.query(getFagLaerer, new Object[]{
            b.getEpost()
        }, new FagMapper());
    }
    
    

    @Override
    public Rom getRombestilling() {
        //return (Rom) jT.queryForObject(getRombestilling, new Object[]{
        
        //}, new RomMapper());
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rom> getRomFraNavn(Rom r) {
        List<Rom> liste = jT.query(getRomFraNavn, new Object[]{
        r.getRomNavn()
        }, new RomMapper());
        for (Rom rom : liste) {
            
        }
        return liste;
    }
    
    @Override
    public List<Rom> getRomFraNavn(String navn) {
        /*return (Bruker) jT.queryForObject
                (getBrukerEpost, new Object[]{epost},new BrukerMapper());*/
        List<Rom> liste = jT.query(getRomFraNavn, new Object[]{navn},
                new RomMapper());
        for (Rom rom : liste) {
            
        }
        return liste;
    }

    @Override
    public List<Rom> getRomFraInnhold(String[] innhold) {
        //return (Rom) jT.queryForObject(getRomFraInnhold, Object[]{
        
        //}, new RomMapper());
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rom> getRomFraType(Rom r) {
        return jT.query(getRomFraType, new Object[]{
            r.getType()
        }, new RomMapper());
    }

    @Override
    public List<Rom> getRomFraStoerrelse(Rom r) {
        return jT.query(getRomFraStoerrelse, new Object[]{
            r.getStorrelse()
        }, new RomMapper());
    }

    @Override
    public Klasse getLaererKlasse(Bruker b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<KalenderEvent> getAlleEventsFraBruker(BrukerB b){
        return jT.query(getAlleEventsFraBruker, new Object[]{
            b.getEpost(),
            b.getEpost(),
            b.getEpost()
        }, new KalenderEventMapper());
    }
    
    @Override
    public List<RomBestilling> getAlleBestillingerFraBruker(BrukerB b){
        return jT.query(getAlleBestillingerFraBruker, new Object[]{
            b.getEpost()
        }, new RomBestillingMapper());
    }
    
    @Override
    public List<Rom> finnRomTypeStorrelse(Rom r) {
        return jT.query(finnRomTypeStorrelse, new Object[]{
            r.getType(),
            r.getStorrelse()
        }, new RomMapper());
    }

}
