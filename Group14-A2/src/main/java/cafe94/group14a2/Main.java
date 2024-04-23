package cafe94.group14a2;

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

/**
 * Main function for the whole application
 * @author Saurav
 */
public class Main extends Application {
    private static Scene scene;

    /**
     * Function to load the fxml page
     * @param fxml sends the fxml file required to load the page as a string
     * @return it returns the loaded fxml file
     * @throws IOException exception called when it cannot find the file
     * @author Saurav
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * It starts the Window
     * @param stage sends the stage to open
     * @throws IOException exception called when it cannot find the file
     * @author Saurav
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 720, 480);
        stage.setTitle("Cafe 94");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the root file in the stage
     * @param fxml sends the fxml file as string
     * @throws IOException exception called when it cannot find the file
     * @author Saurav
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * It is the main method and is the first code run when the app starts
     * @param args arguments called in the main function if any
     * @author Saurav
     */
    public static void main(String[] args) {
        UserController.loadCustomersFromExcel();
        UserController.loadManagersFromExcel();
        UserController.loadChefsFromExcel();
        UserController.loadDriversFromExcel();
        UserController.loadWaitersFromExcel();
        ItemDataController.loadItemsFromExcel();
        OrderDataHandler.loadOrdersFromExcel();
        ReservationDataController.loadOrdersFromExcel();
        launch();
    }
}