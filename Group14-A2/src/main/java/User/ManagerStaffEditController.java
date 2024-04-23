package User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ManagerStaffEditController {
    private ArrayList<Manager> managers = UserController.getManagers();
    private ArrayList<Waiter> waiters = UserController.getWaiters();
    private ArrayList<Chef> chefs = UserController.getChefs();
    private ArrayList<Driver> drivers = UserController.getDrivers();
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
    @FXML private Spinner totalHoursSpinner;
    @FXML private Button removeStaffButton;
    @FXML private Button cancelButton;
    @FXML private Button confirmStaffButton;



    public ManagerStaffEditController(){}

    public void setStage(Stage stage) {
        this.stage = stage;
    }

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

    public void setActiveChef(Chef activeChef) {
        this.activeChef = activeChef;
        initialize();
    }

    public void setActiveDriver(Driver activeDriver) {
        this.activeDriver = activeDriver;
        initialize();
    }

    public void setActiveManager(Manager activeManager) {
        this.activeManager = activeManager;
        initialize();
    }

    public void setActiveWaiter(Waiter activeWaiter) {
        this.activeWaiter = activeWaiter;
        initialize();
    }

    public void initialize(){
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
    }

    private void handleCancelClick() {
        if(stage!=null){
            stage.close();
        }
    }

    private void handleRemoveButton(){
        if(activeStaff.getUserType().equals("Manager")){
            activeManager.setIsActive(false);
        }
        if(activeStaff.getUserType().equals("Chef")){
            activeChef.setIsActive(false);
        }
        if(activeStaff.getUserType().equals("Driver")){
            activeDriver.setIsActive(false);
        }
        if(activeStaff.getUserType().equals("Waiter")){
            activeWaiter.setIsActive(false);
        }
        if(stage!=null){
            stage.close();
        }
    }

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

    private void setDataLabels(Manager manager){
        idLabel.setText("Staff ID: "+manager.getUserId());
        userNameLabel.setText("Name: "+manager.getFullName());
        userTypeLabel.setText("User Type: "+manager.getUserType());
        SpinnerValueFactory<Integer> workHouredValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, manager.getHoursWorked());
        workedHoursSpinner.setValueFactory(workHouredValueFactory);
        SpinnerValueFactory<Integer> totalHoursValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, manager.getTotalHours());
        totalHoursSpinner.setValueFactory(totalHoursValueFactory);
    }
    private void setDataLabels(Chef chef){
        idLabel.setText("Staff ID: "+chef.getUserId());
        userNameLabel.setText("Name: "+chef.getFullName());
        userTypeLabel.setText("User Type: "+chef.getUserType());
        SpinnerValueFactory<Integer> workHouredValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, chef.getHoursWorked());
        workedHoursSpinner.setValueFactory(workHouredValueFactory);
        SpinnerValueFactory<Integer> totalHoursValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, chef.getTotalHours());
        totalHoursSpinner.setValueFactory(totalHoursValueFactory);
    }
    private void setDataLabels(Waiter waiter){
        idLabel.setText("Staff ID: "+waiter.getUserId());
        userNameLabel.setText("Name: "+waiter.getFullName());
        userTypeLabel.setText("User Type: "+waiter.getUserType());
        SpinnerValueFactory<Integer> workHouredValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, waiter.getHoursWorked());
        workedHoursSpinner.setValueFactory(workHouredValueFactory);
        SpinnerValueFactory<Integer> totalHoursValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, waiter.getTotalHours());
        totalHoursSpinner.setValueFactory(totalHoursValueFactory);
    }
    private void setDataLabels(Driver driver){
        idLabel.setText("Staff ID: "+driver.getUserId());
        userNameLabel.setText("Name: "+driver.getFullName());
        userTypeLabel.setText("User Type: "+driver.getUserType());
        SpinnerValueFactory<Integer> workHouredValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, driver.getHoursWorked());
        workedHoursSpinner.setValueFactory(workHouredValueFactory);
        SpinnerValueFactory<Integer> totalHoursValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, driver.getTotalHours());
        totalHoursSpinner.setValueFactory(totalHoursValueFactory);
    }
}
