/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.model;

/**
 *
 * @author TKK
 */
public class TypJedzenia implements IHaveId {

    RodzajJedzenia rodzajJedzenia;

    @Override
    public int getId() {
        return rodzajJedzenia.id;
    }

    

    public TypJedzenia() {
    }

    public void setRodzajJedzenia(RodzajJedzenia rodzajJedzenia) {
        this.rodzajJedzenia = rodzajJedzenia;
    }

    public TypJedzenia(RodzajJedzenia rodzajJedzenia) {
        this.rodzajJedzenia = rodzajJedzenia;
    }

    public RodzajJedzenia getRodzajJedzenia() {
        return rodzajJedzenia;
    }

}
