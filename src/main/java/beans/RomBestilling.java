/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Arne
 */
public class RomBestilling {
    private String romId;
    private Timestamp startDato;
    private Timestamp sluttDato;
    private String eierId;
    private int tilhorerEvent;
    private int bestillingsID;
    private boolean klokkesjekk;
    private boolean sjekketInn;
     
    public String getRomId(){
        return romId;
    }
    public Timestamp getStartDato(){
        return startDato;
    }
    public Timestamp getSluttDato(){
        return sluttDato;
    }
    public String getEierId(){
        return eierId;
    }
    public void setRomId(String romId){
        this.romId = romId;
    }
    public void setStartDato(Timestamp startDato){
        this.startDato = startDato;
    }
    public void setSluttDato(Timestamp sluttDato){
        this.sluttDato = sluttDato;
    }
    public void setEierId(String eierId){
        this.eierId = eierId;
    }
    public int getTilhorerEvent(){
        return tilhorerEvent;
    }
    public void setTilhorerEvent(int tilhorerEvent){
        this.tilhorerEvent = tilhorerEvent;
    }

    public int getBestillingsID() {
        return bestillingsID;
    }

    public void setBestillingsID(int bestillingsID) {
        this.bestillingsID = bestillingsID;
    }

    public boolean isKlokkesjekk() {
        return klokkesjekk;
    }

    public void setKlokkesjekk(boolean klokkesjekk) {
        this.klokkesjekk = klokkesjekk;
    }

    public boolean isSjekketInn() {
        return sjekketInn;
    }

    public void setSjekketInn(boolean sjekketInn) {
        this.sjekketInn = sjekketInn;
    }
       
    public String toString(){
        return "Rom: "+romId+" Start-tid: "+startDato+" Slutt-tid: "+sluttDato;
    }
    
}
