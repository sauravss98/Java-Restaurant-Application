package User;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class UserController {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Manager> managers = new ArrayList<>();
    private ArrayList<Waiter> waiters = new ArrayList<>();
    private ArrayList<Driver> drivers = new ArrayList<>();
    private ArrayList<Chef> chefs = new ArrayList<>();
    private static int userCount = 0;
    public int getUsersCount() {
        return userCount;
    }

//    public static int getUsersCount(){
//        int customerCount = customers.size();
//        int managersCount = managers.size();
//        int waitersCount = waiters.size();
//        int driversCount = drivers.size();
//        int chefCount = chefs.size();
//        return customerCount + managersCount + waitersCount + driversCount + chefCount;
//    }
    @FXML
    public static void createCustomer(String email, String password, String firstName, String lastName, String address, boolean isCustomer){
        int userId = userCount + 1;
        System.out.println(userId);
        Customer newCustomer = new Customer(userId, email, password, firstName, lastName, address, isCustomer);
        System.out.println(newCustomer.toString());
        customers.add(newCustomer);
        userCount++;
        System.out.println(userCount);
    }
    @FXML private TextField emailField;
    @FXML private TextField passwordField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField addressField;

    @FXML
    private void onCreateUserClick(){
        int userId = userCount + 1;
        System.out.println(userId);
        String email = emailField.getText();
        String password = passwordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String address= addressField.getText();
        boolean isCustomer = true;
        Customer newCustomer = new Customer(userId, email, password, firstName, lastName, address, isCustomer);
        System.out.println(newCustomer.toString());
        customers.add(newCustomer);
        userCount++;
    }
}
