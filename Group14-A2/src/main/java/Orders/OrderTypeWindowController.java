package Orders;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class OrderTypeWindowController {
    @FXML private Button dineInButton;
    @FXML private Button deliveryButton;
    @FXML private Button takeOutButton;
    @FXML private Button cancelButton;
    Order currentOrder;
    private Stage stage;

    public void initialize() {
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
    }

    public void setCurrentOrder(Order currentOrder){
        this.currentOrder = currentOrder;
        System.out.println("current order "+currentOrder.getOrderId());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void handleConfirmButtonAction(String type){
        currentOrder.setOrderType(type);
        currentOrder.setOrderStatus("InProgress");
        System.out.println("Order type is:"+ currentOrder.getOrderType());
    }

    private void handleCancelButtonAction(){
        if (stage != null) {
            stage.close();
        }
    }
}
