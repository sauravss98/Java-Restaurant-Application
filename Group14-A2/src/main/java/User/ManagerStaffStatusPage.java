package User;

import Orders.ManagerOrderEditController;
import Orders.Order;
import Reservation.Reservation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ManagerStaffStatusPage {
    private ArrayList<Manager> managers = UserController.getManagers();
    private ArrayList<Waiter> waiters = UserController.getWaiters();
    private ArrayList<Chef> chefs = UserController.getChefs();
    private ArrayList<Driver> drivers = UserController.getDrivers();
    private ArrayList<Staff> staffs = new ArrayList<>();
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
        staffs.addAll(managers);
        staffs.addAll(waiters);
        staffs.addAll(chefs);
        staffs.addAll(drivers);
        activeList.setOnMouseClicked(e->{
            String selectedItem = (String) activeList.getSelectionModel().getSelectedItem();
            editStaffDetail(selectedItem);
        });
        inactiveList.setOnMouseClicked(e->{
            String selectedItem = (String) inactiveList.getSelectionModel().getSelectedItem();
            editStaffDetail(selectedItem);
        });
    }

    private void editStaffDetail(String selectedItem) {
        String id = extractID(selectedItem);
        Staff requiredStaff = getStaffData(id);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/managerStaffEditPage.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ManagerStaffEditController controller = loader.getController();
        controller.setStaff(requiredStaff);

        Stage staffEditStage = new Stage();
        controller.setStage(staffEditStage);
        staffEditStage.setTitle("Edit Staff Detail");
        staffEditStage.setScene(new Scene(root, 600, 600));
        staffEditStage.initModality(Modality.APPLICATION_MODAL);
        staffEditStage.showAndWait();
        refreshInactiveStaffList();
        refreshActiveStaffList();
    }

    public static String extractID(String data) {
        int startIndex = data.indexOf("ID: ") + 4; // Add 4 to skip "ID: "
        int endIndex = data.indexOf(" ", startIndex); // Find the space after the ID value
        if (endIndex == -1) {
            endIndex = data.length(); // If no space is found, consider the end of the string
        }
        return data.substring(startIndex, endIndex);
    }

    private Staff getStaffData(String idString) {
        int id = Integer.parseInt(idString);
        for (Staff staff : staffs) {
            if (staff.getStaffID() == id) {
                return staff;
            }
        }
        return null;
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
