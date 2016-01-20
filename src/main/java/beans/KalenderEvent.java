package beans;

import java.sql.Timestamp;
import java.util.Date;

public class KalenderEvent {
    private int id;
    private String epost;
    private String eierNavn;
    private Timestamp startTid;
    private Timestamp sluttTid;
    private String rom;
    private String fag;
    private int type;
    private boolean privat;
    private String notat;
    private String tittel;
    private int tilhorerEvent;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }


    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public String getFag() {
        return fag;
    }

    public void setFag(String fag) {
        this.fag = fag;
    }

    public boolean isPrivat() {
        return privat;
    }

    public void setPrivat(boolean privat) {
        this.privat = privat;
    }

    public String getNotat() {
        return notat;
    }

    public void setNotat(String notat) {
        this.notat = notat;
    }
    
    public void setEierNavn(String eierNavn){
        this.eierNavn = eierNavn;
    }
    public String getEierNavn(){
        return eierNavn;
    }
    
    public String getTittel(){
        return tittel;
    }
    public void setTittel(String tittel){
        this.tittel = tittel;
    }
    
    public Timestamp getStartTid(){
        return startTid;
    }
    public void setStartTid(Timestamp startTid){
        this.startTid = startTid;
    }
    public Timestamp getSluttTid(){
        return sluttTid;
    }
    public void setSluttTid(Timestamp sluttTid){
        this.sluttTid = sluttTid;
    }

    public int getTilhorerEvent() {
        return tilhorerEvent;
    }

    public void setTilhorerEvent(int tilhorerEvent) {
        this.tilhorerEvent = tilhorerEvent;
    }
    
}
