package User;

import cafe94.group14a2.Login;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

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
        Customer newCustomer = new Customer(userId, email, password, firstName, lastName, address, isCustomer);
        customers.add(newCustomer);
        userCount++;
    }
    public boolean checkEmailValidity(){
        boolean emailIsValid =true;
        for(Customer customer:customers) {
            if (Objects.equals(customer.getEmail(), emailField.getText())) {
                emailIsValid = false;
            }
        }
        return  emailIsValid;
    }

    @FXML private TextField emailField;
    @FXML private TextField passwordField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField addressField;
    @FXML private Label errorLabel;

    @FXML
    private void onCreateUserClick() throws IOException {
//        boolean emailIsValid = true;
        int userId = userCount + 1;
        String email = emailField.getText();
//        for(Customer customer:customers){
//            System.out.println("old emails: "+customer.getEmail());
//            System.out.println("email check: "+customer.getEmail());
//            System.out.println("check"+Objects.equals(customer.getEmail(), emailField.getText()));
//            if(Objects.equals(customer.getEmail(), emailField.getText())){
//                emailIsValid = false;
//            }
//        }
        String password = passwordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String address= addressField.getText();
        boolean isCustomer = true;
        if(checkEmailValidity()) {
            Customer newCustomer = new Customer(userId, email, password, firstName, lastName, address, isCustomer);
            System.out.println(newCustomer);
            if(customers.add(newCustomer)) {
                userCount++;
                Login.setRoot("login");
            }
            else{
                errorLabel.setText("Error..please try again");
            }
        }
        else {
            errorLabel.setText("Email already exists");
        }

    }
}
