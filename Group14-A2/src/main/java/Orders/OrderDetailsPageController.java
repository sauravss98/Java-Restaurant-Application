package Orders;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

/**
 * Class to handle the order details view page
 * @author Saurav
 */
public class OrderDetailsPageController {
    final private String ORDER_MESSAGE= "Order is ";
    final private String ORDER_ID= "Order Id: ";
    final private String ORDER_ID_EMPTY= "Order Id: N/A";
    final private String NO_ITEM_ORDERED= "No items Ordered";
    final private String QUANTITY_MESSAGE= " Quantity: ";
    @FXML private Text orderIdText;
    @FXML private ListView OrderItemsList;
    private Order order;

    /**
     * Function to initialize the UI and fxml components
     * @author Saurav
     */
    public void initialize(){
        refreshOrderIDText();
        refreshItemList();
    }

    /**
     * Function to set the order data
     * @param order Order object is passed
     */
    public void setOrderData(Order order){
        this.order = order;
        System.out.println(ORDER_MESSAGE+order.toString());
        initialize();
    }

    /**
     * Function to get the order data
     * @return Returns the order object required
     */
    public Order getOrderData(){
        return order;
    }

    /**
     * Function to refresh the order id text field
     */
    public void refreshOrderIDText(){
        if (order != null) {
            orderIdText.setText(ORDER_ID + order.getOrderId());
        } else {
            // Handle the case where order is null
            orderIdText.setText(ORDER_ID_EMPTY);
        }
    }

    /**
     * Function to refresh and render the item list
     */
    public void refreshItemList() {
        if (order != null && order.getOrderItems() != null) {
            OrderItemsList.getItems().clear();
            for (OrderItem orderItem : order.getOrderItems()) {
                OrderItemsList.getItems().add(orderItem.getItem().getDescriptionForList() + QUANTITY_MESSAGE + orderItem.getQuantity());
            }
        } else {
            OrderItemsList.getItems().add(NO_ITEM_ORDERED);
        }
    }
}
