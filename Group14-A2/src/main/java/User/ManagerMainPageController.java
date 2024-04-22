package User;

import Items.ItemMainPageController;
import Orders.ManagerOrderView;
import Orders.OrderController;
import Orders.OrderDataHandler;
import Orders.OrderTypeWindowController;
import cafe94.group14a2.Main;
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

public class ManagerMainPageController {
    @FXML private Button createStaffButton;
    @FXML private Button checkStaffStatusButton;
    @FXML private Button checkOrdersButton;
    @FXML private Button checkReservationsButton;
    @FXML private Button manageItemButton;
    @FXML private Button logoutButton;
    @FXML private ListView activeOrdersList;
    @FXML private ListView activeStaffList;
    private static Manager manager;
    private static String activeEmail;
    private ArrayList<Manager> managersList = UserController.getManagers();

    public ManagerMainPageController(){}

    public ManagerMainPageController(String email){
        this.activeEmail = email;
        for (Manager requiredManager: managersList){
            if(activeEmail == requiredManager.getEmail()){
                this.manager = requiredManager;
            }
        }
    }


    public void initialize(){
        refreshActiveOrderList();
        createStaffButton.setOnAction(e->{
            handleCreateStaffClick();
        });
        checkStaffStatusButton.setOnAction(e->{
            handleCheckStaffClick();
        });
        checkOrdersButton.setOnAction(e->{
            handlCheckOrdersButton();
        });
        checkReservationsButton.setOnAction(e->{
            handleCheckReservationButton();
        });
        manageItemButton.setOnAction(e->{
            handleItemButtonClick();
        });
        logoutButton.setOnAction(e->{
            handleLogoutButton();
        });
    }

    private void handleLogoutButton() {
        manager = null;
        activeEmail = "";
        try {
            Main.setRoot("login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleItemButtonClick() {
        try {
            new ItemMainPageController(manager);
            Main.setRoot("itemMainPage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleCheckReservationButton() {
    }

    private void handlCheckOrdersButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/orderViewManager.fxml"));
            Parent root = loader.load();
            ManagerOrderView controller = loader.getController();
            Stage allOrders = new Stage();
            allOrders.setTitle("Check Orders");
            allOrders.setScene(new Scene(root, 600, 600));
            controller.setStage(allOrders);
            allOrders.initModality(Modality.APPLICATION_MODAL);
            allOrders.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleCheckStaffClick() {
    }

    private void handleCreateStaffClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/staffCreatePage.fxml"));
            Parent root = loader.load();
            StaffCreateController controller = loader.getController();

            Stage staffCreateStage = new Stage();
            staffCreateStage.setTitle("Create Staff");
            staffCreateStage.setScene(new Scene(root, 600, 600));

            controller.setStage(staffCreateStage);
            staffCreateStage.initModality(Modality.APPLICATION_MODAL);
            staffCreateStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void refreshActiveOrderList(){

    }
}
