package beans;

import java.util.Date;

public class KalenderEvent {
    private String navn;
    private Bruker eier;
    private Date startDato;
    private int varighet;
    private Rom rom;
    private Fag fag;
    private String type;
    private boolean privat;
    private String notat;

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
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

    public int getVarighet() {
        return varighet;
    }

    public void setVarighet(int varighet) {
        this.varighet = varighet;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
