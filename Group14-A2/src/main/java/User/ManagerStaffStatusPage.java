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
    @FXML private ListView staffList;

    public ManagerStaffStatusPage(){}

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public  void initialize(){
        refreshReservationList();
        goBackButton.setOnAction(e->{
            handleBackButton();
        });
    }

    private void refreshReservationList() {
        staffList.getItems().clear();

        for (Manager manager : managers) {
            staffList.getItems().add(manager.toString());
        }
        for (Chef chef : chefs) {
            staffList.getItems().add(chef.toString());
        }
        for (Waiter waiter : waiters) {
            staffList.getItems().add(waiter.toString());
        }
        for (Driver driver : drivers) {
            staffList.getItems().add(driver.toString());
        }
    }

    private void handleBackButton() {
        if(stage!=null){
            stage.close();
        }
    }
}
