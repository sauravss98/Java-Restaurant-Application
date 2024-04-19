package User;

import Items.Item;
import Items.ItemDataController;
import Orders.*;
import cafe94.group14a2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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
    @FXML private Button CompleteOrder;
    @FXML private Text PriceText;

    private Order currentOrder;
    private ArrayList<Item> orderItems;
    private Customer currentCustomer;

    public CustomerPageController() {
    }

    public CustomerPageController(String activeUserEmail) {
        this.activeUserEmail = activeUserEmail;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("activeemail is this: " + activeUserEmail);
        for (Customer customer : customers) {
            System.out.println("name: " + customer.getFirstName() + " status: " + customer.getIsLoggedIn());
            System.out.println("activeemail " + activeUserEmail);
            if (Objects.equals(customer.getEmail(), activeUserEmail)) {
                currentCustomer = customer;
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

        OrdersList.setOnMouseClicked(event ->{
            String selectedItem = (String) OrdersList.getSelectionModel().getSelectedItem();
            try {
                editItem(selectedItem);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void editItem(String item) throws IOException {
        String id = extractID(item);
        Item requiredItem = getItemData(id);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/orderItemEditPage.fxml"));
        Parent root = loader.load();
        OrderItemEditPageController controller = loader.getController();
        controller.setCurrentItem(requiredItem);
        controller.setCurrentOrder(currentOrder);
        System.out.println("loader "+loader.getController());

        Stage orderTypeStage = new Stage();
        controller.setStage(orderTypeStage);
        orderTypeStage.setTitle("Edit Item Detail");
        orderTypeStage.setScene(new Scene(root, 600, 600));
        orderTypeStage.initModality(Modality.APPLICATION_MODAL);
        orderTypeStage.showAndWait();
        refreshOrderItemList();
    }

    /**
     *This is the funtion that is used to handle the order
     * @param selectedItem
     * If there is no order a new order is created and the item is added to the order
     * If an order exists takes the selected item as parameter and adds the new item to the list
     * @author Saurav Suresh
     */
    private void handleOrder(Item selectedItem){
        if(currentOrder == null){
            int newOrderID =generateOrderId();
            currentOrder = new Order(newOrderID,"dine-in",new ArrayList<>(),false,"cart",currentCustomer.getUserId());
        }
        currentOrder.setItems(selectedItem.getItemID(),selectedItem);
        refreshOrderItemList();
        System.out.println(currentOrder.toString());
    }

    /**
     * A function to generate the order id by getting the lenght of total number of orders present excelsheet(database)
     * @return orderId
     * @author Saurav Suresh
     */
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

    /**
     * A function to return the item corresponding to the id passed through
     * @param idString
     * @return item
     * @author Saurav Suresh
     */
    public Item getItemData(String idString){
        int id = Integer.parseInt(idString);
        for(Item item: items){
            if(item.getItemID() == id){
                return item;
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

    /**
     * Function to display all the items in the menu
     * @author Saurav Suresh
     */
    private void refreshItemList() {
        // Clear the displayed list
        ItemsList.getItems().clear();

        for (Item item : items) {
            ItemsList.getItems().add(item.getDescriptionForMenuList());
        }
    }

    /**
     * Function to display all the items in the order made by the customer
     * @author Saurav Suresh
     */
    private void refreshOrderItemList() {
        // Clear the displayed list
        OrdersList.getItems().clear();
        if(currentOrder==null){
            OrderSectionText.setText("Please make an order");
            OrdersList.setVisible(false);
            CompleteOrder.setVisible(false);
        }
        else if(currentOrder.getItemsObjects()==null){
            System.out.println("In the main thing");
            OrderSectionText.setText("Please make an order");
            OrdersList.setVisible(false);
            CompleteOrder.setVisible(false);
        }else{
            CompleteOrder.setVisible(true);
            if(Objects.equals(currentOrder.getOrderStatus(), "cart")) {
                OrderSectionText.setText("Order Id: " + currentOrder.getOrderId());
                OrdersList.setVisible(true);
                orderItems = currentOrder.getItemsObjects();
                for (Item item : orderItems) {
                    OrdersList.getItems().add(item.getDescriptionForList());
                }
                calculatePrice();
            }
            else {
                OrderSectionText.setText("Please make an order");
                OrdersList.setVisible(false);
            }
        }
    }

    private void calculatePrice(){
        int price = 0;
        ArrayList<Item> items = currentOrder.getItemsObjects();
        for (Item item:items){
            price+= item.getPrice()*item.getQuantity();
        }
        currentOrder.setPrice(price);
        PriceText.setText("Total Cost :"+currentOrder.getPrice());
    }



    /**
     * This function is triggered when the button. It will launch a new window with where the customer can select order type
     * @author Saurav Suresh
     */
    @FXML
    private void onOrderConfirmClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/orderTypeWindow.fxml"));
            Parent root = loader.load();
            OrderTypeWindowController controller = loader.getController();

            Stage orderTypeStage = new Stage();
            orderTypeStage.setTitle("Select Order Type");
            orderTypeStage.setScene(new Scene(root, 600, 600));

            controller.setStage(orderTypeStage);
            controller.setCurrentOrder(currentOrder);
            orderTypeStage.initModality(Modality.APPLICATION_MODAL);
            orderTypeStage.showAndWait();
            OrderDataHandler.addOrder(currentOrder);
            OrderController orderController = new OrderController();
            orderController.setActiveCustomer(currentCustomer);
            try {
                Main.setRoot("orderListPage");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
