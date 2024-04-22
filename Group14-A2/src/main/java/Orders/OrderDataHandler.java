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

public class OrderDataHandler {
    private static ArrayList<Integer> itemlist = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();
    private static ArrayList<Item> allItems = ItemDataController.getItems();

    public static ArrayList<Order> getOrders() {
        return orders;
    }

    public static void addOrder(Order order){
        orders.add(order);
    }

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

                    // parse the string to integer
                    String[] itemsArray = itemsString.substring(1, itemsString.length() - 1).split(",");
                    ArrayList<Integer> items = new ArrayList<>();
                    for (String item : itemsArray) {
                        items.add(Integer.parseInt(item.trim()));
                    }

                    Order order = new Order(orderId, orderType, items, isCompleted, orderStatus, customerId);
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

    private static void calculatePrice(Order order) {
        int price = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            price += orderItem.getItem().getPrice() * orderItem.getQuantity();
        }
        order.setPrice(price);
    }

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
