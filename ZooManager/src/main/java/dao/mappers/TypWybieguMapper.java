/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mappers;

import dao.model.TypWybiegu;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author TKK
 */
public class TypWybieguMapper implements IMapResultSetIntoEntity<TypWybiegu> {

    @Override
    public TypWybiegu map(ResultSet rs) throws SQLException {
        TypWybiegu typWybiegu = new TypWybiegu();
        
        //TypWybiegu.RodzajWybiegu.valueOf(rs.getString("typ_wybiegu"));
        
        typWybiegu.setRodzajWybiegu(TypWybiegu.RodzajWybiegu.valueOf(rs.getString("typ_wybiegu")));
        return typWybiegu;
    }

}
