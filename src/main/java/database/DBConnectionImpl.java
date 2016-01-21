/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Abonemennt;
import mapper.BrukerMapper;
import mapper.RomMapper;
import mapper.KlasseFagMapper;
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
import mapper.AbonemenntMapper;
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
    private final String leggTilKalenderEvent = "INSERT INTO kalender_event VALUES(DEFAULT,?,?,?,?,?,?,?,?,?,?)";
    private final String fjernKalenderEvent = "DELETE FROM KALENDER_EVENT WHERE ID=?";
    private final String getKalenderEventDeltakere = "SELECT DELTAKERID FROM KLANEDER_DELTAKER WHERE EVENTID=?";
    private final String getKalenderEventDeltaker = "SELECT * FROM KALENDER_DELTAKER WHERE EVENTID=? AND DELTAKERID=?";
    private final String getKalenderEventEier = "SELECT *, rom_bestilling.romID FROM kalender_event LEFT OUTER JOIN rom_bestilling ON rom_bestilling.bestillingsID = kalender_event.bestillingsID WHERE EIER=?";
    private final String getKalenderEventRomID = "SELECT *, rom_bestilling.romID FROM kalender_event LEFT OUTER JOIN rom_bestilling ON rom_bestilling.bestillingsID = kalender_event.bestillingsID WHERE ROMID=?";
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
    private final String getRomTypeStorrelse = "SELECT type, størrelse FROM rom WHERE type=? AND størrelse=?;";
    private final String getRom = "SELECT * FROM rom WHERE romID=?";
    
    private final String leggTilAbonemenntBruker = "INSERT INTO abonemennt_bruker (eierID, brukerID) VALUES (?, ?)";
    private final String leggTilAbonemenntFag = "INSERT INTO abonemennt_fag (eierID, fagID) VALUES (?, ?)";
    private final String slettAbonemenntFag = "DELETE FROM abonemennt_fag WHERE eierID = ? AND fagID = ?";
    private final String slettAbonemenntBruker = "DELETE FROM abonemennt_bruker WHERE eierID = ? AND fagID = ?";
    private final String getAbonemenntFraBruker = "SELECT abonemennt_bruker.eierID, abonemennt_bruker.brukerID AS abonererId, 0 AS abType FROM abonemennt_bruker WHERE abonemennt_bruker.eierID =? UNION "
            + "SELECT abonemennt_fag.eierID, abonemennt_fag.fagID AS abonererId, 1 AS abType FROM abonemennt_fag WHERE abonemennt_fag.eierID =?";
    private final String getAlleBestillingerFraBruker = "SELECT rom_bestilling.eierID, rom_bestilling.romID, rom_bestilling.dato_start, rom_bestilling.dato_slutt, rom_bestilling.tilhorer_event FROM rom_bestilling WHERE rom_bestilling.eierID =?";
    
    private final String getAlleEventsFraBruker = "SELECT DISTINCT kalender_event.id, kalender_event.tittel, kalender_event.dato_start, kalender_event.dato_slutt, kalender_event.eier, kalender_event.eier_navn, (CASE WHEN kalender_event.bestillingsID IS NOT NULL AND kalender_event.bestillingsID = rom_bestilling.bestillingsID then rom_bestilling.romID else kalender_event.bestillingsID END) as romID, kalender_event.fagID, kalender_event.type, kalender_event.descr, kalender_event.hidden " +
    "FROM kalender_event, brukere, rom_bestilling WHERE kalender_event.eier =? UNION " +
    "SELECT DISTINCT kalender_event.id, kalender_event.tittel, kalender_event.dato_start, kalender_event.dato_slutt, kalender_event.eier, kalender_event.eier_navn, (CASE WHEN kalender_event.bestillingsID IS NOT NULL AND kalender_event.bestillingsID = rom_bestilling.bestillingsID then rom_bestilling.romID else kalender_event.bestillingsID END) as romID, " +
    "kalender_event.fagID, kalender_event.type, kalender_event.descr, kalender_event.hidden FROM kalender_event, brukere, abonemennt_bruker, rom_bestilling " +
    "WHERE kalender_event.eier = abonemennt_bruker.brukerID  AND abonemennt_bruker.eierID =?  AND kalender_event.hidden = 0 UNION " +
    "SELECT DISTINCT kalender_event.id, kalender_event.tittel, kalender_event.dato_start, kalender_event.dato_slutt, kalender_event.eier, " +
    "kalender_event.eier_navn, (CASE WHEN kalender_event.bestillingsID IS NOT NULL AND kalender_event.bestillingsID = rom_bestilling.bestillingsID then rom_bestilling.romID else kalender_event.bestillingsID END) as romID, kalender_event.fagID, kalender_event.type, kalender_event.descr, kalender_event.hidden FROM rom_bestilling, kalender_event, brukere, abonemennt_fag WHERE kalender_event.fagID = abonemennt_fag.fagID AND abonemennt_fag.eierID =?;";
    
    private final String getRom0Param = "SELECT DISTINCT rom.romID, romnavn, etasje, størrelse, type, sitteplasser FROM rom LEFT OUTER JOIN rom_innhold ON rom.romID = rom_innhold.romID LEFT OUTER JOIN " +
        "rom_bestilling ON rom.romID = rom_bestilling.romID " +
        "WHERE (rom.type LIKE ? AND ? NOT BETWEEN dato_start AND dato_slutt AND " +
        "? NOT BETWEEN dato_start AND dato_slutt  OR rom_bestilling.romID IS NULL AND rom.type LIKE ?)";
    
    private final String getRom1Param = getRom0Param +" AND (rom_innhold.innholdID LIKE ? AND rom_innhold.antall>=?)";
    private final String getRom2Param = getRom1Param +getRom1Param;
    private final String getRom3Param = getRom1Param +getRom1Param+getRom1Param;
    private final String getRom4Param = getRom1Param +getRom1Param+getRom1Param+getRom1Param;
    
    private final String getRomStorrelse = " AND rom.størrelse >= ?";
    private final String getRomPlasser = " AND rom.sitteplasser >= ?";

    /**Søkefunksjon**/
    private final String alleRom="SELECT * FROM rom";
    private final String alleFag="SELECT * FROM fag";
    private final String getStudentSok = "SELECT * FROM brukere WHERE fornavn LIKE ? AND  type = 0 "
            + "OR etternavn LIKE ? AND  type = 0 OR epost LIKE ? AND  type = 0";
    private final String getAnsattSok = "SELECT * FROM brukere WHERE (fornavn LIKE ? AND  (type = 1 OR type = 2))"
            + "OR (etternavn LIKE ? AND  (type = 1 OR type = 2)) OR (epost LIKE ? AND  (type = 1 OR type = 2))"; 
    private final String getFagSok = "SELECT * FROM fag WHERE fagID LIKE ? OR fagnavn LIKE ?";
    private final String getRomSok = "SELECT * FROM rom WHERE romID LIKE ? OR romnavn LIKE ?";
    private final String getKlasseFagSok = "SELECT DISTINCT klasseID FROM klasse_fag WHERE klasseID LIKE ?";
    private final String getKlasseSok = "SELECT * FROM klasse_fag WHERE klasseID LIKE ?";
    
    private final String leggTilBooking = "INSERT INTO rom_bestilling (romID, dato_start, dato_slutt, eierID, tilhorer_event) VALUES (?,?,?,?,?)";
    
    private final String getRomSVG = "SELECT DISTINCT rom.romID, romnavn, etasje, størrelse, type, sitteplasser FROM rom LEFT OUTER JOIN rom_innhold ON rom.romID = rom_innhold.romID LEFT OUTER JOIN " +
        "rom_bestilling ON rom.romID = rom_bestilling.romID " +
        "WHERE (rom.type <= ? AND ? NOT BETWEEN dato_start AND dato_slutt AND " +
        "? NOT BETWEEN dato_start AND dato_slutt  OR rom_bestilling.romID IS NULL AND rom.type <= ?)";
    
    private final String getReserverteRom = "SELECT DISTINCT * FROM rom_bestilling WHERE eierID LIKE ? AND "
            + "(dato_start >= ? OR (? BETWEEN dato_start AND dato_slutt))";
    
    private final String slettBooking = "DELETE FROM rom_bestilling WHERE romID LIKE ? AND dato_start LIKE ? AND dato_slutt LIKE ? AND eierID like ?";
    
    private DataSource dS;
    private JdbcTemplate jT;
    
    @Autowired
    public void setDatabaseSource(DataSource dS){
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
    public List<Bruker> getStudentSok(String sokeord1, String sokeord2, String sokeord3) {
        return jT.query(getStudentSok, new Object[]{sokeord1, sokeord2, sokeord3}, new BrukerMapper());
    } 
    
    @Override
    public List<Bruker> getAnsattSok(String sokeord1, String sokeord2, String sokeord3) {
        return jT.query(getAnsattSok, new Object[]{sokeord1, sokeord2, sokeord3}, new BrukerMapper());
    }
    
    @Override
    public List<Fag> getFagSok(String sokeord1, String sokeord2) {
        return jT.query(getFagSok, new Object[]{sokeord1, sokeord2}, new FagMapper());
    }
    
    @Override
    public List<Rom> getRomSok(String sokeord1, String sokeord2) {
        return jT.query(getRomSok, new Object[]{sokeord1, sokeord2}, new RomMapper());
    }
    
    @Override
    public List<Klasse> getKlasseSok(String sokeord1) {
        List<Klasse> dbListe =  jT.query(getKlasseSok, new Object[]{sokeord1}, new KlasseFagMapper());
        ArrayList<Klasse> klasseListe = new ArrayList<Klasse>();
        boolean finnes = true;
        
        //går gjennom listen hentet fra databasen
        for (int i = 0; i < dbListe.size(); i++) {
            finnes = false;
            
            //går gjennom den nye listen som skal bli den ferdige listen
            for (int j = 0; j < klasseListe.size(); j++) {
            	
            	//hvis klassenavn finnes i den nye listen fra før, finnes=true og legg til det nye faget
                if(klasseListe.get(j).getNavn().equals(dbListe.get(i).getNavn())) {
                    finnes = true;
                    if(dbListe.get(i).getFag().size() > 0) {
			klasseListe.get(j).addFag(dbListe.get(i).getFag().get(0));
                    }
                }
            }
            
            //hvis den ikke fantes i listen, legg den til
            if(!finnes) {
            	klasseListe.add(dbListe.get(i));
            }
        }

        return klasseListe;
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
            ke.getStartTid(),
            ke.getSluttTid(),
            ke.getEpost(),
            ke.isPrivat(),
            ke.getId(),
            ke.getType(),
            ke.getFag(),
            ke.getNotat(),
            ke.getTittel()
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
    public List<KalenderEvent> getKalenderEventEier(BrukerB b) {
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
    public boolean leggTilAbonemennt(Abonemennt ab){
        String bruk = "";
        if (ab.getType() == 0){
            bruk = leggTilAbonemenntBruker;
        }
        else{
            bruk = leggTilAbonemenntFag;
        }
        int antallRader = jT.update(bruk,new Object[]{
            ab.getEierid(),
            ab.getAbonererId()
        });
        if(antallRader > 0){
            return true;
        }
        return false;
    }
    
    @Override
    public boolean slettAbonemennt(Abonemennt ab){
        String bruk = "";
        if (ab.getType() == 0){
            bruk = slettAbonemenntBruker;
        }
        else{
            bruk = slettAbonemenntFag;
        }
        int antallRader = jT.update(bruk,new Object[]{
            ab.getEierid(),
            ab.getAbonererId()
        });
        if(antallRader > 0){
            return true;
        }
        return false;
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
    public List<Abonemennt> getAbonemenntFraBruker(BrukerB b){
        return jT.query(getAbonemenntFraBruker, new Object[]{
            b.getEpost(),
            b.getEpost()
        }, new AbonemenntMapper());
    }
    
    @Override
    public List<RomBestilling> getAlleBestillingerFraBruker(BrukerB b){
        return jT.query(getAlleBestillingerFraBruker, new Object[]{
            b.getEpost()
        }, new RomBestillingMapper());
    }
    
    @Override
    public List<Rom> getRomTypeStorrelse(Rom r) {
        return jT.query(getRomTypeStorrelse, new Object[]{
            r.getType(),
            r.getStorrelse()
        }, new RomMapper());
    }
    
    @Override
    public List<Rom> getRom0Param(Rom r, KalenderEvent ke, boolean storrelse, boolean sitteplasser){
        if(storrelse && sitteplasser){
            return jT.query(getRom0Param+ getRomStorrelse + getRomPlasser, new Object[]{
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType(),
            r.getStorrelse(),
            r.getAntStolplasser()
            }, new RomMapper());
        }else if(sitteplasser){
            System.out.println(getRom0Param+" "+getRomPlasser+" "+r.getAntStolplasser());
            return jT.query(getRom0Param+getRomPlasser, new Object[]{
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType(),
            r.getAntStolplasser()
            }, new RomMapper());
        }else if(storrelse){
            return jT.query(getRom0Param+getRomStorrelse, new Object[]{
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType(),
            r.getStorrelse()
            }, new RomMapper());
        }
        System.out.println("Romtype "+r.getType()+" "+ke.getStartTid()+" "+ke.getSluttTid());
        return jT.query(getRom0Param, new Object[]{
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType()
        }, new RomMapper());
    }

    @Override
    public List<Rom> getRom1Param(Rom r, KalenderEvent ke, boolean storrelse, boolean sitteplasser) {
        ArrayList<String> innhold = r.getInnhold();
        ArrayList<String> tab = new ArrayList<String>(innhold.size()*2);
        for (int i = 0; i < innhold.size(); i++) {
            String[] midl = innhold.get(i).split(" ");
            tab.add(midl[0]);
            tab.add(midl[1]);
        }
        if(storrelse && sitteplasser){
            return jT.query(getRom1Param+ getRomStorrelse + getRomPlasser, new Object[]{
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType(),
            tab.get(0),
            tab.get(1),
            r.getStorrelse(),
            r.getAntStolplasser()
            }, new RomMapper());
        }else if(sitteplasser){
            return jT.query(getRom1Param+getRomPlasser, new Object[]{
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType(),
            tab.get(0),
            tab.get(1),
            r.getAntStolplasser()
            }, new RomMapper());
        }else if(storrelse){
            return jT.query(getRom1Param+getRomStorrelse, new Object[]{
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType(),
            tab.get(0),
            tab.get(1),
            r.getStorrelse()
            }, new RomMapper());
        }
        return jT.query(getRom1Param, new Object[]{
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType(),
            tab.get(0),
            tab.get(1)
        }, new RomMapper());
        
    }

    @Override
    public List<Rom> getRom2Param(Rom r, KalenderEvent ke, boolean storrelse, boolean sitteplasser) {
        ArrayList<String> innhold = r.getInnhold();
        ArrayList<String> tab = new ArrayList<String>(innhold.size()*2);
        for (int i = 0; i < innhold.size(); i++) {
            String[] midl = innhold.get(i).split(" ");
            tab.add(midl[0]);
            tab.add(midl[1]);
        }
        if(storrelse && sitteplasser){
            return jT.query(getRom2Param+ getRomStorrelse + getRomPlasser, new Object[]{
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType(),
            tab.get(0),
            tab.get(1),
            tab.get(2),
            tab.get(3),
            r.getStorrelse(),
            r.getAntStolplasser()
            }, new RomMapper());
        }else if(sitteplasser){
            return jT.query(getRom2Param+getRomPlasser, new Object[]{
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType(),
            tab.get(0),
            tab.get(1),
            tab.get(2),
            tab.get(3),
            r.getAntStolplasser()
            }, new RomMapper());
        }else if(storrelse){
            return jT.query(getRom2Param+getRomStorrelse, new Object[]{
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType(),
            tab.get(0),
            tab.get(1),
            tab.get(2),
            tab.get(3),
            r.getStorrelse()
            }, new RomMapper());
        }
        return jT.query(getRom2Param, new Object[]{
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType(),
            tab.get(0),
            tab.get(1),
            tab.get(2),
            tab.get(3)
        }, new RomMapper());
    }

    @Override
    public List<Rom> getRom3Param(Rom r, KalenderEvent ke, boolean storrelse, boolean sitteplasser) {
        ArrayList<String> innhold = r.getInnhold();
        ArrayList<String> tab = new ArrayList<String>(innhold.size()*2);
        for (int i = 0; i < innhold.size(); i++) {
            String[] midl = innhold.get(i).split(" ");
            tab.add(midl[0]);
            tab.add(midl[1]);
        }
        if(storrelse && sitteplasser){
            return jT.query(getRom3Param+ getRomStorrelse + getRomPlasser, new Object[]{
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType(),
            tab.get(0),
            tab.get(1),
            tab.get(2),
            tab.get(3),
            tab.get(4),
            tab.get(5),
            r.getStorrelse(),
            r.getAntStolplasser()
            }, new RomMapper());
        }else if(sitteplasser){
            return jT.query(getRom3Param+getRomPlasser, new Object[]{
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType(),
            tab.get(0),
            tab.get(1),
            tab.get(2),
            tab.get(3),
            tab.get(4),
            tab.get(5),
            r.getAntStolplasser()
            }, new RomMapper());
        }else if(storrelse){
            return jT.query(getRom3Param+getRomStorrelse, new Object[]{
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType(),
            tab.get(0),
            tab.get(1),
            tab.get(2),
            tab.get(3),
            tab.get(4),
            tab.get(5),
            r.getStorrelse()
            }, new RomMapper());
        }
        return jT.query(getRom3Param, new Object[]{
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType(),
            tab.get(0),
            tab.get(1),
            tab.get(2),
            tab.get(3),
            tab.get(4),
            tab.get(5)
        }, new RomMapper());
    }

    @Override
    public List<Rom> getRom4Param(Rom r, KalenderEvent ke, boolean storrelse, boolean sitteplasser) {
        ArrayList<String> innhold = r.getInnhold();
        ArrayList<String> tab = new ArrayList<String>(innhold.size()*2);
        for (int i = 0; i < innhold.size(); i++) {
            String[] midl = innhold.get(i).split(" ");
            tab.add(midl[0]);
            tab.add(midl[1]);
        }
        if(storrelse && sitteplasser){
            return jT.query(getRom4Param+ getRomStorrelse + getRomPlasser, new Object[]{
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType(),
            tab.get(0),
            tab.get(1),
            tab.get(2),
            tab.get(3),
            tab.get(4),
            tab.get(5),
            tab.get(6),
            tab.get(7),
            r.getStorrelse(),
            r.getAntStolplasser()
            }, new RomMapper());
        }else if(sitteplasser){
            return jT.query(getRom4Param+getRomPlasser, new Object[]{
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType(),
            tab.get(0),
            tab.get(1),
            tab.get(2),
            tab.get(3),
            tab.get(4),
            tab.get(5),
            tab.get(6),
            tab.get(7),
            r.getAntStolplasser()
            }, new RomMapper());
        }else if(storrelse){
            return jT.query(getRom4Param+getRomStorrelse, new Object[]{
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType(),
            tab.get(0),
            tab.get(1),
            tab.get(2),
            tab.get(3),
            tab.get(4),
            tab.get(5),
            tab.get(6),
            tab.get(7),
            r.getStorrelse()
            }, new RomMapper());
        }
        return jT.query(getRom4Param, new Object[]{
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType(),
            tab.get(0),
            tab.get(1),
            tab.get(2),
            tab.get(3),
            tab.get(4),
            tab.get(5),
            tab.get(6),
            tab.get(7)
        }, new RomMapper());
    }
    
    @Override
    public Rom getRom(Rom r){
        return jT.queryForObject(getRom, new Object[]{
            r.getRomID()
        }, new RomMapper());
    }
    
    @Override
    public boolean leggTilBooking(KalenderEvent ke){
        return (0<jT.update(leggTilBooking, new Object[]{
            ke.getRom(),
            ke.getStartTid(),
            ke.getSluttTid(),
            ke.getEpost(),
            ke.getTilhorerEvent()
        }));
    }
    
    @Override
    public List<Rom> getRomSVG(KalenderEvent ke){
        return jT.query(getRomSVG, new Object[]{
            ke.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            ke.getType()
        }, new RomMapper());
    }
    
    @Override
    public List<RomBestilling> getReserverteRom(KalenderEvent ke){
        return jT.query(getReserverteRom, new Object[]{
            ke.getEpost(),
            ke.getStartTid(),
            ke.getStartTid()
        }, new RomBestillingMapper());
    }
    
    @Override
    public boolean slettBooking(KalenderEvent ke){
        return(0<jT.update(slettBooking, new Object[]{
            ke.getRom(),
            ke.getStartTid(),
            ke.getSluttTid(),
            ke.getEpost()
        }));
    }

    @Override
    public boolean leggTilEvent(KalenderEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
