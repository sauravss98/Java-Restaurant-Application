package User;

import cafe94.group14a2.Login;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;

public class UserController {
    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static ArrayList<Manager> getManagers() {
        return managers;
    }

    public static ArrayList<Waiter> getWaiters() {
        return waiters;
    }

    public static ArrayList<Driver> getDrivers() {
        return drivers;
    }

    public static ArrayList<Chef> getChefs() {
        return chefs;
    }

    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Manager> managers = new ArrayList<>();
    private static ArrayList<Waiter> waiters = new ArrayList<>();
    private static ArrayList<Driver> drivers = new ArrayList<>();
    private static ArrayList<Chef> chefs = new ArrayList<>();
    private static int userCount = 0;
    public int getUsersCount() {
        return userCount;
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
        int userId = userCount + 1;
        String email = emailField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String address= addressField.getText();
        boolean isCustomer = true;
        String userType = "Customer";
        if(checkEmailValidity()) {
            if(!(email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || address.isEmpty())){
                Customer newCustomer = new Customer(userId, email, firstName, lastName, address, isCustomer,userType);
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
    public static void loadCustomersFromExcel() {
        try (FileInputStream inputStream = new FileInputStream("src/main/java/User/CustomerDate.xlsx")) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    int userId = (int) row.getCell(0).getNumericCellValue();
                    String email = row.getCell(1).getStringCellValue();
                    String firstName = row.getCell(2).getStringCellValue();
                    String lastName = row.getCell(3).getStringCellValue();
                    String address = row.getCell(4).getStringCellValue();
                    String userType = row.getCell(5).getStringCellValue();
                    boolean isCustomer = true;
                    Customer customer = new Customer(userId, email, firstName, lastName, address, isCustomer,userType);
                    userCount++;
                    customers.add(customer);
                }
            }
            System.out.println("Customers loaded from Excel successfully.");
        } catch (IOException e) {
            System.err.println("Error loading customers from Excel: " + e.getMessage());
        }
    }
    public void saveCustomerDataToExcel(Customer customer){
        Workbook workbook;
        Sheet sheet;

        File file = new File("src/main/java/User/CustomerDate.xlsx");
        if (file.exists()) {
            try (FileInputStream inputStream = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(inputStream);
                sheet = workbook.getSheetAt(0);
            } catch (IOException e) {
                System.err.println("Error opening existing Excel file: " + e.getMessage());
                return;
            }
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("User Data");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("User ID");
            headerRow.createCell(1).setCellValue("Email");
            headerRow.createCell(2).setCellValue("First Name");
            headerRow.createCell(3).setCellValue("Last Name");
            headerRow.createCell(4).setCellValue("Address");
            headerRow.createCell(5).setCellValue("User Type");
        }
        int rowNum = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(customer.getUserId());
        row.createCell(1).setCellValue(customer.getEmail());
        row.createCell(2).setCellValue(customer.getFirstName());
        row.createCell(3).setCellValue(customer.getLastName());
        row.createCell(4).setCellValue(customer.getAddress());
        row.createCell(5).setCellValue(customer.getUserType());

        for (int i = 0; i < 6; i++) {
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
            }
        }
    }
}
