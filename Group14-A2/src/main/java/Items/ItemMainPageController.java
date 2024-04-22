package Items;

import Orders.OrderController;
import Orders.OrderDataHandler;
import Orders.OrderTypeWindowController;
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
    private Stage stage;
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
            itemsList.getItems().add(item.getDescriptionForMenuList());
        }
    }
}
