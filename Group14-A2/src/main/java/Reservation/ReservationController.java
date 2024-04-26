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

public class ReservationController {
    private static ArrayList<Reservation> reservations = ReservationDataController.getReservations();
    @FXML private Label reservationText;
    @FXML private Button createReservationButton;
    @FXML private ListView reservationList;
    @FXML private Button orderSwitchPage;
    private static Customer activeCustomer;

    public ReservationController(){}

    public void setActiveCustomer(Customer activeCustomer) {
        this.activeCustomer = activeCustomer;
    }

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
    }

    public void refreshRefreshListView(){
        reservationList.getItems().clear();

        for (Reservation reservation : reservations) {
            if(reservation.getCustomer().getCustomerID() == activeCustomer.getCustomerID())
            reservationList.getItems().add(reservation.toString());
        }
    }

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
    public void reservationTextRefresh(){
        if(activeCustomer != null){
            reservationText.setText("Hi "+activeCustomer.getFirstName()+". These are your reservations");
        } else{
            reservationText.setText("Invalid Id");
        }
    }
}
