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

public class ManagerOrderView {
    private ArrayList<Order> orders = OrderDataHandler.getOrders();
    private Stage stage;

    @FXML private Button goBackButton;
    @FXML private ListView orderList;

    public ManagerOrderView(){}

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
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

    public static String extractID(String data) {
        int startIndex = data.indexOf("ID: ") + 4; // Add 4 to skip "ID: "
        int endIndex = data.indexOf(" ", startIndex); // Find the space after the ID value
        if (endIndex == -1) {
            endIndex = data.length(); // If no space is found, consider the end of the string
        }
        return data.substring(startIndex, endIndex);
    }

    private Order getOrderData(String idString) {
        int id = Integer.parseInt(idString);
        for (Order order : orders) {
            if (order.getOrderId() == id) {
                return order;
            }
        }
        return null;
    }

    private void refreshOrdersList() {
        orderList.getItems().clear();

        for (Order order : orders) {
            orderList.getItems().add(order.getDescriptionForOrderList());
        }
    }

    private void handleBackButton() {
        if(stage!=null){
            stage.close();
        }
    }
}
