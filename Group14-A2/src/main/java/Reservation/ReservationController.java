package Reservation;

import User.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ReservationController {
    @FXML private Label reservationText;
    @FXML private Button createReservationButton;
    private static Customer activeCustomer;

    public ReservationController(){}

    public void setActiveCustomer(Customer activeCustomer) {
        this.activeCustomer = activeCustomer;
    }

    public void initialize(){
        reservationTextRefresh();
        createReservationButton.setOnAction(e->{
            handlReservationButton();
        });
    }

    private void handlReservationButton(){
        reservationTextRefresh();
    }
    public void reservationTextRefresh(){
        if(activeCustomer != null){
            reservationText.setText("Hi "+activeCustomer.getFirstName()+". These are your reservations");
        } else{
            reservationText.setText("Invalid Id");
        }
    }
}
