package Reservation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ReservationManagerPage {
    private ArrayList<Reservation> reservations = ReservationDataController.getReservations();
    private Stage stage;

    @FXML
    private Button goBackButton;
    @FXML private ListView reservationList;

    public ReservationManagerPage(){}

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public  void initialize(){
        refreshReservationList();
        goBackButton.setOnAction(e->{
            handleBackButton();
        });
    }

    private void refreshReservationList() {
        reservationList.getItems().clear();

        for (Reservation reservation : reservations) {
            reservationList.getItems().add(reservation.toString());
        }
    }

    private void handleBackButton() {
        if(stage!=null){
            stage.close();
        }
    }
}
