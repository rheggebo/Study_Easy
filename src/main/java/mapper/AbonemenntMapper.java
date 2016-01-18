/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import beans.Abonemennt;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Arne
 */
public class AbonemenntMapper implements RowMapper<Abonemennt>{
    @Override
    public Abonemennt mapRow(ResultSet rs, int i) throws SQLException {
        Abonemennt abonemennt = new Abonemennt();
        
        abonemennt.setEierId(rs.getString("eierID"));
        abonemennt.setAbonererId(rs.getString("abonererId"));
        abonemennt.setType(rs.getInt("abType"));
        return abonemennt;
    }
    
}
