package cafe94.group14a2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class LoginController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onLoginClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onSignUpClick() throws IOException {
        Login.setRoot("createUser");
    }
}