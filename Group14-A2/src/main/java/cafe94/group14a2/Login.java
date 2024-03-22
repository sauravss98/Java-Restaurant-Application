package cafe94.group14a2;

//import User.Customer;
//import User.UserController;
import User.UserController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Login extends Application {
    private static Scene scene;
    private static Parent loadFXML(String fxml) throws IOException {
        System.out.println("inside load "+fxml);
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource(fxml + ".fxml"));
        System.out.println("fxmlloader: "+fxmlLoader);
        return fxmlLoader.load();
    }
//    private void switchToCreateCustomer() throws IOException {
//        Login.setRoot("createUser");
//    }
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 720, 480);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    static void setRoot(String fxml) throws IOException {
        System.out.println(fxml);
        scene.setRoot(loadFXML(fxml));
    }
    public static void main(String[] args) {
        int userID = 0;
        String email = "test@gmail.com";
        String password = "test";
        String firstName = "testuser";
        String lastName = "last";
        String address = "house";
        boolean isCustomer = true;
        UserController.createCustomer(email,password,firstName,lastName,address,isCustomer);
//        Customer user1 = new Customer(userID,email,password,firstName,lastName,address,isCustomer);
        int userID1 = 0;
        String email1 = "test@gmail.com";
        String password1 = "test";
        String firstName1 = "testuser";
        String lastName1 = "last";
        String address1 = "house";
        boolean isCustomer1 = true;
        UserController.createCustomer(email1,password1,firstName1,lastName1,address1,isCustomer1);
//        Customer user2 = new Customer(email1,password1,firstName1,lastName1,address1,isCustomer1);
//        System.out.println(user1.toString());
//        System.out.println(user2.toString());
        launch();
    }
}