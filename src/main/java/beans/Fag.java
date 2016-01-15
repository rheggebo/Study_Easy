package beans;

import java.util.ArrayList;

public class Fag {
    private String fagID;
    private String navn;
    private ArrayList<Bruker> laerere;
    private ArrayList<KalenderEvent> kalenderEvents;

    public String getFagID() {
        return fagID;
    }

    public void setFagID(String fagID) {
        this.fagID = fagID;
    }
    
   
    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }


    public ArrayList<Bruker> getLaerere() {
        return laerere;
    }

    public void setLaerere(ArrayList<Bruker> laerere) {
        this.laerere = laerere;
    }

    public ArrayList<KalenderEvent> getKalenderEvents() {
        return kalenderEvents;
    }

    public void setKalenderEvents(ArrayList<KalenderEvent> kalenderEvents) {
        this.kalenderEvents = kalenderEvents;
    }
    
    public String toString() {
        return fagID + " " + navn;
    }
}

/*    private final String leggTilRom = "INSERT INTO ROM VALUES(?,?,?,?,?)";
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
*/