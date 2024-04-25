package User;

import Orders.CardController;
import Orders.Order;
import Orders.OrderDataHandler;
import cafe94.group14a2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ChefMainPageController {
    private ArrayList<Chef> chefs = UserController.getChefs();
    private ArrayList<Order> orders = OrderDataHandler.getOrders();
    private static Chef activeChef;
    @FXML private HBox containerBox;
    @FXML private Button logoutButton;
    @FXML private Button logTimeChangeButton;


    public ChefMainPageController(){}

    public ChefMainPageController(String email){
        for (Chef chef: chefs){
            if(chef.getEmail().equals(email)){
                this.activeChef = chef;
            }
        }
    }

    public void initialize(){
        logoutButton.setOnAction(e->{
            handleLogoutButton();
        });
        try {
            addCard();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleLogoutButton() {
        try {
            Main.setRoot("login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addCard() throws IOException {
        try {
            for (Order order:orders) {
                if (Objects.equals(order.getOrderStatus(), "InProgress")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/orderCard.fxml"));
                    VBox vbox = loader.load();
                    CardController controller = loader.getController();
                    controller.setOrder(order);
                    controller.setCurrentChef(activeChef);
                    controller.initialize();
                    containerBox.getChildren().add(vbox);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}