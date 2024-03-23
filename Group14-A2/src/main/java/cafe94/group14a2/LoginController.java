package cafe94.group14a2;

import User.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class LoginController {

    @FXML private TextField EmailTextField;

    public boolean checkValidEmail(String email){
        boolean flag = false;
        ArrayList<Customer> customers = UserController.getCustomers();
        for(Customer customer:customers){
            if(Objects.equals(customer.getEmail(), email)) {
                return true;
            }
        }
        return flag;
    }
    @FXML
    protected void onLoginClick() {
        String email = EmailTextField.getText();
        if(!email.isEmpty()){
            boolean isValid =false;
             isValid = checkValidEmail(email);
            System.out.println(isValid);
        }
    }

    @FXML
    protected void onSignUpClick() throws IOException {
        Login.setRoot("createUser");
    }
}