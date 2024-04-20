package Reservation;

import java.util.ArrayList;

public class ReservationDataController {
    private static ArrayList<Reservation> reservations = new ArrayList<>();

    public static ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public static void addReservation(Reservation reservation){
        reservations.add(reservation);
    }
}
