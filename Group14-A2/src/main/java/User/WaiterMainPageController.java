package User;

import Orders.WaiterOrderController;
import cafe94.group14a2.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
            handleCheckOrderButtonClick();
        });
        logTimeButton.setOnAction(e->{
            handleLogTimeClick();
        });
        logoutButton.setOnAction(e->{
            handleLogoutButtonClick();
        });
    }

    private void handleLogoutButtonClick() {
        try {
            Main.setRoot("login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleLogTimeClick() {
    }

    private void handleCheckOrderButtonClick() {
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
