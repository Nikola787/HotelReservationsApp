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
public class Room implements GenericEntity {

    private Long id;
    private String floor;
    private String roomType;
    private String additionalMessage;

    public Room() {
    }

    public Room(Long id, String floor, String roomType, String additionalMessage) {
        this.id = id;
        this.floor = floor;
        this.roomType = roomType;
        this.additionalMessage = additionalMessage;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getAdditionalMessage() {
        return additionalMessage;
    }

    public void setAdditionalMessage(String additionalMessage) {
        this.additionalMessage = additionalMessage;
    }

    @Override
    public String getTableName() {
        return "room";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "floor, roomType, additionalMessage";
    }

    @Override
    public void bindInsertValues(PreparedStatement ps) {
        try {
            ps.setString(1, floor);
            ps.setString(2, roomType);
            ps.setString(3, additionalMessage);
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getUpdateValues() {
        return "floor, roomType, additionalMessage";
    }

    @Override
    public void bindUpdateValues(PreparedStatement ps) {
        try {
            ps.setString(1, floor);
            ps.setString(2, roomType);
            ps.setString(3, additionalMessage);
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.floor);
        hash = 59 * hash + Objects.hashCode(this.roomType);
        hash = 59 * hash + Objects.hashCode(this.additionalMessage);
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
        final Room other = (Room) obj;
        if (!Objects.equals(this.floor, other.floor)) {
            return false;
        }
        if (!Objects.equals(this.roomType, other.roomType)) {
            return false;
        }
        if (!Objects.equals(this.additionalMessage, other.additionalMessage)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getColumnNamesForSelect() {
        return "id, floor, roomType, additionalMessage";
    }

    @Override
    public String getWhereClauseForSelect() {
        if (this.getId() != null) {
            return "id = ?";
        }

        List<String> wheres = new ArrayList<>();

        if (floor != null) {
            wheres.add("floor like ?");
        }

        if (roomType != null) {
            wheres.add("roomType like ?");
        }

        if (additionalMessage != null) {
            wheres.add("additionalMessage like ?");
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

                if (floor != null) {
                    ps.setString(i, "%" + floor + "%");
                    i++;
                }

                if (roomType != null) {
                    ps.setString(i, "%" + roomType + "%");
                    i++;
                }

                if (additionalMessage != null) {
                    ps.setString(i, "%" + additionalMessage + "%");
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public GenericEntity newFromResultSet(ResultSet rs) {

        Room room = new Room();
        try {
            room.setId(rs.getLong("id"));
            room.setFloor(rs.getString("floor"));
            room.setRoomType(rs.getString("roomType"));
            room.setAdditionalMessage(rs.getString("additionalMessage"));
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
        return room;
    }

}
