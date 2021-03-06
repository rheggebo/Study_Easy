/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Stein-Erik
 */
public class BrukerB {
    private String fornavn;
    private String etternavn;
    private String epost;
    private Klasse klasse;
    private String notat;
    private int tilgangsniva;
    private ArrayList<KalenderEvent> kalenderEvents;
    private boolean innlogget;
    private Date fodedato;
    private int telefonnummer;
    
    public BrukerB(){
        
    }
    
    public BrukerB(Bruker b){
        fornavn = b.getFornavn();
        etternavn = b.getEtternavn();
        epost = b.getEpost();
        klasse = b.getKlasse();
        notat = b.getNotat();
        tilgangsniva = b.getTilgangsniva();
        kalenderEvents = b.getKalenderEvents();
        innlogget = false;
        fodedato= b.getFodedato();
        telefonnummer = b.getTelefonnummer();
    }

     public String getFornavn() {
        return fornavn;
    }
    
    public void setFornavn(String fornavn){
        this.fornavn = fornavn;
    }
    
    public String getEtternavn(){
        return etternavn;
    }
    
    public void setEtternavn(String etternavn){
        this.etternavn = etternavn;
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

    public Date getFodedato() {
        return fodedato;
    }

    public void setFodedato(Date fodedato) {
        this.fodedato = fodedato;
    }

    public int getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(int telefonnummer) {
        this.telefonnummer = telefonnummer;
    }
    
    
    
}