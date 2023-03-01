/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import domain.CreditCard;
import domain.Reservation;
import domain.Room;
import domain.Service;
import domain.User;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikola
 */
public class Communication {

    Socket socket;
    Sender sender;
    Receiver receiver;
    private static Communication instance;

    private Communication() throws Exception {
        socket = new Socket("localhost", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    public static Communication getInstance() throws Exception {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public User login(String username, String password) throws Exception {

        User gu = new User();
        gu.setUsername(username);
        gu.setPassword(password);

        Request request = new Request(Operation.LOGIN, gu);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (User) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void saveUser(User user) throws Exception {
        System.out.println("SAVE METODA" + user);
        Request request = new Request(Operation.SAVE_USER, user);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public List<Room> getAllRooms() throws Exception {

        Request request = new Request(Operation.GET_ALL_ROOMS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Room>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void saveRoom(Room room) throws Exception {
        Request request = new Request(Operation.SAVE_ROOM, room);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public Room loadRoom(Room room) throws Exception {

        Request request = new Request(Operation.LOAD_ROOM, room);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() != null) {
            throw response.getException();
        }
        return (Room) response.getResult();
    }

    public void deleteRoom(Room room) throws Exception {
        Request request = new Request(Operation.DELETE_ROOM, room);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public List<Reservation> getAllReservations() throws Exception {
        Request request = new Request(Operation.GET_ALL_RESERVATIONS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Reservation>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void saveCard(CreditCard creditCard) throws Exception {

        Request request = new Request(Operation.SAVE_CARD, creditCard);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public List<CreditCard> getAllCreditCards() throws Exception {

        Request request = new Request(Operation.GET_ALL_CARDS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<CreditCard>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<CreditCard> searchCreditCards(CreditCard credit) throws Exception {
        Request request = new Request(Operation.SEARCH_CARDS, credit);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<CreditCard>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void saveReservation(Reservation reservation) throws Exception {
        Request request = new Request(Operation.SAVE_RESERVATION, reservation);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void deleteUser(User user) throws Exception {
        Request request = new Request(Operation.DELETE_USER, user);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public List<Service> searchServices() throws Exception {
        Request request = new Request(Operation.SEARCH_SERVICES, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Service>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<User> getAllUsers() throws Exception {
        Request request = new Request(Operation.GET_ALL_USERS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<User>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void deleteReservation(Reservation res) throws Exception {
        Request request = new Request(Operation.DELETE_RESERVATION, res);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void saveService(Service service) throws Exception {
        Request request = new Request(Operation.SAVE_SERVICE, service);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

}
