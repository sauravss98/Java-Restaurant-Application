package Report;

import Items.Item;
import Items.ItemDataController;
import User.Customer;
import User.Staff;
import User.UserController;

import java.time.LocalDate;
import java.util.ArrayList;

public class Report {
    private int reportId;
    private int mostOrderedItemid;
    private int mostActiveCustomerId;
    private int mostWorkedStaffId;
    private LocalDate createdDate;
    private Item popularItem;
    private Customer popularCustomer;
    private Staff bestStaff;

    public Item getPopularItem() {
        return popularItem;
    }

    public void setPopularItem(int popularItem) {
        this.popularItem = getItem(popularItem);
    }

    private Item getItem(int popularItem){
        for (Item item: ItemDataController.getItems()){
            if(item.getItemID() == popularItem){
                return item;
            }
        }
        return null;
    }

    public Customer getPopularCustomer() {
        return popularCustomer;
    }

    public void setPopularCustomer(int popularCustomer) {
        this.popularCustomer = getCustomerFromId(popularCustomer);
    }

    private Customer getCustomerFromId(int popularCustomer) {
        for(Customer customer: UserController.getCustomers()){
            if(popularCustomer == customer.getUserId()){
                return customer;
            }
        }
        return null;
    }

    public Staff getBestStaff() {
        return bestStaff;
    }

    public void setBestStaff(int bestStaff) {
        this.bestStaff = getStaff(bestStaff);
    }

    public Staff getStaff(int bestStaff){
        for (Staff staff: getAllStaff()){
            if ((staff.getUserId() == bestStaff)){
                return staff;
            }
        }
        return null;
    }

    private static ArrayList<Staff> getAllStaff() {
        ArrayList<Staff> allStaff = new ArrayList<>();
        allStaff.addAll(UserController.getManagers());
        allStaff.addAll(UserController.getWaiters());
        allStaff.addAll(UserController.getChefs());
        allStaff.addAll(UserController.getDrivers());
        return allStaff;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public int getMostOrderedItemid() {
        return mostOrderedItemid;
    }

    public void setMostOrderedItemid(int mostOrderedItemid) {
        this.mostOrderedItemid = mostOrderedItemid;
    }

    public int getMostActiveCustomerId() {
        return mostActiveCustomerId;
    }

    public void setMostActiveCustomerId(int mostActiveCustomerId) {
        this.mostActiveCustomerId = mostActiveCustomerId;
    }

    public int getMostWorkedStaffId() {
        return mostWorkedStaffId;
    }

    public void setMostWorkedStaffId(int mostWorkedStaffId) {
        this.mostWorkedStaffId = mostWorkedStaffId;
    }

    public Report(int reportId,int mostOrderedItemid, int mostActiveCustomerId, int mostWorkedStaffId,LocalDate createdDate) {
        this.reportId = reportId;
        this.mostOrderedItemid = mostOrderedItemid;
        this.mostActiveCustomerId = mostActiveCustomerId;
        this.mostWorkedStaffId = mostWorkedStaffId;
        this.createdDate = createdDate;
        setPopularCustomer(mostActiveCustomerId);
        setPopularItem(mostOrderedItemid);

    }

    @Override
    public String toString() {
        return "Report{" +
                "mostOrderedItemid=" + mostOrderedItemid +
                ", mostActiveCustomerId=" + mostActiveCustomerId +
                ", mostWorkedStaffId=" + mostWorkedStaffId +
                ", createdDate=" + createdDate +
                ", popularItem=" + popularItem +
                ", popularCustomer=" + popularCustomer +
                ", bestStaff=" + bestStaff +
                '}';
    }

    public String getDescriptionForReportList(){
        return "Report{" +
                "Report id" + reportId +
                ", popularItem=" + popularItem +
                ", popularCustomer=" + popularCustomer +
                ", bestStaff=" + bestStaff +
                ", createdDate=" + createdDate+
                '}';
    }

}
