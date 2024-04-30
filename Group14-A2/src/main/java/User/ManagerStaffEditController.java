package User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Class to control the manager staff edit page
 * @author Saurav
 */
public class ManagerStaffEditController {
    private ArrayList<Manager> managers = UserController.getManagers();
    private ArrayList<Waiter> waiters = UserController.getWaiters();
    private ArrayList<Chef> chefs = UserController.getChefs();
    private ArrayList<Driver> drivers = UserController.getDrivers();
    private ArrayList<Staff> staffs;
    private Stage stage;
    private Staff activeStaff;
    private Chef activeChef;
    private Manager activeManager;
    private Waiter activeWaiter;
    private Driver activeDriver;
    @FXML private Label idLabel;
    @FXML private Label userNameLabel;
    @FXML private Label userTypeLabel;
    @FXML private Spinner workedHoursSpinner;
    @FXML private Button removeStaffButton;
    @FXML private Button cancelButton;
    @FXML private Button confirmStaffButton;
    @FXML private Label warningLabel;
    private int oldWorkedHoursValue;

    /**
     * Default constructor for the class
     */
    public ManagerStaffEditController(){}

    /**
     * Function to set the stage object to the class instance
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Function to set the staff array list when loading the
     * @param staffs Pass the staffs arraylist
     */
    public void setStaffArrayList(ArrayList<Staff> staffs){
        this.staffs = staffs;
    }

    /**
     * Function to set the old worked hours
     * @param hoursValue The hours value is passed as int
     */
    public void setOldWorkedHoursValue(int hoursValue){
        this.oldWorkedHoursValue = hoursValue;
    }

    /**
     * Function to set the staff to the class instance
     * @param staff The staff object is passed
     */
    public void setStaff(Staff staff) {
        this.activeStaff = staff;
        String userType = activeStaff.getUserType();

        if (userType.equals("Manager")) {
            for (Manager manager : managers) {
                if (activeStaff.getUserId() == manager.getUserId()) {
                    setActiveManager(manager);
                    return;
                }
            }
        }

        if (userType.equals("Chef")) {
            for (Chef chef : chefs) {
                if (activeStaff.getUserId() == chef.getUserId()) {
                    setActiveChef(chef);
                    return;
                }
            }
        }

        if (userType.equals("Driver")) {
            for (Driver driver : drivers) {
                if (activeStaff.getUserId() == driver.getUserId()) {
                    setActiveDriver(driver);
                    return;
                }
            }
        }

        if (userType.equals("Waiter")) {
            for (Waiter waiter : waiters) {
                if (activeStaff.getUserId() == waiter.getUserId()) {
                    setActiveWaiter(waiter);
                    return;
                }
            }
        }
    }

    /**
     * Function to set the chef object to the class
     * @param activeChef The chef object is passed
     */
    public void setActiveChef(Chef activeChef) {
        this.activeChef = activeChef;
        initialize();
    }

    /**
     * Function to set the driver object to the class
     * @param activeDriver The driver object is passed
     */
    public void setActiveDriver(Driver activeDriver) {
        this.activeDriver = activeDriver;
        initialize();
    }

    /**
     * Function to set the manager object to the class
     * @param activeManager The manager object is passed
     */
    public void setActiveManager(Manager activeManager) {
        this.activeManager = activeManager;
        initialize();
    }

    /**
     * Function to set the waiter object to the class
     * @param activeWaiter The waiter object is passed
     */
    public void setActiveWaiter(Waiter activeWaiter) {
        this.activeWaiter = activeWaiter;
        initialize();
    }

    /**
     * Function to initialize the UI components
     */
    public void initialize(){
        if(activeStaff!=null) {
            setOldWorkedHoursValue(activeStaff.getHoursWorked());
        } else {
            setOldWorkedHoursValue(0);
        }
        warningLabel.setText("");
        warningLabel.setVisible(false);
        refreshLabelData();
        if(activeManager!=null) {
            System.out.println("active user is " + activeManager.getFullName());
        }
        cancelButton.setOnAction(e->{
            handleCancelClick();
        });
        removeStaffButton.setOnAction(e->{
            handleRemoveButton();
        });
        confirmStaffButton.setOnAction(e->{
            handleConfirmClickButton();
        });
    }

    /**
     * Function to handle the confirm button click and make changes to the data
     */
    private void handleConfirmClickButton() {
        if(oldWorkedHoursValue<(int)workedHoursSpinner.getValue()) {
            activeStaff.setHoursWorked((int) workedHoursSpinner.getValue());
            if (activeStaff.getUserType().equals("Manager")) {
                UserController.editStaffExcelData(activeManager, "edit");
            }
            if (activeStaff.getUserType().equals("Chef")) {
                UserController.editStaffExcelData(activeChef, "edit");
            }
            if (activeStaff.getUserType().equals("Driver")) {
                UserController.editStaffExcelData(activeDriver, "edit");
            }
            if (activeStaff.getUserType().equals("Waiter")) {
                UserController.editStaffExcelData(activeWaiter, "edit");
            }
            if (stage != null) {
                stage.close();
            }
        } else {
            warningLabel.setVisible(true);
            warningLabel.setText("New value should be greater than the old value");
        }
    }

    /**
     * Function to handle the cancel button click
     */
    private void handleCancelClick() {
        if(stage!=null){
            stage.close();
        }
    }

    /**
     * Function to handle the remove button click. When clicked the item is removed
     */
    private void handleRemoveButton(){
        if(activeStaff.getUserType().equals("Manager")){
            activeManager.setIsActive(false);
            UserController.editStaffExcelData(activeManager,"remove");
        }
        if(activeStaff.getUserType().equals("Chef")){
            activeChef.setIsActive(false);
            UserController.editStaffExcelData(activeChef,"remove");
        }
        if(activeStaff.getUserType().equals("Driver")){
            activeDriver.setIsActive(false);
            UserController.editStaffExcelData(activeDriver,"remove");
        }
        if(activeStaff.getUserType().equals("Waiter")){
            activeWaiter.setIsActive(false);
            UserController.editStaffExcelData(activeWaiter,"remove");
        }
        if(stage!=null){
            stage.close();
        }
    }

    /**
     * Function to refresh the data label to the selected type
     */
    private void refreshLabelData() {
        if(activeStaff!=null){
            if(activeStaff.getUserType().equals("Manager")){
                setDataLabels(activeManager);
            }
            if(activeStaff.getUserType().equals("Chef")){
                setDataLabels(activeChef);
            }
            if(activeStaff.getUserType().equals("Driver")){
                setDataLabels(activeDriver);
            }
            if(activeStaff.getUserType().equals("Waiter")){
                setDataLabels(activeWaiter);
            }
        }
    }

    /**
     * Function to change the data label to the selected user type
     * @param manager Manager object is passed
     */
    private void setDataLabels(Manager manager){
        idLabel.setText("Staff ID: "+manager.getUserId());
        userNameLabel.setText("Name: "+manager.getFullName());
        userTypeLabel.setText("User Type: "+manager.getUserType());
        SpinnerValueFactory<Integer> workHouredValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, manager.getHoursWorked());
        workedHoursSpinner.setValueFactory(workHouredValueFactory);
    }

    /**
     * Function to change the data label to the selected user type
     * @param chef Chef object is passed
     */
    private void setDataLabels(Chef chef){
        idLabel.setText("Staff ID: "+chef.getUserId());
        userNameLabel.setText("Name: "+chef.getFullName());
        userTypeLabel.setText("User Type: "+chef.getUserType());
        SpinnerValueFactory<Integer> workHouredValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, chef.getHoursWorked());
        workedHoursSpinner.setValueFactory(workHouredValueFactory);
    }

    /**
     * Function to change the data label to the selected user type
     * @param waiter Waiter object is passed
     */
    private void setDataLabels(Waiter waiter){
        idLabel.setText("Staff ID: "+waiter.getUserId());
        userNameLabel.setText("Name: "+waiter.getFullName());
        userTypeLabel.setText("User Type: "+waiter.getUserType());
        SpinnerValueFactory<Integer> workHouredValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, waiter.getHoursWorked());
        workedHoursSpinner.setValueFactory(workHouredValueFactory);
    }

    /**
     * Function to change the data label to the selected user type
     * @param driver Driver object is passed
     */
    private void setDataLabels(Driver driver){
        idLabel.setText("Staff ID: "+driver.getUserId());
        userNameLabel.setText("Name: "+driver.getFullName());
        userTypeLabel.setText("User Type: "+driver.getUserType());
        SpinnerValueFactory<Integer> workHouredValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, driver.getHoursWorked());
        workedHoursSpinner.setValueFactory(workHouredValueFactory);
    }
}
