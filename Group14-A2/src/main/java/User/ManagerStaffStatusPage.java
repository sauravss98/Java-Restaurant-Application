package User;

import Reservation.Reservation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ManagerStaffStatusPage {
    private ArrayList<Manager> managers = UserController.getManagers();
    private ArrayList<Waiter> waiters = UserController.getWaiters();
    private ArrayList<Chef> chefs = UserController.getChefs();
    private ArrayList<Driver> drivers = UserController.getDrivers();
    private Stage stage;

    @FXML
    private Button goBackButton;
    @FXML private ListView activeList;
    @FXML private ListView inactiveList;

    public ManagerStaffStatusPage(){}

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public  void initialize(){
        refreshActiveStaffList();
        refreshInactiveStaffList();
        goBackButton.setOnAction(e->{
            handleBackButton();
        });
    }

    private void refreshInactiveStaffList() {
        inactiveList.getItems().clear();

        for (Manager manager : managers) {
            if(!manager.getIsActive()) {
                inactiveList.getItems().add(manager.getStaffDescription());
            }
        }
        for (Chef chef : chefs) {
            if(!chef.getIsActive()) {
                inactiveList.getItems().add(chef.getStaffDescription());
            }
        }
        for (Waiter waiter : waiters) {
            if(!waiter.getIsActive()) {
                inactiveList.getItems().add(waiter.getStaffDescription());
            }
        }
        for (Driver driver : drivers) {
            if(!driver.getIsActive()) {
                inactiveList.getItems().add(driver.getStaffDescription());
            }
        }
    }

    private void refreshActiveStaffList() {
        activeList.getItems().clear();

        for (Manager manager : managers) {
            if(manager.getIsActive()) {
                activeList.getItems().add(manager.getStaffDescription());
            }
        }
        for (Chef chef : chefs) {
            if(chef.getIsActive()) {
                activeList.getItems().add(chef.getStaffDescription());
            }
        }
        for (Waiter waiter : waiters) {
            if(waiter.getIsActive()) {
                activeList.getItems().add(waiter.getStaffDescription());
            }
        }
        for (Driver driver : drivers) {
            if(driver.getIsActive()) {
                activeList.getItems().add(driver.getStaffDescription());
            }
        }
    }

    private void handleBackButton() {
        if(stage!=null){
            stage.close();
        }
    }
}
