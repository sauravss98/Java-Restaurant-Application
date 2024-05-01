package Orders;

import Items.Item;
import Items.ItemDataController;
import User.UserController;
import User.Waiter;
import cafe94.group14a2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class to control the order page for waiters
 * @author Saurav
 */
public class WaiterOrderController {
    private String activeEmail;
    private static Waiter currentWaiter;
    private final ArrayList<Item> items = ItemDataController.getItems();
    @FXML private ListView ItemsList;
    @FXML private ListView specialsItemList;
    @FXML private ListView OrdersList;
    @FXML private Button backButton;
    @FXML private Text OrderSectionText;
    @FXML private Button CompleteOrder;
    @FXML private Text PriceText;
    private Order currentOrder;
    private final ArrayList<Order> orders = OrderDataHandler.getOrders();
    private final ArrayList<Waiter> waiters = UserController.getWaiters();

    /**
     * Default constructor for the class
     */
    public WaiterOrderController(){
    }

    /**
     * Constructor which initialises the waiter object
     * @param waiter waiter object is passed
     */
    public WaiterOrderController(Waiter waiter){
        this.currentWaiter = waiter;
    }

    /**
     * Function to initialize the UI element
     */
    public void initialize(){
        refreshMenu();
        refreshOrderItemList();
        CompleteOrder.setOnAction(e->{
            handleCompleteOrderClick();
        });
        backButton.setOnAction(e->{
            handleBackButton();
        });
        ItemsList.setOnMouseClicked(e->{
            String selectedItemDescription = (String) ItemsList.getSelectionModel().getSelectedItem();
            System.out.println(selectedItemDescription);
            String id = extractID(selectedItemDescription);
            System.out.println("id is: "+id);
            Item requiredItem = getItemData(id);
            System.out.println("Name: "+requiredItem.getItemName());
            handleOrder(requiredItem);
        });
        specialsItemList.setOnMouseClicked(e->{
            String selectedItemDescription = (String) specialsItemList.getSelectionModel().getSelectedItem();
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

    /**
     * Function to handle the complete order button
     */
    private void handleCompleteOrderClick() {
        currentOrder.setOrderType("dineIn");
        currentOrder.setOrderStatus("InProgress");
        orders.add(currentOrder);
        OrderDataHandler.saveOrderDataToExcel(currentOrder);
        try {
            Main.setRoot("waiterMainPage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Function that is used to edit the details of the item
     * @param item it passes the item details as a string
     * @throws IOException exception called when it cannot find the file
     */
    private void editItem(String item) throws IOException {
        String id = extractID(item);
        OrderItem requiredOrderItem = getOrderItemData(id);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/orderItemEditPage.fxml"));
        Parent root = loader.load();
        OrderItemEditPageController controller = loader.getController();
        controller.setCurrentItem(requiredOrderItem);
        controller.setCurrentOrder(currentOrder);
        System.out.println("loader " + loader.getController());

        Stage orderTypeStage = new Stage();
        controller.setStage(orderTypeStage);
        orderTypeStage.setTitle("Edit Item Detail");
        orderTypeStage.setScene(new Scene(root, 600, 600));
        orderTypeStage.initModality(Modality.APPLICATION_MODAL);
        orderTypeStage.showAndWait();
        refreshOrderItemList();
    }

    /**
     * Function to get the details of order data
     * @param idString it sends the item id as a string
     * @return orderitem
     */
    private OrderItem getOrderItemData(String idString) {
        int id = Integer.parseInt(idString);
        for (OrderItem orderItem : currentOrder.getOrderItems()) {
            if (orderItem.getItem().getItemID() == id) {
                return orderItem;
            }
        }
        return null;
    }

    /**
     * Function to display all the items in the order made by the customer
     */
    private void refreshOrderItemList() {
        // Clear the displayed list
        OrdersList.getItems().clear();
        if (currentOrder == null || currentOrder.getOrderItems().isEmpty()) {
            OrderSectionText.setText("Please make an order");
            OrdersList.setVisible(false);
            CompleteOrder.setVisible(false);
        } else {
            CompleteOrder.setVisible(true);
            if (Objects.equals(currentOrder.getOrderStatus(), "cart")) {
                OrderSectionText.setText("Order Id: " + currentOrder.getOrderId());
                OrdersList.setVisible(true);
                for (OrderItem orderItem : currentOrder.getOrderItems()) {
                    OrdersList.getItems().add(orderItem.getItem().getDescriptionForList()+" Quantity: " + orderItem.getQuantity());
                }
                calculatePrice();
            } else {
                OrderSectionText.setText("Please make an order");
                OrdersList.setVisible(false);
            }
        }
    }

    /**
     * Function to calculate price of all the items in the order
     */
    private void calculatePrice() {
        int price = 0;
        for (OrderItem orderItem : currentOrder.getOrderItems()) {
            price += orderItem.getItem().getPrice() * orderItem.getQuantity();
        }
        currentOrder.setPrice(price);
        PriceText.setText("Total Cost :" + currentOrder.getPrice());
    }

    /**
     *This is the funtion that is used to handle the order
     * @param selectedItem it sends the object of the selected item
     * If there is no order a new order is created and the item is added to the order
     * If an order exists takes the selected item as parameter and adds the new item to the list
     */
    private void handleOrder(Item selectedItem) {
        if (currentOrder == null) {
            int newOrderID = generateOrderId();
            currentOrder = new Order(newOrderID,"dinein",new ArrayList<>(),false,"cart",0,currentWaiter.getUserId(),0,0);
        }
        currentOrder.addToItem(selectedItem.getItemID());
        currentOrder.addItem(selectedItem, 1); // Pass 1 as the quantity for a new item
        refreshOrderItemList();
        System.out.println(currentOrder.toString());
    }



    /**
     * A function to get the id from the String recieved based on the user input
     * @param data it sends the data as a string
     * @return id
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
     * A function to return the item corresponding to the id passed through
     * @param idString it sends id as a string
     * @return item
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

    private void handleBackButton() {
        try {
            Main.setRoot("waiterMainPage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * A function to generate the order id by getting the lenght of total number of orders present excelsheet(database)
     * @return orderId
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
     * Function to refresh the menu list view
     */
    private void refreshMenu() {
        ItemsList.getItems().clear();

        for (Item item : items) {
            if(!item.isSpecialItem()){
                ItemsList.getItems().add(item.getDescriptionForMenuList());
            }
        }

        specialsItemList.getItems().clear();

        for (Item item : items) {
            if(item.isSpecialItem()){
                specialsItemList.getItems().add(item.getDescriptionForMenuList());
            }
        }


    }
}
