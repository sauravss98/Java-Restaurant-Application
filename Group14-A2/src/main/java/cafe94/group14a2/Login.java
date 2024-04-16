package cafe94.group14a2;

import Orders.OrderDataHandler;
import User.UserController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class Login  extends Application {
    private static Scene scene;
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource(fxml + ".fxml"));
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
        UserController.loadCustomersFromExcel();
        launch();
    }
}