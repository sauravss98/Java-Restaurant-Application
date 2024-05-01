package Orders;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class to control the Order Type Edit Window
 */
public class OrderTypeWindowController {
    final private String DINE_IN = "dineIn";
    final private String DELIVERY = "delivery";
    final private String TAKEOUT = "takeout";
    final private String IN_PROGRESS = "InProgress";
    @FXML private Button dineInButton;
    @FXML private Button deliveryButton;
    @FXML private Button takeOutButton;
    @FXML private Button cancelButton;
    @FXML private Button orderDoneButton;
    @FXML private VBox OrderDoneBox;
    @FXML private VBox OrderTypeBox;
    private Order currentOrder;
    private Stage stage;


    /**
     * Function to initialize the UI components
     */
    public void initialize() {
        OrderDoneBox.setVisible(false);
        dineInButton.setOnAction(e -> {
            handleConfirmButtonAction(DINE_IN);
        });
        deliveryButton.setOnAction(e -> {
            handleConfirmButtonAction(DELIVERY);
        });
        takeOutButton.setOnAction(e -> {
            handleConfirmButtonAction(TAKEOUT);
        });
        cancelButton.setOnAction(e -> {
            handleCancelButtonAction();
        });
        orderDoneButton.setOnAction(e -> {
            handleOrderDoneButtonAction();
        });
    }

    /**
     * Function to set the order to the current instance of the class
     * @param currentOrder The order object is passed
     */
    public void setCurrentOrder(Order currentOrder){
        this.currentOrder = currentOrder;
    }

    /**
     * Function to set the stage for the current instance of class
     * @param stage The stage object is passed
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Function to handle the confirm button click and set the order type
     * @param type The order type is passed based on what is selected
     */
    private void handleConfirmButtonAction(String type){
        currentOrder.setOrderType(type);
        currentOrder.setOrderStatus(IN_PROGRESS);
        OrderTypeBox.setVisible(false);
        OrderDoneBox.setVisible(true);
        OrderDataHandler.saveOrderDataToExcel(currentOrder);
    }

    /**
     * Function to handle the cancel button click and it will close the stage
     */
    private void handleCancelButtonAction(){
        if (stage != null) {
            stage.close();
        }
    }

    /**
     * Function to hande the order done button. It closes the window
     */
    private void handleOrderDoneButtonAction(){
        if (stage != null) {
            stage.close();
        }
    }
}
