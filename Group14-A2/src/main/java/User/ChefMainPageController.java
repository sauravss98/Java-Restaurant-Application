package User;

import Orders.CardController;
import Orders.Order;
import Orders.OrderDataHandler;
import cafe94.group14a2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class to control the chef main page
 */
public class ChefMainPageController {
    private ArrayList<Chef> chefs = UserController.getChefs();
    private ArrayList<Order> orders = OrderDataHandler.getOrders();
    private static Chef activeChef;
    @FXML private HBox containerBox;
    @FXML private Button logoutButton;
    @FXML private Button logTimeChangeButton;
    @FXML private Button menuButton;

    /**
     * Default constructor for the class
     */
    public ChefMainPageController(){}

    /**
     * Constructor for instantiating the class with chef details
     * @param email The email is passed
     */
    public ChefMainPageController(String email){
        for (Chef chef: chefs){
            if(chef.getEmail().equals(email)){
                this.activeChef = chef;
            }
        }
    }

    /**
     * Function to initialize the UI components
     */
    public void initialize(){
        logoutButton.setOnAction(e->{
            handleLogoutButton();
        });
        try {
            addCard();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logTimeChangeButton.setOnAction(e->{
            try {
                handleTimeLogButton();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        menuButton.setOnAction(e->{
            handleMenuButtonClick();
        });
    }

    /**
     * Function to handle the menu button click and switch the page
     */
    private void handleMenuButtonClick() {
        new ChefItemPage(activeChef.getEmail());
        try {
            Main.setRoot("chefMenuPage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Function to open the time log window
     * @throws IOException Exception is thrown when there is some file issue
     */
    private void handleTimeLogButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/chefTimeLogger.fxml"));
        Parent root = loader.load();
        ChefTimeLogController controller = loader.getController();
        controller.setCurrentChef(activeChef);

        Stage orderViewStage = new Stage();
        controller.setStage(orderViewStage);
        orderViewStage.setTitle("Edit Time Log");
        orderViewStage.setScene(new Scene(root, 600, 600));
        orderViewStage.initModality(Modality.APPLICATION_MODAL);
        orderViewStage.showAndWait();
    }

    /**
     * Function to handle the logout button
     */
    private void handleLogoutButton() {
        try {
            Main.setRoot("login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Function to instantiate the cards with details of each order
     * @throws IOException Exception is called when there is some file issue
     */
    private void addCard() throws IOException {
        try {
            for (Order order:orders) {
                if (Objects.equals(order.getOrderStatus(), "InProgress")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/orderCard.fxml"));
                    VBox vbox = loader.load();
                    CardController controller = loader.getController();
                    controller.setChefMainPageController(this);
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

    /**
     * Function to refresh the card display
     */
    public void refreshCardDisplay() {
        containerBox.getChildren().clear(); // Clear existing cards
        try {
            addCard(); // Add cards again
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}