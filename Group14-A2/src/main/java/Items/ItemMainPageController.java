package Items;

import User.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ItemMainPageController {
    private Stage stage;
    private Manager activeManager;
    @FXML private Button addItemButton;
    @FXML private Button backButton;
    @FXML private ListView itemsList;

    public ItemMainPageController(Manager manager){
        this.activeManager = manager;
    }
    public ItemMainPageController(){}

    public void initialize(){

    }
}
