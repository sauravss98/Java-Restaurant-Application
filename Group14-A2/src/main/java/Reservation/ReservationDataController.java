package Reservation;

import Items.Item;
import Orders.Order;
import User.Customer;
import User.UserController;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class to handle the reservation data
 * @author Saurav
 */
public class ReservationDataController {
    private static ArrayList<Reservation> reservations = new ArrayList<>();
    private static ArrayList<Customer> customers = UserController.getCustomers();
    private static Customer customer;

    /**
     * Function to get all the reservations which is stored in an arraylist
     * @return Returns the reservations as an array list
     */
    public static ArrayList<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Function to add a new reservation to the array list containing all the reservations
     * @param reservation The new reservation object is passed
     */
    public static void addReservation(Reservation reservation){
        reservations.add(reservation);
    }

    /**
     * Function to save the new reservation data to the Excel sheet
     * @param reservation The new reservation object is passed
     */
    public static void saveReservationDataToExcel(Reservation reservation) {
        Workbook workbook;
        Sheet sheet;

        File file = new File("src/main/java/Reservation/ReservationData.xlsx");
        if (!file.exists()) {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Order Data");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Reservation ID");
            headerRow.createCell(1).setCellValue("Number of Guests");
            headerRow.createCell(2).setCellValue("Date Of Reservation");
            headerRow.createCell(3).setCellValue("Customer ID");
            headerRow.createCell(4).setCellValue("Table Type");
            headerRow.createCell(5).setCellValue("Table Count");
            headerRow.createCell(6).setCellValue("Booking Time");
            headerRow.createCell(7).setCellValue("Booking Status");
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
        row.createCell(0).setCellValue(reservation.getReservationId());
        row.createCell(1).setCellValue(reservation.getNumberOfGuests());
        Cell dateCell = row.createCell(2);
        dateCell.setCellValue(reservation.getDateOfReservation());
        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        short dateFormat = createHelper.createDataFormat().getFormat("yyyy-MM-dd");
        dateCellStyle.setDataFormat(dateFormat);
        dateCell.setCellStyle(dateCellStyle);
//        System.out.println("The date is "+reservation.getDateOfReservation());
        row.createCell(3).setCellValue(reservation.getCustomer().getCustomerID());
        row.createCell(4).setCellValue(reservation.getTabletype());
        row.createCell(5).setCellValue(reservation.getNumberOfTables());
        row.createCell(6).setCellValue(reservation.getBookingTime());
        row.createCell(7).setCellValue(reservation.getBookingStatus());

        for (int i = 0; i < 6; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            workbook.write(outputStream);
            System.out.println("Reservation data saved to Excel file successfully.");
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
     * Function to load reservation objects from the excel sheet when loading the app first time
     */
    public static void loadOrdersFromExcel() {
        try (FileInputStream inputStream = new FileInputStream("src/main/java/Reservation/ReservationData.xlsx")) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    int reservationId = (int) row.getCell(0).getNumericCellValue();
                    int numberOfGuests = (int)row.getCell(1).getNumericCellValue();
                    LocalDate dateOfReservation = LocalDate.parse(dataFormatter.formatCellValue(row.getCell(2)));
                    int customerId = (int)row.getCell(3).getNumericCellValue();
                    String tableType = row.getCell(4).getStringCellValue();
                    int tableCount = (int)row.getCell(5).getNumericCellValue();
                    String time = dataFormatter.formatCellValue(row.getCell(6));
                    String status = row.getCell(7).getStringCellValue();

                    for (Customer customerToLoad : customers){
                        if(customerId == customerToLoad.getUserId()){
                            customer = customerToLoad;
                        }
                    }

                    Reservation reservation = new Reservation(reservationId,numberOfGuests, dateOfReservation, customer,tableType,tableCount,time,status);
                    reservations.add(reservation);
                }
            }
            System.out.println("Reservations loaded from Excel successfully.");
        } catch (IOException e) {
            System.err.println("Error loading reservation from Excel: " + e.getMessage());
        }
    }

    public static void editReservationData(Reservation reservation) {
        Workbook workbook;
        try {
            FileInputStream inputStream = new FileInputStream("src/main/java/Reservation/ReservationData.xlsx");
            workbook = new XSSFWorkbook(inputStream);
            inputStream.close();
        } catch (IOException e) {
            workbook = new XSSFWorkbook();
        }

        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            sheet = workbook.createSheet("Order Data");
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                int reservationID = (int) row.getCell(0).getNumericCellValue();
                if (reservationID == reservation.getReservationId()) {
                    row.getCell(7).setCellValue(reservation.getBookingStatus());
                    break;
                }
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream("src/main/java/Reservation/ReservationData.xlsx")) {
            workbook.write(outputStream);
            System.out.println("Reservation data saved to the Excel successfully.");
        } catch (IOException e) {
            System.err.println("Error saving items to Excel: " + e.getMessage());
        }
    }
}
