package beans;

import java.util.Date;

public class KalenderEvent {
    private int iD;
    private Bruker eier;
    private Date startDato;
    private Date sluttDato;
    private Rom rom;
    private Fag fag;
    private int type;
    private boolean privat;
    private String notat;
    
    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
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
    
    public Bruker getEier() {
        return eier;
    }

    public void setEier(Bruker eier) {
        this.eier = eier;
    }

    public Date getStartDato() {
        return startDato;
    }

    public void setStartDato(Date startDato) {
        this.startDato = startDato;
    }

    public Rom getRom() {
        return rom;
    }

    public void setRom(Rom rom) {
        this.rom = rom;
    }

    public Fag getFag() {
        return fag;
    }

    public void setFag(Fag fag) {
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
}
