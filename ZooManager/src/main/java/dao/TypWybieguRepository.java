/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.mappers.IMapResultSetIntoEntity;
import dao.model.TypWybiegu;
import dao.uow.IUnitOfWork;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author TKK
 */
public class TypWybieguRepository extends RepositoryBase<TypWybiegu> {

    public static final String TABLE_NAME = "typ_wybiegu";

    public TypWybieguRepository(Connection connection, IMapResultSetIntoEntity<TypWybiegu> mapper, IUnitOfWork uow) {
        super(connection, mapper, uow);
    }

    @Override
    protected String insertSql() {
return "insert into "
        + tableName()
        +" (id, typ_wybiegu) values"
        + "(?,?)";
        }

    @Override
    protected String updateSql() {
return "update "
        + tableName()
        + " set (typ_wybiegu) ="
        + "(?) where"
        + " id = ?";}

    @Override
    protected void setUpdate(TypWybiegu entity) throws SQLException {
update.setString(1, entity.getRodzajWybiegu().toString());
update.setInt(2, entity.getId());    }

    @Override
    protected void setInsert(TypWybiegu entity) throws SQLException {
        insert.setInt(1, entity.getId());
        insert.setString(2, entity.getRodzajWybiegu().toString());
//wyciagniecie enuma na podstawie string
//TypWybiegu.RodzajWybiegu.valueOf(entity.getRodzajWybiegu().toString());

    }

    @Override
    protected String createTableSql() {
        return "create table "
                + tableName()
                + " (id int identity primary key,"
                + " typ_wybiegu varchar(20))";
    }

    @Override
    protected String tableName() {
        return TABLE_NAME;
    }

    @Override
    protected void insertNecessaryData() throws SQLException {
        for (TypWybiegu.RodzajWybiegu param : TypWybiegu.RodzajWybiegu.values()) {
            add(new TypWybiegu(param));
        }
        getUnitWork().saveChanges();
    }

}
