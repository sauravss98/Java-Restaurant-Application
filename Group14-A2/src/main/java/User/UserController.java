package User;

import cafe94.group14a2.Login;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;

public class UserController {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Manager> managers = new ArrayList<>();
    private ArrayList<Waiter> waiters = new ArrayList<>();
    private ArrayList<Driver> drivers = new ArrayList<>();
    private ArrayList<Chef> chefs = new ArrayList<>();
    private static int userCount = 0;
    public int getUsersCount() {
        return userCount;
    }

//    public static int getUsersCount(){
//        int customerCount = customers.size();
//        int managersCount = managers.size();
//        int waitersCount = waiters.size();
//        int driversCount = drivers.size();
//        int chefCount = chefs.size();
//        return customerCount + managersCount + waitersCount + driversCount + chefCount;
//    }
    @FXML
    public static void createCustomer(String email, String firstName, String lastName, String address, boolean isCustomer){
        int userId = userCount + 1;
        Customer newCustomer = new Customer(userId, email, firstName, lastName, address, isCustomer);
        customers.add(newCustomer);
        userCount++;
    }
    public boolean checkEmailValidity(){
        boolean emailIsValid =true;
        for(Customer customer:customers) {
            if (Objects.equals(customer.getEmail(), emailField.getText())) {
                emailIsValid = false;
            }
        }
        return  emailIsValid;
    }

    @FXML private TextField emailField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField addressField;
    @FXML private Label errorLabel;

    @FXML
    private void onCreateUserClick() throws IOException {
//        boolean emailIsValid = true;
        int userId = userCount + 1;
        String email = emailField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String address= addressField.getText();
        System.out.println("Entererd email: "+email);
        boolean isCustomer = true;
        if(checkEmailValidity()) {
            if(!(email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || address.isEmpty())){
                Customer newCustomer = new Customer(userId, email, firstName, lastName, address, isCustomer);
                saveCustomerDataToExcel(newCustomer);
                System.out.println(newCustomer);
                if(customers.add(newCustomer)) {
                    userCount++;
                    Login.setRoot("login");
                }
                else{
                    errorLabel.setText("Error..please try again");
                }
            } else {
                errorLabel.setText("Enter all details");
            }
        }
        else {
            errorLabel.setText("Email already exists");
        }
    }
    public void saveCustomerDataToExcel(Customer customer){
        Workbook workbook;
        Sheet sheet;

        File file = new File("src/main/java/User/CustomerDate.xlsx");
        if (file.exists()) {
            // If the file exists, open it and get the existing workbook and sheet
            try (FileInputStream inputStream = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(inputStream);
                sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
            } catch (IOException e) {
                System.err.println("Error opening existing Excel file: " + e.getMessage());
                return;
            }
        } else {
            // If the file doesn't exist, create a new workbook and sheet
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("User Data");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("User ID");
            headerRow.createCell(1).setCellValue("Email");
            headerRow.createCell(2).setCellValue("First Name");
            headerRow.createCell(3).setCellValue("Last Name");
            headerRow.createCell(4).setCellValue("Address");
        }
        // Write new user data to a new row
        int rowNum = sheet.getLastRowNum() + 1; // Get the index for the new row
//        if(customers.size()>0) {
//            Customer customer = customers.getLast(); // Get the last added customer
//        }
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(customer.getUserId());
        row.createCell(1).setCellValue(customer.getEmail());
        row.createCell(2).setCellValue(customer.getFirstName());
        row.createCell(3).setCellValue(customer.getLastName());
        row.createCell(4).setCellValue(customer.getAddress());

        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }
        try (FileOutputStream outputStream = new FileOutputStream("src/main/java/User/CustomerDate.xlsx")) {
            workbook.write(outputStream);
            System.out.println("User data saved to Excel file successfully.");
        } catch (IOException e) {
            System.err.println("Error saving user data to Excel file: " + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                // Handle workbook closing exception
            }
        }
    }
}
