/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author henri
 */
public class LeggTilFagKlasse {
    private String klasseID;
    private String fagID;
    private ArrayList<String> klasseListe = new ArrayList<String>();
    private ArrayList<String> fagListe = new ArrayList<String>();

    public List getKlasseListe() {
        return Arrays.asList(klasseListe);
    }

    public void addKlasseListe(String item) {
        klasseListe.add(item);
    }

    public List getFagListe() {
        return Arrays.asList(fagListe);
    }

    public void addFagListe(String item) {
        fagListe.add(item);
    }

    public String getKlasseID() {
        return klasseID;
    }

    public void setKlasseID(String klasseID) {
        this.klasseID = klasseID;
    }

    public String getFagID() {
        return fagID;
    }

    public void setFagID(String fagID) {
        this.fagID = fagID;
    }
}
