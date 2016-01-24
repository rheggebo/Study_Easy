/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import beans.Fag;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Henrik
 */
public class FormFinnRom {
    private String notat;
    private int skjerm;
    private int tavle;
    private int projektor;
    private int sitteplasser;
    private int storrelse;
    private String tittel;
    private String type;
    private String romtype;
    private Date fraDato;
    private int fraTid;
    private int varighet;
    private String fag;
    private boolean lagHendelse;
    private ArrayList<String> fagListe = new ArrayList<String>();
    
    public String getRomtype() {
        return romtype;
    }

    public void setRomtype(String romtype) {
        this.romtype = romtype;
    }


    public String getNotat() {
        return notat;
    }

    public void setNotat(String notat) {
        this.notat = notat;
    }

    public String getFag() {
        return fag;
    }

    public void setFag(String fag) {
        this.fag = fag;
    }

    public int getStorrelse() {
        return storrelse;
    }

    public void setStorrelse(int storrelse) {
        this.storrelse = storrelse;
    }

    public String getTittel() {
        return tittel;
    }

    public void setTittel(String tittel) {
        this.tittel = tittel;
    }
    

    public List getTypeList(){
        String[] s = {"Ikke lag hendelse","Privat","Forelesning","Møte"};
        return Arrays.asList(s);
    }
    
    public List getRomTypeListScrub(){
        String[] s ={"Grupperom"};
        return Arrays.asList(s);
    }
    
    public List getRomtypeList(){
        String[] s = {"Forelesningssal","Klasserom","Grupperom"};
        return Arrays.asList(s);
    }
    
    public List getUtstyrList(){
        String[] s = {"Projektor","Skjerm","Tavle",""};
        return Arrays.asList(s);
    }

    public int getSkjerm() {
        return skjerm;
    }

    public void setSkjerm(int skjerm) {
        this.skjerm = skjerm;
    }

    public int getTavle() {
        return tavle;
    }

    public void setTavle(int tavle) {
        this.tavle = tavle;
    }

    public int getProjektor() {
        return projektor;
    }

    public void setProjektor(int projektor) {
        this.projektor = projektor;
    }

    public int getSitteplasser() {
        return sitteplasser;
    }

    public void setSitteplasser(int sitteplass) {
        this.sitteplasser = sitteplass;
    }
    
    public List getTiderList(){
        int tidMin = 6;
        int tidMax = 23;
        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY)+1;
        if (hour<tidMin){
            hour = tidMin;
        }
        String[] s = new String[tidMax-hour+1];
        int time;
        for (int i = 0; i <= tidMax-hour; i++) {
            time = hour+i;
            if (time<10){
                s[i] = "0"+ time+"00"; 
                System.out.println("--------------------"+s[i]);
            } else{
                s[i] = time+"00"; 
            }
        }
        return Arrays.asList(s);
    }
    
    public List getVarighetList(){
        String[] s = {"1","2","3"};
        return Arrays.asList(s);
    }
    
    public void addFagListe(String item){
        fagListe.add(item);
    }
    
    public ArrayList<String> getFagList(){
        return fagListe;
    }
    
    
    public void setType(String type) {
        this.type = type;
    }

    public void setFraDato(Date fraDato) {
        this.fraDato = fraDato;
    }

    public void setFraTid(int fraTid) {
        this.fraTid = fraTid;
    }

    public void setVarighet(int varighet) {
        this.varighet = varighet;
    }
    
    

    public String getType() {
        return type;
    }

    public Date getFraDato() {
        return fraDato;
    }

    public int getFraTid() {
        return fraTid;
    }

    public int getVarighet() {
        return varighet;
    }

    public boolean isLagHendelse() {
        return lagHendelse;
    }

    public void setLegHendelse(boolean lagHendelse) {
        this.lagHendelse = lagHendelse;
    }
}
