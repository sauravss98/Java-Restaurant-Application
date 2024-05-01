package Reservation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Class used to control the manager reservation view page
 * @author Saurav
 */
public class ReservationManagerPage {
    private final ArrayList<Reservation> reservations = ReservationDataController.getReservations();
    private Stage stage;

    @FXML
    private Button goBackButton;
    @FXML private ListView reservationList;

    /**
     * Default constructor for the class
     */
    public ReservationManagerPage(){}

    /**
     * Function to set the stage object to the class when initialized
     * @param stage The stage object is used
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Function to initialize the UI components
     */
    public  void initialize(){
        refreshReservationList();
        goBackButton.setOnAction(e->{
            handleBackButton();
        });
    }

    /**
     * Function to refresh the reservations list when the page is loaded
     */
    private void refreshReservationList() {
        reservationList.getItems().clear();

        for (Reservation reservation : reservations) {
            reservationList.getItems().add(reservation.toString());
        }
    }

    /**
     * Function to handle the back button click and it closes the stage/window
     */
    private void handleBackButton() {
        if(stage!=null){
            stage.close();
        }
    }
}
