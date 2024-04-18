package Orders;

import User.Customer;
import User.CustomerPageController;
import cafe94.group14a2.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderTypeWindowController {
    @FXML private Button dineInButton;
    @FXML private Button deliveryButton;
    @FXML private Button takeOutButton;
    @FXML private Button cancelButton;
    @FXML private Button orderDoneButton;
    @FXML private VBox OrderDoneBox;
    @FXML private VBox OrderTypeBox;
    private Order currentOrder;
    private Stage stage;
    private Customer activeCustomer;



    public void initialize() {
        OrderDoneBox.setVisible(false);
        dineInButton.setOnAction(e -> {
            handleConfirmButtonAction("dineIn");
        });
        deliveryButton.setOnAction(e -> {
            handleConfirmButtonAction("delivery");
        });
        takeOutButton.setOnAction(e -> {
            handleConfirmButtonAction("takeout");
        });
        cancelButton.setOnAction(e -> {
            handleCancelButtonAction();
        });
        orderDoneButton.setOnAction(e -> {
            handleOrderDoneButtonAction();
        });
    }

    public void setActiveCustomer(Customer activeCustomer){
        this.activeCustomer = activeCustomer;
    }

    public void setCurrentOrder(Order currentOrder){
        this.currentOrder = currentOrder;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void handleConfirmButtonAction(String type){
        currentOrder.setOrderType(type);
        currentOrder.setOrderStatus("InProgress");
        OrderTypeBox.setVisible(false);
        OrderDoneBox.setVisible(true);
    }

    private void handleCancelButtonAction(){
        if (stage != null) {
            stage.close();
        }
    }

    private void handleOrderDoneButtonAction(){
        if (stage != null) {
            stage.close();
        }
    }
}
