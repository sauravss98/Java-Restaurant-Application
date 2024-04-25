package User;

import Orders.Order;
import Orders.OrderDataHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DriverDeliveryPageController {
    private static Driver currentDriver;
    private Stage stage;
    private static Customer customer;
    private Order currentOrder;
    @FXML
    private Label orderIdLabel;
    @FXML private Label customerNameLabel;
    @FXML private Label addressLabel;
    @FXML private Button completeOrderButton;

    public DriverDeliveryPageController(){}

    public void setCurrentDriver(Driver driver){
        this.currentDriver = driver;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public void setCurrentOrder(Order order){
        this.currentOrder = order;
        initialize();
    }

    public void initialize(){
        completeOrderButton.setOnAction(e->{
            handleCompleteButton();
        });
        refreshWindow();
    }

    private void handleCompleteButton() {
        currentOrder.setOrderStatus("Completed");
        currentOrder.setCompleted(true);
        OrderDataHandler.editDriverExcelData(currentOrder,"completed");
        if (stage!=null){
            stage.close();
        }
    }

    private void refreshWindow() {
        if (currentOrder != null && customer != null) {
            orderIdLabel.setText("Order Id: "+currentOrder.getOrderId());
            customerNameLabel.setText("Customer Name: "+customer.getFirstName());
            addressLabel.setText("Delivery Address :"+customer.getAddress());
        } else {
            System.out.println("Customer or current order is null.");
        }
    }
}
