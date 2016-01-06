package beans;

import java.util.ArrayList;

public class Fag {
    private String navn;
    private Klasse klasse;
    private ArrayList<Bruker> laerere;
    private ArrayList<KalenderEvent> kalenderEvents;

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Klasse getKlasse() {
        return klasse;
    }

    public void setKlasse(Klasse klasse) {
        this.klasse = klasse;
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
}
