package Orders;

import User.Customer;
import javafx.fxml.FXML;

public class OrderController {
    private static Customer activeCustomer;
    @FXML private javafx.scene.text.Text UserText;

    public OrderController() {
        // Default constructor
    }

    public void setActiveCustomer(Customer activeCustomer) {
        this.activeCustomer = activeCustomer;
    }

    public void initialize()
    {
        System.out.println("Hi "+activeCustomer.getFirstName());
        UserText.setText("Hi "+activeCustomer.getFirstName());
    }
}
