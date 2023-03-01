/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations.reservations;

import domain.Reservation;
import domain.Room;
import operations.AbstractOperation;

/**
 *
 * @author Nikola
 */
public class DeleteReservationOperation extends AbstractOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Reservation)) {
            throw new Exception("Invalid reservation data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.repository.delete((Reservation) param);
    }

}
