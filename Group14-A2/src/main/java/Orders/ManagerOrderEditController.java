package Orders;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * Class for controlling the order edit page for the manager
 * @author Saurav
 */
public class ManagerOrderEditController {
    final private String CANCELLED = "Cancelled";
    final private String IN_PROGRESS = "In Progress";
    final private String COMPLETED = "Completed";
    final private String FOOD_PREPARED = "Food Prepared";
    final private String DELIVERED = "Delivered";
    final private String ENTER_ALL_VALUES = "Enter all values";
    @FXML private Label orderIdLabel;
    @FXML private Label orderTypeLabel;
    @FXML private Label orderStatusLabel;
    @FXML private ChoiceBox statusChoiceBox;
    @FXML private Button updateStatusButton;
    @FXML private Button cancelButton;
    @FXML private Label warningLabel;
    private Order currentOrder;
    private Stage stage;
    private final ObservableList<String> options = FXCollections.observableArrayList();

    /**
     * Default constructor for the class
     * @author Saurav
     */
    public ManagerOrderEditController(){}

    /**
     * Function to set the instance of the class with the order
     * @param order passes order from different classes
     * @author Saurav
     */
    public void setOrder(Order order){
        this.currentOrder = order;
        initialize();
    }

    /**
     * Function to set the instance of the class with the stage
     * @param stage passes the stage from different classes
     * @author Saurav
     */
    public void setStage(Stage stage){
        this.stage = stage;
    }

    /**
     * Function to initialize the UI elements of the application
     * @author Saurav
     */
    public void initialize(){
        renderPage();
        warningLabel.setVisible(false);
        statusChoiceBox.setItems(options);
        options.addAll(CANCELLED,IN_PROGRESS,COMPLETED,FOOD_PREPARED,DELIVERED);
        statusChoiceBox.getSelectionModel().select(IN_PROGRESS);
        cancelButton.setOnAction(e->{
            handleCancelClick();
        });
        updateStatusButton.setOnAction(e->{
            handleUpdateButton();
        });
    }

    /**
     * Function to handle the update button click
     * The required data is saved
     * @author Saurav
     */
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
            warningLabel.setText(ENTER_ALL_VALUES);
        }
    }

    /**
     * Function to render the page with data required
     * @author Saurav
     */
    private void renderPage() {
        if(currentOrder!=null) {
            orderIdLabel.setText(String.valueOf(currentOrder.getOrderId()));
            orderTypeLabel.setText(currentOrder.getOrderType());
            orderStatusLabel.setText(currentOrder.getOrderStatus());
        }
        else {
            warningLabel.setVisible(true);
            warningLabel.setText(ENTER_ALL_VALUES);
            orderIdLabel.setText("");
            orderTypeLabel.setText("");
            orderStatusLabel.setText("");
        }
    }

    /**
     * Function to handle the cancel click button
     * It closes the stage when the button is clicked
     * @author Saurav
     */
    private void handleCancelClick() {
        if(stage!=null){
            stage.close();
        }
    }
}
