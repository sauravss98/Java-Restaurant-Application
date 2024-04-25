package Orders;

import User.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class DriverCardController {
    private Order currentOrder;
    private static Driver currentDriver;
    private ArrayList<Customer> customers = UserController.getCustomers();
    @FXML private Label orderIdLabel;
    @FXML private Label customerNameLabel;
    @FXML private Label addressLabel;
    @FXML private Button completeOrderButton;
    private DriverMainPageController driverMainPageController;

    public DriverCardController(){}

    public void setOrder(Order order){
        this.currentOrder = order;
    }

    public void setDriverMainPageController(DriverMainPageController controller) {
        this.driverMainPageController = controller;
    }

    public void setCurrentDriver(Driver driver){
        this.currentDriver = driver;
    }

    public void initialize(){
        if (currentOrder!=null && currentDriver!=null){
            refreshCard();
        }
        completeOrderButton.setOnAction(e->{
            try {
                handleCompleteButton();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void handleCompleteButton() throws IOException {
        currentOrder.setOrderStatus("Delivery");
        currentOrder.setDriverId(currentDriver.getUserId());
        OrderDataHandler.editDriverExcelData(currentOrder,"predelivery");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/driverDeliveryPage.fxml"));
        Parent root = loader.load();
        DriverDeliveryPageController controller = loader.getController();
        controller.setCurrentDriver(currentDriver);
        controller.setCurrentOrder(currentOrder);
        controller.setCustomer(getCustomerData(currentOrder));
        controller.initialize();
        Stage driverDeliveryStage = new Stage();
        controller.setStage(driverDeliveryStage);
        driverDeliveryStage.setTitle("Complete Delivery");
        driverDeliveryStage.setScene(new Scene(root, 600, 600));
        driverDeliveryStage.initModality(Modality.APPLICATION_MODAL);
        driverDeliveryStage.showAndWait();
        driverMainPageController.refreshCardDisplay();
    }

    private void refreshCard() {
        Customer orderCustomer = getCustomerData(currentOrder);
        orderIdLabel.setText("Order Id: "+currentOrder.getOrderId());
        customerNameLabel.setText("Customer Name: "+orderCustomer.getFirstName());
        addressLabel.setText("Delivery Address :"+orderCustomer.getAddress());
    }

    private Customer getCustomerData(Order order) {
        for(Customer customer : customers)
            if(customer.getUserId() == order.getCustomerId()){
                return customer;
            }
        return null;
    }
}
