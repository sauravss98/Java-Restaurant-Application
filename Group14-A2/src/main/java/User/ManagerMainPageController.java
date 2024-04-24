package User;

import Items.ItemMainPageController;
import Orders.*;
import Reservation.ReservationManagerPage;
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
    @FXML private Button reportButton;
    @FXML private ListView activeOrdersList;
    private static Manager manager;
    private static String activeEmail;
    private ArrayList<Manager> managersList = UserController.getManagers();
    private ArrayList<Order> orders = OrderDataHandler.getOrders();

    /**
     * Construnctor for the controller
     */
    public ManagerMainPageController(){}

    /**
     * Constructor to instantiate the controller
     * @param email
     */
    public ManagerMainPageController(String email){
        this.activeEmail = email;
        for (Manager requiredManager: managersList){
            if(activeEmail.equals(requiredManager.getEmail())){
                this.manager = requiredManager;
            }
        }
    }

    /**
     * Function to initialize the controller U@I
     */
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

    /**
     * Function to handle the logout button
     */
    private void handleLogoutButton() {
        manager = null;
        activeEmail = "";
        try {
            Main.setRoot("login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Function to handle the item button click
     */
    private void handleItemButtonClick() {
        try {
            new ItemMainPageController(manager);
            Main.setRoot("itemMainPage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Function to handle the check reservation button
     */
    private void handleCheckReservationButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/managerReservationViewPage.fxml"));
            Parent root = loader.load();
            ReservationManagerPage controller = loader.getController();
            Stage reservationManagerStage = new Stage();
            reservationManagerStage.setTitle("Check Reservations");
            reservationManagerStage.setScene(new Scene(root, 720, 600));
            controller.setStage(reservationManagerStage);
            reservationManagerStage.initModality(Modality.APPLICATION_MODAL);
            reservationManagerStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function to handle the check orders button
     */
    private void handlCheckOrdersButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/orderViewManager.fxml"));
            Parent root = loader.load();
            ManagerOrderView controller = loader.getController();
            Stage allOrders = new Stage();
            allOrders.setTitle("Check Orders");
            allOrders.setScene(new Scene(root, 720, 600));
            controller.setStage(allOrders);
            allOrders.initModality(Modality.APPLICATION_MODAL);
            allOrders.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function to handle the check staff button
     */
    private void handleCheckStaffClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/staffStatusPage.fxml"));
            Parent root = loader.load();
            ManagerStaffStatusPage controller = loader.getController();

            Stage staffStage = new Stage();
            staffStage.setTitle("Create Staff");
            staffStage.setScene(new Scene(root, 720, 600));

            controller.setStage(staffStage);
            staffStage.initModality(Modality.APPLICATION_MODAL);
            staffStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function to handle the create staff button
     */
    private void handleCreateStaffClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/staffCreatePage.fxml"));
            Parent root = loader.load();
            StaffCreateController controller = loader.getController();

            Stage staffCreateStage = new Stage();
            staffCreateStage.setTitle("Create Staff");
            staffCreateStage.setScene(new Scene(root, 720, 600));

            controller.setStage(staffCreateStage);
            staffCreateStage.initModality(Modality.APPLICATION_MODAL);
            staffCreateStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void refreshActiveOrderList(){
        activeOrdersList.getItems().clear();

        for (Order order : orders) {
            if(order.getOrderStatus().equals("InProgress")){
                activeOrdersList.getItems().add(order.getDescriptionForOrderList());
            }
        }
    }
}
