/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.*;

/**
 *
 * @author Nikola
 */
public class DatabaseBroker {

    private Connection connection;
    private static DatabaseBroker instance;

    private DatabaseBroker() {
    }

    public static DatabaseBroker getInstance() {
        if (instance == null) {
            instance = new DatabaseBroker();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bazaseminarski", "root", "");
            this.connection.setAutoCommit(false);
        }

        return this.connection;
    }

    public void disconnect() throws SQLException {
        if (this.connection != null) {
            if (!this.connection.isClosed()) {
                this.connection.close();
            }

            this.connection = null;
        }
    }
}
