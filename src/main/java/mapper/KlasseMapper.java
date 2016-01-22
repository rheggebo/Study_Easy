/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;
import beans.Klasse;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author henri
 */
public class KlasseMapper implements RowMapper<Klasse> {

    @Override
    public Klasse mapRow(ResultSet rs, int i) throws SQLException {
        
        Klasse klasse = new Klasse();
        klasse.setNavn(rs.getString("klasseID"));
        return klasse;

    }
}
