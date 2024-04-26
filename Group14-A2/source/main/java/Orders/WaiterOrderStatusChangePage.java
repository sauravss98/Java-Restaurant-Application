package Orders;

import User.Waiter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Class to control the waiter status change page
 * @author Saurav
 */
public class WaiterOrderStatusChangePage {
    private Order currentOrder;
    private Stage stage;
    private static Waiter activeWaiter;
    @FXML private Button backButton;
    @FXML private Button completeOrderButton;
    @FXML private Text orderIdText;
    @FXML private ListView OrderItemsList;


    /**
     * Constructor for the class
     */
    public WaiterOrderStatusChangePage(){}

    /**
     * Function to set teh waiter object in the current instance
     * @param waiter The waiter object is passed
     */
    public void setActiveWaiter(Waiter waiter) {
        this.activeWaiter = waiter;
    }

    /**
     * Function to set the stage
     * @param stage The stage object is passed
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Function to set the current order object
     * @param currentOrder Passed the object for order
     */
    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
        initialize();
    }

    /**
     * Function to initialize the UI elements
     */
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

    /**
     * Function to handle the confirm button click
     */
    private void handleConfirmClick() {
        currentOrder.setOrderStatus("Completed");
        currentOrder.setCompleted(true);
        currentOrder.setWaiterId(activeWaiter.getUserId());
        OrderDataHandler.editOrderWaiterCompleteExcelSheetData(currentOrder);
        if(stage!=null){
            stage.close();
        }
    }

    /**
     * Function to handle the cancel button click. It will close the window
     */
    private void handleCancelClick() {
        if(stage!=null){
            stage.close();
        }
    }

    /**
     * Function to render the Text component
     */
    public void refreshOrderIDText(){
        if (currentOrder != null) {
            orderIdText.setText("Order Id: " + currentOrder.getOrderId());
        } else {
            // Handle the case where order is null
            orderIdText.setText("Order Id: N/A");
        }
    }

    /**
     * Function to render the item list
     */
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
