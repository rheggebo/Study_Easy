/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Stein-Erik
 */
public class StringMapper implements RowMapper<String[][]>{
    
    /*public StringMapper(int i){
        
    }*/
    
    @Override
    public String[][] mapRow(ResultSet rs, int i) throws SQLException {
        //String[][] tabell = new String[][];
        return null;
    }
}
