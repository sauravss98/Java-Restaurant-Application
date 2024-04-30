package Items;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ChefItemEditController {
    private Item currentItem;
    private Stage stage;
    @FXML private Label itemName;
    @FXML private Label specialItemLabel;
    @FXML private Button backButton;
    @FXML private Button confirmButton;
    @FXML private CheckBox specialCheckBox;

    public  ChefItemEditController(){}

    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
        initialize();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

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

    private void handleConfirmButtonClick() {
        boolean check = specialCheckBox.isSelected();
        currentItem.setSpecialItem(check);
        ItemDataController.editExcelSheetData(currentItem,"special");
        if (stage!=null){
            stage.close();
        }
    }

    private void handleBackClick() {
        if (stage!=null){
            stage.close();
        }
    }

    private void renderItemDetails() {
        itemName.setText("Item Name: "+currentItem.getItemName());
        specialItemLabel.setText("Daily Special: "+currentItem.isSpecialItem());
        if (currentItem.isSpecialItem()) {
            specialCheckBox.setSelected(true);
        }
    }
}
