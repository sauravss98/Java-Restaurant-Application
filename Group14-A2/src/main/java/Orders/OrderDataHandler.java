package Orders;
import Items.Item;
import Items.ItemDataController;
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

/**
 * Class to handle all the order data
 * @author Saurav
 */
public class OrderDataHandler {
    private static ArrayList<Integer> itemlist = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();
    private static ArrayList<Item> allItems = ItemDataController.getItems();
    private static final String FILE_PATH = "src/main/java/Orders/OrderData.xlsx";

    /**
     * Function to return the order list
     * @return returns order list
     */
    public static ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Function to add order to the orders list
     * @param order the order object is passed
     */
    public static void addOrder(Order order){
        orders.add(order);
    }


    /**
     * Function to edit the Excel sheet data
     * This function is used when the chef completes the order
     * @param order The order object is passed
     */
    public static void saveChefExcelChange(Order order) {
        Workbook workbook;
        try {
            FileInputStream inputStream = new FileInputStream(FILE_PATH);
            workbook = new XSSFWorkbook(inputStream);
            inputStream.close();
        } catch (IOException e) {
            // File doesn't exist or is empty, create a new workbook
            workbook = new XSSFWorkbook();
        }

        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            // Workbook is empty, create a new sheet
            sheet = workbook.createSheet("Sheet1");
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                int orderId = (int) row.getCell(0).getNumericCellValue();
                if (orderId == order.getOrderId()) {
                    row.getCell(3).setCellValue(order.isCompleted());
                    row.getCell(4).setCellValue(order.getOrderStatus());
                    row.getCell(7).setCellValue(order.getChefId());
                    break;
                }
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(FILE_PATH)) {
            workbook.write(outputStream);
            System.out.println("Order data saved to the Excel successfully.");
        } catch (IOException e) {
            System.err.println("Error saving items to Excel: " + e.getMessage());
        }
    }

    /**
     * Function to edit the Excel sheet data
     * @param order The order object is passed
     */
    public static void editOrderWaiterCompleteExcelSheetData(Order order) {
        Workbook workbook;
        try {
            FileInputStream inputStream = new FileInputStream(FILE_PATH);
            workbook = new XSSFWorkbook(inputStream);
            inputStream.close();
        } catch (IOException e) {
            // File doesn't exist or is empty, create a new workbook
            workbook = new XSSFWorkbook();
        }

        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            // Workbook is empty, create a new sheet
            sheet = workbook.createSheet("Sheet1");
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                int orderId = (int) row.getCell(0).getNumericCellValue();
                if (orderId == order.getOrderId()) {
                    row.getCell(3).setCellValue(order.isCompleted());
                    row.getCell(4).setCellValue(order.getOrderStatus());
                    row.getCell(6).setCellValue(order.getWaiterId());
                    break;
                }
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(FILE_PATH)) {
            workbook.write(outputStream);
            System.out.println("Order data saved to the Excel successfully.");
        } catch (IOException e) {
            System.err.println("Error saving items to Excel: " + e.getMessage());
        }
    }

    /**
     * Function to edit the Excel sheet data for order
     */
    public static void editOrderExcelSheetData(Order order) {
        Workbook workbook;
        try {
            FileInputStream inputStream = new FileInputStream(FILE_PATH);
            workbook = new XSSFWorkbook(inputStream);
            inputStream.close();
        } catch (IOException e) {
            // File doesn't exist or is empty, create a new workbook
            workbook = new XSSFWorkbook();
        }

        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            // Workbook is empty, create a new sheet
            sheet = workbook.createSheet("Sheet1");
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                int orderId = (int) row.getCell(0).getNumericCellValue();
                if (orderId == order.getOrderId()) {
                    row.getCell(4).setCellValue(order.getOrderStatus());
                    break;
                }
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(FILE_PATH)) {
            workbook.write(outputStream);
            System.out.println("Order data saved to the Excel successfully.");
        } catch (IOException e) {
            System.err.println("Error saving items to Excel: " + e.getMessage());
        }
    }

    /**
     * Function to load order data from the Excel sheet
     */
    public static void loadOrdersFromExcel() {
        try (FileInputStream inputStream = new FileInputStream("src/main/java/Orders/OrderData.xlsx")) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    int orderId = (int) row.getCell(0).getNumericCellValue();
                    String orderType = row.getCell(1).getStringCellValue();
                    String itemsString = row.getCell(2).getStringCellValue();
                    boolean isCompleted = row.getCell(3).getBooleanCellValue();
                    String orderStatus = row.getCell(4).getStringCellValue();
                    int customerId = (int) row.getCell(5).getNumericCellValue();
                    int waiterId = (int) row.getCell(6).getNumericCellValue();
                    int chefId = (int) row.getCell(7).getNumericCellValue();
                    int driverId = (int) row.getCell(8).getNumericCellValue();

                    // parse the string to integer
                    String[] itemsArray = itemsString.substring(1, itemsString.length() - 1).split(",");
                    ArrayList<Integer> items = new ArrayList<>();
                    for (String item : itemsArray) {
                        items.add(Integer.parseInt(item.trim()));
                    }

                    Order order = new Order(orderId, orderType, items, isCompleted, orderStatus, customerId,waiterId,chefId,driverId);
                    for (Integer itemId : items) {
                        boolean itemExists = false;
                        for (OrderItem orderItem : order.getOrderItems()) {
                            if (orderItem.getItem().getItemID() == itemId) {
                                itemExists = true;
                                orderItem.incrementQuantity();
                                break;
                            }
                        }
                        if (!itemExists) {
                            for (Item requiredItem : allItems) {
                                if (itemId == requiredItem.getItemID()) {
                                    order.addItem(requiredItem, 1);
                                    break;
                                }
                            }
                        }
                    }
                    calculatePrice(order);
                    orders.add(order);
                }
            }
            System.out.println("Orders loaded from Excel successfully.");
        } catch (IOException e) {
            System.err.println("Error loading orders from Excel: " + e.getMessage());
        }
    }

    /**
     * Function to calculate the price of items in the order
     * @param order Order object is passed
     */
    private static void calculatePrice(Order order) {
        int price = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            price += orderItem.getItem().getPrice() * orderItem.getQuantity();
        }
        order.setPrice(price);
    }

    /**
     * Function to save new order data into the Excel sheet
     * @param order Order object is passed
     */
    public static void saveOrderDataToExcel(Order order) {
        Workbook workbook;
        Sheet sheet;

        File file = new File("src/main/java/Orders/OrderData.xlsx");
        if (!file.exists()) {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Order Data");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Order ID");
            headerRow.createCell(1).setCellValue("Order Type");
            headerRow.createCell(2).setCellValue("Items");
            headerRow.createCell(3).setCellValue("Order Completed Status");
            headerRow.createCell(4).setCellValue("Order status");
            headerRow.createCell(5).setCellValue("Customer ID");
            headerRow.createCell(6).setCellValue("Waiter Type");
            headerRow.createCell(7).setCellValue("Chef Type");
            headerRow.createCell(8).setCellValue("Driver Type");
        } else {
            try (FileInputStream inputStream = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(inputStream);
                sheet = workbook.getSheetAt(0);
            } catch (IOException e) {
                System.err.println("Error opening existing Excel file: " + e.getMessage());
                return;
            }
        }

        int rowNum = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(order.getOrderId());
        row.createCell(1).setCellValue(order.getOrderType());
        row.createCell(2).setCellValue(String.valueOf(order.getItems()));
        row.createCell(3).setCellValue(order.isCompleted());
        row.createCell(4).setCellValue(order.getOrderStatus());
        row.createCell(5).setCellValue(order.getCustomerId());
        row.createCell(6).setCellValue(order.getWaiterId());
        row.createCell(7).setCellValue(order.getChefId());
        row.createCell(8).setCellValue(order.getDriverId());

        for (int i = 0; i < 6; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            workbook.write(outputStream);
            System.out.println("Order data saved to Excel file successfully.");
        } catch (IOException e) {
            System.err.println("Error saving order data to Excel file: " + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                System.err.println("Error closing workbook: " + e.getMessage());
            }
        }
    }
}
