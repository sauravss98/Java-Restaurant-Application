package Items;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.w3c.dom.Text;

public class ItemEditPageController {
    @FXML private Label itemNameText;
    @FXML private Spinner priceSpinner;
    @FXML private Button removeItemButton;
    @FXML private Button confirmItemButton;
    @FXML private Button cancelButton;
    @FXML private ToggleButton specialButton;

    private Item currentItem;
    private Stage stage;

    public ItemEditPageController(){}

     public void setCurrentItem(Item item){
        this.currentItem=item;
        initialize();
     }

    public void setStage(Stage stage){
        this.stage=stage;
    }

    public void initialize(){
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

    private void refreshPriceSpinner() {
        if (currentItem != null) {
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, currentItem.getPrice());
            priceSpinner.setValueFactory(valueFactory);
        } else {
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 1);
            priceSpinner.setValueFactory(valueFactory);
        }
    }

    private void handleCancelButton() {
        if (stage != null) {
            stage.close();
        }
    }

    private void handleConfirmButton() {
        int price = (int) priceSpinner.getValue();
        boolean toggleButtonInput = specialButton.isSelected();
        System.out.println("status "+toggleButtonInput);
        currentItem.setPrice(price);
        currentItem.setSpecialItem(toggleButtonInput);
        ItemDataController.editExcelSheetData(currentItem,"edit");
        if (stage != null) {
            stage.close();
        }
    }

    private void handleRemoveButton() {
        currentItem.setItemIsActive(false);
        ItemDataController.editExcelSheetData(currentItem,"remove");
        if (stage != null) {
            stage.close();
        }
    }

    private void displayItemName() {
        if(currentItem!=null) {
            itemNameText.setText("Item Name : "+currentItem.getItemName());
        } else{
            itemNameText.setText("Empty");
        }
    }
}
