/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controllers.Controller;
import domain.CreditCard;
import domain.Reservation;
import domain.Room;
import domain.Service;
import domain.User;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import thread.Server;

/**
 *
 * @author Nikola
 */
public class ProcessClientsRequests extends Thread {

    User loggedInUser;
    Socket socket;
    Sender sender;
    Receiver receiver;
    Server server;

    public ProcessClientsRequests(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    public void stopHandler() throws IOException {
        if (this.socket != null) {
            if (!this.socket.isClosed()) {
                this.socket.close();
            }
        }
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                try {
                    switch (request.getOperation()) {
                        case LOGIN:
                            User params = (User) request.getArgument();
                            User u = Controller.getInstance().login(params);
                            response.setResult(u);
                            this.loggedInUser = u;
                            break;
                        case SAVE_USER: // ovo se koristi za azuriranje kao i za kreiranje novog naloga
                            User user = (User) request.getArgument();
                            if (user.getId() != null) {
                                Controller.getInstance().updateUser(user);
                            } else {
                                Controller.getInstance().createUser(user);
                            }
                            response.setResult(user);
                            break;
                        case GET_ALL_ROOMS:
                            response.setResult(Controller.getInstance().loadAllRooms());
                            break;
                        case SAVE_ROOM:
                            Room room = (Room) request.getArgument();
                            if (room.getId() != null) {
                                Controller.getInstance().updateRoom(room);
                            } else {
                                Controller.getInstance().createRoom(room);
                            }
                            response.setResult(room);
                            break;
                        case DELETE_ROOM:
                            room = (Room) request.getArgument();
                            Controller.getInstance().deleteRoom(room);
                            response.setResult(room);
                            break;
                        case GET_ALL_RESERVATIONS:
                            response.setResult(Controller.getInstance().loadAllReservations());
                            break;
                        case SAVE_CARD:
                            CreditCard creditCard = (CreditCard) request.getArgument();
                            if (creditCard.getId() != null) {

                            } else {
                                Controller.getInstance().createCreditCard(creditCard);
                            }
                            response.setResult(creditCard);
                            break;
                        case GET_ALL_CARDS:
                            response.setResult(Controller.getInstance().loadAllCards());
                            break;
                        case SEARCH_CARDS:
                            CreditCard cc = (CreditCard) request.getArgument();
                            response.setResult(Controller.getInstance().searchCards(cc));
                            break;
                        case SAVE_RESERVATION:
                            Reservation reservation = (Reservation) request.getArgument();
                            if (reservation.getId() != null) {

                            } else {
                                Controller.getInstance().saveReservation(reservation);
                            }
                            response.setResult(reservation);
                            break;
                        case DELETE_USER:
                            user = (User) request.getArgument();
                            Controller.getInstance().deleteUser(user);
                            response.setResult(user);
                            break;
                        case SEARCH_SERVICES:
                            response.setResult(Controller.getInstance().searchServices());
                            break;
                        case GET_ALL_USERS:
                            response.setResult(Controller.getInstance().loadAllUsers());
                            break;
                        case DELETE_RESERVATION:
                            reservation = (Reservation) request.getArgument();
                            Controller.getInstance().deleteReservation(reservation);
                            response.setResult(reservation);
                            break;
                        case SAVE_SERVICE:
                            Service service = (Service) request.getArgument();
                            Controller.getInstance().saveService(service);
                            response.setResult(service);
                            break;

                    }
                } catch (Exception e) {
                    response.setException(e);
                    Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, e);
                }

                sender.send(response);

            } catch (Exception ex) {
                this.loggedInUser = null;
                this.server.removePcr(this);
                try {
                    this.socket.close();
                    this.socket = null;
                } catch (IOException exc) {
                }
                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
    }

}
