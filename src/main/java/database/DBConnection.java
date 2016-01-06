/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import mapper.BrukerMapper;
import mapper.StringMapper;
import beans.Bruker;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author stein.erik.bjornnes
 */
public class DBConnection implements DBInterface{
    
    private final String getBrukerNavn = "SELECT * FROM BRUKER WHERE BRUKERNAVN=?";
    private final String getBrukerEmail = "SELECT * FROM BRUKER WHERE EMAIL=?";
    private final String endreBruker = "UPDATE BRUKER SET PASSORD=?, EMAIL=?, ADMIN=? WHERE BRUKERNAVN=?";
    private final String nyBruker = "INSERT INTO BRUKER VALUES(?,?,?,?)";
    private final String slettBruker = "DELETE FROM BRUKER WHERE BRUKERNAVN=?";
    private final String alleBrukere = "SELECT * FROM BRUKER";
    
    
    
    private DataSource dS;
    private JdbcTemplate jT;
    
    @Autowired
    private void setDatabaseSource(DataSource dS){
        this.dS = dS;
        jT = new JdbcTemplate(dS);
    }

    @Override
    public Bruker getBrukerN(String brukernavn) {
        System.out.println("Bruker hentet fra DB: ");
        return (Bruker) jT.queryForObject(getBrukerNavn, new Object[]{brukernavn},new BrukerMapper());
    
    }

    @Override
    public Bruker getBrukerE(String email) {
        return (Bruker) jT.queryForObject(getBrukerEmail, new Object[]{email},new BrukerMapper());
    }
    
    @Override
    public boolean loggInn(String brukernavn, String passord) {
        Bruker bruker = (Bruker) jT.queryForObject(getBrukerNavn, new Object[]{brukernavn},new BrukerMapper());
        
        if(bruker.getPassord().equals(passord)){
            return true;
        }
        return false;
    }

    @Override
    public boolean oppdaterBruker(Bruker b) {
        int antallRader = jT.update(endreBruker, new Object[]{
            b.getPassord(),
            b.getEmail(),
            b.getTilgang(),
            b.getBrukernavn()});
        if(antallRader>0){
            return true;
        }
        return false;   
    }

    @Override
    public boolean slettBruker(Bruker b) {
        int antallRader = jT.update(slettBruker,new Object[]{b.getBrukernavn()});
        if(antallRader > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean leggTilBruker(Bruker b) {
        int antallRader = jT.update(nyBruker,new Object[]{
            b.getBrukernavn(),
            b.getPassord(),
            b.getEmail(),
            b.getTilgang()
        });
        if(antallRader > 0){
            return true;
        }
        return false;
    }
    

    @Override
    public List<Bruker> hentAlleBrukere() {
        return jT.query(alleBrukere, new BrukerMapper());
    }
    
}
