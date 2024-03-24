package User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class CustomerPageController implements Initializable {
    private static String activeUserEmail;
    private static ArrayList<Customer> customers = UserController.getCustomers();
    @FXML
    private Label NameDisplayLabel;

    public CustomerPageController() {
    }
    public CustomerPageController(String activeUserEmail) {
        this.activeUserEmail = activeUserEmail;
    }
    @Override
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
    }

}
