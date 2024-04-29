package Reservation;

import javafx.stage.Stage;

public class CustomerReservationEditController {
    private Stage stage;
    private Reservation currentReservation;

    public CustomerReservationEditController(){}

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCurrentReservation(Reservation currentReservation) {
        this.currentReservation = currentReservation;
    }

}
