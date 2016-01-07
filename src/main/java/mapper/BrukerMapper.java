/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import beans.Bruker;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Stein-Erik
 */
public class BrukerMapper implements RowMapper<Bruker>{

    @Override
    public Bruker mapRow(ResultSet rs, int i) throws SQLException {
        Bruker bruker = new Bruker();
        
        bruker.(rs.getString("brukernavn"));
        bruker.setPassord(rs.getString("passord"));
        bruker.setEmail(rs.getString("email"));
        bruker.setTilgang(rs.getInt("tilgang"));
        
        return bruker;
    }
    
}
