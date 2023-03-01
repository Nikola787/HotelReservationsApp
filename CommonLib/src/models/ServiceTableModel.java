/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Room;
import domain.Service;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikola
 */
public class ServiceTableModel extends AbstractTableModel {

    private final String[] columnNames = {"id", "name", "dailyPrice"};
    private final List<Service> services;

    public ServiceTableModel(List<Service> services) {
        this.services = services;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        if (services == null) {
            return 0;
        }
        return services.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public String getColumnName(int column) {

        switch (column) {
            case 0:
                return "ID USLUGE";
            case 1:
                return "NAZIV USLUGE";
            case 2:
                return "CENA USLUGE DAN/RSD";
            default:
                return "n/a";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Service service = services.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return service.getId();
            case 1:
                return service.getName();
            case 2:
                return service.getDailyPrice();
            default:
                return "n/a";
        }
    }

    public void removeItem(int row) {
        services.remove(row);
        fireTableDataChanged();
    }

    public void addItem(Service service) {
        services.add(service);
        fireTableDataChanged();
    }

    public Service getServiceAt(int row) {
        return this.services.get(row);
    }

    public long returnPrice() {
        long price = 0L;
        for (int i = 0; i < services.size(); i++) {
            price += services.get(i).getDailyPrice();
        }
        return price;
    }

    public Service returnObject(int row) {
        return services.get(row);
    }
}
