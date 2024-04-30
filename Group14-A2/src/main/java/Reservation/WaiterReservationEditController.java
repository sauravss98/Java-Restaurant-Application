package Reservation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Class to handle the waiter reservation edit page
 * @author Saurav
 */
public class WaiterReservationEditController {
    private Stage stage;
    private Reservation currentReservation;
    @FXML
    private Label reservationTimeLabel;
    @FXML private Label reservationIdLabel;
    @FXML private Label reservationDateLabel;
    @FXML private Button approveButton;
    @FXML private Button backButton;

    /**
     * Default constructor for the class
     */
    public WaiterReservationEditController(){}

    /**
     * Function to set the stage object
     * @param stage The stage object is passed
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Function to set the reservation
     * @param currentReservation The reservation object is passed
     */
    public void setCurrentReservation(Reservation currentReservation) {
        this.currentReservation = currentReservation;
        initialize();
    }

    /**
     * Function to initialize the UI components
     */
    public void initialize(){
        renderCancelButton();
        refreshLabel();
        backButton.setOnAction(e->{
            handleBackClick();
        });
        approveButton.setOnAction(e->{
            handleCancelButtonClick();
        });
    }

    /**
     * Function to render the cancelButton based on the status of th reservation
     */
    private void renderCancelButton() {
        if(currentReservation!=null){
            if(Objects.equals(currentReservation.getBookingStatus(), "cancelled")){
                approveButton.setVisible(false);
            }
        }
    }

    private void handleCancelButtonClick() {
        currentReservation.setBookingStatus("confirmed");
        ReservationDataController.editReservationData(currentReservation);
        if(stage!=null){
            stage.close();
        }
    }

    private void handleBackClick() {
        if(stage!=null){
            stage.close();
        }
    }

    private void refreshLabel() {
        if(currentReservation!=null){
            reservationIdLabel.setText("Reservation ID: "+currentReservation.getReservationId());
            reservationTimeLabel.setText("Reservation Time: "+currentReservation.getBookingTime());
            reservationDateLabel.setText("Reservation Date: "+currentReservation.getDateOfReservation());
        }
    }
}
