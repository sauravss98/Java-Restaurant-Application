package Orders;

import Items.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.SpinnerValueFactory;

/**
 * Class to control the item edit page for order
 * @author Saurav
 */
public class OrderItemEditPageController {
    @FXML private Text quantityNameText;
    @FXML private Spinner quantitySpinner;
    @FXML private Button removeItemButton;
    @FXML private Button confirmItemButton;
    @FXML private Button cancelButton;
    private Order order;
    private Item item;
    private Stage stage;
    private OrderItem currentOrderItem;

    /**
     * Function to initialize the UI elements in the fxml file
     */
    public void initialize(){
        refreshItemText();
        refreshQuantitySpinner();
        removeItemButton.setOnAction(e -> {
            handleRemoveButton();
        });
        confirmItemButton.setOnAction(e -> {
            handleConfirmButton();
        });
        cancelButton.setOnAction(e -> {
            handleCancelButton();
        });
    }

    /**
     * Function to refresh the spinner component
     */
    private void refreshQuantitySpinner() {
        if (currentOrderItem != null) {
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, currentOrderItem.getQuantity());
            quantitySpinner.setValueFactory(valueFactory);
        } else {
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 1);
            quantitySpinner.setValueFactory(valueFactory);
        }
    }

    /**
     * Function to refresh the Text element
     */
    private void refreshItemText(){
        if (item != null) {
            quantityNameText.setText("Item: "+item.getItemName());
        } else {
            // Handle the case where order is null
            quantityNameText.setText("Item : N/A");
        }
    }

    /**
     * Function to handle the cancel button click
     */
    private void handleCancelButton(){
        if (stage != null) {
            stage.close();
        }
    }

    /**
     * Function to handle the confirm button click
     */
    private void handleConfirmButton() {
        int quantity = (int) quantitySpinner.getValue();
        for (OrderItem orderItem : order.getOrderItems()) {
            if (orderItem.getItem().getItemID() == item.getItemID()) {
                orderItem.setQuantity(quantity);
                break;
            }
        }
        if (stage != null) {
            stage.close();
        }
    }

    /**
     * Function to handle the remove button click
     */
    private void handleRemoveButton() {
        order.removeItem(item.getItemID());
        if (stage != null) {
            stage.close();
        }
    }

    /**
     * Construnctor for the class
     */
    public OrderItemEditPageController(){}

    /**
     * Function to set the stage
     * @param stage Stage object is passed as params
     */
    public void setStage(Stage stage){
        this.stage = stage;
    }

    /**
     * Function to set the order item for the instance of the class
     * @param orderItem Passes the item in each item
     */
    public void setCurrentItem(OrderItem orderItem) {
        this.currentOrderItem = orderItem;
        this.item = orderItem.getItem();
        System.out.println("Item " + item.getItemName());
        initialize();
    }

    /**
     * Function to set the order for the instance
     * @param order Passes the order instance
     */
    public void setCurrentOrder(Order order){
        this.order = order;
    }
}
