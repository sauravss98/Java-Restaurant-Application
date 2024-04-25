package Orders;

import User.Chef;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class CardController {
    private static Order currentOrder;
    private static Chef currentChef;
    @FXML private Label orderIdLabel;
    @FXML private ListView itemsList;
    @FXML private Button completeOrderButton;

    public CardController(){}

    public CardController (Order order,Chef chef){
        this.currentOrder = order;
        this.currentChef = chef;
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
        System.out.println("Order Id is "+currentOrder.getOrderId());
    }

    private void refreshCard() {
        orderIdLabel.setText("Order Id "+currentOrder.getOrderId());
    }
}
