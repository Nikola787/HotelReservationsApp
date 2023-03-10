/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

/**
 *
 * @author Nikola
 */
import database.DatabaseBroker;
import domain.GenericEntity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class GenericRepository<T extends GenericEntity> implements DBRepository<T> {

    @Override
    public void add(T param) throws Exception {
        try {
            Connection connection = DatabaseBroker.getInstance().getConnection();

            String columnsToInsert = param.getColumnNamesForInsert();

            String[] cols = columnsToInsert.split(",");
            for (int i = 0; i < cols.length; i++) {
                cols[i] = "?";
            }

            StringBuilder sb = new StringBuilder();

            sb.append("INSERT INTO ")
                    .append(param.getTableName())
                    .append(" (").append(columnsToInsert).append(")")
                    .append(" VALUES (")
                    .append(String.join(",", cols))
                    .append(");");

            String query = sb.toString();

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            param.bindInsertValues(statement);

            statement.executeUpdate();

            ResultSet rsKey = statement.getGeneratedKeys();

            if (rsKey.next()) {
                Long id = rsKey.getLong(1);
                param.setId(id);
            }

            statement.close();
            rsKey.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public List<T> getAll(T param) throws Exception {
        try {
            StringBuilder sb = new StringBuilder();

            sb.append("SELECT ")
                    .append(param.getColumnNamesForSelect())
                    .append(" FROM ")
                    .append(param.getTableName());

            String wheres = param.getWhereClauseForSelect();

            if (wheres.length() > 0) {
                sb.append(" WHERE ")
                        .append(wheres);
            }

            sb.append(";");

            String sql = sb.toString();

            Connection connection = DatabaseBroker.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            param.bindWhereClauseValuesForSelect(statement);

            ResultSet rs = statement.executeQuery();

            List<T> ges = new ArrayList<>();
            while (rs.next()) {
                ges.add((T) param.newFromResultSet(rs));
            }

            rs.close();

            statement.close();

            return ges;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public void edit(T param) throws Exception {
        try {
            Connection connection = DatabaseBroker.getInstance().getConnection();

            String columnsToUpdate = param.getUpdateValues();

            String[] cols = columnsToUpdate.split(",");
            for (int i = 0; i < cols.length; i++) {
                cols[i] = cols[i] + " = ?";
            }

            StringBuilder sb = new StringBuilder();

            sb.append("UPDATE ")
                    .append(param.getTableName())
                    .append(" SET ")
                    .append(String.join(",", cols))
                    .append(" WHERE id = ?;");

            String query = sb.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            param.bindUpdateValues(statement);
            statement.setLong(cols.length + 1, param.getId());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void delete(T param) throws Exception {
        try {
            String sql = "DELETE FROM " + param.getTableName() + " WHERE id = ?;";
            Connection connection = DatabaseBroker.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, param.getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public List<T> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
