/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mappers;

import dao.model.Wybieg;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author TKK
 */
public class WybiegMapper implements IMapResultSetIntoEntity<Wybieg> {

    @Override
    public Wybieg map(ResultSet rs) throws SQLException {
        Wybieg wybieg = new Wybieg();

        wybieg.setId(rs.getInt("id"));
        wybieg.setIdRodzajWybiegu(rs.getInt("id_typ_wybiegu"));
        Wybieg.STAN_WYBIEGU stanWybiegu = Wybieg.STAN_WYBIEGU.valueOf(rs.getString("stan_wybiegu"));
        wybieg.setStanWybiegu(stanWybiegu);
        return wybieg;
    }

}
