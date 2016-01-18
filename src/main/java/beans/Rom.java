package beans;

import java.util.ArrayList;

public class Rom {
    private String romID;
    private String romNavn;
    private int etasje;
    private int type;
    private int antStolplasser;
    private int storrelse;
    private ArrayList<String> innhold; //syntax:innhold antall

    public ArrayList<String> getInnhold() {
        return innhold;
    }

    public void setInnhold(ArrayList<String> innhold) {
        this.innhold = innhold;
    }

    public int getStorrelse() {
        return storrelse;
    }

    public void setStorrelse(int storrelse) {
        this.storrelse = storrelse;
    }

    public String getRomNavn() {
        return romNavn;
    }

    public void setRomNavn(String romNavn) {
        this.romNavn = romNavn;
    }

    public String getRomID() {
        return romID;
    }

    public void setRomID(String romID) {
        this.romID = romID;
    }

    public int getEtasje() {
        return etasje;
    }

    public void setEtasje(int etasje) {
        this.etasje = etasje;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public String toString() {
        return "Rom: " + romID + " " + romNavn + " etasje: " + etasje + " størrelse: " + storrelse;
    }
    
    public int getAntStolplasser(){
        return antStolplasser;
    }
    
    public void setAntStolplasser(int antStolplasser) {
        this.antStolplasser = antStolplasser;
    }
}
