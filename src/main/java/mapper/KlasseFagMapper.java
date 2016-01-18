/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;


import beans.Fag;
import beans.Klasse;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author kasperrostvold
 */
public class KlasseFagMapper implements RowMapper<Klasse> {

    @Override
    public Klasse mapRow(ResultSet rs, int i) throws SQLException {
        
        Klasse klasse = new Klasse();
        ArrayList<Fag> fagListe = new ArrayList<Fag>();
        klasse.setNavn(rs.getString("klasseid"));
        Fag fag = new Fag();
        fag.setFagID(rs.getString("fagid"));
        fagListe.add(fag);
        klasse.setFag(fagListe);
        return klasse;

    }
    
}
