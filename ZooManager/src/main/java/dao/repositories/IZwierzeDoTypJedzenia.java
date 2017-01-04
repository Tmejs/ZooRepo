/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.repositories;

import dao.mappers.ZwierzeDoTypJedzMapper;
import dao.model.RodzajJedzenia;
import dao.model.TypJedzenia;
import dao.model.ZwierzeDoTypJedzenia;
import java.util.List;

/**
 *
 * @author TKK
 */
public interface IZwierzeDoTypJedzenia {
    public List <ZwierzeDoTypJedzenia> pobierzListeJedzeniaDlaZwierze (int id);
}
