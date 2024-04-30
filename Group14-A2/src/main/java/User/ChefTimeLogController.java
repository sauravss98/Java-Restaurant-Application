package User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

/**
 * Class to handle the chef time log page
 * @author Saurav
 */
public class ChefTimeLogController {
    private static Chef currentChef;
    private Stage stage;
    @FXML
    private Button backButton;
    @FXML private Button confirmButton;
    @FXML private Spinner timeSpinner;
    @FXML private Label warningLabel;
    private static int oldHoursWorked;

    /**
     * Default Constructor
     */
    public ChefTimeLogController(){}

    /**
     * Function to set the current chef
     * @param currentChef The chef object is passed
     */
    public void setCurrentChef(Chef currentChef) {
        this.currentChef = currentChef;
        setOldHoursWorked(currentChef.getHoursWorked());
        initialize();
    }

    /**
     * Function to set the old worked hours to compare the time change when submitting the data
     * @param oldHoursWorked The date is passed
     */
    public void setOldHoursWorked(int oldHoursWorked) {
        this.oldHoursWorked = oldHoursWorked;
    }

    /**
     * Function to set the stage object
     * @param stage The stage object is passed
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Function to initialize the UI component
     */
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

    /**
     * Function to refresh the spinner
     */
    private void refreshSpinner() {
        if (currentChef != null) {
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, currentChef.getHoursWorked());
            timeSpinner.setValueFactory(valueFactory);
        } else {
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 1);
            timeSpinner.setValueFactory(valueFactory);
        }
    }

    /**
     * Function to handle the confirm click and save the data
     */
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

    /**
     * Function handle the back button click
     */
    private void handleBackClick() {
        if(stage!=null){
            stage.close();
        }
    }
}
