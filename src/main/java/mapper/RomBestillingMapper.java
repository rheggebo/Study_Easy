/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import beans.RomBestilling;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Arne
 */
public class RomBestillingMapper implements RowMapper<RomBestilling>{
    
    @Override
    public RomBestilling mapRow(ResultSet rs, int i) throws SQLException {
        RomBestilling bestilling = new RomBestilling();
        bestilling.setRomId(rs.getString("romID"));
        bestilling.setStartDato(rs.getTimestamp("dato_start"));
        bestilling.setSluttDato(rs.getTimestamp("dato_slutt"));
        bestilling.setEierId(rs.getString("eierID"));
        return bestilling;
    }
    
}
