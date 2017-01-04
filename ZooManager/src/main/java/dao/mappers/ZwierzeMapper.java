/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mappers;

import dao.model.Zwierze;
//import dao.model.RodzajJedzenia;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author TKK
 */
public class ZwierzeMapper implements IMapResultSetIntoEntity<Zwierze> {

    @Override
    public Zwierze map(ResultSet rs) throws SQLException {

        Zwierze zwierze = new Zwierze();

        zwierze.setId(rs.getInt("id"));
        zwierze.setNazwa(rs.getString("nazwa"));
        zwierze.setTempoJedzenia(rs.getInt("tempo_jedzenia"));
        zwierze.setPoziomNajedzenia(rs.getInt("poziom_najedzenia"));
        zwierze.setIdWybiegu(rs.getInt("id_wybiegu"));

        return zwierze;
    }

}
