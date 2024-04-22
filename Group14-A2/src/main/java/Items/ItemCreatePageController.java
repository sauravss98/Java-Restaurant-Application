package Items;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ItemCreatePageController {
    private ArrayList<Item> items = ItemDataController.getItems();
    @FXML private Button cancelButton;
    @FXML private Button addItemButton;
    @FXML private TextField itemNameField;
    @FXML private Spinner priceSpinner;

    private Stage stage;
    public ItemCreatePageController(){}

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void initialize(){
        cancelButton.setOnAction(e->{
            handleCancelClick();
        });
        addItemButton.setOnAction(e->{
            handleItemCreateButton();
        });
        refreshPriceSpinner();
    }

    private void refreshPriceSpinner() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 1);
        priceSpinner.setValueFactory(valueFactory);
    }

    private void handleItemCreateButton() {
        String itemName = itemNameField.getText();
        int itemPrice = (int)priceSpinner.getValue();
        if(!itemName.isEmpty()){
            int itemId = ItemDataController.getItemIDCounter()+1;
            Item item = new Item(itemId,itemName,itemPrice);
            ItemDataController.setItemIDCounter(itemId);
            items.add(item);
            ItemDataController.saveItemDataToExcel(item);
        }

        if(stage!=null){
            stage.close();
        }
    }

    private void handleCancelClick() {
        if(stage!=null){
            stage.close();
        }
    }

}
