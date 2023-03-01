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
public class User implements GenericEntity {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
    private String password;
    private Date createdAt;
    private Date updatedAt;
    private String type;

    public User() {
    }

    public User(Long id, String firstName, String lastName, String username, String email, String phone, String password, 
            Date createdAt, Date updatedAt, String type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String getTableName() {
        return "user";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "firstName, lastName, username, email, phone, password, type";
    }

    @Override
    public void bindInsertValues(PreparedStatement ps) {

        try {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, username);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, password);
            ps.setString(7, type);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getUpdateValues() {
        return "firstName, lastName, username, email, phone, password, updatedAt, type";
    }

    @Override
    public void bindUpdateValues(PreparedStatement ps) {
        try {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, username);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, password);
            ps.setTimestamp(7, new java.sql.Timestamp(updatedAt.getTime()));
            ps.setString(8, type);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getColumnNamesForSelect() {
        return "id, firstName, lastName, username, email, phone, password, createdAt, updatedAt, type";
    }

    @Override
    public String getWhereClauseForSelect() {

        if (this.getId() != null) {
            return "id = ?";
        }

        List<String> wheres = new ArrayList<>();

        if (firstName != null) {
            wheres.add("firstName like ?");
        }
        if (lastName != null) {
            wheres.add("lastName like ?");
        }
        if (username != null) {
            wheres.add("username like ?");
        }
        if (email != null) {
            wheres.add("email like ?");
        }
        if (phone != null) {
            wheres.add("phone like ?");
        }
        if (type != null) {
            wheres.add("type like ?");
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

                if (firstName != null) {
                    ps.setString(i, "%" + firstName + "%");
                    i++;
                }

                if (lastName != null) {
                    ps.setString(i, "%" + lastName + "%");
                    i++;
                }

                if (username != null) {
                    ps.setString(i, "%" + username + "%");
                    i++;
                }

                if (email != null) {
                    ps.setString(i, "%" + email + "%");
                    i++;
                }

                if (phone != null) {
                    ps.setString(i, "%" + phone + "%");
                    i++;
                }
                if (type != null) {
                    ps.setString(i, "%" + type + "%");
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public GenericEntity newFromResultSet(ResultSet rs) {
        User u = new User();

        try {
            u.setId(rs.getLong("id"));
            u.setFirstName(rs.getString("firstName"));
            u.setLastName(rs.getString("lastName"));
            u.setUsername(rs.getString("username"));
            u.setEmail(rs.getString("email"));
            u.setPhone(rs.getString("phone"));
            u.setPassword(rs.getString("password"));
            u.setCreatedAt(rs.getDate("createdAt"));
            u.setUpdatedAt(rs.getDate("updatedAt"));
            u.setType(rs.getString("type"));
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.firstName);
        hash = 13 * hash + Objects.hashCode(this.lastName);
        hash = 13 * hash + Objects.hashCode(this.username);
        hash = 13 * hash + Objects.hashCode(this.email);
        hash = 13 * hash + Objects.hashCode(this.phone);
        hash = 13 * hash + Objects.hashCode(this.password);
        hash = 13 * hash + Objects.hashCode(this.createdAt);
        hash = 13 * hash + Objects.hashCode(this.updatedAt);
        hash = 13 * hash + Objects.hashCode(this.type);
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
        final User other = (User) obj;

        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.createdAt, other.createdAt)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return Objects.equals(this.updatedAt, other.updatedAt);
    }

    @Override
    public String toString() {
        return email;
    }

}
