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

/**
 * Class to control the card element for the driver page
 * @author Saurav
 */
public class DriverCardController {
    final private String PREDELIVERY = "predelivery";
    final private String DELIVERY = "Delivery";
    final private String FXMLFILE = "/cafe94/group14a2/driverDeliveryPage.fxml";
    final private String COMPLETE_DELIVERY = "Complete Delivery";
    final private String DELIVERY_ADDRESS = "Delivery Address :";
    final private String CUSTOMER_NAME = "Customer Name: ";
    final private String ORDER_ID = "Order Id: ";
    private Order currentOrder;
    private static Driver currentDriver;
    private final ArrayList<Customer> customers = UserController.getCustomers();
    @FXML private Label orderIdLabel;
    @FXML private Label customerNameLabel;
    @FXML private Label addressLabel;
    @FXML private Button completeOrderButton;
    private DriverMainPageController driverMainPageController;

    /**
     * Default Constructor for the class
     */
    public DriverCardController(){}

    /**
     * Function to set the order object in the class instance
     * @param order The order object is passed
     */
    public void setOrder(Order order){
        this.currentOrder = order;
    }

    /**
     * Function to set the parent class object as instance
     * @param controller The parent class instance is passed
     */
    public void setDriverMainPageController(DriverMainPageController controller) {
        this.driverMainPageController = controller;
    }

    /**
     * Function to set the driver instance to the class
     * @param driver The driver object is passed
     */
    public void setCurrentDriver(Driver driver){
        this.currentDriver = driver;
    }

    /**
     * Function to initialized to the UI component
     */
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

    /**
     * Function to handle the complete button click
     * @throws IOException Exception to catch the files
     */
    private void handleCompleteButton() throws IOException {
        currentOrder.setOrderStatus(DELIVERY);
        currentOrder.setDriverId(currentDriver.getUserId());
        OrderDataHandler.editDriverExcelData(currentOrder,PREDELIVERY);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLFILE));
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

    /**
     * Function to refresh the UI component
     */
    private void refreshCard() {
        Customer orderCustomer = getCustomerData(currentOrder);
        orderIdLabel.setText(ORDER_ID+currentOrder.getOrderId());
        customerNameLabel.setText(CUSTOMER_NAME+orderCustomer.getFirstName());
        addressLabel.setText(DELIVERY_ADDRESS+orderCustomer.getAddress());
    }

    /**
     * Function to get the customer data
     * @param order Order object
     * @return Return the customer data
     */
    private Customer getCustomerData(Order order) {
        for(Customer customer : customers)
            if(customer.getUserId() == order.getCustomerId()){
                return customer;
            }
        return null;
    }
}
