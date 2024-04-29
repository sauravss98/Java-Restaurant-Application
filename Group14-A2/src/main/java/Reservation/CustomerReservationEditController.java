package Reservation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CustomerReservationEditController {
    private Stage stage;
    private Reservation currentReservation;
    @FXML private Label reservationTimeLabel;
    @FXML private Label reservationIdLabel;
    @FXML private Label reservationDateLabel;
    @FXML private Button cancelBookingButton;
    @FXML private Button backButton;

    public CustomerReservationEditController(){}

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCurrentReservation(Reservation currentReservation) {
        this.currentReservation = currentReservation;
        initialize();
    }

    public void initialize(){
        refreshLabel();
        backButton.setOnAction(e->{
            handleBackClick();
        });
        cancelBookingButton.setOnAction(e->{
            handleCancelButtonClick();
        });
    }

    private void handleCancelButtonClick() {
        currentReservation.setBookingStatus("Cancelled");
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
