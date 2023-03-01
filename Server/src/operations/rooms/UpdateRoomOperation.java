/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations.rooms;

import domain.Room;
import operations.AbstractOperation;

/**
 *
 * @author Nikola
 */
public class UpdateRoomOperation extends AbstractOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Room)) {
            throw new Exception("Invalid room data!");
        }

        Room r = (Room) param;

        boolean isValid = (r.getFloor() != null && r.getFloor().length() >= 3)
                && (r.getRoomType() != null && r.getRoomType().length() >= 3);

        if (!isValid) {
            throw new Exception("Invalid room data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.repository.edit((Room) param);
    }
}
