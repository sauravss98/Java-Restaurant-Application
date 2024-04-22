package Orders;

import Reservation.Reservation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ManagerOrderView {
    private ArrayList<Order> orders = OrderDataHandler.getOrders();
    private Stage stage;

    @FXML private Button goBackButton;
    @FXML private ListView orderList;

    public ManagerOrderView(){}

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public  void initialize(){
        refreshOrdersList();
        goBackButton.setOnAction(e->{
            handleBackButton();
        });
    }

    private void refreshOrdersList() {
        orderList.getItems().clear();

        for (Order order : orders) {
            orderList.getItems().add(order.getDescriptionForOrderList());
        }
    }

    private void handleBackButton() {
        if(stage!=null){
            stage.close();
        }
    }
}
