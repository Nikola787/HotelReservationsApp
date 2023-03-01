/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

/**
 *
 * @author Nikola
 */
import database.DatabaseBroker;

public interface DBRepository<T> extends IRepository<T> {

    default public void connect() throws Exception {
        DatabaseBroker.getInstance().getConnection();
    }

    default public void disconnect() throws Exception {
        DatabaseBroker.getInstance().getConnection().close();
    }

    default public void commit() throws Exception {
        DatabaseBroker.getInstance().getConnection().commit();
    }

    default public void rollback() throws Exception {
        DatabaseBroker.getInstance().getConnection().rollback();
    }
}
