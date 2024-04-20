package Orders;

import User.Customer;
import cafe94.group14a2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

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
        OrderListView.setOnMouseClicked(event -> {
            String selectedItemDescription = (String) OrderListView.getSelectionModel().getSelectedItem();
            String id = extractID(selectedItemDescription);
            Order requiredOrder = getOrderData(id);
            try {
                launchListPage(requiredOrder);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void launchListPage(Order requiredOrder) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/orderDetailsPage.fxml"));
        Parent root = loader.load();
        OrderDetailsPageController controller = loader.getController();
        controller.setOrderData(requiredOrder);
        System.out.println("loader "+loader.getController());

        Stage orderTypeStage = new Stage();
        orderTypeStage.setTitle("Order Items");
        orderTypeStage.setScene(new Scene(root, 600, 600));
        orderTypeStage.show();
    }

    private void handleNavButtonAction(String fxml){
        try {
            Main.setRoot(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * A function to return the item corresponding to the id passed through
     * @param idString
     * @return item
     * @author Saurav Suresh
     */
    public Order getOrderData(String idString){
        int id = Integer.parseInt(idString);
        for(Order order: orders){
            if(order.getOrderId() == id){
                return order;
            }
        }
        return null;
    }


    /**
     * A function to get the id from the String recieved based on the user input
     * @param data
     * @return id
     * @author Saurav Suresh
     */
    public static String extractID(String data) {
        int startIndex = data.indexOf("ID: ") + 4; // Add 4 to skip "ID: "
        int endIndex = data.indexOf(" ", startIndex); // Find the space after the ID value
        if (endIndex == -1) {
            endIndex = data.length(); // If no space is found, consider the end of the string
        }
        return data.substring(startIndex, endIndex);
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
