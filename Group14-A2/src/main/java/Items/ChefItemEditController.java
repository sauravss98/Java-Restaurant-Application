package Items;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Class to control the chefs item edit page
 */
public class ChefItemEditController {
    private Item currentItem;
    private Stage stage;
    @FXML private Label itemName;
    @FXML private Label specialItemLabel;
    @FXML private Button backButton;
    @FXML private Button confirmButton;
    @FXML private CheckBox specialCheckBox;

    /**
     * Default constructor for the class
     */
    public  ChefItemEditController(){}

    /**
     * Function to set the item object when class is instantiated
     * @param currentItem The Item object is passed
     */
    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
        initialize();
    }

    /**
     * Function to set the stage object when class is instantiated
     * @param stage The stage object is passed
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Function to initialize the UI element
     */
    public void initialize(){
        if(currentItem!=null) {
            renderItemDetails();
        }
        backButton.setOnAction(e->{
            handleBackClick();
        });
        confirmButton.setOnAction(e->{
            handleConfirmButtonClick();
        });
    }

    /**
     * Function to handle the confirm button click
     */
    private void handleConfirmButtonClick() {
        boolean check = specialCheckBox.isSelected();
        currentItem.setSpecialItem(check);
        String SPECIAL = "special";
        ItemDataController.editExcelSheetData(currentItem, SPECIAL);
        if (stage!=null){
            stage.close();
        }
    }

    /**
     * Function to handle the back button click
     */
    private void handleBackClick() {
        if (stage!=null){
            stage.close();
        }
    }

    /**
     * Function to render the item list
     */
    private void renderItemDetails() {
        String ITEM_NAME = "Item Name: ";
        itemName.setText(ITEM_NAME +currentItem.getItemName());
        String DAILY_SPECIAL = "Daily Special: ";
        specialItemLabel.setText(DAILY_SPECIAL +currentItem.isSpecialItem());
        if (currentItem.isSpecialItem()) {
            boolean TRUE = true;
            specialCheckBox.setSelected(TRUE);
        }
    }
}
