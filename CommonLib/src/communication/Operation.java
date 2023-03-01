package communication;

import java.io.Serializable;

public enum Operation implements Serializable {

    LOGIN,
    SAVE_USER,
    GET_ALL_ROOMS,
    SAVE_ROOM,
    LOAD_ROOM,
    DELETE_ROOM,
    GET_ALL_RESERVATIONS, 
    SAVE_CARD, 
    GET_ALL_CARDS,
    SEARCH_CARDS, SAVE_RESERVATION,
    DELETE_USER,
    SEARCH_SERVICES,
    GET_ALL_USERS,
    DELETE_RESERVATION,
    SAVE_SERVICE,

}
