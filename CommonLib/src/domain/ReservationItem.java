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
public class ReservationItem implements GenericEntity {

    private Long id;
    private Reservation reservation;
    private Service service;

    public ReservationItem() {
    }

    public ReservationItem(Long id, Reservation reservation, Service service) {
        this.id = id;
        this.reservation = reservation;
        this.service = service;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String getTableName() {
        return "reservationItem";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "reservationId, serviceId";
    }

    @Override
    public void bindInsertValues(PreparedStatement ps) {

        try {
            ps.setLong(1, this.getReservation().getId());
            ps.setLong(2, this.getService().getId());
        } catch (SQLException ex) {
            Logger.getLogger(ReservationItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getUpdateValues() {
        return "reservationId, serviceId";
    }

    @Override
    public void bindUpdateValues(PreparedStatement ps) {

        try {
            ps.setLong(1, this.reservation.getId());
            ps.setLong(2, this.service.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ReservationItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getColumnNamesForSelect() {
        return "id, reservationId, serviceId";
    }

    @Override
    public String getWhereClauseForSelect() {

        if (this.getId() != null) {
            return "id = ?";
        }

        List<String> wheres = new ArrayList<>();

        if (this.reservation != null && this.reservation.getId() != 0L) {
            wheres.add("reservationId like ?");
        }

        if (this.service != null && this.service.getId() != 0L) {
            wheres.add("serviceId like ?");
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

                if (this.reservation != null) {
                    ps.setString(i, "%" + this.reservation.getId() + "%");
                    i++;
                }

                if (this.service != null) {
                    ps.setString(i, "%" + this.service.getId() + "%");
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public GenericEntity newFromResultSet(ResultSet rs) {

        ReservationItem item = new ReservationItem();

        try {

            item.setId(rs.getLong("id"));
            Reservation r = new Reservation();
            r.setId(rs.getLong("reservationId"));
            item.setReservation(r);
            Service s = new Service();
            s.setId(rs.getLong("serviceId"));
            item.setService(s);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
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
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.reservation);
        hash = 41 * hash + Objects.hashCode(this.service);
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
        final ReservationItem other = (ReservationItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.reservation, other.reservation)) {
            return false;
        }
        return Objects.equals(this.service, other.service);
    }

    @Override
    public String toString() {
        return "ReservationItem{" + "id=" + id + ", reservation=" + reservation + ", service=" + service + '}';
    }
    
    

}
