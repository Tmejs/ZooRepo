/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mappers;

import dao.model.APracownik;
import dao.model.Typ_Pracownika;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author TKK
 */
public class PracownikMapper implements IMapResultSetIntoEntity<APracownik> {

    @Override
    public APracownik map(ResultSet rs) throws SQLException {

        Integer id = rs.getInt("id");
        String imie = rs.getString("imie");
        String nazwisko = rs.getString("nazwisko");
        Double pensja = rs.getDouble("pensja");
        Typ_Pracownika typPracownika = Typ_Pracownika.valueOf(rs.getString("typ_pracownika"));

//        return new APracownik(int id, imie, nazwisko, 0, TYP_PRACOWNIKA.TECHNICZNY)
        //wyciagamy typ pracownika z enum przez ztring wiec po zmianach switch jest zbedny
//int typ_pracownika=rs.getInt("typ_pracownika");
//        switch (TYP_PRACOWNIKA.getEnumById(typ_pracownika)){
//            case OPIEKUN:
//                return new PracownikOpiekun(rs.getInt("id"), rs.getString("imie"), rs.getString("nazwisko"), rs.getDouble("pensja"));
//            case TECHNICZNY:
//                return new PracownikTechniczny(rs.getInt("id"), rs.getString("imie"), rs.getString("nazwisko"), rs.getDouble("pensja"));
//            case MAGAZYNIER:
//                return new Magazynier(rs.getInt("id"), rs.getString("imie"), rs.getString("nazwisko"), rs.getDouble("pensja"));
//            default:
//                return null;
        return new APracownik(id, imie, nazwisko, pensja, typPracownika);
    }
}
