/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import beans.KalenderEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Stein-Erik
 */
public class KalenderEventMapper implements RowMapper<KalenderEvent>{

    @Override
    public KalenderEvent mapRow(ResultSet rs, int i) throws SQLException {
        KalenderEvent event = new KalenderEvent();
        event.setId(rs.getInt("id"));
        event.setEier(rs.getString("epost"));
        event.setEierNavn(rs.getString("navn"));
        event.setStartDato(rs.getDate("dato_start"));
        event.setSluttDato(rs.getDate("dato_slutt"));
        event.setRom(rs.getString("romID"));
        event.setFag(rs.getString("fagID"));
        
        return event;
    }
    
}
