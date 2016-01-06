package beans;

import java.util.ArrayList;

public class Klasse {
    private String navn;
    private ArrayList<Fag> fag;

    public ArrayList<Fag> getFag() {
        return fag;
    }

    public void setFag(ArrayList<Fag> fag) {
        this.fag = fag;
    }

    public String getNavn() {

        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
}
