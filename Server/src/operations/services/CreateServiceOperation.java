/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations.services;

import domain.Room;
import domain.Service;
import operations.AbstractOperation;

/**
 *
 * @author Nikola
 */
public class CreateServiceOperation extends AbstractOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Service)) {
            throw new Exception("Invalid service data!");
        }

        Service s = (Service) param;

        boolean isValid = (s.getName()!= null && s.getName().length() >= 3)
                && (s.getDailyPrice() != 0L);

        if (!isValid) {
            throw new Exception("Invalid room data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.repository.add((Service) param);
    }

}
