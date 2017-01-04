/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mappers;

import dao.model.ZwierzeDoTypWybiegu;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author TKK
 */
public class ZwierzeDoTypWybMapper implements IMapResultSetIntoEntity<ZwierzeDoTypWybiegu>{

    @Override
    public ZwierzeDoTypWybiegu map(ResultSet rs) throws SQLException {
ZwierzeDoTypWybiegu mapper = new ZwierzeDoTypWybiegu(rs.getInt("id"),rs.getInt("id_zwierze"),rs.getInt("id_typ_wybiegu"));

        return mapper;
    }
    
    
}
