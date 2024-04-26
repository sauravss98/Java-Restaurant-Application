package Report;

import Orders.Order;
import cafe94.group14a2.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class ReportPageController {
    @FXML private Button backButton;
    @FXML private Button generateReportButton;
    @FXML private ListView reportListView;
    private ArrayList<Report> reports = ReportGenerator.getReports();

    public ReportPageController(){}

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

    private void refreshList() {
        reportListView.getItems().clear();
        for (Report report : reports) {
            reportListView.getItems().add(report.getDescriptionForReportList());
        }
    }

    private void handleGenerateButton() {
        ReportGenerator.generateReport();
    }
}
