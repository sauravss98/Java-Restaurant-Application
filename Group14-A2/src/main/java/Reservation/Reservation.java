package Reservation;

import User.Customer;

import java.security.Timestamp;
import java.util.Date;

public class Reservation {
    private int reservationId;
    private int numberOfGuests;
    private Timestamp timeOfReservation;
    private Date dateOfReservation;
    private Customer customer;

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public Timestamp getTimeOfReservation() {
        return timeOfReservation;
    }

    public void setTimeOfReservation(Timestamp timeOfReservation) {
        this.timeOfReservation = timeOfReservation;
    }

    public Date getDateOfReservation() {
        return dateOfReservation;
    }

    public void setDateOfReservation(Date dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Reservation(int reservationId, int numberOfGuests, Timestamp timeOfReservation, Date dateOfReservation, Customer customer) {
        this.reservationId = reservationId;
        this.numberOfGuests = numberOfGuests;
        this.timeOfReservation = timeOfReservation;
        this.dateOfReservation = dateOfReservation;
        this.customer = customer;
    }
}
