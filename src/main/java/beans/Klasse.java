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
    
    public void addFag(Fag fag) {
        this.fag.add(fag);
    }

    public String getNavn() {

        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
    
    public String toString(){
        String str = "Klasse: " + navn + ", Fag: ";
        for (int i = 0; i < fag.size(); i++) {
            str += fag.get(i).getFagID() + " ";
        }
        return str;
    }
}
