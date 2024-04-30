package Reservation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Class to control the customer reservation edit pages
 * @author Saurav
 */
public class CustomerReservationEditController {
    private Stage stage;
    private Reservation currentReservation;
    @FXML private Label reservationTimeLabel;
    @FXML private Label reservationIdLabel;
    @FXML private Label reservationDateLabel;
    @FXML private Button cancelBookingButton;
    @FXML private Button backButton;

    /**
     * Default constructor for the class
     */
    public CustomerReservationEditController(){}

    /**
     * Function to set the stage object to the class
     * @param stage The stage object is passed
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Function to set the reservation object
     * @param currentReservation The reservation object is passed
     */
    public void setCurrentReservation(Reservation currentReservation) {
        this.currentReservation = currentReservation;
        initialize();
    }

    /**
     * Function to initialize the UI component
     */
    public void initialize(){
        renderCancelButton();
        refreshLabel();
        backButton.setOnAction(e->{
            handleBackClick();
        });
        cancelBookingButton.setOnAction(e->{
            handleCancelButtonClick();
        });
    }

    /**
     * Function to show/hide the cancel button based on the status of the reservation
     */
    private void renderCancelButton() {
        if(currentReservation!=null){
            if(Objects.equals(currentReservation.getBookingStatus(), "cancelled")){
                cancelBookingButton.setVisible(false);
            }
        }
    }

    /**
     * Function to handle the cancel button click
     */
    private void handleCancelButtonClick() {
        currentReservation.setBookingStatus("cancelled");
        ReservationDataController.editReservationData(currentReservation);
        if(stage!=null){
            stage.close();
        }
    }

    /**
     * Function to handle the back button click
     */
    private void handleBackClick() {
        if(stage!=null){
            stage.close();
        }
    }

    /**
     * Function to refresh the text in the poge
     */
    private void refreshLabel() {
        if(currentReservation!=null){
            reservationIdLabel.setText("Reservation ID: "+currentReservation.getReservationId());
            reservationTimeLabel.setText("Reservation Time: "+currentReservation.getBookingTime());
            reservationDateLabel.setText("Reservation Date: "+currentReservation.getDateOfReservation());
        }
    }
}
