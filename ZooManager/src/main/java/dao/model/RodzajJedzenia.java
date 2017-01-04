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
public enum RodzajJedzenia {

    WOLOWINA(1), KONINA(2), SIANO(3), KISZONKA(4);
    public int id;

    private RodzajJedzenia(int id) {
        this.id = id;
    }

    public static RodzajJedzenia getById(int j) {
        for (RodzajJedzenia rd : RodzajJedzenia.values()) {
            if (rd.id == j) {
                return rd;
            }
        }
        return null;
    }

}
