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

public class WaiterMainPageController {
    private static String activeEmail;
    private static Waiter activeWaiter;
    private static ArrayList<Waiter> waiters = UserController.getWaiters();

    @FXML private Button makeOrderButton;
    @FXML private Button checkOrderButton;
    @FXML private Button logTimeButton;
    @FXML private Button logoutButton;
    @FXML private Button reservationButton;

    public WaiterMainPageController(){}

    public WaiterMainPageController(String email){
        this.activeEmail = email;
        for (Waiter waiter: waiters){
            if(waiter.getEmail().equals(email)){
                this.activeWaiter = waiter;
            }
        }
        setActiveEmail(email);
    }

    private void setActiveEmail(String email) {
        this.activeEmail = email;
    }

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

    private void handleReservationClick() {
        new WaiterReservationMainPageController(activeWaiter.getEmail());
        try {
            Main.setRoot("waiterReservationPage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleLogoutButtonClick() {
        try {
            Main.setRoot("login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

    private void handleMakeOrderButtonClick() {
        new WaiterOrderController(activeWaiter);
        try {
            Main.setRoot("waiterOrderCreatePage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
