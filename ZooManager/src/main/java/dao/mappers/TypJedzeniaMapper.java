/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mappers;

import dao.model.RodzajJedzenia;
import dao.model.TypJedzenia;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author TKK
 */
public class TypJedzeniaMapper implements IMapResultSetIntoEntity<TypJedzenia> {

    @Override
    public TypJedzenia map(ResultSet rs) throws SQLException {
        TypJedzenia typJedzenia = new TypJedzenia();

        String nazwaEnum = rs.getString("typ_jedzenia");
        //wyciagnac enum po stringu "nazwaEnum"
        
        
       //TypJedzenia.RodzajJedzenia.valueOf(nazwaEnum);
        
        typJedzenia.setRodzajJedzenia(RodzajJedzenia.valueOf(nazwaEnum));
        
        
        return typJedzenia;

    }

}
