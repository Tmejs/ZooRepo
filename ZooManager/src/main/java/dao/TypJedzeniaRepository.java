/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.mappers.IMapResultSetIntoEntity;
import dao.model.RodzajJedzenia;
import dao.model.TypJedzenia;
import dao.uow.IUnitOfWork;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author TKK
 */
public class TypJedzeniaRepository extends RepositoryBase<TypJedzenia> {

    public static final String TABLE_NAME = "typ_jedzenia";

    public TypJedzeniaRepository(Connection connection, IMapResultSetIntoEntity<TypJedzenia> mapper, IUnitOfWork uow) {
        super(connection, mapper, uow);
    }

    @Override
    protected String insertSql() {
        return "insert into "
                + tableName()
                + " (id, typ_jedzenia)"
                + " values "
                + "(?,?)";
    }

    @Override
    protected String updateSql() {
        return "update "
                + tableName()
                + " set (typ_jedzenia) ="
                + " (?)"
                + " where id =?";
    }

    @Override
    protected void setUpdate(TypJedzenia entity) throws SQLException {
        update.setString(1, entity.getRodzajJedzenia().toString());
        update.setInt(2, entity.getId());
    }

    @Override
    protected void setInsert(TypJedzenia entity) throws SQLException {
        insert.setInt(1, entity.getId());
        insert.setString(2, entity.getRodzajJedzenia().toString());
    }

    @Override
    protected String createTableSql() {
        return "create table "
                + tableName()
                + "(id int identity primary key,"
                + "typ_jedzenia varchar(20))";
    }

    @Override
    protected String tableName() {
        return TABLE_NAME;
    }

    @Override
    protected void insertNecessaryData() throws SQLException {
        for (RodzajJedzenia rodzajJedzenia : RodzajJedzenia.values()) {
            add(new TypJedzenia(rodzajJedzenia));
        }
        getUnitWork().saveChanges();
    }

}
