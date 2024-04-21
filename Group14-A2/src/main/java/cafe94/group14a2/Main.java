package cafe94.group14a2;

import Items.Item;
import Items.ItemDataController;
import Orders.OrderDataHandler;
import Reservation.ReservationDataController;
import User.UserController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    private static Scene scene;
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 720, 480);
        stage.setTitle("Cafe 94");
        stage.setScene(scene);
        stage.show();
    }
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    public static void main(String[] args) {
        Item item1 = new Item(1,"coffee",10);
        ItemDataController.addItems(item1);
        Item item2 = new Item(2,"tea",10);
        ItemDataController.addItems(item2);
        UserController.loadCustomersFromExcel();
        UserController.loadManagersFromExcel();
        OrderDataHandler.loadOrdersFromExcel();
        ReservationDataController.loadOrdersFromExcel();
        launch();
    }
}