package Reservation;

import User.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReservationCreateController {
    private static ArrayList<Reservation> reservations = ReservationDataController.getReservations();
    private Customer customer;
    private Stage stage;
    @FXML private Spinner guestSpinner;
    @FXML private Button confirmReservationButton;
    @FXML private DatePicker datePickerElement;
    private Reservation reservation;

    public ReservationCreateController(){

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void initialize(){
        refreshQuantitySpinner();
        confirmReservationButton.setOnAction(e->{
            handleConfirmButton();
        });
    }

    private void refreshQuantitySpinner() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE,1);
        guestSpinner.setValueFactory(valueFactory);
    }

    private void handleConfirmButton(){
        int reservationId = generateOrderId();
        int numberOfGuests = (int) guestSpinner.getValue();
        LocalDate reservationDate = datePickerElement.getValue();
        reservation = new Reservation(reservationId,numberOfGuests,reservationDate,customer);
        ReservationDataController.addReservation(reservation);
        ReservationDataController.saveReservationDataToExcel(reservation);
        if (stage != null) {
            stage.close();
        }
    }

    public int generateOrderId() {
        int maxReservationId = 0;
        // Find the maximum order ID from the existing orders
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() > maxReservationId) {
                maxReservationId = reservation.getReservationId();
            }
        }
        // Return the next available order ID
        return maxReservationId + 1;
    }
}
