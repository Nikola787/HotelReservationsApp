/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations.reservations;

import domain.Reservation;
import java.util.List;
import operations.AbstractOperation;

/**
 *
 * @author Nikola
 */
public class LoadAllReservationsOperation extends AbstractOperation {

    private List<Reservation> reservations;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Reservation)) {
            throw new Exception("Invalid reservation data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.reservations = this.repository.getAll(param);
    }

    public List<Reservation> getResult() {
        return reservations;
    }

}
