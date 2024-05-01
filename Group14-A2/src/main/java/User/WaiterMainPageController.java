package User;

import Orders.WaiterOrderController;
import Orders.WaiterOrderViewController;
import Reservation.WaiterReservationMainPageController;
import cafe94.group14a2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to control the waiter main page
 * @author Saurav
 */
public class WaiterMainPageController {
    private static String activeEmail;
    private static Waiter activeWaiter;
    private static final ArrayList<Waiter> waiters = UserController.getWaiters();

    @FXML private Button makeOrderButton;
    @FXML private Button checkOrderButton;
    @FXML private Button logTimeButton;
    @FXML private Button logoutButton;
    @FXML private Button reservationButton;

    /**
     * Default Constructor
     */
    public WaiterMainPageController(){}

    /**
     * Constructor to instantiate the class with waiter data
     * @param email The email is passed as string
     */
    public WaiterMainPageController(String email){
        this.activeEmail = email;
        for (Waiter waiter: waiters){
            if(waiter.getEmail().equals(email)){
                this.activeWaiter = waiter;
            }
        }
        setActiveEmail(email);
    }

    /**
     * Function to set the active email to the class
     * @param email The email is passed as string
     */
    private void setActiveEmail(String email) {
        this.activeEmail = email;
    }

    /**
     * Function to initialize the UI component
     */
    public void initialize(){
        makeOrderButton.setOnAction(e->{
            handleMakeOrderButtonClick();
        });
        checkOrderButton.setOnAction(e->{
            try {
                handleCheckOrderButtonClick();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        logTimeButton.setOnAction(e->{
            try {
                handleLogTimeClick();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        logoutButton.setOnAction(e->{
            handleLogoutButtonClick();
        });
        reservationButton.setOnAction(e->{
            handleReservationClick();
        });
    }

    /**
     * Function the handle the reservation click and switch the page to the reservation page
     */
    private void handleReservationClick() {
        new WaiterReservationMainPageController(activeWaiter.getEmail());
        try {
            Main.setRoot("waiterReservationPage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Function to logout the user and go to the login page
     */
    private void handleLogoutButtonClick() {
        try {
            Main.setRoot("login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Function to switch to the time log page
     * @throws IOException The exception is thrown if there is issue loading the fxml file
     */
    private void handleLogTimeClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/waiterTimeLogger.fxml"));
        Parent root = loader.load();
        WaiterTimeLogController controller = loader.getController();
        controller.setCurrentWaiter(activeWaiter);

        Stage orderViewStage = new Stage();
        controller.setStage(orderViewStage);
        orderViewStage.setTitle("Edit Time Log");
        orderViewStage.setScene(new Scene(root, 600, 600));
        orderViewStage.initModality(Modality.APPLICATION_MODAL);
        orderViewStage.showAndWait();
    }

    /**
     * Function to switch to the order list page
     * @throws IOException The exception is thrown if there is issue loading the fxml file
     */
    private void handleCheckOrderButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/waiterOrderView.fxml"));
        Parent root = loader.load();
        WaiterOrderViewController controller = loader.getController();
        controller.setActiveWaiter(activeWaiter);

        Stage orderViewStage = new Stage();
        controller.setStage(orderViewStage);
        orderViewStage.setTitle("Edit Item Detail");
        orderViewStage.setScene(new Scene(root, 600, 600));
        orderViewStage.initModality(Modality.APPLICATION_MODAL);
        orderViewStage.showAndWait();
    }

    /**
     * Function to switch to the order create page
     */
    private void handleMakeOrderButtonClick() {
        new WaiterOrderController(activeWaiter);
        try {
            Main.setRoot("waiterOrderCreatePage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
