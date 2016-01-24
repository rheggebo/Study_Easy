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
import mapper.KlasseMapper;
import mapper.RomBestillingMapper;
import mapper.RomInnholdMapper;
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
    private final String endreBrukerFag = "UPDATE fag_lærer SET FAGID=? WHERE BRUKERID=?";
    private final String endreBrukerKlasse = "UPDATE KLASSE_DELTAKER SET KLASSEID=? WHERE BRUKERID=?";
    private final String endreKlasseFag = "UPDATE KLASSE_FAG SET FAGID=? WHERE KLASSEID=?";
    private final String endreRom = "UPDATE rom SET ROMNAVN=?, TYPE=?, ETASJE=?, STØRRELSE=?, SITTEPLASSER=? WHERE ROMID=?";
    private final String slettRom = "DELETE FROM rom WHERE ROMID=?";
    private final String slettRomInnhold = "DELETE FROM ROM_INNHOLD WHERE ROMID=? AND INNHOLDID=?";
    private final String leggTilInnhold = "INSERT INTO INNHOLD VALUES(?)";
    private final String slettInnhold = "DELETE FROM INNHOLD WHERE INNHOLDID=?";
    private final String leggTilRomInnhold = "INSERT INTO ROM_INNHOLD VALUES(?,?)";
    private final String slettBrukerFag = "DELETE FROM FAG_LÆERER WHERE FAGID=? AND BRUKERID=?";
    private final String leggTilKalenderDeltaker = "INSERT INTO KALENDER_DELTAKER VALUES(?,?)";
    private final String fjernKalenderDeltaker = "DELETE FROM KALENDER_DELTAKER WHERE EVENTID=? AND DELTAKERID=?";
    private final String leggTilFag = "INSERT INTO fag VALUES(?,?)";
    private final String leggTilRom = "INSERT INTO rom VALUES(?,?,?,?,?,?)";
    private final String leggTilKalenderEvent = "INSERT INTO kalender_event VALUES(DEFAULT,?,?,?,?,?,?,?,?,?,?)";
    private final String fjernKalenderEvent = "DELETE FROM kalender_event WHERE eier = ? AND id = ?";
    private final String getKalenderEventDeltakere = "SELECT DELTAKERID FROM KLANEDER_DELTAKER WHERE EVENTID=?";
    private final String getKalenderEventDeltaker = "SELECT * FROM KALENDER_DELTAKER WHERE EVENTID=? AND DELTAKERID=?";
    private final String getKalenderEventEier = "SELECT *, rom_bestilling.romID FROM kalender_event LEFT OUTER JOIN rom_bestilling ON rom_bestilling.bestillingsID = kalender_event.bestillingsID WHERE EIER=?";
    private final String getKalenderEventRomID = "SELECT *, rom_bestilling.romID FROM kalender_event LEFT OUTER JOIN rom_bestilling ON rom_bestilling.bestillingsID = kalender_event.bestillingsID WHERE ROMID=?";
    private final String getKalenderEventHidden = "SELECT hidden FROM kalender_event Where id = ?";
    private final String getFagLaerer = "SELECT fagID FROM fag_lærer WHERE brukerID=?";
    private final String getRombestilling = "";
    private final String getRomFraNavn = "SELECT * FROM rom WHERE romnavn=?";
    private final String getRomFraInnhold = "";
    private final String getRomFraType = "SELECT * FROM rom WHERE TYPE=?";
    private final String getRomFraStoerrelse = "SELECT * FROM rom WHERE STØRRELSE=?";
    private final String getLaererKlasse = "";
    private final String getKlasseDeltaker = "";
    private final String leggTilAbonnement = "";
    private final String getAbonnementDeltakere = "SELECT eierID FROM abonemennt_bruker WHERE brukerID=?";
    private final String slettAbonnement = "";
    private final String getAbonnement = "";
    private final String getRomTypeStorrelse = "SELECT type, størrelse FROM rom WHERE type=? AND størrelse=?";
    private final String getRom = "SELECT * FROM rom WHERE romID=?";
    private final String leggTilFagKlasse= "INSERT INTO klasse_fag (fagID, klasseID) VALUES(?,?);";
    private final String getAlleInnholdRom= "SELECT innholdID, antall from rom_innhold WHERE romID LIKE ?";
    private final String oppdaterInnholdRom= "INSERT INTO rom_innhold (innholdID, antall, romID) VALUES ((SELECT innhold.innholdID FROM innhold WHERE innhold.innholdID LIKE ?),?,(SELECT rom.romID from rom WHERE rom.romID LIKE ?)) ON DUPLICATE KEY UPDATE antall=VALUES(antall);";
    
    private final String leggTilAbonemenntBruker = "INSERT INTO abonemennt_bruker (eierID, brukerID) VALUES (?, ?)";
    private final String leggTilAbonemenntFag = "INSERT INTO abonemennt_fag (eierID, fagID) VALUES (?, ?)";
    private final String slettAbonemenntFag = "DELETE FROM abonemennt_fag WHERE eierID = ? AND fagID = ?";
    private final String slettAbonemenntBruker = "DELETE FROM abonemennt_bruker WHERE eierID = ? AND brukerID = ?";
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
    
    private final String getRomStart = "SELECT DISTINCT rom_bestilling.romID, rom.romnavn, rom.etasje, rom.type, rom.størrelse, rom.sitteplasser FROM rom, rom_bestilling LEFT OUTER JOIN rom_innhold ON rom_bestilling.romID = rom_innhold.romID WHERE rom.type = ? AND rom.romID = rom_bestilling.romID AND rom_bestilling.romID NOT IN (" +
        "SELECT rom_bestilling.romID FROM rom_bestilling WHERE (? BETWEEN rom_bestilling.dato_start AND rom_bestilling.dato_slutt) OR " +
        "(? BETWEEN rom_bestilling.dato_start AND rom_bestilling.dato_slutt) OR (? < rom_bestilling.dato_start AND ? > rom_bestilling.dato_start)) ";
    
    
    private final String getRomSlutt = "UNION SELECT DISTINCT rom.romID, rom.romnavn, rom.etasje, rom.type, rom.størrelse, rom.sitteplasser FROM rom LEFT OUTER JOIN rom_innhold ON rom.romID = rom_innhold.romID WHERE rom.type = ? AND rom.romID NOT IN (SELECT rom_bestilling.romID FROM rom_bestilling) ";
    private final String getRomMiddelStorrelse = "AND rom.størrelse >= ? ";
    private final String getRomMiddelSitteplasser ="AND rom.sitteplasser >= ? ";
    
    private final String getRom1Param = " AND rom_innhold.innholdID = ? AND rom_innhold.antall >= ?";
    
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
    private final String getFagKlasse = "SELECT klasse_fag.fagID, fagnavn from klasse_fag LEFT OUTER JOIN fag ON klasse_fag.fagID = fag.fagID WHERE klasseID LIKE ?";
    private final String leggTilBooking = "INSERT INTO rom_bestilling (romID, dato_start, dato_slutt, eierID, tilhorer_event) VALUES (?,?,?,?,?)";
    
    private final String getRomSVG = "SELECT DISTINCT rom_bestilling.romID, rom.romnavn, rom.etasje, rom.type, rom.størrelse, rom.sitteplasser FROM rom, rom_bestilling LEFT OUTER JOIN rom_innhold ON rom_bestilling.romID = rom_innhold.romID WHERE rom.type <= ? AND rom.romID = rom_bestilling.romID AND rom_bestilling.romID NOT IN (" +
        "SELECT rom_bestilling.romID FROM rom_bestilling WHERE (? BETWEEN rom_bestilling.dato_start AND rom_bestilling.dato_slutt) OR " +
        "(? BETWEEN rom_bestilling.dato_start AND rom_bestilling.dato_slutt) OR (? < rom_bestilling.dato_start AND ? > rom_bestilling.dato_start)) "+
        "UNION SELECT DISTINCT rom.romID, rom.romnavn, rom.etasje, rom.type, rom.størrelse, rom.sitteplasser FROM rom LEFT OUTER JOIN rom_innhold ON rom.romID = rom_innhold.romID WHERE rom.type <= ? AND rom.romID NOT IN (SELECT rom_bestilling.romID FROM rom_bestilling);";
    
   
    
    private final String getReserverteRom = "SELECT DISTINCT * FROM rom_bestilling WHERE eierID LIKE ? AND "
            + "(dato_start >= ? OR (? BETWEEN dato_start AND dato_slutt))";
    
    private final String slettBooking = "DELETE FROM rom_bestilling WHERE romID LIKE ? AND dato_start LIKE ? AND dato_slutt LIKE ? AND eierID like ?";
    
    private final String getRomBooking = "SELECT * FROM rom_bestilling WHERE romID LIKE ? AND dato_start LIKE ? AND eierID LIKE ?";
    private final String slettKalenderEvent = "DELETE FROM kalender_event WHERE bestillingsID LIKE ? AND eier LIKE ?";
    
    private final String leggTilEvent = "INSERT INTO kalender_event (dato_start, dato_slutt, eier, hidden, bestillingsID, type, fagID, descr, tittel, eier_navn) VALUES (?, ?, ?, ?, (SELECT rom_bestilling.bestillingsID from rom_bestilling WHERE rom_bestilling.bestillingsID LIKE ?), ?, ?, ?, ?, ?);";
    
    private final String getBrukerAbonnement = "SELECT eierID, brukerID AS abonererId, 0 AS abType FROM abonemennt_bruker WHERE brukerID = ?";
    
    private final String getAlleKLasser = "SELECT DISTINCT klasseID FROM klasse_fag";
    
    private final String erRomLedig = "SELECT * FROM rom_bestilling WHERE romID = ? AND (((dato_start BETWEEN ? AND ?) OR (dato_slutt BETWEEN ? AND ?)) OR  (dato_start < ? AND dato_slutt >?))";
    
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
        try{
            System.out.println(r.getRomID() + " "
            +r.getRomNavn() + " "
            +r.getType() + " "
            +r.getEtasje() + " "
            +r.getStorrelse() + " "
            +r.getAntStolplasser());
        int antallRader = jT.update(endreRom,new Object[]{
            r.getRomID(),
            r.getRomNavn(),
            r.getType(),
            r.getEtasje(),
            r.getStorrelse(),
            r.getAntStolplasser()
        });
        if(antallRader > 0){
            return true;
        }
        }catch(Exception e){}
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
            f.getFagID(),
            f.getNavn()
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
            r.getStorrelse(),
            r.getAntStolplasser()
        });
        if(antallRader > 0){
            return true;
        }
        return false;
    }
    
    @Override
    public boolean fjernKalenderEvent(KalenderEvent ke) {
        int antallRader = jT.update(fjernKalenderEvent,new Object[]{
            ke.getEpost(),
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
    //sql-tabell finnes ikke i databasen lengre

    @Override
    public Bruker getKalenderEventDeltaker(KalenderEvent ke, Bruker b) {
        return (Bruker) jT.queryForObject(getKalenderEventDeltaker, new Object[]{
            ke.getId(),
            b.getEpost()
        }, new BrukerMapper());
    }//sql-tabell finnes ikke i databasen lengre

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
    }//tatt

    @Override
    public List<KalenderEvent> getKalenderEventRomID(Rom r) {
        return jT.query(getKalenderEventRomID, new Object[]{
            r.getRomID()
        }, new KalenderEventMapper());
    }//tatt
    
    @Override
    public List<KalenderEvent> getKalenderEventHidden(KalenderEvent ke) {
        List<KalenderEvent> liste = jT.query(getKalenderEventHidden,new Object[]{
            ke.getEpost()}, new KalenderEventMapper());
        return liste;

    } //tatt

    @Override
    public List<Fag> getFagLaerer(Bruker b) {
        return jT.query(getFagLaerer, new Object[]{
            b.getEpost()
        }, new FagMapper());
    }//Får ikke tatt inn fagnavn til fagmapperen
    
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
    }//tatt
    
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
    }//tatt
    
    @Override
    public List<Rom> getRomFraNavn(Rom r) {
        List<Rom> liste = jT.query(getRomFraNavn, new Object[]{
        r.getRomNavn()
        }, new RomMapper());
        for (Rom rom : liste) {
            
        }
        return liste;
    }//tatt
    
    @Override
    public List<Rom> getRomFraNavn(String navn) {
        /*return (Bruker) jT.queryForObject
                (getBrukerEpost, new Object[]{epost},new BrukerMapper());*/
        List<Rom> liste = jT.query(getRomFraNavn, new Object[]{navn},
                new RomMapper());
        for (Rom rom : liste) {
            
        }
        return liste;
    }//tatt

    /*
    ikke i bruk
    @Override
    public List<Rom> getRomFraType(Rom r) {
        return jT.query(getRomFraType, new Object[]{
            r.getType()
        }, new RomMapper());
    }//tatt

    @Override
    public List<Rom> getRomFraStoerrelse(Rom r) {
        return jT.query(getRomFraStoerrelse, new Object[]{
            r.getStorrelse()
        }, new RomMapper());
    }//tatt
    */

    @Override
    public List<KalenderEvent> getAlleEventsFraBruker(BrukerB b){
        return jT.query(getAlleEventsFraBruker, new Object[]{
            b.getEpost(),
            b.getEpost(),
            b.getEpost()
        }, new KalenderEventMapper());
    }//tatt
    
    @Override
    public List<Abonemennt> getAbonnementDeltakere(String ke) {
        return jT.query(getAbonnementDeltakere, new Object[]{
            ke
        }, new AbonemenntMapper());
    }//feil i forhold til det som sendes til mapper
    
    @Override
    public List<Abonemennt> getAbonemenntFraBruker(BrukerB b){
        return jT.query(getAbonemenntFraBruker, new Object[]{
            b.getEpost(),
            b.getEpost()
        }, new AbonemenntMapper());
    }//tatt
    
    @Override
    public List<RomBestilling> getAlleBestillingerFraBruker(BrukerB b){
        return jT.query(getAlleBestillingerFraBruker, new Object[]{
            b.getEpost()
        }, new RomBestillingMapper());
    }//tatt
    
    @Override
    public List<Rom> getRomTypeStorrelse(Rom r) {
        return jT.query(getRomTypeStorrelse, new Object[]{
            r.getType(),
            r.getStorrelse()
        }, new RomMapper());
    }//får noe feil med romID-en
    
    @Override
    public List<Rom> getRom0Param(Rom r, KalenderEvent ke, boolean storrelse, boolean sitteplasser){
        String melding = getRomStart;
        if(storrelse && sitteplasser){
            melding += getRomMiddelSitteplasser + getRomMiddelStorrelse + getRomSlutt + getRomMiddelSitteplasser + getRomMiddelStorrelse;
            
            return jT.query(melding, new Object[]{
                //finn rekkefølge...
                /*
                Start:
                1: rom type
                2: nystartdato
                3: nysluttdato
                4: nystartdato
                5: nysluttdato
                
                Sitteplasser:
                1: sitteplasser
                
                Størrelse:
                1: størrelse
                
                Slutt:
                1: type
                
                Sitteplasser:
                1: sitteplasser
                
                Størrelse:
                1: størrelse
                
                */
                //start
                r.getType(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid(),
                //størrelse
                r.getStorrelse(),
                //sitteplasser
                r.getAntStolplasser(),
                //slutt
                r.getType(),
                //størrelse
                r.getStorrelse(),
                //sitteplasser
                r.getAntStolplasser()
                

            }, new RomMapper());
        }else if(sitteplasser){
            melding += getRomMiddelSitteplasser + getRomSlutt + getRomMiddelSitteplasser;
            System.out.println(melding);
            return jT.query(melding, new Object[]{
                /*
                Start:
                1: rom type
                2: nystartdato
                3: nysluttdato
                4: nystartdato
                5: nysluttdato
                
                Sitteplasser:
                1: sitteplasser
                
                Slutt:
                1: type
                
                Sitteplasser:
                1: sitteplasser
                
                */
                
                r.getType(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid(),
                r.getAntStolplasser(),
                r.getType(),
                r.getAntStolplasser()
            }, new RomMapper());
        }else if(storrelse){
            melding += getRomMiddelStorrelse + getRomSlutt + getRomMiddelStorrelse;
            return jT.query(melding, new Object[]{
                /*
                Start:
                1: rom type
                2: nystartdato
                3: nysluttdato
                4: nystartdato
                5: nysluttdato
                
                STORRELSE:
                1: størrelse
                SLUTT:
                1: Type
                
                STORRELSE:
                1: størrelse
                
                */
                //start
                r.getType(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid(),
                r.getStorrelse(),
                r.getType(),
                r.getStorrelse()
            
            }, new RomMapper());
        }
        return jT.query(melding + getRomSlutt, new Object[]{
            /*
            START
            1: rom type
            2: nystartdato
            3: nysluttdato
            4: nystartdato
            5: nysluttdato
            
            SLUTT:
            1: Type
            */
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
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
        String melding = getRomStart;
        if(storrelse && sitteplasser){
            melding += getRomMiddelStorrelse + getRomMiddelSitteplasser + getRom1Param + getRomSlutt  + getRomMiddelStorrelse + getRomMiddelSitteplasser + getRom1Param;
            return jT.query(melding, new Object[]{
                /*
                Start:
                1: rom type
                2: nystartdato
                3: nysluttdato
                4: nystartdato
                5: nysluttdato
                
                Størrelse:
                1: Størrelse
                
                Sitteplasser:
                1: Sitteplasser
                
                param1:
                1: innholdID
                2: antall
                
                Slutt:
                1: rom type
                
                størrelse:
                1: størrelse
                
                sitteplasser:
                1: sitteplasser
                
                param1:
                1: innholdID
                2: antall
                
                */
                
                //start
                r.getType(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid(),
                
                //størrelse
                r.getStorrelse(),
                
                //sitteplasser
                r.getAntStolplasser(),
                
                //param1
                tab.get(0),
                tab.get(1),
                
                //slutt
                r.getType(),
                
                //størrelse
                r.getStorrelse(),
                
                //sitteplasser
                r.getAntStolplasser(),
                
                //param1
                tab.get(0),
                tab.get(1)
            }, new RomMapper());
        }else if(sitteplasser){
            melding += getRomMiddelSitteplasser + getRom1Param + getRomSlutt  + getRomMiddelSitteplasser + getRom1Param;
            return jT.query(melding, new Object[]{
                //start
                r.getType(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid(),
                
                
                //sitteplasser
                r.getAntStolplasser(),
                
                //param1
                tab.get(0),
                tab.get(1),
                
                //slutt
                r.getType(),
                
                
                //sitteplasser
                r.getAntStolplasser(),
                
                //param1
                tab.get(0),
                tab.get(1)
            }, new RomMapper());
        }else if(storrelse){
            melding += getRomMiddelStorrelse + getRom1Param + getRomSlutt  + getRomMiddelStorrelse + getRom1Param;
            return jT.query(melding, new Object[]{
                //start
                r.getType(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid(),
                
                //størrelse
                r.getStorrelse(),
                
                
                //param1
                tab.get(0),
                tab.get(1),
                
                //slutt
                r.getType(),
                
                //størrelse
                r.getStorrelse(),
                
                
                //param1
                tab.get(0),
                tab.get(1)
                
            }, new RomMapper());
        }
        melding += getRom1Param + getRomSlutt + getRom1Param;
        return jT.query(melding, new Object[]{
            //start
                r.getType(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid(),
                
                
                //param1
                tab.get(0),
                tab.get(1),
                
                //slutt
                r.getType(),
                
                
                //param1
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
        String melding = getRomStart;
        if(storrelse && sitteplasser){
            melding += getRomMiddelStorrelse + getRomMiddelSitteplasser + getRom2Param + getRomSlutt + getRomMiddelStorrelse + getRomMiddelSitteplasser + getRom2Param;
            return jT.query(melding, new Object[]{
                //start
                r.getType(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid(),
                
                //storrelse
                r.getStorrelse(),
                
                //sitteplasser
                r.getAntStolplasser(),
                
                //rom2param
                tab.get(0),
                tab.get(1),
                tab.get(2),
                tab.get(3),
                
                //slutt
                r.getType(),
                
                //storrelse
                r.getStorrelse(),
                
                //sitteplasser
                r.getAntStolplasser(),
                
                //rom2param
                tab.get(0),
                tab.get(1),
                tab.get(2),
                tab.get(3)
            }, new RomMapper());
        }else if(sitteplasser){
            melding += getRomMiddelSitteplasser + getRom2Param + getRomSlutt + getRomMiddelSitteplasser + getRom2Param;
            return jT.query(melding, new Object[]{
                //start
                r.getType(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid(),
                
                
                //sitteplasser
                r.getAntStolplasser(),
                
                //rom2param
                tab.get(0),
                tab.get(1),
                tab.get(2),
                tab.get(3),
                
                //slutt
                r.getType(),
                
                
                //sitteplasser
                r.getAntStolplasser(),
                
                //rom2param
                tab.get(0),
                tab.get(1),
                tab.get(2),
                tab.get(3)

            }, new RomMapper());
        }else if(storrelse){
            melding += getRomMiddelStorrelse  + getRom2Param + getRomSlutt + getRomMiddelStorrelse + getRom2Param;
            return jT.query(melding, new Object[]{
                
                //start
                r.getType(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid(),
                
                //storrelse
                r.getStorrelse(),
                
                
                //rom2param
                tab.get(0),
                tab.get(1),
                tab.get(2),
                tab.get(3),
                
                //slutt
                r.getType(),
                
                //storrelse
                r.getStorrelse(),
                
                
                //rom2param
                tab.get(0),
                tab.get(1),
                tab.get(2),
                tab.get(3)
            }, new RomMapper());
        }
        melding +=  getRom2Param + getRomSlutt + getRom2Param;
        return jT.query(melding, new Object[]{
            
                //start
                r.getType(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid(),
                
                
                //rom2param
                tab.get(0),
                tab.get(1),
                tab.get(2),
                tab.get(3),
                
                //slutt
                r.getType(),
               
                
                
                //rom2param
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
        String melding = getRomStart;
        if(storrelse && sitteplasser){
            melding += getRomMiddelStorrelse + getRomMiddelSitteplasser + getRom3Param + getRomSlutt + getRomMiddelStorrelse + getRomMiddelSitteplasser + getRom3Param;
            return jT.query(melding, new Object[]{
                //start
                r.getType(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid(),
                
                //storrelse
                r.getStorrelse(),
                
                //sitteplasser
                r.getAntStolplasser(),
                
                //rom3param
                tab.get(0),
                tab.get(1),
                tab.get(2),
                tab.get(3),
                tab.get(4),
                tab.get(5),
                
                //slutt
                r.getType(),
                
                //storrelse
                r.getStorrelse(),
                
                //sitteplasser
                r.getAntStolplasser(),
                
                //rom3param
                tab.get(0),
                tab.get(1),
                tab.get(2),
                tab.get(3),
                tab.get(4),
                tab.get(5)
                
            }, new RomMapper());
        }else if(sitteplasser){
            melding += getRomMiddelSitteplasser + getRom3Param + getRomSlutt + getRomMiddelSitteplasser + getRom3Param;
            return jT.query(melding, new Object[]{
                //start
                r.getType(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid(),
                
                
                //sitteplasser
                r.getAntStolplasser(),
                
                //rom3param
                tab.get(0),
                tab.get(1),
                tab.get(2),
                tab.get(3),
                tab.get(4),
                tab.get(5),
                
                //slutt
                r.getType(),
                
                
                //sitteplasser
                r.getAntStolplasser(),
                
                //rom3param
                tab.get(0),
                tab.get(1),
                tab.get(2),
                tab.get(3),
                tab.get(4),
                tab.get(5)
            }, new RomMapper());
        }else if(storrelse){
            melding += getRomMiddelStorrelse  + getRom3Param + getRomSlutt + getRomMiddelStorrelse  + getRom3Param;
            return jT.query(melding, new Object[]{
                //start
                r.getType(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid(),
                
                //storrelse
                r.getStorrelse(),
                
                
                //rom3param
                tab.get(0),
                tab.get(1),
                tab.get(2),
                tab.get(3),
                tab.get(4),
                tab.get(5),
                
                //slutt
                r.getType(),
                
                //storrelse
                r.getStorrelse(),
                
                
                //rom3param
                tab.get(0),
                tab.get(1),
                tab.get(2),
                tab.get(3),
                tab.get(4),
                tab.get(5)
            }, new RomMapper());
        }
            melding += getRom3Param + getRomSlutt + getRom3Param;
            return jT.query(melding, new Object[]{
                //start
                r.getType(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid(),
                
                
                //rom3param
                tab.get(0),
                tab.get(1),
                tab.get(2),
                tab.get(3),
                tab.get(4),
                tab.get(5),
                
                //slutt
                r.getType(),
                
                
                //rom3param
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
        String melding = getRomStart;
        if(storrelse && sitteplasser){
            melding += getRomMiddelStorrelse + getRomMiddelSitteplasser + getRom4Param + getRomSlutt + getRomMiddelStorrelse + getRomMiddelSitteplasser + getRom4Param;
            return jT.query(melding, new Object[]{
                //start
                r.getType(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid(),
                
                //storrelse
                r.getStorrelse(),
                
                //sitteplasser
                r.getAntStolplasser(),
                
                //rom3param
                tab.get(0),
                tab.get(1),
                tab.get(2),
                tab.get(3),
                tab.get(4),
                tab.get(5),
                tab.get(6),
                tab.get(7),
                
                //slutt
                r.getType(),
                
                //storrelse
                r.getStorrelse(),
                
                //sitteplasser
                r.getAntStolplasser(),
                
                //rom3param
                tab.get(0),
                tab.get(1),
                tab.get(2),
                tab.get(3),
                tab.get(4),
                tab.get(5),
                tab.get(6),
                tab.get(7)
            }, new RomMapper());
        }else if(sitteplasser){
            melding +=  getRomMiddelSitteplasser + getRom4Param + getRomSlutt + getRomMiddelSitteplasser + getRom4Param;
            return jT.query(melding, new Object[]{
                //start
                r.getType(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid(),
                
                
                //sitteplasser
                r.getAntStolplasser(),
                
                //rom3param
                tab.get(0),
                tab.get(1),
                tab.get(2),
                tab.get(3),
                tab.get(4),
                tab.get(5),
                tab.get(6),
                tab.get(7),
                
                //slutt
                r.getType(),
                
                
                //sitteplasser
                r.getAntStolplasser(),
                
                //rom3param
                tab.get(0),
                tab.get(1),
                tab.get(2),
                tab.get(3),
                tab.get(4),
                tab.get(5),
                tab.get(6),
                tab.get(7)
            }, new RomMapper());
        }else if(storrelse){
            melding += getRomMiddelStorrelse  + getRom4Param + getRomSlutt + getRomMiddelStorrelse  + getRom4Param;
            return jT.query(melding, new Object[]{
                //start
                r.getType(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid(),
                
                //storrelse
                r.getStorrelse(),
                
                
                //rom3param
                tab.get(0),
                tab.get(1),
                tab.get(2),
                tab.get(3),
                tab.get(4),
                tab.get(5),
                tab.get(6),
                tab.get(7),
                
                //slutt
                r.getType(),
                
                //storrelse
                r.getStorrelse(),
                
                
                //rom3param
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
            melding += getRom4Param + getRomSlutt + getRom4Param;
            return jT.query(melding, new Object[]{
                //start
                r.getType(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid(),
                
                
                //rom3param
                tab.get(0),
                tab.get(1),
                tab.get(2),
                tab.get(3),
                tab.get(4),
                tab.get(5),
                tab.get(6),
                tab.get(7),
                
                //slutt
                r.getType(),
                
                
                //rom3param
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
    }//tatt
    
    @Override
    public boolean leggTilBooking(KalenderEvent ke){
        return (0<jT.update(leggTilBooking, new Object[]{
            ke.getRom(),
            ke.getStartTid(),
            ke.getSluttTid(),
            ke.getEpost(),
            ke.getTilhorerEvent()
        }));
    }// tatt
    
    @Override
    public List<Rom> getRomSVG(Rom r, KalenderEvent ke){
        return jT.query(getRomSVG, new Object[]{
            /*
            1: rom type
            2: nystartdato
            3: nysluttdato
            4: nystartdato
            5: nysluttdato
            6: Type
            */
            r.getType(),
            ke.getStartTid(),
            ke.getSluttTid(),
            ke.getStartTid(),
            ke.getSluttTid(),
            r.getType()
        }, new RomMapper());
    }//tatt
    
    @Override
    public List<RomBestilling> getReserverteRom(KalenderEvent ke){
        //System.out.println("Epost: " + ke.getEpost());
        //System.out.println("Starttid: " + ke.getStartTid());
        return jT.query(getReserverteRom, new Object[]{
            ke.getEpost(),
            ke.getStartTid(),
            ke.getStartTid()
        }, new RomBestillingMapper());
    }//Tatt
    
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
    public boolean leggTilEvent (KalenderEvent ke){
        //dato_start, dato_slutt, eier, hidden, bestillingsID, type, fagID, descr, tittel, eier_navn
        int antallRader = jT.update(leggTilEvent,new Object[]{
            ke.getStartTid(),
            ke.getSluttTid(),
            ke.getEpost(),
            ke.isPrivat(),
            ke.getBestillingsID(),
            ke.getType(),
            ke.getFag(),
            ke.getNotat(),
            ke.getTittel(),
            ke.getEierNavn()
        
        });
        if(antallRader > 0){
            return true;
        }
        return false;
    } 
    
    @Override
    public RomBestilling getRomBooking(KalenderEvent ke){
        return jT.queryForObject(getRomBooking, new Object[]{
            ke.getRom(),
            ke.getStartTid(),
            ke.getEpost()
        }, new RomBestillingMapper());
    }

    @Override
    public boolean slettKalenderEvent(RomBestilling r){
        return (0<jT.update(slettKalenderEvent, new Object[]{
            r.getBestillingsID(),
            r.getEierId()
        }));
    }
    
    @Override
    public List<Abonemennt> getBrukerAbonnement(String epost){
        return jT.query(getBrukerAbonnement, new Object[]{
            epost
        }, new AbonemenntMapper());
    }
    
    @Override
    public List<Klasse> getAlleKlasser(){
        return jT.query(getAlleKLasser, new KlasseMapper());
    }
    
    
    @Override
    public boolean erRomLedig(KalenderEvent ke) {
         try {
             RomBestilling temp = jT.queryForObject(erRomLedig,new Object[]{
                ke.getRom(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid(),
                ke.getStartTid(),
                ke.getSluttTid()
                }, new RomBestillingMapper());
         }catch(Exception e) {
             System.out.println(e.fillInStackTrace());
             return true;
         }
        return false;
    }
    
    @Override
    public List<Fag> getFagKlasse(String k){
        return jT.query(getFagKlasse, new Object[]{
            k
        }, new FagMapper());
    }
    
    @Override
    public boolean leggTilFagKlasse(String fag, String klasse){
        return (0<jT.update(leggTilFagKlasse, new Object[]{
            fag,
            klasse
        }));
    }
    
    @Override
    public List<String> getAlleInnholdRom(Rom r){
        return (jT.query(getAlleInnholdRom, new Object[]{
            r.getRomID()
        }, new RomInnholdMapper()));
    }
    
    @Override
    public boolean oppdaterInnholdRom(String romID, String[] innhold){
        //"INSERT INTO rom_innhold (innholdID, antall, romID) VALUES (?,?, ?) ON DUPLICATE KEY UPDATE antall=VALUES(antall);"
        return 0<jT.update(oppdaterInnholdRom, new Object[]{
            innhold[0],
            innhold[1],
            romID});
    }
}
