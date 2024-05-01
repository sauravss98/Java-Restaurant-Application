package Reservation;

import User.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class to control the reservation create page
 * @author Saurav
 */
public class ReservationCreateController {
    private static final ArrayList<Reservation> reservations = ReservationDataController.getReservations();
    private Customer customer;
    private Stage stage;
    @FXML private Spinner guestSpinner;
    @FXML private Button confirmReservationButton;
    @FXML private DatePicker datePickerElement;
    @FXML private ChoiceBox tableChoiceBox;
    @FXML private ChoiceBox timeChoiceBox;
    @FXML private Label warningLabel;
    private final ObservableList<String> options = FXCollections.observableArrayList();
    private final ObservableList<String> timeOptions = FXCollections.observableArrayList();

    /**
     * Default constructor for the class
     */
    public ReservationCreateController(){

    }

    /**
     * Function to set the stage instance to the class
     * @param stage The stage object is passed
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Function to set the customer object to the class
     * @param customer The customer object is passed
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Function to initialize the UI elements
     */
    public void initialize(){
        warningLabel.setVisible(false);
        tableChoiceBox.setItems(options);
        options.addAll("2 seat","4 seat","8 seat", "10 seat");
        tableChoiceBox.getSelectionModel().select("2 seat");
        timeChoiceBox.setItems(timeOptions);
        timeOptions.addAll("10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00");
        timeChoiceBox.getSelectionModel().select("10:00");
        refreshQuantitySpinner();
        confirmReservationButton.setOnAction(e->{
            handleConfirmButton();
        });
    }

    /**
     * Function to refresh the spinner UI element
     */
    private void refreshQuantitySpinner() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE,1);
        guestSpinner.setValueFactory(valueFactory);
    }

    /**
     * Function to handle the confirm button click
     */
    private void handleConfirmButton(){
        try {
            int reservationId = generateReservationId();
            int numberOfGuests = (int) guestSpinner.getValue();
            String tableType = tableChoiceBox.getValue().toString();
            int tablesRequired = calculateNumberOfTables(tableType,numberOfGuests);
            String time = timeChoiceBox.getValue().toString();
            String status = "pending";
            if(tablesRequired==0){
                tablesRequired =1;
            }
            System.out.println("table is "+ tablesRequired);
            LocalDate reservationDate = datePickerElement.getValue();
            if ((!reservationDate.isBefore(LocalDate.now()))|| reservationDate == null) {
                Reservation reservation = new Reservation(reservationId, numberOfGuests, reservationDate, customer, tableType, tablesRequired, time, status);
                ReservationDataController.addReservation(reservation);
                ReservationDataController.saveReservationDataToExcel(reservation);
            } else {
                warningLabel.setVisible(true);
                warningLabel.setText("Enter all details");
            }
            if (stage != null) {
                stage.close();
            }
        } catch (NullPointerException nullPointerException){
            warningLabel.setVisible(true);
            warningLabel.setText("Enter all details");
        }
    }

    /**
     * Function to calculate the number of tables required based on the table type selected and number of guests
     * @param table The table type is passed
     * @param numberOfGuests The number of guests is passed
     * @return The number of tables required as an integer value
     */
    private int calculateNumberOfTables(String table,int numberOfGuests) {
        if (Objects.equals(table, "2 seat")){
            if(numberOfGuests%2 ==0){
                return numberOfGuests/2;
            }
            return  (numberOfGuests/2)+1;
        } else if (Objects.equals(table, "4 seat")){
            if(numberOfGuests%4 ==0){
                return numberOfGuests/4;
            }
            return  (numberOfGuests/4)+1;
        } else if (Objects.equals(table, "8 seat")){
            if(numberOfGuests%8 ==0){
                return numberOfGuests/8;
            }
            return  (numberOfGuests/8)+1;
        } else if (Objects.equals(table, "10 seat")){
            if(numberOfGuests%10 ==0){
                return numberOfGuests/10;
            }
            return  (numberOfGuests/10)+1;
        }
        return 0;
    }

    /**
     * Function to generate the reservation id for new reservation based on the total number reservation that exists
     * @return Returns an integer id
     */
    public int generateReservationId() {
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
