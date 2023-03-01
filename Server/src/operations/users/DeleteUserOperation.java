/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations.users;

import domain.User;
import operations.AbstractOperation;

/**
 *
 * @author Nikola
 */
public class DeleteUserOperation extends AbstractOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof User)) {
            throw new Exception("Invalid user data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.repository.delete((User) param);
    }
}
