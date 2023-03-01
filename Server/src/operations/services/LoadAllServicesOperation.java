/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations.services;

import domain.Service;
import java.util.List;
import operations.AbstractOperation;

/**
 *
 * @author Nikola
 */
public class LoadAllServicesOperation extends AbstractOperation {

    private List<Service> result;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Service)) {
            throw new Exception("Invalid service data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.result = this.repository.getAll((Service) param);
    }

    public List<Service> getResult() {
        return result;
    }

}
