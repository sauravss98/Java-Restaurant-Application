package Orders;

import User.Waiter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class to control the waiter order view page
 */
public class WaiterOrderViewController {
    private Stage stage;
    private static Waiter activeWaiter;
    private static final ArrayList<Order> orders = OrderDataHandler.getOrders();
    @FXML private Button backButton;
    @FXML private ListView ordersListView;


    /**
     * Default Constructor
     */
    public WaiterOrderViewController(){
    }

    /**
     * Function to set the stage instance
     * @param stage The stage object is possed
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Function to set the active waiter details
     * @param activeWaiter The waiter object is passed
     */
    public void setActiveWaiter(Waiter activeWaiter) {
        this.activeWaiter = activeWaiter;
    }

    /**
     * Function to initialize the UI component when loaded
     */
    public void initialize(){
        backButton.setOnAction(e->{
            handleBackClick();
        });
        refreshList();
        ordersListView.setOnMouseClicked(event -> {
            String selectedItemDescription = (String) ordersListView.getSelectionModel().getSelectedItem();
            String id = extractID(selectedItemDescription);
            Order requiredOrder = getOrderData(id);
            try {
                launchListPage(requiredOrder);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Function to launch the new page when button is press
     * @param requiredOrder The order object is passed
     * @throws IOException The exception to handle when file does not load properly
     */
    private void launchListPage(Order requiredOrder) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/orderStatusChangePage.fxml"));
        Parent root = loader.load();
        WaiterOrderStatusChangePage  controller = loader.getController();
        controller.setCurrentOrder(requiredOrder);
        controller.setActiveWaiter(activeWaiter);

        Stage orderStatusChangeStage = new Stage();
        controller.setStage(orderStatusChangeStage);
        orderStatusChangeStage.setTitle("Order Items");
        orderStatusChangeStage.setScene(new Scene(root, 600, 600));
        orderStatusChangeStage.initModality(Modality.APPLICATION_MODAL);
        orderStatusChangeStage.showAndWait();
        refreshList();
    }

    /**
     * A function to return the item corresponding to the id passed through
     * @param idString The id is passed as string
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
     * @param data The data is passed
     * @return id
     * @author Saurav Suresh
     */
    public static String extractID(String data) {
        int startIndex = data.indexOf("ID: ") + 4;
        int endIndex = data.indexOf(" ", startIndex);
        if (endIndex == -1) {
            endIndex = data.length();
        }
        return data.substring(startIndex, endIndex);
    }

    /**
     * Function to refresh the UI list when changes are made
     */
    private void refreshList() {
        ordersListView.getItems().clear();

        for (Order order : orders) {
            if(order.getOrderStatus().equals("InProgress") && Objects.equals(order.getOrderType(), "dineIn")){
                ordersListView.getItems().add(order.getDescriptionForOrderList());
            }
        }
    }

    /**
     * Function to handle the back button click
     */
    private void handleBackClick() {
        if(stage!=null){
            stage.close();
        }
    }

}
