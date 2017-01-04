/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.repositories;

import dao.model.TypWybiegu;
import dao.model.ZwierzeDoTypWybiegu;
import java.util.List;

/**
 *
 * @author TKK
 */
public interface IZwierzeDoTypWybiegu {
    
    public List<ZwierzeDoTypWybiegu> getRodzajeWybieguDlaZwierze(int idZwierze);
    
}
