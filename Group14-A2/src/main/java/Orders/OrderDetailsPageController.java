package Orders;

import Items.Item;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class OrderDetailsPageController {
    @FXML private Text orderIdText;
    @FXML private ListView OrderItemsList;
    private Order order;

    public void initialize(){
        refreshOrderIDText();
        refreshItemList();
    }
    public void setOrderData(Order order){
        this.order = order;
        System.out.println("Order is "+order.toString());
        initialize();
    }

    public Order getOrderData(){
        return order;
    }

    public void refreshOrderIDText(){
        if (order != null) {
            orderIdText.setText("Order Id: " + order.getOrderId());
        } else {
            // Handle the case where order is null
            orderIdText.setText("Order Id: N/A");
        }
    }

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
