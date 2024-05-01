package Report;

import cafe94.group14a2.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to control the report main page
 * @author Saurav
 */
public class ReportPageController {
    @FXML private Button backButton;
    @FXML private Button generateReportButton;
    @FXML private ListView reportListView;
    private final ArrayList<Report> reports = ReportGenerator.getReports();

    /**
     * Default constructor for the class
     */
    public ReportPageController(){}

    /**
     * Function to initialize the UI components
     */
    public void initialize(){
        refreshList();
        backButton.setOnAction(e->{
            try {
                Main.setRoot("managerMainPage");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        generateReportButton.setOnAction(e->{
            handleGenerateButton();
            refreshList();
        });
    }

    /**
     * Function to refresh the report list view
     */
    private void refreshList() {
        reportListView.getItems().clear();
        for (Report report : reports) {
            reportListView.getItems().add(report.getDescriptionForReportList());
        }
    }

    /**
     * Function to handle the generate button click and it creates a report as an Excel sheet
     */
    private void handleGenerateButton() {
        ReportGenerator.generateReport();
    }
}
