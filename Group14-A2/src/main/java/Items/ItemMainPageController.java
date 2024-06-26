package Items;

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

/**
 * Class to control the item main page
 * @author Saurav
 */
public class ItemMainPageController {
    private final String FXML_PATH = "/cafe94/group14a2/mainItemEditPage.fxml";
    private final String ITEM_CREATE_FXML_PATH = "/cafe94/group14a2/itemCreatePage.fxml";
    private final String EDIT_ITEM_DETAIL = "Edit Item Detail";
    private final String ADD_ITEM_DETAIL = "Add Item";
    private final ArrayList<Item> items = ItemDataController.getItems();
    private static Manager activeManager;
    @FXML private Button addItemButton;
    @FXML private Button backButton;
    @FXML private ListView itemsList;

    /**
     * Constructor to instantiate the class
     * @param manager Manager object is passed
     * @author Saurav
     */
    public ItemMainPageController(Manager manager){
        this.activeManager = manager;
        setActiveManager(manager);
    }

    /**
     * Default constructor for the Class
     * @author Saurav
     */
    public ItemMainPageController(){}

    /**
     * Function to set the active manager in the instance of the class form other classes
     * @param manager manager instance is passed
     * @author Saurav
     */
    public void setActiveManager(Manager manager){
        this.activeManager = manager;
    }

    /**
     * Function to initialize the UI elements in the page
     * @author Saurav
     */
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

    /**
     * Function handle the edit item button. A new window is opened with the details of the item to be edited loaded
     * @param selectedItem The selected item is passed
     * @throws IOException exception called when it cannot find the file
     * @author Saurav
     */
    private void editItem(String selectedItem) throws IOException {
        String id = extractID(selectedItem);
        Item requiredItem = getItemData(id);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH));
        Parent root = loader.load();
        ItemEditPageController controller = loader.getController();
        controller.setCurrentItem(requiredItem);

        Stage itemEditStage = new Stage();
        controller.setStage(itemEditStage);
        itemEditStage.setTitle(EDIT_ITEM_DETAIL);
        itemEditStage.setScene(new Scene(root, 600, 600));
        itemEditStage.initModality(Modality.APPLICATION_MODAL);
        itemEditStage.showAndWait();
        refreshItemList();
    }

    /**
     * Function to get the id as integer from the ui element
     * @param data String data of the selected item details is sent
     * @return it returns the id as string
     * @author Saurav
     */
    public static String extractID(String data) {
        int startIndex = data.indexOf("ID: ") + 4; // Add 4 to skip "ID: "
        int endIndex = data.indexOf(" ", startIndex); // Find the space after the ID value
        if (endIndex == -1) {
            endIndex = data.length(); // If no space is found, consider the end of the string
        }
        return data.substring(startIndex, endIndex);
    }

    /**
     * Function to get the item data
     * @param idString passes the id as string
     * @return returns the item object
     * @author Saurav
     */
    private Item getItemData(String idString) {
        int id = Integer.parseInt(idString);
        for (Item item : items) {
            if (item.getItemID() == id) {
                return item;
            }
        }
        return null;
    }

    /**
     * Function to handle the new item button click
     * It will open a new window where the user can create the new item
     * @author Saurav
     */
    private void handleNewItemMenuClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ITEM_CREATE_FXML_PATH));
            Parent root = loader.load();
            ItemCreatePageController controller = loader.getController();
            Stage orderTypeStage = new Stage();
            orderTypeStage.setTitle(ADD_ITEM_DETAIL);
            orderTypeStage.setScene(new Scene(root, 600, 600));
            controller.setStage(orderTypeStage);
            orderTypeStage.initModality(Modality.APPLICATION_MODAL);
            orderTypeStage.showAndWait();
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * function to handle the back button click.
     * When triggered it will go back to the main manager landing page
     * @author Saurav
     */
    private void handleBackButtonClick() {
        try {
            Main.setRoot("managerMainPage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Function to display all the items in the database(Excel sheets)
     * @author Saurav
     */
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
