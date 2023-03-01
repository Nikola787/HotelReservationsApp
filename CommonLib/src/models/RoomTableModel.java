/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Room;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikola
 */
public class RoomTableModel extends AbstractTableModel {

    private final String[] columnNames = {"id", "floor", "roomType", "additionalMessage"};
    private final List<Room> rooms;

    public RoomTableModel(List<Room> rooms) {
        this.rooms = rooms;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        if(rooms == null){
            return 0;
        }
        return rooms.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public String getColumnName(int column) {

        switch (column) {
            case 0:
                return "ID SOBE";
            case 1:
                return "SPRAT";
            case 2:
                return "TIP SOBE";
            case 3:
                return "NAPOMENA";
            default:
                return "n/a";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Room room = rooms.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return room.getId();
            case 1:
                return room.getFloor();
            case 2:
                return room.getRoomType();
            case 3:
                return room.getAdditionalMessage();
            default:
                return "n/a";
        }
    }

    public void removeItem(int row) {
        rooms.remove(row);
        fireTableDataChanged();
    }

    public Room getRoomAt(int row) {
        return this.rooms.get(row);
    }


}
