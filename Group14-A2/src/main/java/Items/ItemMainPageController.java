package Items;

import Orders.*;
import User.Manager;
import cafe94.group14a2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ItemMainPageController {
    private ArrayList<Item> items = ItemDataController.getItems();
    private static Manager activeManager;
    @FXML private Button addItemButton;
    @FXML private Button backButton;
    @FXML private ListView itemsList;

    public ItemMainPageController(Manager manager){
        this.activeManager = manager;
        setActiveManager(manager);
    }
    public ItemMainPageController(){}

    public void setActiveManager(Manager manager){
        this.activeManager = manager;
    }

    public void initialize(){
        refreshItemList();
        backButton.setOnAction(e->{
            handleBackButtonClick();
        });
        addItemButton.setOnAction(e->{
            handleNewItemMenuClick();
        });
        itemsList.setOnMouseClicked(e->{
            String selectedItem = (String) itemsList.getSelectionModel().getSelectedItem();
            try {
                editItem(selectedItem);
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        });
    }

    private void editItem(String selectedItem) throws IOException {
        String id = extractID(selectedItem);
        Item requiredItem = getItemData(id);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/mainItemEditPage.fxml"));
        Parent root = loader.load();
        ItemEditPageController controller = loader.getController();
        controller.setCurrentItem(requiredItem);

        Stage itemEditStage = new Stage();
        controller.setStage(itemEditStage);
        itemEditStage.setTitle("Edit Item Detail");
        itemEditStage.setScene(new Scene(root, 600, 600));
        itemEditStage.initModality(Modality.APPLICATION_MODAL);
        itemEditStage.showAndWait();
        refreshItemList();
    }

    public static String extractID(String data) {
        int startIndex = data.indexOf("ID: ") + 4; // Add 4 to skip "ID: "
        int endIndex = data.indexOf(" ", startIndex); // Find the space after the ID value
        if (endIndex == -1) {
            endIndex = data.length(); // If no space is found, consider the end of the string
        }
        return data.substring(startIndex, endIndex);
    }

    private Item getItemData(String idString) {
        int id = Integer.parseInt(idString);
        for (Item item : items) {
            if (item.getItemID() == id) {
                return item;
            }
        }
        return null;
    }

    private void handleNewItemMenuClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/itemCreatePage.fxml"));
            Parent root = loader.load();
            ItemCreatePageController controller = loader.getController();
            Stage orderTypeStage = new Stage();
            orderTypeStage.setTitle("Add Item");
            orderTypeStage.setScene(new Scene(root, 600, 600));
            controller.setStage(orderTypeStage);
            orderTypeStage.initModality(Modality.APPLICATION_MODAL);
            orderTypeStage.showAndWait();
            initialize();
            try {
                Main.setRoot("orderListPage");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleBackButtonClick() {
        try {
            Main.setRoot("managerMainPage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void refreshItemList() {
        // Clear the displayed list
        itemsList.getItems().clear();

        for (Item item : items) {
//            System.out.println("item status "+item.isItemIsActive());
            if(item.isItemIsActive()) {
                itemsList.getItems().add(item.getDescriptionForMenuList());
            }
        }
    }
}
