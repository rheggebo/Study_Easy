package beans;

import java.util.Date;

public class KalenderEvent {
    private int id;
    private String eier;
    private String eierNavn;
    private Date startDato;
    private Date sluttDato;
    private String rom;
    private String fag;
    private int type;
    private boolean privat;
    private String notat;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSluttDato() {
        return sluttDato;
    }

    public void setSluttDato(Date sluttDato) {
        this.sluttDato = sluttDato;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public String getEier() {
        return eier;
    }

    public void setEier(String eier) {
        this.eier = eier;
    }

    public Date getStartDato() {
        return startDato;
    }

    public void setStartDato(Date startDato) {
        this.startDato = startDato;
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
}
