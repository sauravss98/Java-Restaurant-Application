package Items;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Class for controlling the item edit page
 * @author Saurav
 */
public class ItemEditPageController {
    final private String ITEM_DAILY_SPECIAL = "Item is Daily Special";
    final private String MAKE_ITEM_DAILY_SPECIAL = "Make Item Daily Special";
    final private String EMPTY = "Empty";
    final private String EDIT = "edit";
    final private String REMOVE = "remove";
    final private String ITEM_NAME = "Item Name : ";
    @FXML private Label itemNameText;
    @FXML private Label warningLabel;
    @FXML private Spinner priceSpinner;
    @FXML private Button removeItemButton;
    @FXML private Button confirmItemButton;
    @FXML private Button cancelButton;
    @FXML private ToggleButton specialButton;
    @FXML private TextField itemNameTextBox;

    private Item currentItem;
    private Stage stage;

    /**
     * Constructor: default
     * @author Saurav
     */
    public ItemEditPageController(){}

    /**
     * Function to set the current item in the instance of the class
     * @param item instance of item is sent
     * @author Saurav
     */
     public void setCurrentItem(Item item){
        this.currentItem=item;
        initialize();
     }

    /**
     * Function to set the stage(window) in the current class instance
     * @param stage the stage instance is passed
     * @author Saurav
     */
    public void setStage(Stage stage){
        this.stage=stage;
    }

    /**
     * Function to initialize the UI elements
     * @author Saurav
     */
    public void initialize(){
        warningLabel.setText("");
        warningLabel.setVisible(false);
        displayItemName();
        refreshPriceSpinner();
        removeItemButton.setOnAction(e -> {
            handleRemoveButton();
        });
        confirmItemButton.setOnAction(e -> {
            handleConfirmButton();
        });
        cancelButton.setOnAction(e -> {
            handleCancelButton();
        });
    }

    /**
     * Function to display the price spinner
     * @author Saurav
     */
    private void refreshPriceSpinner() {
        if (currentItem != null) {
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, currentItem.getPrice());
            priceSpinner.setValueFactory(valueFactory);
            specialButton.setSelected(currentItem.isSpecialItem());
        } else {
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 1);
            priceSpinner.setValueFactory(valueFactory);
        }
    }

    /**
     * Function to handle the cancel click and close the window
     * @author Saurav
     */
    private void handleCancelButton() {
        if (stage != null) {
            stage.close();
        }
    }

    /**
     * Function to handle the confirm button and save the data changes made
     * @author Saurav
     */
    private void handleConfirmButton() {
        String itemName = itemNameTextBox.getText();
        int price = (int) priceSpinner.getValue();
        boolean toggleButtonInput = specialButton.isSelected();
        if(!Objects.equals(itemName, "")) {
            currentItem.setItemName(itemName);
        }
        currentItem.setPrice(price);
        currentItem.setSpecialItem(toggleButtonInput);
        ItemDataController.editExcelSheetData(currentItem, EDIT);
        if (stage != null) {
            stage.close();
        }
    }

    /**
     * Function to handle the remove button and remove the item form
     * @author Saurav
     */
    private void handleRemoveButton() {
        currentItem.setItemIsActive(false);
        ItemDataController.editExcelSheetData(currentItem,REMOVE);
        if (stage != null) {
            stage.close();
        }
    }

    /**
     * Function to display the item name ui content
     * @author Saurav
     */
    private void displayItemName() {
        if(currentItem!=null) {
            itemNameText.setText(ITEM_NAME+currentItem.getItemName());
            if (currentItem.isSpecialItem()){
                specialButton.setText(ITEM_DAILY_SPECIAL);
            } else {
                specialButton.setText(MAKE_ITEM_DAILY_SPECIAL);
            }
        } else{
            itemNameText.setText(EMPTY);
        }
    }
}
