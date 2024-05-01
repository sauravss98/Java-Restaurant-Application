package User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to control the manager staff status page
 * @author Saurav
 */
public class ManagerStaffStatusPage {
    private final ArrayList<Manager> managers = UserController.getManagers();
    private final ArrayList<Waiter> waiters = UserController.getWaiters();
    private final ArrayList<Chef> chefs = UserController.getChefs();
    private final ArrayList<Driver> drivers = UserController.getDrivers();
    private final ArrayList<Staff> staffs = new ArrayList<>();
    private Stage stage;

    @FXML
    private Button goBackButton;
    @FXML private ListView activeList;
    @FXML private ListView inactiveList;

    /**
     * Default constructor
     */
    public ManagerStaffStatusPage(){}

    /**
     * Function to set the stage to the instance
     * @param stage Stage object is passed
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Function to initialize the UI components
     */
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

    /**
     * Function to edit the staff details. The user is routed to the new page
     * @param selectedItem The selected item id is passed as a string
     */
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
        controller.setStaffArrayList(staffs);

        Stage staffEditStage = new Stage();
        controller.setStage(staffEditStage);
        staffEditStage.setTitle("Edit Staff Detail");
        staffEditStage.setScene(new Scene(root, 600, 600));
        staffEditStage.getScene().getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        staffEditStage.initModality(Modality.APPLICATION_MODAL);
        staffEditStage.showAndWait();
        refreshInactiveStaffList();
        refreshActiveStaffList();
    }

    /**
     * Function to get the id from the data passed
     * @param data Data is passed as string
     * @return Returns the id as string
     */
    public static String extractID(String data) {
        int startIndex = data.indexOf("ID: ") + 4; // Add 4 to skip "ID: "
        int endIndex = data.indexOf(" ", startIndex); // Find the space after the ID value
        if (endIndex == -1) {
            endIndex = data.length(); // If no space is found, consider the end of the string
        }
        return data.substring(startIndex, endIndex);
    }

    /**
     * Function to get the staff data based on the id passed
     * @param idString The id is passed as string
     * @return Returns the staff
     */
    private Staff getStaffData(String idString) {
        int id = Integer.parseInt(idString);
        for (Staff staff : staffs) {
            if (staff.getUserId() == id) {
                return staff;
            }
        }
        return null;
    }

    /**
     * Function to refresh the list of staff who are inactive
     */
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

    /**
     * Function to refresh the list of staff who are active
     */
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

    /**
     * Function to handle the back button click
     */
    private void handleBackButton() {
        if(stage!=null){
            stage.close();
        }
    }
}
