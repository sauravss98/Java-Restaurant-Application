package Reservation;

import User.Customer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation {
    private int reservationId;
    private int numberOfGuests;
    private LocalDate dateOfReservation;
    private Customer customer;
    private String tabletype;
    private int numberOfTables;
    private LocalDateTime time;

    public String getTabletype() {
        return tabletype;
    }

    public void setTabletype(String tabletype) {
        this.tabletype = tabletype;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(int numberOfTables) {
        this.numberOfTables = numberOfTables;
    }

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

    public Reservation(int reservationId, int numberOfGuests, LocalDate dateOfReservation, Customer customer,String tabletype,int numberOfTables) {
        this.reservationId = reservationId;
        this.numberOfGuests = numberOfGuests;
        this.dateOfReservation = dateOfReservation;
        this.customer = customer;
        this.tabletype = tabletype;
        this.numberOfTables = numberOfTables;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", numberOfGuests=" + numberOfGuests +
                ", dateOfReservation=" + dateOfReservation +
                ", customer=" + customer +
                '}';
    }
}
