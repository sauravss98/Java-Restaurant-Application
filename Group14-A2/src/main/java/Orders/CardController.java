package Orders;

import User.Chef;
import User.ChefMainPageController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class CardController {
    private Order currentOrder;
    private static Chef currentChef;
    @FXML private Label orderIdLabel;
    @FXML private ListView itemsList;
    @FXML private Button completeOrderButton;
    private ChefMainPageController chefMainPageController;

    public CardController(){}

    public CardController (Order order,Chef chef){
        this.currentOrder = order;
        this.currentChef = chef;
    }

    public void setChefMainPageController(ChefMainPageController controller) {
        this.chefMainPageController = controller;
    }

    public void setOrder(Order order){
        this.currentOrder = order;
    }

    public void setCurrentChef(Chef chef){
        this.currentChef = chef;
    }

    public void initialize(){
        if (currentOrder!=null && currentChef!=null){
            refreshCard();
        }
        completeOrderButton.setOnAction(e->{
            handleCompleteButton();
        });
        refreshList();
    }

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

    private void handleCompleteButton() {
        currentOrder.setOrderStatus("Food Prepared");
        currentOrder.setCompleted(true);
        currentOrder.setChefId(currentChef.getUserId());
        OrderDataHandler.saveChefExcelChange(currentOrder);
        chefMainPageController.refreshCardDisplay();
    }

    private void refreshCard() {
        orderIdLabel.setText("Order Id "+currentOrder.getOrderId());
    }
}
