package Orders;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ManagerOrderEditController {
    @FXML private Label orderIdLabel;
    @FXML private Label orderTypeLabel;
    @FXML private Label orderStatusLabel;
    @FXML private ChoiceBox statusChoiceBox;
    @FXML private Button updateStatusButton;
    @FXML private Button cancelButton;
    @FXML private Label warningLabel;
    private Order currentOrder;
    private Stage stage;
    private ObservableList<String> options = FXCollections.observableArrayList();

    public ManagerOrderEditController(){}

    public void setOrder(Order order){
        this.currentOrder = order;
        initialize();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void initialize(){
        renderPage();
        warningLabel.setVisible(false);
        statusChoiceBox.setItems(options);
        options.addAll("Cancelled","In Progress","In Progress","Food Prepared","Delivered");
        cancelButton.setOnAction(e->{
            handleCancelClick();
        });
        updateStatusButton.setOnAction(e->{
            handleUpdateButton();
        });
    }

    private void handleUpdateButton() {
        try {
            String userType = statusChoiceBox.getValue().toString();
            currentOrder.setOrderStatus(userType);
            OrderDataHandler.editOrderExcelSheetData(currentOrder);
            if(stage!=null){
                stage.close();
            }
        } catch (NullPointerException exception){
            warningLabel.setVisible(true);
            warningLabel.setText("Enter all values");
        }
    }

    private void renderPage() {
        if(currentOrder!=null) {
            orderIdLabel.setText(String.valueOf(currentOrder.getOrderId()));
            orderTypeLabel.setText(currentOrder.getOrderType());
            orderStatusLabel.setText(currentOrder.getOrderStatus());
        }
        else {
            warningLabel.setVisible(true);
            warningLabel.setText("Enter all values");
            orderIdLabel.setText("");
            orderTypeLabel.setText("");
            orderStatusLabel.setText("");
        }
    }

    private void handleCancelClick() {
        if(stage!=null){
            stage.close();
        }
    }
}
