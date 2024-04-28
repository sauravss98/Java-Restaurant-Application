package Orders;

import User.Chef;
import User.ChefMainPageController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * Class to handle the card instance when there are multiple orders
 * @author Saurav
 */
public class CardController {
    private Order currentOrder;
    private static Chef currentChef;
    @FXML private Label orderIdLabel;
    @FXML private ListView itemsList;
    @FXML private Button completeOrderButton;
    private ChefMainPageController chefMainPageController;

    /**
     * Default Contructor for the class
     */
    public CardController(){}

    /**
     * Constructor to take initialize the class with the data required
     * @param order Order object is passed
     * @param chef Chef object is passed
     */
    public CardController (Order order,Chef chef){
        this.currentOrder = order;
        this.currentChef = chef;
    }

    /**
     * Function to set the parent controller
     * @param controller The controller instance is passed
     */
    public void setChefMainPageController(ChefMainPageController controller) {
        this.chefMainPageController = controller;
    }

    /**
     * Function to set the order data for the instance of the class
     * @param order The order instance is passed
     */
    public void setOrder(Order order){
        this.currentOrder = order;
    }

    /**
     * Function to set the chef data for the instance of the class
     * @param chef The cef order instance is passed
     */
    public void setCurrentChef(Chef chef){
        this.currentChef = chef;
    }

    /**
     * Function to initialize the UI components
     */
    public void initialize(){
        if (currentOrder!=null && currentChef!=null){
            refreshCard();
        }
        completeOrderButton.setOnAction(e->{
            handleCompleteButton();
        });
        refreshList();
    }

    /**
     * Function to refresh the list UI components
     */
    private void refreshList() {
        if (currentOrder != null && currentOrder.getOrderItems() != null) {
            itemsList.getItems().clear();
            for (OrderItem orderItem : currentOrder.getOrderItems()) {
                itemsList.getItems().add(orderItem.getItem().getDescriptionForList() + " Quantity: " + orderItem.getQuantity());
            }
        } else {
            itemsList.getItems().add("No items Ordered");
        }
    }

    /**
     * Function to handle complete button click and save the data
     */
    private void handleCompleteButton() {
        currentOrder.setOrderStatus("Food Prepared");
        currentOrder.setCompleted(true);
        currentOrder.setChefId(currentChef.getUserId());
        OrderDataHandler.saveChefExcelChange(currentOrder);
        chefMainPageController.refreshCardDisplay();
    }

    /**
     * Function to refresh the UI card element
     */
    private void refreshCard() {
        orderIdLabel.setText("Order Id "+currentOrder.getOrderId());
    }
}
