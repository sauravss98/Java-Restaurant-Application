package Reservation;

import User.UserController;
import User.Waiter;
import cafe94.group14a2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class for handling the waiter reservation main page
 * @author Saurav
 */
public class WaiterReservationMainPageController {
    private ArrayList<Waiter> waiters = UserController.getWaiters();
    private ArrayList<Reservation> reservations = ReservationDataController.getReservations();
    private Waiter activeWaiter;
    @FXML private Button backButton;
    @FXML private ListView incompleteList;
    @FXML private ListView completedList;

    /**
     * Default constructor for the class
     */
    public WaiterReservationMainPageController(){}

    /**
     * Counstructor used to create a class instance and instantiate the waiter
     * @param email The email is passed as a parameter
     */
    public WaiterReservationMainPageController(String email){
        for (Waiter waiter:waiters){
            if(Objects.equals(waiter.getEmail(), email)){
                activeWaiter = waiter;
            }
        }
    }

    /**
     * Function to intialize the UI component
     */
    public void initialize(){
        renderListView();
        incompleteList.setOnMouseClicked(e->{
            String selectedReservationDescription = (String) incompleteList.getSelectionModel().getSelectedItem();
            try {
                handleReservation(selectedReservationDescription);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        backButton.setOnAction(e->{
            handleBackClick();
        });
    }

    /**
     * Function to handle the back click
     */
    private void handleBackClick() {
        try {
            Main.setRoot("waiterMainPage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Function to handle to open the new reservation page
     * @param reservation The reservation id is passed as the string
     * @throws IOException The exception to handle file issues
     */
    private void handleReservation(String reservation) throws IOException {
        String id = extractID(reservation);
        Reservation requiredReservation = getReservationDataData(id);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/reservationEditPageWaiter.fxml"));
        Parent root = loader.load();
        WaiterReservationEditController controller = loader.getController();
        controller.setCurrentReservation(requiredReservation);
        Stage reservationEditPage = new Stage();
        controller.setStage(reservationEditPage);
        reservationEditPage.setTitle("Approve Reservation");
        reservationEditPage.setScene(new Scene(root, 600, 600));
        reservationEditPage.initModality(Modality.APPLICATION_MODAL);
        reservationEditPage.showAndWait();
        renderListView();
    }

    /**
     * A function to get the id from the String recieved based on the user input
     * @param data it sends the data as a string
     * @return id
     */
    public static String extractID(String data) {
        int startIndex = data.indexOf("ID: ") + 4; // Add 4 to skip "ID: "
        int endIndex = data.indexOf(" ", startIndex); // Find the space after the ID value
        if (endIndex == -1) {
            endIndex = data.length(); // If no space is found, consider the end of the string
        }
        return data.substring(startIndex, endIndex);
    }

    /**
     * A function to return the reservation corresponding to the id passed through
     * @param idString it sends id as a string
     * @return reservation
     */
    public Reservation getReservationDataData(String idString){
        int id = Integer.parseInt(idString);
        for(Reservation reservation: reservations){
            if(reservation.getReservationId() == id){
                return reservation;
            }
        }
        return null;
    }

    /**
     * Function to render the list view
     */
    private void renderListView() {
        incompleteList.getItems().clear();

        for (Reservation reservation : reservations) {
            if(!(Objects.equals(reservation.getBookingStatus(), "confirmed"))) {
                incompleteList.getItems().add(reservation.toString());
            }
        }

        completedList.getItems().clear();

        for (Reservation reservation : reservations) {
            if(Objects.equals(reservation.getBookingStatus(), "confirmed")) {
                completedList.getItems().add(reservation.toString());
            }
        }
    }

}
