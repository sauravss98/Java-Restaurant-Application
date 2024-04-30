package User;

import Orders.Order;
import Orders.OrderDataHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Class to handle the drivers delivery page.
 * @author Saurav
 */
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

    /**
     * Default constructor for the class
     */
    public DriverDeliveryPageController(){}

    /**
     * Function to set the driver
     * @param driver Driver object is passed
     */
    public void setCurrentDriver(Driver driver){
        this.currentDriver = driver;
    }

    /**
     * Function to set the stage
     * @param stage Stage instance is passed
     */
    public void setStage(Stage stage){
        this.stage = stage;
    }

    /**
     * Function to set the customer object to the class instance
     * @param customer Customer object is passed
     */
    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    /**
     * Function to set the current order
     * @param order Order object is passed
     */
    public void setCurrentOrder(Order order){
        this.currentOrder = order;
        initialize();
    }

    /**
     * Function to initialize the UI element
     */
    public void initialize(){
        completeOrderButton.setOnAction(e->{
            handleCompleteButton();
        });
        refreshWindow();
    }

    /**
     * Function to set the handle complete button. The data is submitted and data is saved
     */
    private void handleCompleteButton() {
        currentOrder.setOrderStatus("Completed");
        currentOrder.setCompleted(true);
        OrderDataHandler.editDriverExcelData(currentOrder,"completed");
        if (stage!=null){
            stage.close();
        }
    }

    /**
     * Function to refresh the window when loading the order data
     */
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
