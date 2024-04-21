package User;

import cafe94.group14a2.Main;
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

public class  UserController {
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
    private static int staffCount = 0;
    public boolean checkEmailValidity(){
        boolean emailIsValid =true;
        for(Customer customer:customers) {
            if (Objects.equals(customer.getEmail(), emailField.getText())) {
                emailIsValid = false;
            }
        }
        for(Manager manager:managers) {
            if (Objects.equals(manager.getEmail(), emailField.getText())) {
                emailIsValid = false;
            }
        }
        for(Waiter waiter:waiters) {
            if (Objects.equals(waiter.getEmail(), emailField.getText())) {
                emailIsValid = false;
            }
        }
        for(Driver driver:drivers) {
            if (Objects.equals(driver.getEmail(), emailField.getText())) {
                emailIsValid = false;
            }
        }
        for(Chef chef:chefs) {
            if (Objects.equals(chef.getEmail(), emailField.getText())) {
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

    public static void setUserCount(int userCount) {
        UserController.userCount = userCount;
    }

    public static int getUsersCount() {
        return userCount;
    }

    public static int getStaffCount() {
        return staffCount;
    }

    public static void setStaffCount(int staffCount) {
        UserController.staffCount = staffCount;
    }

    @FXML
    private void onCreateUserClick() throws IOException {
        int userId = userCount + 1;
        String email = emailField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String address= addressField.getText();
        boolean isCustomer = true;
        String userType = "Customer";
        boolean isLoggedIn = false;
        if(checkEmailValidity()) {
            if(!(email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || address.isEmpty())){
                Customer newCustomer = new Customer(userId, email, firstName, lastName, address, isCustomer,userType,isLoggedIn);
                saveCustomerDataToExcel(newCustomer);
                System.out.println(newCustomer);
                if(customers.add(newCustomer)) {
                    userCount++;
                    Main.setRoot("login");
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

    public static void loadManagersFromExcel() {
        try (FileInputStream inputStream = new FileInputStream("src/main/java/User/ManagerData.xlsx")) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    int userId = (int) row.getCell(0).getNumericCellValue();
                    String email = row.getCell(1).getStringCellValue();
                    String firstName = row.getCell(2).getStringCellValue();
                    String lastName = row.getCell(3).getStringCellValue();
                    int staffId = (int) row.getCell(4).getNumericCellValue();
                    String userType = row.getCell(5).getStringCellValue();
                    int hoursWorked = (int) row.getCell(6).getNumericCellValue();
                    int totalHours = (int) row.getCell(7).getNumericCellValue();
                    boolean isStaff = true;
                    boolean isManager = true;
                    boolean isLoggedIn = false;
                    Manager manager = new Manager(userId, email, firstName, lastName, staffId, hoursWorked,totalHours,isStaff,isManager,userType,isLoggedIn);
                    userCount++;
                    staffCount++;
                    managers.add(manager);
                }
            }
            System.out.println("Managers loaded from Excel successfully.");
        } catch (IOException e) {
            System.err.println("Error loading managers from Excel: " + e.getMessage());
        }
    }

    public static void loadChefsFromExcel() {
        try (FileInputStream inputStream = new FileInputStream("src/main/java/User/ChefData.xlsx")) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    int userId = (int) row.getCell(0).getNumericCellValue();
                    String email = row.getCell(1).getStringCellValue();
                    String firstName = row.getCell(2).getStringCellValue();
                    String lastName = row.getCell(3).getStringCellValue();
                    int staffId = (int) row.getCell(4).getNumericCellValue();
                    String userType = row.getCell(5).getStringCellValue();
                    int hoursWorked = (int) row.getCell(6).getNumericCellValue();
                    int totalHours = (int) row.getCell(7).getNumericCellValue();
                    boolean isStaff = true;
                    boolean isManager = true;
                    boolean isLoggedIn = false;
                    Chef chef = new Chef(userId, email, firstName, lastName, staffId, hoursWorked,totalHours,isStaff,isManager,userType,isLoggedIn);
                    userCount++;
                    staffCount++;
                    chefs.add(chef);
                }
            }
            System.out.println("Chefs loaded from Excel successfully.");
        } catch (IOException e) {
            System.err.println("Error loading Chefs from Excel: " + e.getMessage());
        }
    }

    public static void loadWaitersFromExcel() {
        try (FileInputStream inputStream = new FileInputStream("src/main/java/User/WaiterData.xlsx")) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    int userId = (int) row.getCell(0).getNumericCellValue();
                    String email = row.getCell(1).getStringCellValue();
                    String firstName = row.getCell(2).getStringCellValue();
                    String lastName = row.getCell(3).getStringCellValue();
                    int staffId = (int) row.getCell(4).getNumericCellValue();
                    String userType = row.getCell(5).getStringCellValue();
                    int hoursWorked = (int) row.getCell(6).getNumericCellValue();
                    int totalHours = (int) row.getCell(7).getNumericCellValue();
                    boolean isStaff = true;
                    boolean isManager = true;
                    boolean isLoggedIn = false;
                    Waiter waiter = new Waiter(userId, email, firstName, lastName, staffId, hoursWorked,totalHours,isStaff,isManager,userType,isLoggedIn);
                    userCount++;
                    staffCount++;
                    waiters.add(waiter);
                }
            }
            System.out.println("Waiters loaded from Excel successfully.");
        } catch (IOException e) {
            System.err.println("Error loading waiters from Excel: " + e.getMessage());
        }
    }

    public static void loadDriversFromExcel() {
        try (FileInputStream inputStream = new FileInputStream("src/main/java/User/DriverData.xlsx")) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    int userId = (int) row.getCell(0).getNumericCellValue();
                    String email = row.getCell(1).getStringCellValue();
                    String firstName = row.getCell(2).getStringCellValue();
                    String lastName = row.getCell(3).getStringCellValue();
                    int staffId = (int) row.getCell(4).getNumericCellValue();
                    String userType = row.getCell(5).getStringCellValue();
                    int hoursWorked = (int) row.getCell(6).getNumericCellValue();
                    int totalHours = (int) row.getCell(7).getNumericCellValue();
                    boolean isStaff = true;
                    boolean isManager = true;
                    boolean isLoggedIn = false;
                    Driver driver = new Driver(userId, email, firstName, lastName, staffId, hoursWorked,totalHours,isStaff,isManager,userType,isLoggedIn);
                    userCount++;
                    staffCount++;
                    drivers.add(driver);
                }
            }
            System.out.println("Drivers loaded from Excel successfully.");
        } catch (IOException e) {
            System.err.println("Error loading Drivers from Excel: " + e.getMessage());
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
                    boolean isLoggedIn = false;
                    Customer customer = new Customer(userId, email, firstName, lastName, address, isCustomer,userType,isLoggedIn);
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

    public static void saveManagerDataToExcel(Manager manager){
        Workbook workbook;
        Sheet sheet;

        File file = new File("src/main/java/User/ManagerData.xlsx");
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
            headerRow.createCell(4).setCellValue("Staff ID");
            headerRow.createCell(5).setCellValue("User Type");
            headerRow.createCell(6).setCellValue("Hours Worked");
            headerRow.createCell(7).setCellValue("Total Hours");
        }
        int rowNum = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(manager.getUserId());
        row.createCell(1).setCellValue(manager.getEmail());
        row.createCell(2).setCellValue(manager.getFirstName());
        row.createCell(3).setCellValue(manager.getLastName());
        row.createCell(4).setCellValue(manager.getStaffID());
        row.createCell(5).setCellValue(manager.getUserType());
        row.createCell(6).setCellValue(manager.getHoursWorked());
        row.createCell(7).setCellValue(manager.getTotalHours());

        for (int i = 0; i < 8; i++) {
            sheet.autoSizeColumn(i);
        }
        try (FileOutputStream outputStream = new FileOutputStream("src/main/java/User/ManagerData.xlsx")) {
            workbook.write(outputStream);
            System.out.println("Manager data saved to Excel file successfully.");
        } catch (IOException e) {
            System.err.println("Error saving user data to Excel file: " + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
            }
        }
    }

    public static void saveChefDataToExcel(Chef chef){
        Workbook workbook;
        Sheet sheet;

        File file = new File("src/main/java/User/ChefData.xlsx");
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
            headerRow.createCell(4).setCellValue("Staff ID");
            headerRow.createCell(5).setCellValue("User Type");
            headerRow.createCell(6).setCellValue("Hours Worked");
            headerRow.createCell(7).setCellValue("Total Hours");
        }
        int rowNum = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(chef.getUserId());
        row.createCell(1).setCellValue(chef.getEmail());
        row.createCell(2).setCellValue(chef.getFirstName());
        row.createCell(3).setCellValue(chef.getLastName());
        row.createCell(4).setCellValue(chef.getStaffID());
        row.createCell(5).setCellValue(chef.getUserType());
        row.createCell(6).setCellValue(chef.getHoursWorked());
        row.createCell(7).setCellValue(chef.getTotalHours());

        for (int i = 0; i < 8; i++) {
            sheet.autoSizeColumn(i);
        }
        try (FileOutputStream outputStream = new FileOutputStream("src/main/java/User/ChefData.xlsx")) {
            workbook.write(outputStream);
            System.out.println("Chef data saved to Excel file successfully.");
        } catch (IOException e) {
            System.err.println("Error saving user data to Excel file: " + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
            }
        }
    }

    public static void saveDriverDataToExcel(Driver driver){
        Workbook workbook;
        Sheet sheet;

        File file = new File("src/main/java/User/DriverData.xlsx");
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
            headerRow.createCell(4).setCellValue("Staff ID");
            headerRow.createCell(5).setCellValue("User Type");
            headerRow.createCell(6).setCellValue("Hours Worked");
            headerRow.createCell(7).setCellValue("Total Hours");
        }
        int rowNum = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(driver.getUserId());
        row.createCell(1).setCellValue(driver.getEmail());
        row.createCell(2).setCellValue(driver.getFirstName());
        row.createCell(3).setCellValue(driver.getLastName());
        row.createCell(4).setCellValue(driver.getStaffID());
        row.createCell(5).setCellValue(driver.getUserType());
        row.createCell(6).setCellValue(driver.getHoursWorked());
        row.createCell(7).setCellValue(driver.getTotalHours());

        for (int i = 0; i < 8; i++) {
            sheet.autoSizeColumn(i);
        }
        try (FileOutputStream outputStream = new FileOutputStream("src/main/java/User/DriverData.xlsx")) {
            workbook.write(outputStream);
            System.out.println("Driver data saved to Excel file successfully.");
        } catch (IOException e) {
            System.err.println("Error saving user data to Excel file: " + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
            }
        }
    }

    public static void saveWaiterDataToExcel(Waiter waiter){
        Workbook workbook;
        Sheet sheet;

        File file = new File("src/main/java/User/WaiterData.xlsx");
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
            headerRow.createCell(4).setCellValue("Staff ID");
            headerRow.createCell(5).setCellValue("User Type");
            headerRow.createCell(6).setCellValue("Hours Worked");
            headerRow.createCell(7).setCellValue("Total Hours");
        }
        int rowNum = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(waiter.getUserId());
        row.createCell(1).setCellValue(waiter.getEmail());
        row.createCell(2).setCellValue(waiter.getFirstName());
        row.createCell(3).setCellValue(waiter.getLastName());
        row.createCell(4).setCellValue(waiter.getStaffID());
        row.createCell(5).setCellValue(waiter.getUserType());
        row.createCell(6).setCellValue(waiter.getHoursWorked());
        row.createCell(7).setCellValue(waiter.getTotalHours());

        for (int i = 0; i < 8; i++) {
            sheet.autoSizeColumn(i);
        }
        try (FileOutputStream outputStream = new FileOutputStream("src/main/java/User/WaiterData.xlsx")) {
            workbook.write(outputStream);
            System.out.println("Waiter data saved to Excel file successfully.");
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
