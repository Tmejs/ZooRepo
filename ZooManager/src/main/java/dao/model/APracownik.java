/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.model;

import dao.RepositoryCatalogue;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TKK
 */
public class APracownik implements IHaveId {

    private static double PENJSA_PODSTAWOWA = 1200;

    protected final int id;
    protected String imie;
    protected String nazwisko;
    protected double pensja;
    protected Typ_Pracownika typPracownika;

    public APracownik(int id, String imie, String nazwisko, double penjsa, Typ_Pracownika typPracownika) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pensja = penjsa;
        this.typPracownika = typPracownika;

    }

    public APracownik(int id, String imie, String nazwisko, Typ_Pracownika typPracownika) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pensja = PENJSA_PODSTAWOWA;
        this.typPracownika = typPracownika;
    }

    public static void setPodstawowaPensja(double podstawowaPensja) {
        PENJSA_PODSTAWOWA = podstawowaPensja;
    }

    public void setPensja(double pensja) {
        this.pensja = pensja;

    }

    @Override
    public int getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public double getPensja() {
        return this.pensja;
    }

    public Typ_Pracownika getTypPracownika() {
        return typPracownika;
    }

    public void nakarmZwierze(Zwierze zw) {
        if (typPracownika == Typ_Pracownika.OPIEKUN) {

            if (zw.czyGlodny()) {

                Random rand = new Random();

                zw.jedz(zw.getRodzajJedzenia().get(rand.nextInt(zw.getRodzajJedzenia().size())).getRodzajJedzenia());
                RepositoryCatalogue rc = null;
                try {
                    rc = new RepositoryCatalogue();
                    rc.zwierzetaRepository().update(zw);
                    rc.save();
                    rc.close();
                    System.out.println("Zwierze " + zw.getId() + " zostalo naarmione");
                } catch (SQLException ex) {
                    Logger.getLogger(APracownik.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                System.out.println("zwierze id " + zw.getId() + " jest juz najedzony");
            }
        } else {
            System.out.println("pracownik id " + getId() + " brak uprawnien do karmienia");
        }
    }

    public void posprzatajWybieg(Wybieg wb) {
        if (typPracownika == Typ_Pracownika.TECHNICZNY) {
            if (wb.czyWymagaSprzatania()) {
                wb.sprzatajWybieg();

                try {
                    RepositoryCatalogue rc = new RepositoryCatalogue();
                    rc.wybiegRepository().update(wb);
                    rc.save();
                    rc.close();
                } catch (SQLException ex) {
                    Logger.getLogger(APracownik.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                System.out.println("wybieg id " + wb.getId() + " nie wymaga czyszczenia");
            }
        } else {
            System.out.println("pracownik id " + getId() + " brak uprawnien do sprzatania");
        }
    }

    public void zmianaRodzajuWyiegu(Wybieg wb, Zwierze zw) {
        if (typPracownika.equals(Typ_Pracownika.TECHNICZNY)) {

            try {
                Random rand = new Random();

//            int maxValue = zw.getRodzajWybiegu().size();
//            
//            Integer randomId = rand.nextInt(maxValue);
//            
//            ZwierzeDoTypWybiegu zwierzeDoTypWybiegu= zw.getRodzajWybiegu().get(randomId);
//            
//            int id=zwierzeDoTypWybiegu.getRodzajWybiegu().id;
//            
//            wb.setIdRodzajWybiegu(id);
                wb.setIdRodzajWybiegu(zw.
                        getRodzajWybiegu().
                        get(rand.nextInt(zw.
                                getRodzajWybiegu().
                                size())).
                        getRodzajWybiegu().id);

                RepositoryCatalogue rc = new RepositoryCatalogue();
                rc.wybiegRepository().update(wb);
                rc.save();
                rc.close();
            } catch (SQLException ex) {
                Logger.getLogger(APracownik.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("pracownik id " + getId() + " brak uprawnien do zmiany wybiegu");
        }
    }

}
