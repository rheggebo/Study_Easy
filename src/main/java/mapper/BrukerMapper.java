/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import beans.Bruker;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
        
        bruker.setEpost(rs.getString("epost"));
        bruker.setPassord(rs.getString("passord"));
        bruker.setTilgangsniva(rs.getInt("type"));
        bruker.setFornavn(rs.getString("fornavn"));
        bruker.setEtternavn(rs.getString("etternavn"));
        
        
        return bruker;
    }
    
}
