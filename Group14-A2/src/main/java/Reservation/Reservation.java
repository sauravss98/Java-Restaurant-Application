package Reservation;

import User.Customer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation {
    private int reservationId;
    private int numberOfGuests;
    private LocalDateTime timeOfReservation;
    private LocalDate dateOfReservation;
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

    public LocalDateTime getTimeOfReservation() {
        return timeOfReservation;
    }

    public void setTimeOfReservation(LocalDateTime timeOfReservation) {
        this.timeOfReservation = timeOfReservation;
    }

    public LocalDate getDateOfReservation() {
        return dateOfReservation;
    }

    public void setDateOfReservation(LocalDate dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Reservation(int reservationId, int numberOfGuests, LocalDateTime timeOfReservation, LocalDate dateOfReservation, Customer customer) {
        this.reservationId = reservationId;
        this.numberOfGuests = numberOfGuests;
        this.timeOfReservation = timeOfReservation;
        this.dateOfReservation = dateOfReservation;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", numberOfGuests=" + numberOfGuests +
                ", timeOfReservation=" + timeOfReservation +
                ", dateOfReservation=" + dateOfReservation +
                ", customer=" + customer +
                '}';
    }
}
