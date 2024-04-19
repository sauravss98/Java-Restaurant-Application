package Orders;

import Items.Item;
import User.Customer;
import cafe94.group14a2.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class OrderController {
    private static Customer activeCustomer;
    @FXML private ListView OrderListView;
    @FXML private Button orderNavButton;
    private static ArrayList<Order> orders = OrderDataHandler.getOrders();

    public OrderController() {
        // Default constructor
    }

    public void setActiveCustomer(Customer activeCustomer) {
        this.activeCustomer = activeCustomer;
    }

    public void initialize()
    {
        System.out.println("Hi "+activeCustomer.getFirstName());
        refreshItemList();
        orderNavButton.setOnAction(e -> {
            handleNavButtonAction("customerMainPage");
        });
    }

    private void handleNavButtonAction(String fxml){
        try {
            Main.setRoot(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshItemList() {
        // Clear the displayed list
        OrderListView.getItems().clear();

        for (Order order : orders) {
            if(order.getCustomerId() == activeCustomer.getCustomerID()) {
                OrderListView.getItems().add(order.getDescriptionForOrderList());
            }
        }
    }
}
