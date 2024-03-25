package User;

import Items.Item;
import Items.ItemDataController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class CustomerPageController implements Initializable {
    private static String activeUserEmail;
    private static ArrayList<Customer> customers = UserController.getCustomers();
    private ArrayList<Item> items = ItemDataController.getItems();
    @FXML
    private Label NameDisplayLabel;
    @FXML private ListView ItemsList;

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

        ItemsList.setOnMouseClicked(event -> {
            String selectedItemDescription = (String) ItemsList.getSelectionModel().getSelectedItem();
            System.out.println(selectedItemDescription);
        });
    }

    private void refreshItemList() {
        // Clear the displayed list
        ItemsList.getItems().clear();

        for (Item item : items) {
            ItemsList.getItems().add(item.getDescriptionForList());
        }
    }
}
