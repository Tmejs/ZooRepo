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
public class PozycjaMagazyn {
    
    private String kod;
    private String nazwa;
    private double ilosc;
    private String jednostka;

    public PozycjaMagazyn(String kod, String nazwa, double ilosc, String jednostka) {
        this.kod = kod;
        this.nazwa = nazwa;
        this.ilosc = ilosc;
        this.jednostka = jednostka;
    }

    public String getKod() {
        return kod;
    }

    public String getNazwa() {
        return nazwa;
    }

    public double getIlosc() {
        return ilosc;
    }

    public String getJednostka() {
        return jednostka;
    }
    
    
    
    
}
