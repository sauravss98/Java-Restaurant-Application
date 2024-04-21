package User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

public class StaffCreateController {
    @FXML private TextField emailField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private ChoiceBox userTypeField;
    @FXML private Button createUserButton;
    @FXML private Button cancelButton;
    private ObservableList<String> options = FXCollections.observableArrayList();
    private Stage stage;


    public StaffCreateController(){

    }

    public void initialize(){
        userTypeField.setItems(options);
        options.addAll("Chef","Driver","Manager","Waiter");
        createUserButton.setOnAction(e->{
            handleCreateButtonClick();
        });
        cancelButton.setOnAction(e->{
            handleCancelClick();
        });
    }

    private void handleCancelClick() {
        if(stage!=null){
            stage.close();
        }
    }

    private void handleCreateButtonClick() {
        String email = emailField.getText();
        String firstname = firstNameField.getText();
        String lastname = lastNameField.getText();
        String userType = userTypeField.getTypeSelector();
        System.out.println(userType);
        if(Objects.equals(userType, "Waiter")){
            System.out.println("Email: "+email+"Name: "+firstname+" "+lastname+"userType "+userType);
        } else if(Objects.equals(userType, "Manager")){
            System.out.println("Email: "+email+"Name: "+firstname+" "+lastname+"userType "+userType);
        }else if (Objects.equals(userType, "Chef")){
            System.out.println("Email: "+email+"Name: "+firstname+" "+lastname+"userType "+userType);
        } else if(Objects.equals(userType, "Driver")){
            System.out.println("Email: "+email+"Name: "+firstname+" "+lastname+"userType "+userType);
        }
        if(stage!=null){
            stage.close();
        }
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
}
