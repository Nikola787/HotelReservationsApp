/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations.users;

import domain.Room;
import domain.User;
import java.util.List;
import operations.AbstractOperation;

/**
 *
 * @author Nikola
 */
public class LoadAllUsersOpereation extends AbstractOperation {

    private List<User> users;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof User)) {
            throw new Exception("Invalid user data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.users = this.repository.getAll(param);
    }

    public List<User> getResult() {
        return users;
    }

}
