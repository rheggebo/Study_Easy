/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import mapper.BrukerMapper;
import mapper.RomMapper;
import beans.Bruker;
import beans.Fag;
import beans.KalenderEvent;
import beans.Klasse;
import beans.Rom;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import mapper.FagMapper;
import mapper.KalenderEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import verktøy.PasswordHasher;

/**
 *
 * @author stein.erik.bjornnes
 */
public class DBConnection implements DBInterface{
    
    //private final String getBrukerNavn = "SELECT * FROM BRUKERE WHERE BRUKERNAVN=?";
    private final String getBrukerEpost = "SELECT * FROM BRUKERE WHERE EMAIL=?";
    private final String endreBruker = "UPDATE BRUKERE SET PASSORD=?, TYPE=?, NAVN=? WHERE EPOST=?";
    private final String nyBruker = "INSERT INTO BRUKERE VALUES(?,?,?,?)";
    private final String slettBruker = "DELETE FROM BRUKERE WHERE EPOST=?";
    private final String alleBrukere = "SELECT * FROM BRUKERE";
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
    private final String getRombestilling = "sindre";
    private final String getRomFraNavn = "SELECT * FROM ROM WHERE ROMNAVN=?";
    private final String getRomFraInnhold = "sindre";
    private final String getRomFraType = "SELECT * FROM ROM WHERE TYPE=?";
    private final String getRomFraStoerrelse = "SELECT * FROM ROM WHERE STØRRELSE=?";
    private final String getLaererKlasse = "sindre";
    private final String getKlasseDeltaker = "sindre";
    private final String leggTilAbonnement = "";
    private final String slettAbonnement = "";
    private final String getAbonnement = "";
    
    
    
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
    public boolean loggInn(String epost, String passord) {
        Bruker bruker = (Bruker) jT.queryForObject(getBrukerEpost, new Object[]{epost},new BrukerMapper());
        try {
            if (PasswordHasher.check(passord, bruker.getPassord())){
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean oppdaterBruker(Bruker b) {
        int antallRader = jT.update(endreBruker, new Object[]{
            b.getPassord(),
            b.getTilgangniva(),
            b.getNavn(),
            b.getEpost()
        });
        if(antallRader>0){
            return true;
        }
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
        int antallRader = jT.update(nyBruker,new Object[]{
            b.getEpost(),
            b.getPassord(),
            b.getTilgangniva(),
            b.getNavn()
        });
        if(antallRader > 0){
            return true;
        }
        return false;
    }
    

    @Override
    public List<Bruker> getAlleBrukere() {
        return jT.query(alleBrukere, new BrukerMapper());
    }

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
        int antallRader = jT.update(endreKlasseFag,new Object[]{
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
        int antallRader = jT.update(endreKlasseFag,new Object[]{
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
        int antallRader = jT.update(endreRom,new Object[]{
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
            ke.getStartDato(),
            ke.getSluttDato(),
            ke.getEier(),
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        return jT.query(getRomFraNavn, new Object[]{
        r.getRomNavn()
        }, new RomMapper());
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
}
