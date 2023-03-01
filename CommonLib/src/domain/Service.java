/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikola
 */
public class Service implements GenericEntity {

    private Long id;
    private String name;
    private long dailyPrice;

    public Service() {
    }

    public Service(Long id, String name, long dailyPrice) {
        this.id = id;
        this.name = name;
        this.dailyPrice = dailyPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(long dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + (int) (this.dailyPrice ^ (this.dailyPrice >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Service other = (Service) obj;
        if (this.dailyPrice != other.dailyPrice) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "NAZIV: "+name+ " CENA: "+dailyPrice;
    }

    @Override
    public String getTableName() {
        return "service";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "name, dailyPrice";
    }

    @Override
    public void bindInsertValues(PreparedStatement ps) {

        try {
            ps.setString(1, name);
            ps.setLong(2, dailyPrice);
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getUpdateValues() {
        return "name, dailyPrice";
    }

    @Override
    public void bindUpdateValues(PreparedStatement ps) {

        try {
            ps.setString(1, name);
            ps.setLong(2, dailyPrice);
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getColumnNamesForSelect() {
        return "id, name, dailyPrice";
    }

    @Override
    public String getWhereClauseForSelect() {
        if (this.getId() != null) {
            return "id = ?";
        }

        List<String> wheres = new ArrayList<>();

        if (name != null) {
            wheres.add("name like ?");
        }

        if (dailyPrice != 0L) {
            wheres.add("dailyPrice like ?");
        }

        return String.join(" or ", wheres);
    }

    @Override
    public void bindWhereClauseValuesForSelect(PreparedStatement ps) {

        try {
            if (this.getId() != null) {
                ps.setLong(1, this.getId());
            } else {
                int i = 1;
                if (name != null) {
                    ps.setString(i, "%" + name + "%");
                    i++;
                }
                if (dailyPrice != 0L) {
                    ps.setString(i, "%" + dailyPrice + "%");
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public GenericEntity newFromResultSet(ResultSet rs) {

        Service service = new Service();

        try {
            service.setId(rs.getLong("id"));
            service.setName(rs.getString("name"));
            service.setDailyPrice(rs.getLong("dailyPrice"));
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return service;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
    

}
