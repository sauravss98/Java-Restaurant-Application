package Orders;

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

/**
 * Class to control the Manager Order View page
 * @author Saurav
 */
public class ManagerOrderView {
    private ArrayList<Order> orders = OrderDataHandler.getOrders();
    private Stage stage;

    @FXML private Button goBackButton;
    @FXML private ListView orderList;

    /**
     * Default constructor for the class
     * @author Saurav
     */
    public ManagerOrderView(){}

    /**
     * Function to set the stage object to open the new window
     * @param stage The stage required to open the window is passed from another class
     * @author Saurav
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Function to initialize the UI element
     * @author Saurav
     */
    public  void initialize(){
        refreshOrdersList();
        goBackButton.setOnAction(e->{
            handleBackButton();
        });
        orderList.setOnMouseClicked(e->{
            String selectedItem = (String) orderList.getSelectionModel().getSelectedItem();
            try {
                editOrder(selectedItem);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Function to edit the order
     * It sends item to a new class which will handle the item edit process
     * @param selectedItem The item to be edited
     * @throws IOException exception called when it cannot find the file
     * @author Saurav
     */
    private void editOrder(String selectedItem) throws IOException {
        String id = extractID(selectedItem);
        Order requiredOrder = getOrderData(id);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/managerOrderEditPage.fxml"));
        Parent root = loader.load();

        ManagerOrderEditController controller = loader.getController();
        controller.setOrder(requiredOrder);

        Stage orderEditStage = new Stage();
        controller.setStage(orderEditStage);
        orderEditStage.setTitle("Edit Order Detail");
        orderEditStage.setScene(new Scene(root, 600, 600));
        orderEditStage.initModality(Modality.APPLICATION_MODAL);
        orderEditStage.showAndWait();
        refreshOrdersList();
    }

    /**
     * Function to get the id from the data in the ui
     * @param data The data is recieved from the UI
     * @return Return the extracted id as string
     * @author Saurav
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
     * Function to get the order data based on the id passed
     * @param idString Extracted Id is passed as a string
     * @return the order object is returned based on the condition
     * @author Saurav
     */
    private Order getOrderData(String idString) {
        int id = Integer.parseInt(idString);
        for (Order order : orders) {
            if (order.getOrderId() == id) {
                return order;
            }
        }
        return null;
    }

    /**
     * Function to render the order list
     * @author Saurav
     */
    private void refreshOrdersList() {
        orderList.getItems().clear();

        for (Order order : orders) {
            orderList.getItems().add(order.getDescriptionForOrderList());
        }
    }

    /**
     * Funtion to handle the back button click.
     * It will close the window when pressed
     * @author Saurav
     */
    private void handleBackButton() {
        if(stage!=null){
            stage.close();
        }
    }
}
