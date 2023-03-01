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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikola
 */
public class Reservation implements GenericEntity {

    private Long id;
    private Date arrivalDate;
    private Date departureDate;
    private long price;
    private CreditCard creditCard;
    private Room room;
    private User user;
    private ArrayList<Service> services;

    public Reservation() {
    }

    public Reservation(Long id, Date arrivalDate, Date departureDate, ArrayList<Service> services, long price, 
            CreditCard creditCard, Room room, User user) {
        this.id = id;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.services = services;
        this.price = price;
        this.creditCard = creditCard;
        this.room = room;
        this.user = user;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getTableName() {
        return "reservation";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "arrivalDate, departureDate, price, creditCardId, userId, roomId";
    }

    @Override
    public void bindInsertValues(PreparedStatement ps) {

        try {
            ps.setDate(1, new java.sql.Date(arrivalDate.getTime()));
            ps.setDate(2, new java.sql.Date(departureDate.getTime()));
            ps.setLong(3, price);
            ps.setLong(4, creditCard.getId());
            ps.setLong(5, user.getId());
            ps.setLong(6, room.getId());
        } catch (SQLException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getUpdateValues() {
        return "arrivalDate, departureDate, price, creditCardId, userId, roomId";
    }

    @Override
    public void bindUpdateValues(PreparedStatement ps) {

        try {
            ps.setDate(1, new java.sql.Date(arrivalDate.getTime()));
            ps.setDate(2, new java.sql.Date(departureDate.getTime()));
            ps.setLong(3, price);
            ps.setLong(4, creditCard.getId());
            ps.setLong(5, user.getId());
            ps.setLong(6, room.getId());
        } catch (SQLException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getColumnNamesForSelect() {
        return "id, arrivalDate, departureDate, price, creditCardId, userId, roomId";
    }

    @Override
    public String getWhereClauseForSelect() {
        if (this.getId() != null) {
            return "id = ?";
        }

        List<String> wheres = new ArrayList<>();

        if (arrivalDate != null) {
            wheres.add("arrivalDate like ?");
        }
        if (departureDate != null) {
            wheres.add("departureDate like ?");
        }
        if (price != 0L) {
            wheres.add("price like ?");
        }
        if (creditCard != null) {
            wheres.add("creditCardId like ?");
        }
        if (user != null) {
            wheres.add("userId like ?");
        }
        if (room != null) {
            wheres.add("roomId like ?");
        }

        return String.join(" or ", wheres);
    }

    @Override
    public void bindWhereClauseValuesForSelect(PreparedStatement ps) {
        if (this.getId() != null) {
            try {
                ps.setLong(1, this.getId());
            } catch (SQLException ex) {
                Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            int i = 1;

            if (arrivalDate != null) {
                try {
                    ps.setString(i, "%" + arrivalDate + "%");
                    i++;
                } catch (SQLException ex) {
                    Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (departureDate != null) {
                try {
                    ps.setString(i, "%" + departureDate + "%");
                    i++;
                } catch (SQLException ex) {
                    Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (price != 0L) {
                try {
                    ps.setString(i, "%" + price + "%");
                    i++;
                } catch (SQLException ex) {
                    Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (creditCard != null && creditCard.getId() != null) {
                try {
                    ps.setString(i, "%" + creditCard.getId() + "%");
                    i++;
                } catch (SQLException ex) {
                    Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (user != null && user.getId() != null) {
                try {
                    ps.setString(i, "%" + user.getId() + "%");
                    i++;
                } catch (SQLException ex) {
                    Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (room != null && room.getId() != null) {
                try {
                    ps.setString(i, "%" + room.getId() + "%");
                    i++;
                } catch (SQLException ex) {
                    Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public GenericEntity newFromResultSet(ResultSet rs) {

        Reservation r = new Reservation();

        try {
            r.setId(rs.getLong("id"));
            r.setArrivalDate(rs.getDate("arrivalDate"));
            r.setDepartureDate(rs.getDate("departureDate"));
            r.setPrice(rs.getLong("price"));
            CreditCard credit = new CreditCard();
            credit.setId(rs.getLong("creditCardId"));
            r.setCreditCard(credit);
            User user = new User();
            user.setId(rs.getLong("userId"));
            r.setUser(user);
            Room room = new Room();
            room.setId(rs.getLong("roomId"));
            r.setRoom(room);
        } catch (SQLException ex) {
            Logger.getLogger(CreditCard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
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
