package Orders;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

/**
 * Class to handle the order details view page
 * @author Saurav
 */
public class OrderDetailsPageController {
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
        System.out.println("Order is "+order.toString());
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
            orderIdText.setText("Order Id: " + order.getOrderId());
        } else {
            // Handle the case where order is null
            orderIdText.setText("Order Id: N/A");
        }
    }

    /**
     * Function to refresh and render the item list
     */
    public void refreshItemList() {
        if (order != null && order.getOrderItems() != null) {
            OrderItemsList.getItems().clear();
            for (OrderItem orderItem : order.getOrderItems()) {
                OrderItemsList.getItems().add(orderItem.getItem().getDescriptionForList() + " Quantity: " + orderItem.getQuantity());
            }
        } else {
            OrderItemsList.getItems().add("No items Ordered");
        }
    }
}
