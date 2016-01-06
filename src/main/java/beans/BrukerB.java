/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Stein-Erik
 */
public class BrukerB {
    
    private String brukernavn;
    private int tilgangsNiva;
    private boolean innlogget;

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public int getTilgangsNiva() {
        return tilgangsNiva;
    }

    public void setTilgangsNiva(int tilgangsNiva) {
        this.tilgangsNiva = tilgangsNiva;
    }

    public boolean isInnlogget() {
        return innlogget;
    }

    public void setInnlogget(boolean innlogget) {
        this.innlogget = innlogget;
    }
    
}
