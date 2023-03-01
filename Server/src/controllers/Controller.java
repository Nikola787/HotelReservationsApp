/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import creditcards.CreateCreditCardOperation;
import domain.CreditCard;
import domain.Reservation;
import domain.Room;
import domain.Service;
import domain.User;
import operations.creditCards.LoadAllCreditCardsOperation;
import operations.creditCards.SearchCreditCardsOperation;
import operations.reservations.CreateReservationOperation;
import operations.reservations.DeleteReservationOperation;
import operations.reservations.LoadAllReservationsOperation;
import operations.rooms.CreateRoomOperation;
import operations.rooms.DeleteRoomOperation;
import operations.rooms.LoadAllRoomsOperation;
import operations.rooms.UpdateRoomOperation;
import operations.services.CreateServiceOperation;
import operations.services.LoadAllServicesOperation;
import operations.users.CreateUserOperation;
import operations.users.DeleteUserOperation;
import operations.users.LoadAllUsersOpereation;
import operations.users.UpdateUserOperation;
import repositories.GenericRepository;

/**
 *
 * @author Nikola
 */
public class Controller {

    private static Controller instance;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }

        return instance;
    }

    public User login(User params) throws Exception {
        User u = null;

        for (User us : (new GenericRepository<User>()).getAll(new User())) {
            if (us.getUsername().equals(params.getUsername()) && us.getPassword().equals(params.getPassword())) {
                u = us;
                break;
            }
        }

        return u;
    }

    public void updateUser(User user) throws Exception {
        (new UpdateUserOperation()).execute(user);
    }

    public void createUser(User user) throws Exception {
        (new CreateUserOperation()).execute(user);
    }

    public Object loadAllRooms() throws Exception {
        LoadAllRoomsOperation o = new LoadAllRoomsOperation();
        o.execute(new Room());
        return o.getResult();
    }

    public void updateRoom(Room room) throws Exception {
        (new UpdateRoomOperation()).execute(room);
    }

    public void createRoom(Room room) throws Exception {
        (new CreateRoomOperation()).execute(room);
    }

    public void deleteRoom(Room room) throws Exception {
        (new DeleteRoomOperation()).execute(room);
    }

    public Object loadAllReservations() throws Exception {
        LoadAllReservationsOperation o = new LoadAllReservationsOperation();
        o.execute(new Reservation());
        return o.getResult();
    }

    public void createCreditCard(CreditCard creditCard) throws Exception {
        (new CreateCreditCardOperation()).execute(creditCard);
    }

    public Object loadAllCards() throws Exception {
        LoadAllCreditCardsOperation o = new LoadAllCreditCardsOperation();
        o.execute(new CreditCard());
        return o.getResult();
    }

    public Object searchCards(CreditCard cc) throws Exception {
        SearchCreditCardsOperation o = new SearchCreditCardsOperation();
        o.execute(cc);
        return o.getResult();
    }

    public void saveReservation(Reservation reservation) throws Exception {
        (new CreateReservationOperation()).execute(reservation);
    }

    public void deleteUser(User user) throws Exception {
        (new DeleteUserOperation()).execute(user);
    }

    public Object searchServices() throws Exception {
        LoadAllServicesOperation o = new LoadAllServicesOperation();
        o.execute(new Service());
        return o.getResult();
    }

    public Object loadAllUsers() throws Exception {
        LoadAllUsersOpereation o = new LoadAllUsersOpereation();
        o.execute(new User());
        return o.getResult();
    }

    public void deleteReservation(Reservation reservation) throws Exception {
        (new DeleteReservationOperation()).execute(reservation);
    }

    public void saveService(Service service) throws Exception {
        (new CreateServiceOperation()).execute(service);
    }
}
