/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.mappers.PracownikMapper;
import dao.mappers.TypJedzeniaMapper;
import dao.mappers.TypWybieguMapper;
import dao.mappers.WybiegMapper;
import dao.mappers.ZwierzeDoTypJedzMapper;
import dao.mappers.ZwierzeDoTypWybMapper;
import dao.mappers.ZwierzeMapper;
import dao.uow.IUnitOfWork;
import dao.uow.UnitOfWork;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author TKK
 */
public class RepositoryCatalogue {

    private final static String CONNECTION_STRING = "jdbc:hsqldb:hsql://localhost/workdb";

    IUnitOfWork uow;
    Connection connection;

    TypJedzeniaRepository typJedzeniaRepozytory;
    TypWybieguRepository typWybiegRepozytory;
    WybiegRepository wybiegRepo;
    ZwierzeDoTypJedzeniaRepository zwDoJedz;
    ZwierzeDoTypWybieguRepository zwDoWyb;
    ZwierzetaRepository zwierzeRepo;
    PracownikRepository pracownikRepo;

    private Connection getNewConnection() throws SQLException {

        return DriverManager.getConnection(CONNECTION_STRING);
    }

    private IUnitOfWork getNewUow() {
        return new UnitOfWork(connection);
    }

    public void setUow(IUnitOfWork uow) {
        this.uow = uow;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public RepositoryCatalogue() throws SQLException {

        setConnection(getNewConnection());
        setUow(getNewUow());
        
        typJedzeniaRepository();
        typWybieguRepository();
              

    }

    public void save(){
        uow.saveChanges();
    }
        
    public void close() {
        try {
            uow.saveChanges();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public WybiegRepository wybiegRepository() {
        if (wybiegRepo == null) {
            wybiegRepo = new WybiegRepository(connection, new WybiegMapper(), uow);
        }
        return wybiegRepo;
    }

    public PracownikRepository pracownikRepository() {
        if (pracownikRepo == null) {
            pracownikRepo = new PracownikRepository(connection, new PracownikMapper(), uow);
        }
        return pracownikRepo;
    }

    public TypJedzeniaRepository typJedzeniaRepository() {
        if (typJedzeniaRepozytory == null) {
            typJedzeniaRepozytory = new TypJedzeniaRepository(connection, new TypJedzeniaMapper(), uow);
        }

        return typJedzeniaRepozytory;
    }

    public TypWybieguRepository typWybieguRepository() {
        if (typWybiegRepozytory == null) {
            typWybiegRepozytory = new TypWybieguRepository(connection, new TypWybieguMapper(), uow);
        }

        return typWybiegRepozytory;
    }

    public ZwierzeDoTypWybieguRepository zwierzeDoTypWybieguRepository() {
        if (zwDoWyb == null) {
            zwDoWyb = new ZwierzeDoTypWybieguRepository(connection, new ZwierzeDoTypWybMapper(), uow);
        }

        return zwDoWyb;
    }

    public ZwierzeDoTypJedzeniaRepository zwierzeDoTypJedzeniaRepository() {
        if (zwDoJedz == null) {
            zwDoJedz = new ZwierzeDoTypJedzeniaRepository(connection, new ZwierzeDoTypJedzMapper(), uow);
        }
        return zwDoJedz;
    }

    public ZwierzetaRepository zwierzetaRepository() {
        if (zwierzeRepo == null) {
            zwierzeRepo = new ZwierzetaRepository(connection, new ZwierzeMapper(), uow);
        }
        return zwierzeRepo;
    }

}
