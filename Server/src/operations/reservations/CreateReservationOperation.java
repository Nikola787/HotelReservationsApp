/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations.reservations;

import domain.Reservation;
import domain.ReservationItem;
import domain.Room;
import domain.Service;
import operations.AbstractOperation;

/**
 *
 * @author Nikola
 */
public class CreateReservationOperation extends AbstractOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Reservation)) {
            throw new Exception("Invalid reservation data!");
        }

        Reservation r = (Reservation) param;

        boolean isValid = (r.getPrice() >= 0);

        if (!isValid) {
            throw new Exception("Invalid room data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {

        Reservation reservation = (Reservation) param;

        // create product if its new
        if (reservation.getId() == null) {
            this.repository.add(reservation);

            // If the product was created successfully,
            // create all of its features too
            if (reservation.getId() != null) {
                for (Service ser : reservation.getServices()) {
                    ReservationItem ri = new ReservationItem();
                    ri.setReservation(reservation);
                    ri.setService(ser);
                    this.repository.add(ri);
                }
            }
        }

        if (reservation.getId() != null) {
            this.repository.edit(reservation);
        } else {
            this.repository.add(reservation);
        }

    }
}
