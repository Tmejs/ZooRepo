/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.model;

import dao.RepositoryCatalogue;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TKK
 */
public class Zwierze implements IHaveId {

    private Integer id;
    private String nazwa;
    private static final int MAX_NAJEDZENIE = 100;
    private Integer tempoJedzenia;
    private Integer poziomNajedzenia;
    private List<ZwierzeDoTypJedzenia> rodzajJedzenia;
    private List<ZwierzeDoTypWybiegu> rodzajWybiegu;
    private Integer idWybiegu;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setTempoJedzenia(int tempoJedzenia) {
        this.tempoJedzenia = tempoJedzenia;
    }

    public void setPoziomNajedzenia(Integer poziomNajedzenia) {
        this.poziomNajedzenia = poziomNajedzenia;
    }

    public void setRodzajJedzenia(List<ZwierzeDoTypJedzenia> rodzajJedzenia) {
        this.rodzajJedzenia = rodzajJedzenia;
    }

    public void setRodzajWybiegu(List<ZwierzeDoTypWybiegu> rodzajWybiegu) {
        this.rodzajWybiegu = rodzajWybiegu;
    }

    public void setIdWybiegu(Integer idWybiegu) {
        this.idWybiegu = idWybiegu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public static int getMAX_NAJEDZENIE() {
        return MAX_NAJEDZENIE;
    }

    public int getTempoJedzenia() {
        return tempoJedzenia;
    }

    public List<ZwierzeDoTypJedzenia> getRodzajJedzenia() {
        return rodzajJedzenia;
    }

    public List<ZwierzeDoTypWybiegu> getRodzajWybiegu() {
        return rodzajWybiegu;
    }

    public Integer getIdWybiegu() {
        return idWybiegu;
    }

    private void pobierzListeTypJedzenia() {

    }

    private void pobierzListeTypWybiegu() {
    }

    public boolean jedz(RodzajJedzenia jedzenie) {
        // sprawdzam poziom najedzenia i rodzaj jedzenia
        if (poziomNajedzenia < MAX_NAJEDZENIE) {
            if (rodzajJedzenia.contains(jedzenie)) {
//                poziomNajedzenia = (poziomNajedzenia + ilosc)/(MAX_NAJEDZENIE+1);

                poziomNajedzenia = MAX_NAJEDZENIE;

                return true;
            }

        }

        return false;

    }

    @Override
    public int getId() {

        return id;
    }

    public Zwierze(Integer id, String nazwa, int tempoJedzenia, Integer poziomNajedzenia, List<ZwierzeDoTypJedzenia> rodzajJedzenia, List<ZwierzeDoTypWybiegu> rodzajWybiegu, Integer idWybiegu) {
        this.id = id;
        this.nazwa = nazwa;
        this.tempoJedzenia = tempoJedzenia;
        this.poziomNajedzenia = poziomNajedzenia;
        this.rodzajJedzenia = rodzajJedzenia;
        this.rodzajWybiegu = rodzajWybiegu;
        this.idWybiegu = idWybiegu;
        glodniej();
        niszczWybieg();
    }

    private Zwierze getThisZwierze() {
        return this;
    }

    public Zwierze() {
        glodniej();
        niszczWybieg();
    }

    public boolean zwierzeZainicjowane() {
        if (id == null
                || nazwa == null
                || tempoJedzenia == null
                || poziomNajedzenia == null
                || rodzajJedzenia == null
                ||  rodzajWybiegu == null) {
            return false;
        } else {
            return true;
        }

    }

    public void glodniej() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!zwierzeZainicjowane()) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException r) {
                        System.err.println("Zwierze niezainicjowane");
                        r.printStackTrace();
                    }
                }

                while (true) {
                    System.err.println("Zwierze glodnieje");
                    try {
                        System.err.println("Odpalam funkcje glodnienia dla zw "+getThisZwierze().getId());
                        Thread.sleep(3000);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Zwierze.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                    poziomNajedzenia = poziomNajedzenia - tempoJedzenia;
                    System.out.println(nazwa + " poziom najedzenia " + poziomNajedzenia);
                    RepositoryCatalogue rp = null;
                    try {
                        rp = new RepositoryCatalogue();
                        rp.zwierzetaRepository().update(getThisZwierze());
                        rp.save();
                        rp.close();
                        System.err.println("Zwierze " +getThisZwierze().id +"zgłodniało,stan: "+getThisZwierze().poziomNajedzenia);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        ).start();

    }

    public void niszczWybieg() {

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                while (!zwierzeZainicjowane()) {
                    try {
                        Thread.sleep(300);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Zwierze.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
                while (true) {
                    try {
                        Thread.sleep(10000);
                        if (idWybiegu != null) {
                            RepositoryCatalogue rp = new RepositoryCatalogue();
                            Wybieg wb = rp.wybiegRepository().get(idWybiegu);
                            if (wb.getStanWybiegu() == Wybieg.STAN_WYBIEGU.czysty) {
                                wb.setStanWybiegu(Wybieg.STAN_WYBIEGU.brudny);
                                rp.wybiegRepository().update(wb);
                                rp.save();

                            }
                            rp.close();

                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Zwierze.class
                                .getName()).log(Level.SEVERE, null, ex);

                    } catch (SQLException ex) {
                        Logger.getLogger(Zwierze.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };

        Thread jeden= new Thread(myRunnable);
        jeden.start();
    }

    public Integer getPoziomNajedzenia() {
        return poziomNajedzenia;
    }
    
    public boolean czyGlodny (){
        return poziomNajedzenia < MAX_NAJEDZENIE;
    }
    
    
}
