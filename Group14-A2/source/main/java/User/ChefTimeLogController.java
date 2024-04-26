package User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

public class ChefTimeLogController {
    private static Chef currentChef;
    private Stage stage;
    @FXML
    private Button backButton;
    @FXML private Button confirmButton;
    @FXML private Spinner timeSpinner;
    @FXML private Label warningLabel;
    private static int oldHoursWorked;

    public ChefTimeLogController(){}

    public void setCurrentChef(Chef currentChef) {
        this.currentChef = currentChef;
        setOldHoursWorked(currentChef.getHoursWorked());
        initialize();
    }

    public void setOldHoursWorked(int oldHoursWorked) {
        this.oldHoursWorked = oldHoursWorked;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize(){
        warningLabel.setVisible(false);
        backButton.setOnAction(e->{
            handleBackClick();
        });
        confirmButton.setOnAction(e->{
            handleConfirmClick();
        });
        refreshSpinner();
    }

    private void refreshSpinner() {
        if (currentChef != null) {
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, currentChef.getHoursWorked());
            timeSpinner.setValueFactory(valueFactory);
        } else {
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 1);
            timeSpinner.setValueFactory(valueFactory);
        }
    }

    private void handleConfirmClick() {
        if((int)timeSpinner.getValue()>oldHoursWorked){
            currentChef.setHoursWorked((int)timeSpinner.getValue());
            UserController.editChefTimeLogExcelData(currentChef);
            if(stage!=null){
                stage.close();
            }
        }
        else {
            warningLabel.setVisible(true);
            warningLabel.setText("The entered value cannot be less than the previous value");
        }
    }

    private void handleBackClick() {
        if(stage!=null){
            stage.close();
        }
    }
}
