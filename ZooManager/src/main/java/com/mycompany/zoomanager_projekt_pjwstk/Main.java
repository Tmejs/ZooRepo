/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zoomanager_projekt_pjwstk;

import dao.RepositoryCatalogue;
import dao.WybiegRepository;
import dao.ZwierzetaRepository;
import dao.model.RodzajJedzenia;
import dao.model.TypWybiegu;
import dao.model.Wybieg;
import dao.model.Zwierze;
import dao.model.ZwierzeDoTypJedzenia;
import dao.model.ZwierzeDoTypWybiegu;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TKK
 */
public class Main {

    public static ArrayList<Zwierze> listaZwierzat = new ArrayList<>();
    public static ArrayList<Wybieg> listaWybiegow = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
            pobierzZwierzaki();
//        List<Zwierze> listaZierzat = null;
//        try {
//            RepositoryCatalogue repos = new RepositoryCatalogue();
//            ZwierzetaRepository zr = repos.zwierzetaRepository();
//            repos.save();
//            listaZierzat = zr.getAll();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        listaZierzat.stream().forEach((a) -> {
//            System.out.println(a.getNazwa());
//        });

    }

    public static void pobierzZwierzaki() {
        try {

            RepositoryCatalogue rp = new RepositoryCatalogue();
            ZwierzetaRepository zwr = rp.zwierzetaRepository();

            List<Zwierze> zwieList = zwr.getAll();

            zwieList.forEach((a) -> {
                System.out.println(a.getNazwa());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pobierzTypyWybiegu() {

        ArrayList<String> rodzaje = new ArrayList<String>();
        for (TypWybiegu.RodzajWybiegu rw : TypWybiegu.RodzajWybiegu.values()) {
            rodzaje.add(rw.toString());
        }

//        rodzaje.stream().forEach(a->{
//            System.out.println(a);
//        });
    }

    public static void dodajWybiegi() {
        System.out.println("com.mycompany.zoomanager_projekt_pjwstk.Main.dodajWybiegi()");
        RepositoryCatalogue repos;
        try {
            repos = new RepositoryCatalogue();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        WybiegRepository wr = repos.wybiegRepository();

        for (int i = 1; i < 11; i++) {
            //String wybieg = "wybieg"+i;
            int j = (i % 3) + 1;
            wr.add(new Wybieg(i, j, Wybieg.STAN_WYBIEGU.czysty));
        }
        repos.save();
        repos.close();
        try {
            repos = new RepositoryCatalogue();
            repos.wybiegRepository().getAll().stream().forEach(e -> {
                listaWybiegow.add((Wybieg) e);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void dodajZwierzaki() {
        System.out.println("com.mycompany.zoomanager_projekt_pjwstk.Main.dodajZwierzaki()");
        try {
            RepositoryCatalogue repos;
            try {
                repos = new RepositoryCatalogue();
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
//            System.out.println("po repo");
//            System.out.println(repos.toString());
            ZwierzetaRepository zr = repos.zwierzetaRepository();
//            System.out.println(zr.toString());

            for (int i = 1; i < 4; i++) {
                //String wybieg = "wybieg"+i;
                zr.add(new Zwierze(i, "Zwierze" + i, 6, 100, null, null, i));
            }
//            System.out.println("po zw");
            repos.close();

//            System.out.println("po save");
            repos = new RepositoryCatalogue();
            repos.zwierzetaRepository().getAll().stream().forEach(e -> {
                listaZwierzat.add((Zwierze) e);
            }
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("po dodajZwierzaki()");

    }

    public static void pobierzZwierzaka() {
        System.out.println("com.mycompany.zoomanager_projekt_pjwstk.Main.pobierzZwierzaka()");
        RepositoryCatalogue repos;
        try {
            repos = new RepositoryCatalogue();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        ZwierzetaRepository zr = repos.zwierzetaRepository();

        repos.zwierzeDoTypJedzeniaRepository().add(new ZwierzeDoTypJedzenia(0, 4, 3));
        repos.zwierzeDoTypWybieguRepository().add(new ZwierzeDoTypWybiegu(0, 4, 1));
        repos.zwierzeDoTypWybieguRepository().add(new ZwierzeDoTypWybiegu(0, 4, 2));
        repos.save();
        Zwierze zwierze = zr.get(4);
        System.out.println(zwierze.getNazwa());
        for (ZwierzeDoTypJedzenia zw : zwierze.getRodzajJedzenia()) {
            System.out.println(RodzajJedzenia.getById(zw.getIdTypJedzenia()).toString());
        }
        repos.close();
    }

}
