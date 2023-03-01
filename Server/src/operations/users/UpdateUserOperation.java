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
public class UpdateUserOperation extends AbstractOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof User)) {
            throw new Exception("Invalid user data!");
        }

        User u = (User) param;

        boolean isValid = (u.getFirstName() != null && u.getFirstName().length() >= 3)
                && (u.getUsername() != null && u.getUsername().length() >= 3)
                && (u.getLastName() != null && u.getLastName().length() >= 3)
                && (u.getEmail() != null && u.getEmail().contains("@") && u.getEmail().length() >= 3)
                && (u.getPhone() != null && u.getPhone().length() >= 3)
                && (u.getPassword() != null && u.getPassword().length() >= 3);

        if (!isValid) {
            throw new Exception("Invalid user data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.repository.edit((User) param);
    }

}
