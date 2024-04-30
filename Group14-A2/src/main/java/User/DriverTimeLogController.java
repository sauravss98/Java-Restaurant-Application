package User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

/**
 * Class to control the driver time log page
 * @author Saurav
 */
public class DriverTimeLogController {
    private static Driver currentDriver;
    private Stage stage;
    @FXML private Button backButton;
    @FXML private Button confirmButton;
    @FXML private Spinner timeSpinner;
    @FXML private Label warningLabel;
    private static int oldHoursWorked;

    /**
     * Default Constructor for the class
     */
    public DriverTimeLogController(){}

    /**
     * Function to set the current driver
     * @param currentDriver Driver object is passed
     */
    public void setCurrentDriver(Driver currentDriver) {
        this.currentDriver = currentDriver;
        setOldHoursWorked(currentDriver.getHoursWorked());
        initialize();
    }

    /**
     * Function to set the old hours worked to compare the difference
     * @param oldHoursWorked Time is passed as integer
     */
    public void setOldHoursWorked(int oldHoursWorked) {
        this.oldHoursWorked = oldHoursWorked;
    }

    /**
     * Function to set stage to the instance
     * @param stage The stage is passed
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Function to initialize the UI components when fxml is loaded
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
        if (currentDriver != null) {
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, currentDriver.getHoursWorked());
            timeSpinner.setValueFactory(valueFactory);
        } else {
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 1);
            timeSpinner.setValueFactory(valueFactory);
        }
    }

    /**
     * Function to handle the confirm button click and save the data
     */
    private void handleConfirmClick() {
        if((int)timeSpinner.getValue()>oldHoursWorked){
            currentDriver.setHoursWorked((int)timeSpinner.getValue());
            UserController.editDriverTimeLogExcelData(currentDriver);
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
     * Function to handle the back button click
     */
    private void handleBackClick() {
        if(stage!=null){
            stage.close();
        }
    }
}
