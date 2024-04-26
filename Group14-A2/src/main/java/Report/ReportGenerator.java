package Report;

import Orders.Order;
import Orders.OrderDataHandler;
import Orders.OrderItem;
import User.Staff;
import User.UserController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReportGenerator {
    private static int reportCounter = 0;
    private static ArrayList<Report> reports = new ArrayList<>();

    public static ArrayList<Report> getReports(){
        return reports;
    }

    public static int getReportIDCounter() {
        return reportCounter;
    }

    public void setReportCounter(int reportCounter){
        this.reportCounter = reportCounter;
    }

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
        System.out.println("Report "+report.getDescriptionForReportList());
        reports.add(report);
        reportCounter++;
//        return report;
    }

    private static ArrayList<Staff> getAllStaff() {
        ArrayList<Staff> allStaff = new ArrayList<>();
        allStaff.addAll(UserController.getManagers());
        allStaff.addAll(UserController.getWaiters());
        allStaff.addAll(UserController.getChefs());
        allStaff.addAll(UserController.getDrivers());
        return allStaff;
    }

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

    private static int findMostActiveCustomer(ArrayList<Order> orders) {
        Map<Integer, Integer> customerOrdersCount = new HashMap<>();
        for (Order order : orders) {
            int customerId = order.getCustomerId();
            customerOrdersCount.put(customerId, customerOrdersCount.getOrDefault(customerId, 0) + 1);
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
}
