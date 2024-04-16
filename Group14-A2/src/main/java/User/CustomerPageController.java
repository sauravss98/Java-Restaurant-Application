package User;

import Items.Item;
import Items.ItemDataController;
import Orders.Order;
import Orders.OrderDataHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class CustomerPageController implements Initializable {
    private static String activeUserEmail;
    private static ArrayList<Customer> customers = UserController.getCustomers();
    private ArrayList<Item> items = ItemDataController.getItems();
    private static ArrayList<Order> orders = OrderDataHandler.getOrders();
    @FXML private Label NameDisplayLabel;
    @FXML private ListView ItemsList;
    @FXML private ListView OrdersList;
    @FXML private Text OrderSectionText;
    private Order currentOrder;
    private ArrayList<Item> orderItems;

    public CustomerPageController() {
    }

    public CustomerPageController(String activeUserEmail) {
        this.activeUserEmail = activeUserEmail;
    }
//    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("activeemail is this: " + activeUserEmail);
        for (Customer customer : customers) {
            System.out.println("name: " + customer.getFirstName() + " status: " + customer.getIsLoggedIn());
            System.out.println("activeemail " + activeUserEmail);
            if (Objects.equals(customer.getEmail(), activeUserEmail)) {
                customer.setLoggedIn(true);
                NameDisplayLabel.setText("Hi, " + customer.getFirstName());
                break;
            }
        }
        Item item1 = new Item(1,"coffee",10);
        ItemDataController.addItems(item1);
        Item item2 = new Item(2,"tea",10);
        ItemDataController.addItems(item2);
        refreshItemList();
        refreshOrderItemList();

        ItemsList.setOnMouseClicked(event -> {
            String selectedItemDescription = (String) ItemsList.getSelectionModel().getSelectedItem();
            System.out.println(selectedItemDescription);
            String id = extractID(selectedItemDescription);
            System.out.println("id is: "+id);
            Item requiredItem = getItemData(id);
            System.out.println("Name: "+requiredItem.getItemName());
            handleOrder(requiredItem);
        });
    }

    private void handleOrder(Item selctedItem){
        if(currentOrder == null){
            int newOrderID =generateOrderId();
            currentOrder = new Order(newOrderID,"dine-in",new ArrayList<>(),false);
        }
        currentOrder.setItems(selctedItem.getItemID(),selctedItem);
        refreshOrderItemList();
        System.out.println(currentOrder.toString());
    }

    public int generateOrderId() {
        int maxOrderId = 0;

        // Find the maximum order ID from the existing orders
        for (Order order : orders) {
            if (order.getOrderId() > maxOrderId) {
                maxOrderId = order.getOrderId();
            }
        }

        // Return the next available order ID
        return maxOrderId + 1;
    }
    public Item getItemData(String idString){
        int id = Integer.parseInt(idString);
        for(Item item: items){
            if(item.getItemID() == id){
                return item;
            }
        }
        return null;
    }
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
        ItemsList.getItems().clear();

        for (Item item : items) {
            ItemsList.getItems().add(item.getDescriptionForMenuList());
        }
    }

    private void refreshOrderItemList() {
        // Clear the displayed list
        System.out.println("in order refresh");
        OrdersList.getItems().clear();
        System.out.println(currentOrder);
        if(currentOrder==null){
            OrderSectionText.setText("Please make an order");
            OrdersList.setVisible(false);
        }
        else{
            OrderSectionText.setVisible(false);
            OrdersList.setVisible(true);
            orderItems = currentOrder.getItemsObjects();
            for (Item item : orderItems) {
            OrdersList.getItems().add(item.getDescriptionForList());
            }
        }
    }
}
