package User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

/**
 * Class to handle the waiter time logger page
 * @author Saurav
 */
public class WaiterTimeLogController {
    private static Waiter currentWaiter;
    private Stage stage;
    @FXML private Button backButton;
    @FXML private Button confirmButton;
    @FXML private Spinner timeSpinner;
    @FXML private Label warningLabel;
    private static int oldHoursWorked;

    /**
     * Default constructor for the class
     */
    public WaiterTimeLogController(){}

    /**
     * Function to set the waiter instance to the class
     * @param currentWaiter The waiter object is passed
     */
    public void setCurrentWaiter(Waiter currentWaiter) {
        this.currentWaiter = currentWaiter;
        setOldHoursWorked(currentWaiter.getHoursWorked());
        initialize();
    }

    /**
     * Function to set the old hours worked value to compare if there is change in the value
     * @param oldHoursWorked
     */
    public void setOldHoursWorked(int oldHoursWorked) {
        this.oldHoursWorked = oldHoursWorked;
    }

    /**
     * Function to set the stage object to the class
     * @param stage The stage object is passed
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Function to initialize the UI components
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
     * Function to refresh the spinner value
     */
    private void refreshSpinner() {
            if (currentWaiter != null) {
                SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, currentWaiter.getHoursWorked());
                timeSpinner.setValueFactory(valueFactory);
            } else {
                SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 1);
                timeSpinner.setValueFactory(valueFactory);
            }
    }

    /**
     * Function to handle the confirm button click and save data
     */
    private void handleConfirmClick() {
        if((int)timeSpinner.getValue()>oldHoursWorked){
            currentWaiter.setHoursWorked((int)timeSpinner.getValue());
            UserController.editWaiterTimeLogExcelData(currentWaiter);
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
