/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.repositories;

import dao.model.Zwierze;
import java.util.List;

/**
 *
 * @author TKK
 */
public interface IRepositoryZwierze extends IRepository<Zwierze>{
    public List<Zwierze> getByName (String name);
    
}
