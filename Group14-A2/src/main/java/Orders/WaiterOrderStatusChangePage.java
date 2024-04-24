package Orders;

import User.Waiter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WaiterOrderStatusChangePage {
    private Order currentOrder;
    private Stage stage;
    private static Waiter activeWaiter;
    @FXML private Button backButton;
    @FXML private Button completeOrderButton;
    @FXML private Text orderIdText;
    @FXML private ListView OrderItemsList;


    public WaiterOrderStatusChangePage(){}

    public void setActiveWaiter(Waiter waiter) {
        this.activeWaiter = waiter;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
        initialize();
    }

    public void initialize(){
        refreshOrderIDText();
        refreshItemList();
        backButton.setOnAction(e->{
            handleCancelClick();
        });
        completeOrderButton.setOnAction(e->{
            handleConfirmClick();
        });
    }

    private void handleConfirmClick() {
        currentOrder.setOrderStatus("Completed");
        currentOrder.setCompleted(true);
        currentOrder.setWaiterId(activeWaiter.getUserId());
        OrderDataHandler.editOrderWaiterCompleteExcelSheetData(currentOrder);
        if(stage!=null){
            stage.close();
        }
    }

    private void handleCancelClick() {
        if(stage!=null){
            stage.close();
        }
    }

    public void refreshOrderIDText(){
        if (currentOrder != null) {
            orderIdText.setText("Order Id: " + currentOrder.getOrderId());
        } else {
            // Handle the case where order is null
            orderIdText.setText("Order Id: N/A");
        }
    }

    public void refreshItemList() {
        if (currentOrder != null && currentOrder.getOrderItems() != null) {
            OrderItemsList.getItems().clear();
            for (OrderItem orderItem : currentOrder.getOrderItems()) {
                OrderItemsList.getItems().add(orderItem.getItem().getDescriptionForList() + " Quantity: " + orderItem.getQuantity());
            }
        } else {
            OrderItemsList.getItems().add("No items Ordered");
        }
    }
}
