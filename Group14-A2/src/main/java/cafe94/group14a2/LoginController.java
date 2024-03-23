package cafe94.group14a2;

import User.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class LoginController {

    @FXML private TextField EmailTextField;

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
    @FXML
    protected void onLoginClick() {
        String email = EmailTextField.getText();
        if(!email.isEmpty()){
            String UserType = "";
            UserType = checkValidEmail(email);
            System.out.println(UserType);
        }
    }

    @FXML
    protected void onSignUpClick() throws IOException {
        Login.setRoot("createUser");
    }
}