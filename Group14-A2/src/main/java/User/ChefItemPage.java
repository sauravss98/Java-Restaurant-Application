package User;

import Items.ChefItemEditController;
import Items.Item;
import Items.ItemDataController;
import Items.ItemEditPageController;
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
import java.util.Objects;

/**
 * Class to handle the chef item page
 * @author Saurav
 */
public class ChefItemPage {
    private Chef activeChef;
    private ArrayList<Chef> chefs = UserController.getChefs();
    private ArrayList<Item> items = ItemDataController.getItems();
    @FXML private ListView itemsList;
    @FXML private Button backbutton;

    /**
     * Default constructor for the class
     */
    public ChefItemPage(){}

    /**
     * Constructor for instantiate the class with details of chef
     * @param activeEmail The email is passed as string
     */
    public ChefItemPage(String activeEmail){
        for(Chef chef:chefs){
            if(Objects.equals(chef.getEmail(), activeEmail)){
                activeChef = chef;
            }
        }
    }

    /**
     * Function to initialize the UI element
     */
    public void initialize(){
        refreshItemList();
        backbutton.setOnAction(e->{
            handleBackButtonClick();
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe94/group14a2/chefItemEditPage.fxml"));
        Parent root = loader.load();
        ChefItemEditController controller = loader.getController();
        controller.setCurrentItem(requiredItem);

        Stage itemEditStage = new Stage();
        controller.setStage(itemEditStage);
        itemEditStage.setTitle("Edit Item Detail");
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
     * Function to handle the back button
     */
    private void handleBackButtonClick() {
        try {
            Main.setRoot("chefMainPage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Function to refresh the item list
     */
    private void refreshItemList() {
        itemsList.getItems().clear();

        for (Item item : items) {
            if(item.isItemIsActive()) {
                itemsList.getItems().add(item.getDescriptionForMenuList());
            }
        }
    }

}
