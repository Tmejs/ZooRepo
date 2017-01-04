/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.model;

import dao.model.IHaveId;

/**
 *
 * @author TKK
 */
public class Wybieg implements IHaveId {

    public enum STAN_WYBIEGU{
        czysty, brudny;
    }
    
    private Integer id;
    private Integer idRodzajWybiegu;
    private STAN_WYBIEGU stanWybiegu;

    public void setIdRodzajWybiegu(Integer idRodzajWybiegu) {
        this.idRodzajWybiegu = idRodzajWybiegu;
    }

    public void setStanWybiegu(STAN_WYBIEGU stanWybiegu) {
        this.stanWybiegu = stanWybiegu;
    }

    public Wybieg() {
    }

    

    @Override
    public int getId() {
        return id;
    }

    public Integer getRodzajWybiegu() {
        return idRodzajWybiegu;
    }

    public void setRodzajWybiegu(Integer rodzajWybiegu) {
        this.idRodzajWybiegu = rodzajWybiegu;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getIdRodzajWybiegu() {
        return idRodzajWybiegu;
    }

    public STAN_WYBIEGU getStanWybiegu() {
        return stanWybiegu;
    }

    public Wybieg(Integer id, Integer idRodzajWybiegu, STAN_WYBIEGU stanWybiegu) {
        this.id = id;
        this.idRodzajWybiegu = idRodzajWybiegu;
        this.stanWybiegu = stanWybiegu;
    }
    
    public boolean czyWymagaSprzatania(){
        return stanWybiegu != STAN_WYBIEGU.czysty;
    }
    
    public void sprzatajWybieg(){
        stanWybiegu = STAN_WYBIEGU.czysty;
    }
    
    

}
