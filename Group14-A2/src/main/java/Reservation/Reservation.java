package Reservation;

import User.Customer;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class to create object of Reservation instance
 * @author Saurav
 */
public class Reservation {
    private int reservationId;
    private int numberOfGuests;
    private LocalDate dateOfReservation;
    private Customer customer;
    private String tabletype;
    private int numberOfTables;
    private LocalDateTime time;

    /**
     * Funtion to get the table type
     * @return Returns the string as table type
     */
    public String getTabletype() {
        return tabletype;
    }

    /**
     * Function to set the table type to the instance
     * @param tabletype Table type is sent as string
     */
    public void setTabletype(String tabletype) {
        this.tabletype = tabletype;
    }

    /**
     * Function to get the number of tables
     * @return Integer value with number of tables
     */
    public int getNumberOfTables() {
        return numberOfTables;
    }

    /**
     * Function which sets the number of tables
     * @param numberOfTables Integer value with number of tables is set
     */
    public void setNumberOfTables(int numberOfTables) {
        this.numberOfTables = numberOfTables;
    }

    /**
     * Function to get the reservation id
     * @return Returns an integer id value
     */
    public int getReservationId() {
        return reservationId;
    }

    /**
     * Function to set the reservation id to the instance
     * @param reservationId The reservation id is  set
     */
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    /**
     * Function to return the number of guests
     * @return Returns the number of guests as integer
     */
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    /**
     * Function to set the number of guests
     * @param numberOfGuests The integer value of number of guests is passed
     */
    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    /**
     * Function to get data of the reservation
     * @return Returns the date of the reservation made
     */
    public LocalDate getDateOfReservation() {
        return dateOfReservation;
    }

    /**
     * Function to set the date of reservation
     * @param dateOfReservation The date of reservation is passed
     */
    public void setDateOfReservation(LocalDate dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
    }

    /**
     * Function to get the customer instance saved in the object
     * @return Returns the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Function to set the customer object to the instance
     * @param customer The customer instance is set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Constructor to create new instance of the class
     * @param reservationId The id is passed
     * @param numberOfGuests The number of guests is passed
     * @param dateOfReservation The date of the reservation is passsed
     * @param customer The customer instance is passed
     * @param tabletype The table type is sent
     * @param numberOfTables The number of tables is sent
     */
    public Reservation(int reservationId, int numberOfGuests, LocalDate dateOfReservation, Customer customer,String tabletype,int numberOfTables) {
        this.reservationId = reservationId;
        this.numberOfGuests = numberOfGuests;
        this.dateOfReservation = dateOfReservation;
        this.customer = customer;
        this.tabletype = tabletype;
        this.numberOfTables = numberOfTables;
    }

    /**
     * Function to get the to string to display the string
     * @return Returns the string with all required data
     */
    @Override
    public String toString() {
        return "reservationId= " + reservationId +
                ", numberOfGuests= " + numberOfGuests +
                ", dateOfReservation= " + dateOfReservation +
                ", customer= " + customer.getFullName() +
                ", table type= "+tabletype+
                ", Number of tables= "+numberOfTables;
    }
}
