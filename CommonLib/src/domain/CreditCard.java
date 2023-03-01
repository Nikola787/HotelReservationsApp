/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikola
 */
public class CreditCard implements GenericEntity {

    private Long id;
    private String bankName;
    private String type;
    private Date validThru;
    private String number;
    private String csv;
    private User user;

    public CreditCard() {
    }

    public CreditCard(Long id, String bankName, String type, Date validThru, String number, String csv, User user) {
        this.id = id;
        this.bankName = bankName;
        this.type = type;
        this.validThru = validThru;
        this.number = number;
        this.csv = csv;
        this.user = user;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getValidThru() {
        return validThru;
    }

    public void setValidThru(Date validThru) {
        this.validThru = validThru;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCsv() {
        return csv;
    }

    public void setCsv(String csv) {
        this.csv = csv;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getTableName() {
        return "creditcard";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "bankName, type, validThru, number, csv, userId";
    }

    @Override
    public void bindInsertValues(PreparedStatement ps) {

        try {
            ps.setString(1, bankName);
            ps.setString(2, type);
            ps.setDate(3, new java.sql.Date(validThru.getTime()));
            ps.setString(4, number);
            ps.setString(5, csv);
            ps.setLong(6, user.getId());
        } catch (SQLException ex) {
            Logger.getLogger(CreditCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getUpdateValues() {
        return "bankName, type, validThru, number, csv";
    }

    @Override
    public void bindUpdateValues(PreparedStatement ps) {

        try {
            ps.setString(1, bankName);
            ps.setString(2, type);
            ps.setDate(3, new java.sql.Date(validThru.getTime()));
            ps.setString(4, number);
            ps.setString(5, csv);
        } catch (SQLException ex) {
            Logger.getLogger(CreditCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getColumnNamesForSelect() {
        return "id, bankName, type, validThru, number, csv, userId";
    }

    @Override
    public String getWhereClauseForSelect() {

        if (this.getId() != null) {
            return "id = ?";
        }

        List<String> wheres = new ArrayList<>();

        if (bankName != null) {
            wheres.add("bankName like ?");
        }
        if (type != null) {
            wheres.add("type like ?");
        }
        if (validThru != null) {
            wheres.add("validThru like ?");
        }
        if (number != null) {
            wheres.add("number like ?");
        }
        if (user != null && user.getId() != null) {
            wheres.add("userId like ?");
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

                if (bankName != null) {
                    ps.setString(i, "%" + bankName + "%");
                    i++;
                }

                if (type != null) {
                    ps.setString(i, "%" + type + "%");
                    i++;
                }

                if (validThru != null) {
                    ps.setString(i, "%" + validThru + "%");
                    i++;
                }

                if (number != null) {
                    ps.setString(i, "%" + number + "%");
                    i++;
                }

                if (user != null) {
                    ps.setString(i, "%" + user.getId() + "%");
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public GenericEntity newFromResultSet(ResultSet rs) {

        CreditCard cc = new CreditCard();

        try {
            cc.setId(rs.getLong("id"));
            cc.setBankName(rs.getString("bankName"));
            cc.setType(rs.getString("type"));
            cc.setValidThru(rs.getDate("validThru"));
            cc.setNumber(rs.getString("number"));
            cc.setCsv(rs.getString("csv"));
            User user = new User();
            user.setId(rs.getLong("userId"));
            cc.setUser(user);
        } catch (SQLException ex) {
            Logger.getLogger(CreditCard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cc;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.bankName);
        hash = 67 * hash + Objects.hashCode(this.type);
        hash = 67 * hash + Objects.hashCode(this.validThru);
        hash = 67 * hash + Objects.hashCode(this.number);
        hash = 67 * hash + Objects.hashCode(this.csv);
        hash = 67 * hash + Objects.hashCode(this.user);
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
        final CreditCard other = (CreditCard) obj;
        if (!Objects.equals(this.bankName, other.bankName)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        if (!Objects.equals(this.csv, other.csv)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.validThru, other.validThru)) {
            return false;
        }
        return Objects.equals(this.user, other.user);
    }

    @Override
    public String toString() {
        return number;
    } 

}
