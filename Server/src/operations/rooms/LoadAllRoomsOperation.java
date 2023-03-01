/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations.rooms;

import domain.Room;
import domain.User;
import java.util.List;
import operations.AbstractOperation;

/**
 *
 * @author Nikola
 */
public class LoadAllRoomsOperation extends AbstractOperation {

    private List<Room> rooms;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Room)) {
            throw new Exception("Invalid room data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.rooms = this.repository.getAll(param);
    }

    public List<Room> getResult() {
        return rooms;
    }

}
