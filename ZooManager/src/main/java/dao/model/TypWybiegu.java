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
public class TypWybiegu implements IHaveId {

    RodzajWybiegu rodzajWybiegu;

    public TypWybiegu() {
    }

    @Override
    public int getId() {
        return rodzajWybiegu.id;
    }

    public enum RodzajWybiegu {

        KLATKA(1), ZAMKNIETY(2), OTWARTY(3);
        public int id;

        RodzajWybiegu(int value) {
            this.id = value;
        }

        public static RodzajWybiegu getById(int j) {
            for (RodzajWybiegu rw : RodzajWybiegu.values()) {
                if (rw.id == j) {
                    return rw;
                }

            }
            return null;

        }
    }

    public RodzajWybiegu getRodzajWybiegu() {
        return rodzajWybiegu;
    }

    public void setRodzajWybiegu(RodzajWybiegu rodzajWybiegu) {
        this.rodzajWybiegu = rodzajWybiegu;
    }

    public TypWybiegu(RodzajWybiegu rodzajWybiegu) {
        this.rodzajWybiegu = rodzajWybiegu;
    }

}
