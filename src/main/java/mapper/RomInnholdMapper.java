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
public class RomInnholdMapper implements RowMapper<String>{

    @Override
    public String mapRow(ResultSet rs, int i) throws SQLException {
        return rs.getString("innholdID")+" "+rs.getInt("antall");
    }
    
}
