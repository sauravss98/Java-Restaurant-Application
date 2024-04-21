package Reservation;

import Orders.Order;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ReservationDataController {
    private static ArrayList<Reservation> reservations = new ArrayList<>();

    public static ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public static void addReservation(Reservation reservation){
        reservations.add(reservation);
    }

    public static void saveOrderDataToExcel(Order order){
        Workbook workbook;
        Sheet sheet;

        File file = new File("src/main/java/Reservation/ReservationData.xlsx");
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
            headerRow.createCell(0).setCellValue("Reservation ID");
            headerRow.createCell(1).setCellValue("Number of Guests");
            headerRow.createCell(2).setCellValue("Date Of Reservation");
            headerRow.createCell(3).setCellValue("Customer ID");
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
}
