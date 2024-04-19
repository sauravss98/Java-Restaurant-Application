package Orders;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

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

    public void refreshOrderIDText(){
        if (order != null) {
            orderIdText.setText("Order Id: " + order.getOrderId());
        } else {
            // Handle the case where order is null
            orderIdText.setText("Order Id: N/A");
        }
    }

    public void  refreshItemList(){

    }
}
