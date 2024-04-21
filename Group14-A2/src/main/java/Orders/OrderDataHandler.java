package Orders;
import User.Customer;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class OrderDataHandler {
    private static ArrayList<Order> orders = new ArrayList<>();

    public static ArrayList<Order> getOrders() {
        return orders;
    }

    public static void addOrder(Order order){
        orders.add(order);
    }

//    public static void loadCustomersFromExcel() {
//        try (FileInputStream inputStream = new FileInputStream("src/main/java/User/CustomerDate.xlsx")) {
//            Workbook workbook = new XSSFWorkbook(inputStream);
//            Sheet sheet = workbook.getSheetAt(0);
//
//            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//                Row row = sheet.getRow(i);
//                if (row != null) {
//                    int userId = (int) row.getCell(0).getNumericCellValue();
//                    String email = row.getCell(1).getStringCellValue();
//                    String firstName = row.getCell(2).getStringCellValue();
//                    String lastName = row.getCell(3).getStringCellValue();
//                    String address = row.getCell(4).getStringCellValue();
//                    String userType = row.getCell(5).getStringCellValue();
//                    boolean isCustomer = true;
//                    boolean isLoggedIn = false;
//                    Customer customer = new Customer(userId, email, firstName, lastName, address, isCustomer,userType,isLoggedIn);
//                    userCount++;
//                    customers.add(customer);
//                }
//            }
//            System.out.println("Customers loaded from Excel successfully.");
//        } catch (IOException e) {
//            System.err.println("Error loading customers from Excel: " + e.getMessage());
//        }
//    }

    public static void saveOrderDataToExcel(Order order){
        Workbook workbook;
        Sheet sheet;

        File file = new File("src/main/java/Orders/OrderData.xlsx");
        if (!file.exists()) {
            try {
                FileInputStream inputStream = new FileInputStream(file);
                workbook = new XSSFWorkbook(inputStream);
                sheet = workbook.getSheetAt(0);
            } catch (IOException e) {
                System.err.println("Error opening existing Excel file: " + e.getMessage());
                return;
            }
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Order Data");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Order ID");
            headerRow.createCell(1).setCellValue("Order Type");
            headerRow.createCell(2).setCellValue("Items");
            headerRow.createCell(3).setCellValue("Order Completed Status");
            headerRow.createCell(4).setCellValue("Order status");
            headerRow.createCell(5).setCellValue("Customer ID");
        }
        int rowNum = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(order.getOrderId());
        row.createCell(1).setCellValue(order.getOrderType());
        row.createCell(2).setCellValue(String.valueOf(order.getItems()));
        row.createCell(3).setCellValue(order.isCompleted());
        row.createCell(4).setCellValue(order.getOrderStatus());
        row.createCell(5).setCellValue(order.getCustomerId());

        for (int i = 0; i < 6; i++) {
            sheet.autoSizeColumn(i);
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            System.out.println("User data saved to Excel file successfully.");
        } catch (IOException e) {
            System.err.println("Error saving user data to Excel file: " + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                System.err.println("Error closing workbook: " + e.getMessage());
            }
        }
    }

//    public static void saveOrderDataToExcel(Order order){
//        Workbook workbook;
//        Sheet sheet;
//
//        File file = new File("src/main/java/Orders/OrderData.xlsx");
//        if (!file.exists()) {
//            try (FileInputStream inputStream = new FileInputStream(file)) {
//                workbook = new XSSFWorkbook(inputStream);
//                sheet = workbook.getSheetAt(0);
//            } catch (IOException e) {
//                System.err.println("Error opening existing Excel file: " + e.getMessage());
//                return;
//            }
//        } else {
//            workbook = new XSSFWorkbook();
//            sheet = workbook.createSheet("Order Data");
//            Row headerRow = sheet.createRow(0);
//            headerRow.createCell(0).setCellValue("Order ID");
//            headerRow.createCell(1).setCellValue("Order Type");
//            headerRow.createCell(2).setCellValue("Items");
//            headerRow.createCell(3).setCellValue("Order Completed Status");
//            headerRow.createCell(4).setCellValue("Order status");
//            headerRow.createCell(5).setCellValue("Customer ID");
//        }
//        int rowNum = sheet.getLastRowNum() + 1;
//        Row row = sheet.createRow(rowNum);
//        row.createCell(0).setCellValue(order.getOrderId());
//        row.createCell(1).setCellValue(order.getOrderType());
//        row.createCell(2).setCellValue(String.valueOf(order.getItems()));
//        row.createCell(3).setCellValue(order.isCompleted());
//        row.createCell(4).setCellValue(order.getOrderStatus());
//        row.createCell(5).setCellValue(order.getCustomerId());
//
//        for (int i = 0; i < 6; i++) {
//            sheet.autoSizeColumn(i);
//        }
//        try (FileOutputStream outputStream = new FileOutputStream("src/main/java/Orders/OrderData.xlsx")) {
//            workbook.write(outputStream);
//            System.out.println("User data saved to Excel file successfully.");
//        } catch (IOException e) {
//            System.err.println("Error saving user data to Excel file: " + e.getMessage());
//        } finally {
//            try {
//                workbook.close();
//            } catch (IOException e) {
//            }
//        }
//    }
}
