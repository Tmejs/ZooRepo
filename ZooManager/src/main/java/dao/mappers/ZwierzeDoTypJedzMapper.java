/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mappers;

import dao.model.ZwierzeDoTypJedzenia;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author TKK
 */
public class ZwierzeDoTypJedzMapper implements IMapResultSetIntoEntity<ZwierzeDoTypJedzenia>{

    @Override
    public ZwierzeDoTypJedzenia map(ResultSet rs) throws SQLException {

        return new ZwierzeDoTypJedzenia(rs.getInt("id"),rs.getInt("id_zwierze"),rs.getInt("id_typ_jedzenia") );
        
    }
    
    
}
