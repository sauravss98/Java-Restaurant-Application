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
    final private String CUSTOMER = "Customer";
    final private String MANAGER = "Manager";
    final private String WAITER = "Waiter";
    final private String DRIVER = "Driver";
    final private String CHEF = "Chef";
    final private String CUSTOMER_MAIN_PAGE = "customerMainPage";
    final private String MANAGER_MAIN_PAGE = "managerMainPage";
    final private String WAITER_MAIN_PAGE = "waiterMainPage";
    final private String CHEF_MAIN_PAGE = "chefMainPage";
    final private String DRIVER_MAIN_PAGE = "driverMainPage";
    final private String ERROR_LABEL = "Not a user. Please try again or create new user";
    final private String CREATE_USER = "createUser";

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
                return CUSTOMER;
            }
        }
        for(Manager manager:managers){
            if(Objects.equals(manager.getEmail(), email)) {
                return MANAGER;
            }
        }
        for(Waiter waiter:waiters){
            if(Objects.equals(waiter.getEmail(), email)) {
                return WAITER;
            }
        }
        for(Driver driver:drivers){
            if(Objects.equals(driver.getEmail(), email)) {
                return DRIVER;
            }
        }
        for(Chef chef:chefs){
            if(Objects.equals(chef.getEmail(), email)) {
                return CHEF;
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
            if (userType.equals(CUSTOMER)){
                try {
                    new CustomerPageController(email);
                    Main.setRoot(CUSTOMER_MAIN_PAGE);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if(userType.equals(MANAGER)) {
                new ManagerMainPageController(email);
                Main.setRoot(MANAGER_MAIN_PAGE);
            } else if (userType.equals(WAITER)) {
                new WaiterMainPageController(email);
                Main.setRoot(WAITER_MAIN_PAGE);
            }else if(userType.equals(CHEF)){
                new ChefMainPageController(email);
                Main.setRoot(CHEF_MAIN_PAGE);
            }else if(userType.equals(DRIVER)){
                new DriverMainPageController(email);
                Main.setRoot(DRIVER_MAIN_PAGE);
            }else {
                    LoginErrorLabel.setText(ERROR_LABEL);
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
        Main.setRoot(CREATE_USER);
    }
}