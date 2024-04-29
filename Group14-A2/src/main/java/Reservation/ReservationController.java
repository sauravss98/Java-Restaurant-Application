package Reservation;

import User.Customer;
import cafe94.group14a2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to handle the reservation main page
 * @author Saurav
 */
public class ReservationController {
    private static ArrayList<Reservation> reservations = ReservationDataController.getReservations();
    @FXML private Label reservationText;
    @FXML private Button createReservationButton;
    @FXML private ListView reservationList;
    @FXML private Button orderSwitchPage;
    private static Customer activeCustomer;

    /**
     * Constructor to create default UI components
     */
    public ReservationController(){}

    /**
     * Function to set the customer object to the UI page
     * @param activeCustomer
     */
    public void setActiveCustomer(Customer activeCustomer) {
        this.activeCustomer = activeCustomer;
    }

    /**
     * Function to initialize the UI components
     */
    public void initialize(){
        reservationTextRefresh();
        refreshRefreshListView();
        createReservationButton.setOnAction(e->{
            try {
                handlReservationButton();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        orderSwitchPage.setOnAction(e->{
            try {
                Main.setRoot("customerMainPage");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        reservationList.setOnMouseClicked(e->{
            String selectedReservationDescription = (String) reservationList.getSelectionModel().getSelectedItem();
            try {
                handleReservation(selectedReservationDescription);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void handleReservation(String reservation) throws IOException {
        String id = extractID(reservation);
        Reservation requiredReservation = getReservationDataData(id);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/reservationEditPageCustomer.fxml"));
        Parent root = loader.load();
        CustomerReservationEditController controller = loader.getController();
        controller.setCurrentReservation(requiredReservation);
        Stage reservationEditPage = new Stage();
        controller.setStage(reservationEditPage);
        reservationEditPage.setTitle("Edit Reservation Status");
        reservationEditPage.setScene(new Scene(root, 600, 600));
        reservationEditPage.initModality(Modality.APPLICATION_MODAL);
        reservationEditPage.showAndWait();
        refreshRefreshListView();
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
     * Function to refresh the list view
     */
    public void refreshRefreshListView(){
        reservationList.getItems().clear();

        for (Reservation reservation : reservations) {
            if(reservation.getCustomer().getCustomerID() == activeCustomer.getCustomerID())
            reservationList.getItems().add(reservation.toString());
        }
    }

    /**
     * Function to handle the reservation button click, which when pressed it will load a new window where usr can create the reservation
     * @throws IOException Exception to handle issue when loading the fxml file
     */
    private void handlReservationButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/reservationCreatePage.fxml"));
        Parent root = loader.load();
        ReservationCreateController controller = loader.getController();
        Stage orderTypeStage = new Stage();
        orderTypeStage.setTitle("Select Order Type");
        orderTypeStage.setScene(new Scene(root, 600, 600));
        controller.setCustomer(activeCustomer);
        controller.setStage(orderTypeStage);
        orderTypeStage.initModality(Modality.APPLICATION_MODAL);
        orderTypeStage.showAndWait();
        for(Reservation reservation:reservations){
            System.out.println(reservation.toString());
        }
        initialize();
    }

    /**
     * Function to refresh the text in the reservation page
     */
    public void reservationTextRefresh(){
        if(activeCustomer != null){
            reservationText.setText("Hi "+activeCustomer.getFirstName()+". These are your reservations");
        } else{
            reservationText.setText("Invalid Id");
        }
    }
}
