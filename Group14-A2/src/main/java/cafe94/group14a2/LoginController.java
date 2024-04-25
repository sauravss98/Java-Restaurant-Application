package cafe94.group14a2;

import User.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class to control the login section
 * @author Saurav
 */
public class LoginController {
    @FXML private TextField EmailTextField;
    @FXML private Label LoginErrorLabel;

    /**
     * Function to check whether email is valid or not
     * @param email it send the email as a string
     * @return returns the type of the user based on the used send
     * @author Saurav
     */
    public String checkValidEmail(String email){
        String type = "";
        ArrayList<Customer> customers = UserController.getCustomers();
        ArrayList<Manager> managers = UserController.getManagers();
        ArrayList<Waiter> waiters = UserController.getWaiters();
        ArrayList<Driver> drivers = UserController.getDrivers();
        ArrayList<Chef> chefs = UserController.getChefs();
        for(Customer customer:customers){
            if(Objects.equals(customer.getEmail(), email)) {
                return "Customer";
            }
        }
        for(Manager manager:managers){
            if(Objects.equals(manager.getEmail(), email)) {
                return "Manager";
            }
        }
        for(Waiter waiter:waiters){
            if(Objects.equals(waiter.getEmail(), email)) {
                return "Waiter";
            }
        }
        for(Driver driver:drivers){
            if(Objects.equals(driver.getEmail(), email)) {
                return "Driver";
            }
        }
        for(Chef chef:chefs){
            if(Objects.equals(chef.getEmail(), email)) {
                return "Chef";
            }
        }
        return type;
    }

    /**
     * Function to handle the login click in UI
     * it cheks if the user details are valid and are correct and then send it to the correct page based on the user type
     * @throws IOException exception called when it cannot find the file
     * @author Saurav
     */
    @FXML
    protected void onLoginClick() throws IOException {
        String email = EmailTextField.getText();
        if(!email.isEmpty()){
            String userType = "";
            userType = checkValidEmail(email);
            System.out.println(userType);
            if (userType.equals("Customer")){
                try {
                    new CustomerPageController(email);
                    Main.setRoot("customerMainPage");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if(userType.equals("Manager")) {
                new ManagerMainPageController(email);
                Main.setRoot("managerMainPage");
            } else if (userType.equals("Waiter")) {
                new WaiterMainPageController(email);
                Main.setRoot("waiterMainPage");
            }else if(userType.equals("Chef")){
                new ChefMainPageController(email);
                Main.setRoot("chefMainPage");
            }else {
                    LoginErrorLabel.setText("Not a user. Please try again or create new user");
            }
        }
    }

    /**
     * Function to handle the sigh up click button in the UI
     * it changes the page to the create user page
     * @throws IOException exception called when it cannot find the file
     * @author Saurav
     */
    @FXML
    protected void onSignUpClick() throws IOException {
        Main.setRoot("createUser");
    }
}