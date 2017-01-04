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
public class ZwierzeDoTypJedzenia implements IHaveId {

    private Integer id;
    private Integer idZwierze;
    private Integer idTypJedzenia;
    private RodzajJedzenia rodzajJedzenia;

    public ZwierzeDoTypJedzenia(Integer id, Integer idZwierze, Integer idTypJedzenia) {
        this.id = id;
        this.idZwierze = idZwierze;
        this.idTypJedzenia = idTypJedzenia;
        this.rodzajJedzenia= RodzajJedzenia.getById(idTypJedzenia);
    }

    public RodzajJedzenia getRodzajJedzenia() {
        return rodzajJedzenia;
    }

    public Integer getIdZwierze() {
        return idZwierze;
    }

    public Integer getIdTypJedzenia() {
        return idTypJedzenia;
    }

    @Override
    public int getId() {
        return id;
    }

}
