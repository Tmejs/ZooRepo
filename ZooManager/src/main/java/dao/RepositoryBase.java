package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.mappers.IMapResultSetIntoEntity;
import dao.repositories.IRepository;
import dao.model.IHaveId;
import dao.uow.IUnitOfWork;
import dao.uow.Entity;
import dao.uow.IUnitOfWorkRepository;
import java.sql.SQLIntegrityConstraintViolationException;

public abstract class RepositoryBase<TEntity extends IHaveId> implements
        IRepository<TEntity>, IUnitOfWorkRepository {

    protected Connection connection;

    protected PreparedStatement insert;
    protected PreparedStatement selectById;
    protected PreparedStatement update;
    protected PreparedStatement delete;
    protected PreparedStatement selectAll;
    protected IUnitOfWork uow;
    protected IMapResultSetIntoEntity<TEntity> mapper;

    public Connection getConnection() {
        return connection;
    }

    protected RepositoryBase(Connection connection,
            IMapResultSetIntoEntity<TEntity> mapper, IUnitOfWork uow) {
        this.connection = connection;
        this.uow = uow;
        try {
            this.mapper = mapper;
            createTableIfnotExists();
            insert = connection.prepareStatement(insertSql());
            selectById = connection.prepareStatement(selectByIdSql());
            update = connection.prepareStatement(updateSql());
            delete = connection.prepareStatement(deleteSql());
            selectAll = connection.prepareStatement(selectAllSql());
            //funkcja dla tabeli slownikowych (z naszych enum)
        insertNecessaryData();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<TEntity> getAll() {
        try {
            ResultSet rs = selectAll.executeQuery();
            List<TEntity> result = new ArrayList<TEntity>();
            while (rs.next()) {
                result.add(mapper.map(rs));
            }
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public TEntity get(int id) {
        try {
            selectById.setInt(1, id);
            ResultSet rs = selectById.executeQuery();
            while (rs.next()) {
                TEntity newTEntity = mapper.map(rs);
                addMoreData(newTEntity);
                return newTEntity;
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public void add(TEntity entity) {
        Entity ent = new Entity(this);
        ent.setEntity(entity);
        uow.markAsNew(ent);

    }

    public void delete(TEntity entity) {
        Entity ent = new Entity(this);
        ent.setEntity(entity);
        uow.markAsDeleted(ent);
    }

    public void update(TEntity entity) {
        Entity ent = new Entity(this);
        ent.setEntity(entity);
        uow.markAsChanged(ent);
    }

    public void persistUpdate(Entity entity) {
        try {
            TEntity ent = (TEntity) entity.getEntity();
            setUpdate(ent);
            update.setInt(3, ent.getId());
            update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void persistAdd(Entity entity) {
        try {
            setInsert((TEntity) entity.getEntity());
            insert.executeUpdate();
            insertPlus((TEntity) entity.getEntity());
        } catch (SQLIntegrityConstraintViolationException ex) {
            //obsluga bledu powtarzajacego sie PK
//            System.err.println("dao.RepositoryBase.persistAdd()");
//             System.err.println("Tutaj bład bo inserty do tabeli enumow");
//            System.err.println(this.getClass());
//            System.err.println(((TEntity) entity.getEntity()).getId());
        } catch (SQLException sqexp){
            sqexp.printStackTrace();
        }
    }

    public void persistDelete(Entity entity) {
        try {
            //delete.setInt(1, ((TEntity) entity.getEntity()).getId());
            setDelete((TEntity)entity.getEntity());
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void setDelete(TEntity entity) throws SQLException{
        delete.setInt(1, entity.getId());
    }
    protected String selectByIdSql() {
        return "SELECT * FROM " + tableName() + " WHERE id=?";
    }

    protected String deleteSql() {
        return "DELETE FROM " + tableName() + " WHERE id=?";
    }

    protected String selectAllSql() {
        return "SELECT * FROM " + tableName();
    }

    private void createTableIfnotExists() throws SQLException {
        Statement createTable = this.connection.createStatement();

        boolean tableExists = false;

        ResultSet rs = connection.getMetaData().getTables(null, null, null,
                null);

        while (rs.next()) {
            if (rs.getString("Table_Name").equalsIgnoreCase(tableName())) {
                tableExists = true;
                break;
            }
        }
        if (!tableExists) {
            createTable.executeUpdate(createTableSql());
        }
    }

    public IUnitOfWork getUnitWork() {
        return uow;
    }

    protected abstract String insertSql();

    protected abstract String updateSql();

    protected abstract void setUpdate(TEntity entity) throws SQLException;

    protected abstract void setInsert(TEntity entity) throws SQLException;

    protected abstract String createTableSql();

    protected abstract String tableName();

    protected abstract void insertNecessaryData() throws SQLException;
    
    protected void addMoreData(TEntity entity){
        //Nothing to do
    }
    protected void insertPlus (TEntity entity) {};
}
