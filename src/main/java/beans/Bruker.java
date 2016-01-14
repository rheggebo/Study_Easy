package beans;
import beans.KalenderEvent;
import beans.Klasse;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Bruker {
    @NotEmpty
    private String fornavn;
    @NotEmpty
    private String etternavn;
    private String passord;
    @Email
    private String epost;
    private Klasse klasse;
    private String notat;
    private int tilgangniva;
    private Date fodedato;
    private int telefonnummer;
    private ArrayList<KalenderEvent> kalenderEvents;

    public Date getFodedato() {
        return fodedato;
    }

    public void setFodedato(Date fodedato) {
        this.fodedato = fodedato;
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

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
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

    public int getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(int telefonnummer) {
        this.telefonnummer = telefonnummer;
    }
    
    public String toString(){
        return epost + fornavn + etternavn + tilgangniva;
    }
    
    
}