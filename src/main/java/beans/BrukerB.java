/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;

/**
 *
 * @author Stein-Erik
 */
public class BrukerB {
    
    private String navn;
    private String epost;
    private Klasse klasse;
    private String notat;
    private int tilgangniva;
    private ArrayList<KalenderEvent> kalenderEvents;

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public Klasse getKlasse() {
        return klasse;
    }

    public void setKlasse(Klasse klasse) {
        this.klasse = klasse;
    }

    public String getNotat() {
        return notat;
    }

    public void setNotat(String notat) {
        this.notat = notat;
    }

    public int getTilgangniva() {
        return tilgangniva;
    }

    public void setTilgangniva(int tilgangniva) {
        this.tilgangniva = tilgangniva;
    }

    public ArrayList<KalenderEvent> getKalenderEvents() {
        return kalenderEvents;
    }

    public void setKalenderEvents(ArrayList<KalenderEvent> kalenderEvents) {
        this.kalenderEvents = kalenderEvents;
    }
    
}
