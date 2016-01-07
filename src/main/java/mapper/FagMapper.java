/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;


import beans.Fag;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Stein-Erik
 */
public class FagMapper implements RowMapper<Fag>{

    @Override
    public Fag mapRow(ResultSet rs, int i) throws SQLException {
        Fag fag = new Fag();
        fag.setFagID(rs.getString("id"));
        return fag;
        
    }
    
}
