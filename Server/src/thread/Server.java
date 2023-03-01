/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

/**
 *
 * @author Nikola
 */
import database.DatabaseBroker;
import thread.ProcessClientsRequests;
import domain.User;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import forms.FrmServer;

public class Server extends Thread {

    private ServerSocket serverSocket;
    private List<ProcessClientsRequests> pcrs;
    private FrmServer fs;

    public Server(FrmServer fs) {
        this.fs = fs;
        this.pcrs = new ArrayList<>();
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(9000);
            while (serverSocket != null && !serverSocket.isClosed()) {
                System.out.println("Waiting for connection...");
                Socket socket = serverSocket.accept();
                System.out.println("Connected!");
                handleClient(socket);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stopServer() throws SQLException, IOException {
        for (ProcessClientsRequests pcr : this.pcrs) {
            pcr.stopHandler();
        }

        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
            serverSocket = null;
        }

        DatabaseBroker.getInstance().disconnect();
    }

    public void removePcr(ProcessClientsRequests pcr) {
        this.pcrs.remove(pcr);
    }

    private void handleClient(Socket socket) throws Exception {
        ProcessClientsRequests pcr = new ProcessClientsRequests(socket);
        pcr.setServer(this);
        pcr.start();
        this.pcrs.add(pcr);
    }

    public List<User> getLoggedInUsers() {
        List<User> res = new ArrayList<>();
        for (ProcessClientsRequests pcr : this.pcrs) {
            if (pcr.getLoggedInUser() != null) {
                res.add(pcr.getLoggedInUser());
            }
        }
        return res;
    }
}
