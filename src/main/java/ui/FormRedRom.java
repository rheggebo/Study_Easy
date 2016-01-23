/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

/**
 *
 * @author Ingvild
 */
public class FormRedRom {
    private String romID;
    private String romNavn;
    private int romType;
    private int romStr;
    private int antSittePl;
    private int antSkjermer;
    private int antTavler;
    private int antProsjektorer;
    
    
    public String getRomID() {
        return romID;
    }

    public void setRomId(String romID) {
        this.romID = romID;
    }

    public String getRomNavn() {
        return romNavn;
    }

    public void setRomNavn(String romNavn) {
        this.romNavn = romNavn;
    }

    public int getRomType() {
        return romType;
    }

    public void setRomType(int romType) {
        this.romType = romType;
    }

    public int getRomStr() {
        return romStr;
    }

    public void setRomStr(int romStr) {
        this.romStr = romStr;
    }

    public int getAntSittePl() {
        return antSittePl;
    }

    public void setAntSittePl(int antSittePl) {
        this.antSittePl = antSittePl;
    }

    public int getAntSkjermer() {
        return antSkjermer;
    }

    public void setAntSkjermer(int antSkjermer) {
        this.antSkjermer = antSkjermer;
    }

    public int getAntTavler() {
        return antTavler;
    }

    public void setAntTavler(int antTavler) {
        this.antTavler = antTavler;
    }

    public int getAntProsjektorer() {
        return antProsjektorer;
    }

    public void setAntProsjektorer(int antProsjektorer) {
        this.antProsjektorer = antProsjektorer;
    }    
    
}
