package Reservation;

import User.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class ReservationController {
    @FXML private Label ReservationText;
    private Customer activeCustomer;

    public ReservationController(){

    }

    public void initialize(){
//        reservationTextRefresh();
    }

    public void reservationTextRefresh(){
        System.out.println("Active customer is in the reservation" +activeCustomer.getFirstName());
        if(activeCustomer != null){
            ReservationText.setText("Hi "+activeCustomer.getFirstName()+". These are your reservations");
        } else{
            ReservationText.setText("Invalid Id");
        }
    }

    public void setActiveCustomer(Customer activeCustomer) {
        this.activeCustomer = activeCustomer;
        System.out.println("Active customer is in method" +activeCustomer.getFirstName());
        reservationTextRefresh();
    }
}
