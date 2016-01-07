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
    private int tilgangsniva;
    private ArrayList<KalenderEvent> kalenderEvents;
    private boolean innlogget;
    
    public BrukerB(){
        
    }
    
    public BrukerB(Bruker b){
        navn = b.getNavn();
        epost = b.getEpost();
        klasse = b.getKlasse();
        notat = b.getNotat();
        tilgangsniva = b.getTilgangniva();
        kalenderEvents = b.getKalenderEvents();
        innlogget = false;
    }

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

    public int getTilgangsniva() {
        return tilgangsniva;
    }

    public void setTilgangsniva(int tilgangsniva) {
        this.tilgangsniva = tilgangsniva;
    }

    public ArrayList<KalenderEvent> getKalenderEvents() {
        return kalenderEvents;
    }

    public void setKalenderEvents(ArrayList<KalenderEvent> kalenderEvents) {
        this.kalenderEvents = kalenderEvents;
    }
    
    public boolean isInnlogget(){
        return innlogget;
    }
    
    public void setInnlogget(boolean innlogget){
        this.innlogget = innlogget;
    }
    
}
