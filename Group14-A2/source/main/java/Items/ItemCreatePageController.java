package Items;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Class to control the item create page
 * @author Saurav
 */
public class ItemCreatePageController {
    private ArrayList<Item> items = ItemDataController.getItems();
    @FXML private Button cancelButton;
    @FXML private Button addItemButton;
    @FXML private TextField itemNameField;
    @FXML private Spinner priceSpinner;

    private Stage stage;

    /**
     * Default constructor for the class
     * @author Saurav
     */
    public ItemCreatePageController(){}

    /**
     * Function to set the main stage after the ui loads
     * @param stage the instance of stage is sent
     * @author Saurav
     */
    public void setStage(Stage stage){
        this.stage = stage;
    }

    /**
     * function to initialize the ui element when it loads
     * @author Saurav
     */
    public void initialize(){
        cancelButton.setOnAction(e->{
            handleCancelClick();
        });
        addItemButton.setOnAction(e->{
            handleItemCreateButton();
        });
        refreshPriceSpinner();
    }

    /**
     * function to load the price spinner
     * @author Saurav
     */
    private void refreshPriceSpinner() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 1);
        priceSpinner.setValueFactory(valueFactory);
    }

    /**
     * Function to handle the item create button
     * It will take details of new item from the various ui elements used and then submits and saves it
     * @author Saurav
     */
    private void handleItemCreateButton() {
        String itemName = itemNameField.getText();
        int itemPrice = (int)priceSpinner.getValue();
        boolean isSpecialItem = false;
        boolean isActive = true;
        if(!itemName.isEmpty()){
            int itemId = ItemDataController.getItemIDCounter()+1;
            Item item = new Item(itemId,itemName,itemPrice,isSpecialItem,isActive);
            ItemDataController.setItemIDCounter(itemId);
            items.add(item);
            ItemDataController.saveItemDataToExcel(item);
        }

        if(stage!=null){
            stage.close();
        }
    }

    /**
     * Function to handle the cancel click.
     * It will close the window when pressed
     * @author Saurav
     */
    private void handleCancelClick() {
        if(stage!=null){
            stage.close();
        }
    }

}
