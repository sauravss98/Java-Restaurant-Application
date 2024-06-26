package Report;

import Orders.Order;
import Orders.OrderDataHandler;
import Orders.OrderItem;
import User.Staff;
import User.UserController;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to generate the report instance and to handle the report data
 * @author Saurav
 */
public class ReportGenerator {
    private static final int reportGenerationCounter = 0;
    private static int reportCounter = 0;
    private static final ArrayList<Report> reports = new ArrayList<>();

    /**
     * Function to get the arrayList of reports
     * @return Returns arraylist of reports
     */
    public static ArrayList<Report> getReports(){
        return reports;
    }

    /**
     * Function to return the count of the number of reports created
     * @return Integer value of count is returned
     */
    public static int getReportIDCounter() {
        return reportCounter;
    }

    /**
     * Function to set the report counter value
     * @param reportCounter The value of the counter is set
     */
    public void setReportCounter(int reportCounter){
        this.reportCounter = reportCounter;
    }

    /**
     * Function to generate the report when button is pressed
     */
    public static void generateReport() {
        ArrayList<Order> orders = OrderDataHandler.getOrders();

        Map<Integer, Integer> itemCounts = countItemsOrdered(orders);

        int mostOrderedItemId = findMostOrderedItem(itemCounts);

        int mostActiveCustomerId = findMostActiveCustomer(orders);

        ArrayList<Staff> allStaff = getAllStaff();

        int mostWorkedStaffId = findMostWorkedStaff(allStaff);

        LocalDate today = LocalDate.now();
        int reportId = getReportIDCounter()+1;
        Report report = new Report(reportId,mostOrderedItemId, mostActiveCustomerId, mostWorkedStaffId, today);
        reports.add(report);
        reportCounter++;
        saveReportDataToExcel(report);
        createNewExcelReport(report);
    }

    /**
     * Function to return all the different type of staff types as an array list of type staff
     * @return Returns all the staffs
     */
    private static ArrayList<Staff> getAllStaff() {
        ArrayList<Staff> allStaff = new ArrayList<>();
        allStaff.addAll(UserController.getManagers());
        allStaff.addAll(UserController.getWaiters());
        allStaff.addAll(UserController.getChefs());
        allStaff.addAll(UserController.getDrivers());
        return allStaff;
    }

    /**
     * Function to find the most worked staff
     * @param allStaff Array list of staff is passed
     * @return returns the id of most worked staff
     */
    private static int findMostWorkedStaff(ArrayList<Staff> allStaff) {
        int mostWorkedStaffId = -1;
        int maxHours = Integer.MIN_VALUE;
        for (Staff staff : allStaff) {
            if (staff.getTotalHours() > maxHours) {
                mostWorkedStaffId = staff.getUserId();
                maxHours = staff.getTotalHours();
            }
        }
        return mostWorkedStaffId;
    }

    /**
     * Function to set the count for al the items from all the orders
     * @param orders The order instance is passed
     * @return Returns the hash map containing the item and count
     */
    private static Map<Integer, Integer> countItemsOrdered(ArrayList<Order> orders) {
        Map<Integer, Integer> itemCounts = new HashMap<>();
        for (Order order : orders) {
            for (OrderItem orderItem : order.getOrderItems()) {
                int itemId = orderItem.getItem().getItemID();
                itemCounts.put(itemId, itemCounts.getOrDefault(itemId, 0) + orderItem.getQuantity());
            }
        }
        return itemCounts;
    }

    /**
     * Function to find the most orderd item from the hashmap
     * @param itemCounts The hashmap which has the item id and count of the id is passed
     * @return Returns the most ordered item
     */
    private static int findMostOrderedItem(Map<Integer, Integer> itemCounts) {
        int mostOrderedItemId = -1;
        int maxCount = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : itemCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostOrderedItemId = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return mostOrderedItemId;
    }

    /**
     * Function to set the count for all the customers from all the orders
     * @param orders The order instance is passed
     * @return Returns the hash map containing the item and count
     */
    private static int findMostActiveCustomer(ArrayList<Order> orders) {
        Map<Integer, Integer> customerOrdersCount = new HashMap<>();
        for (Order order : orders) {
            int customerId = order.getCustomerId();
            if (customerId != 0) {
                customerOrdersCount.put(customerId, customerOrdersCount.getOrDefault(customerId, 0) + 1);
            }
        }
        int mostActiveCustomerId = -1;
        int maxOrders = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : customerOrdersCount.entrySet()) {
            if (entry.getValue() > maxOrders) {
                mostActiveCustomerId = entry.getKey();
                maxOrders = entry.getValue();
            }
        }
        return mostActiveCustomerId;
    }

    /**
     * Function to save the report data to the Excel sheet
     * @param report The report object is passed
     */
    public static void saveReportDataToExcel(Report report) {
        Workbook workbook;
        Sheet sheet;

        File file = new File("src/main/java/Report/ReportData.xlsx");
        if (!file.exists()) {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Report Data");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Report ID");
            headerRow.createCell(1).setCellValue("Popular Item");
            headerRow.createCell(2).setCellValue("Popular Customer");
            headerRow.createCell(3).setCellValue("Popular Staff");
            headerRow.createCell(4).setCellValue("Created Date");
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
        row.createCell(0).setCellValue(report.getReportId());
        row.createCell(1).setCellValue(report.getMostOrderedItemid());
        row.createCell(2).setCellValue(report.getMostActiveCustomerId());
        row.createCell(3).setCellValue(report.getMostWorkedStaffId());
        Cell dateCell = row.createCell(4);
        dateCell.setCellValue(report.getCreatedDate());
        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        short dateFormat = createHelper.createDataFormat().getFormat("yyyy-MM-dd");
        dateCellStyle.setDataFormat(dateFormat);
        dateCell.setCellStyle(dateCellStyle);

        for (int i = 0; i < 6; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            workbook.write(outputStream);
            System.out.println("Report data saved to Excel file successfully.");
        } catch (IOException e) {
            System.err.println("Error saving reservation data to Excel file: " + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                System.err.println("Error closing workbook: " + e.getMessage());
            }
        }
    }

    public static int randomNumberGenerator(){
        int min = 50;
        int max = 100;
        return  (int)Math.floor(Math.random() * (max - min + 1) + min);
    }

    /**
     * Function to create the new Excel sheet when report generate button is pressed
     * @param report The report object is passed
     */
    public static void createNewExcelReport(Report report) {
        String reportName = String.valueOf(report.getCreatedDate())+randomNumberGenerator();
        Workbook workbook;
        Sheet sheet;

        File file = new File("downloads/"+reportName+".xlsx");
        if (!file.exists()) {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Report Data");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Report ID");
            headerRow.createCell(1).setCellValue("Popular Item");
            headerRow.createCell(2).setCellValue("Popular Customer");
            headerRow.createCell(3).setCellValue("Popular Staff");
            headerRow.createCell(4).setCellValue("Created Date");
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
        row.createCell(0).setCellValue(report.getReportId());
        row.createCell(1).setCellValue(report.getPopularCustomer().getFullName());
        row.createCell(2).setCellValue(report.getBestStaff().getFullName());
        row.createCell(3).setCellValue(report.getPopularItem().getItemName());
        Cell dateCell = row.createCell(4);
        dateCell.setCellValue(report.getCreatedDate());
        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        short dateFormat = createHelper.createDataFormat().getFormat("yyyy-MM-dd");
        dateCellStyle.setDataFormat(dateFormat);
        dateCell.setCellStyle(dateCellStyle);

        for (int i = 0; i < 6; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            workbook.write(outputStream);
            System.out.println("Report data saved to Excel file successfully.");
        } catch (IOException e) {
            System.err.println("Error saving reservation data to Excel file: " + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                System.err.println("Error closing workbook: " + e.getMessage());
            }
        }
    }

    /**
     * Function to load the data from the excel sheet to and create the objects
     */
    public static void loadReportExcelData(){
            try (FileInputStream inputStream = new FileInputStream("src/main/java/Report/ReportData.xlsx")) {
                Workbook workbook = new XSSFWorkbook(inputStream);
                Sheet sheet = workbook.getSheetAt(0);
                DataFormatter dataFormatter = new DataFormatter();

                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        int reportId = (int) row.getCell(0).getNumericCellValue();
                        int popularItem = (int) row.getCell(1).getNumericCellValue();
                        int popularCustomer = (int)row.getCell(2).getNumericCellValue();
                        int bestStaff = (int)row.getCell(3).getNumericCellValue();
                        LocalDate createdData = LocalDate.parse(dataFormatter.formatCellValue(row.getCell(4)));
                        Report newReport = new Report(reportId,popularItem,popularCustomer,bestStaff,createdData);
                        reports.add(newReport);
                        reportCounter++;
                    }
                }
                System.out.println("Report loaded from Excel successfully.");
            } catch (IOException e) {
                System.err.println("Error loading items from Excel: " + e.getMessage());
            }
    }
}
