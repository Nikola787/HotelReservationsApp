/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Reservation;
import domain.Room;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikola
 */
public class ReservationTableModel extends AbstractTableModel {

    private final String[] columnNames = {"id", "arrivalDate", "departureDate", "price", "creditCardId", "roomId"};
    private final List<Reservation> reservations;

    public ReservationTableModel(List<Reservation> reservations) {
        this.reservations = reservations;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        if (reservations == null) {
            return 0;
        }
        return reservations.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public String getColumnName(int column) {

        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "ARRIVAL DATE";
            case 2:
                return "DEPARTURE DATE";
            case 3:
                return "PRICE (RSD)";
            case 4:
                return "CREDIT CARD";
            case 5:
                return "USER";
            case 6:
                return "ROOM";
            default:
                return "n/a";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Reservation reservation = reservations.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return reservation.getId();
            case 1:
                return reservation.getArrivalDate();
            case 2:
                return reservation.getDepartureDate();
            case 3:
                return reservation.getPrice();
            case 4:
                return reservation.getCreditCard();
            case 5:
                return reservation.getUser();
            case 6:
                return reservation.getRoom();
            default:
                return "n/a";
        }
    }

    public void removeItem(int row) {
        reservations.remove(row);
        
        
        fireTableDataChanged();
    }

    public Reservation getReservationAt(int row) {
        return this.reservations.get(row);
    }

    public void orderByDateAsc() {

        for (int i = 0; i < reservations.size(); i++) {
            for (int j = i + 1; j < reservations.size(); j++) {
                Reservation tmp = null;
                if (reservations.get(i).getArrivalDate().after(reservations.get(j).getArrivalDate())) {
                    tmp = reservations.get(i);
                    reservations.set(i, reservations.get(j));
                    reservations.set(j, tmp);
                }
            }
        }
        fireTableDataChanged();
    }

    public void orderByDateDesc() {
        for (int i = 0; i < reservations.size(); i++) {
            for (int j = i + 1; j < reservations.size(); j++) {
                Reservation tmp = null;
                if (reservations.get(i).getArrivalDate().before(reservations.get(j).getArrivalDate())) {
                    tmp = reservations.get(i);
                    reservations.set(i, reservations.get(j));
                    reservations.set(j, tmp);
                }
            }
        }
        fireTableDataChanged();
    }

}
