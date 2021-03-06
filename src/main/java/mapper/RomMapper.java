/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import beans.Rom;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Stein-Erik
 */
public class RomMapper implements RowMapper<Rom>{

    @Override
    public Rom mapRow(ResultSet rs, int i) throws SQLException {
        Rom rom = new Rom();
        rom.setRomID(rs.getString("romID"));
        rom.setRomNavn(rs.getString("romnavn"));
        rom.setEtasje(rs.getInt("etasje"));
        rom.setType(rs.getInt("type"));
        rom.setStorrelse(rs.getInt("størrelse"));
        rom.setAntStolplasser(rs.getInt("sitteplasser"));
        return rom;
        
    }
    
}
