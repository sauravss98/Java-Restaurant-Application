package User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class StaffCreateController {
    private ArrayList<Customer> customers = UserController.getCustomers();
    private ArrayList<Waiter> waiters = UserController.getWaiters();
    private ArrayList<Manager> managers = UserController.getManagers();
    private ArrayList<Chef> chefs = UserController.getChefs();
    private ArrayList<Driver> drivers = UserController.getDrivers();
    @FXML private TextField emailField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private ChoiceBox userTypeField;
    @FXML private Button createUserButton;
    @FXML private Button cancelButton;
    @FXML private Label warningLabel;
    private ObservableList<String> options = FXCollections.observableArrayList();
    private Stage stage;


    public StaffCreateController(){

    }

    public void initialize(){
        warningLabel.setVisible(false);
        userTypeField.setItems(options);
        options.addAll("Chef","Driver","Manager","Waiter");
        createUserButton.setOnAction(e->{
            handleCreateButtonClick();
        });
        cancelButton.setOnAction(e->{
            handleCancelClick();
        });
    }

    private void handleCancelClick() {
        if(stage!=null){
            stage.close();
        }
    }

    private void handleCreateButtonClick() {
        String email = emailField.getText();
        String firstname = firstNameField.getText();
        String lastname = lastNameField.getText();
        String userType = "";
        try {
            userType = userTypeField.getValue().toString();
        } catch (NullPointerException nullPointerException){
            System.out.println("in catch");
            warningLabel.setVisible(true);
            warningLabel.setText("Please enter all the details");
        }
        boolean isStaff = true;
        int hoursWorked = 0;
        int totalHours = 20;
        boolean isLoggedIn = false;
        boolean isActive = true;
        if(UserController.patternMatches(email)) {
            if (checkEmailValidity()) {
                if (!(email.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || userType.isEmpty())) {
                    int userId = UserController.getUsersCount() + 1;
                    int staffId = UserController.getStaffCount() + 1;
                    if (Objects.equals(userType, "Waiter")) {
                        boolean isWaiter = true;
                        Waiter waiter = new Waiter(userId, email, firstname, lastname, staffId, hoursWorked, totalHours, isStaff, isWaiter, "Waiter", isLoggedIn, isActive);
                        waiters.add(waiter);
                        UserController.saveWaiterDataToExcel(waiter);
                    } else if (Objects.equals(userType, "Manager")) {
                        boolean isManager = true;
                        Manager manager = new Manager(userId, email, firstname, lastname, staffId, hoursWorked, totalHours, isStaff, isManager, "Manager", isLoggedIn, isActive);
                        managers.add(manager);
                        UserController.saveManagerDataToExcel(manager);
                    } else if (Objects.equals(userType, "Chef")) {
                        boolean isChef = true;
                        Chef chef = new Chef(userId, email, firstname, lastname, staffId, hoursWorked, totalHours, isStaff, isChef, "Chef", isLoggedIn, isActive);
                        chefs.add(chef);
                        UserController.saveChefDataToExcel(chef);
                    } else if (Objects.equals(userType, "Driver")) {
                        boolean isDriver = true;
                        Driver driver = new Driver(userId, email, firstname, lastname, staffId, hoursWorked, totalHours, isStaff, isDriver, "Driver", isLoggedIn, isActive);
                        drivers.add(driver);
                        UserController.saveDriverDataToExcel(driver);
                    }
                    UserController.setUserCount(userId);
                    UserController.setStaffCount(staffId);
                    if (stage != null) {
                        stage.close();
                    }
                } else {
                    warningLabel.setVisible(true);
                    warningLabel.setText("Please enter all the details");
                }
            } else {
                warningLabel.setVisible(true);
                warningLabel.setText("Username already exists");
            }
        }else {
            warningLabel.setVisible(true);
            warningLabel.setText("Invalid Email Format");
        }
    }

    public boolean checkEmailValidity(){
        boolean emailIsValid =true;
        for(Customer customer:customers) {
            if (Objects.equals(customer.getEmail(), emailField.getText())) {
                emailIsValid = false;
            }
        }
        for(Manager manager:managers) {
            if (Objects.equals(manager.getEmail(), emailField.getText())) {
                emailIsValid = false;
            }
        }
        for(Waiter waiter:waiters) {
            if (Objects.equals(waiter.getEmail(), emailField.getText())) {
                emailIsValid = false;
            }
        }
        for(Driver driver:drivers) {
            if (Objects.equals(driver.getEmail(), emailField.getText())) {
                emailIsValid = false;
            }
        }
        for(Chef chef:chefs) {
            if (Objects.equals(chef.getEmail(), emailField.getText())) {
                emailIsValid = false;
            }
        }
        return  emailIsValid;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
}
