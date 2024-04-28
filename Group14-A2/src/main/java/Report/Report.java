package Report;

import Items.Item;
import Items.ItemDataController;
import User.Customer;
import User.Staff;
import User.UserController;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class to create objects of report
 * @author Saurav
 */
public class Report {
    private int reportId;
    private int mostOrderedItemid;
    private int mostActiveCustomerId;
    private int mostWorkedStaffId;
    private LocalDate createdDate;
    private Item popularItem;
    private Customer popularCustomer;
    private Staff bestStaff;

    /**
     * Function to get the popular item object
     * @return item
     */
    public Item getPopularItem() {
        return popularItem;
    }

    /**
     * Function to set the popular item
     * @param popularItem The item object is passed
     */
    public void setPopularItem(int popularItem) {
        this.popularItem = getItem(popularItem);
    }

    /**
     * Function to get the item details from item Arraylist
     * @param popularItem The popular object is passed
     * @return Returns the item.
     */
    private Item getItem(int popularItem){
        for (Item item: ItemDataController.getItems()){
            if(item.getItemID() == popularItem){
                return item;
            }
        }
        return null;
    }

    /**
     * Function to get the popular customer
     * @return Returns th emost popular item
     */
    public Customer getPopularCustomer() {
        return popularCustomer;
    }

    /**
     * Function to set the most popular item
     * @param popularCustomer The id of the customer is passed
     */
    public void setPopularCustomer(int popularCustomer) {
        this.popularCustomer = getCustomerFromId(popularCustomer);
    }

    /**
     * Function to get the customer object based on the id
     * @param popularCustomer The customer id is sent
     * @return Returns the customer
     */
    private Customer getCustomerFromId(int popularCustomer) {
        for(Customer customer: UserController.getCustomers()){
            if(popularCustomer == customer.getUserId()){
                return customer;
            }
        }
        return null;
    }

    /**
     * Function to get the best staff
     * @return Returns the staff object
     */
    public Staff getBestStaff() {
        return bestStaff;
    }

    /**
     * Function to get set the best staff data to the instance of the report
     * @param bestStaff The id of the staff is passed
     */
    public void setBestStaff(int bestStaff) {
        this.bestStaff = getStaff(bestStaff);
    }

    /**
     * Function to get the staff data based on the id passed
     * @param bestStaff The id if staff is passed
     * @return The staff object is passed
     */
    public Staff getStaff(int bestStaff){
        for (Staff staff: getAllStaff()){
            if ((staff.getUserId() == bestStaff)){
                return staff;
            }
        }
        return null;
    }

    /**
     * Funtion to set all the different type of staffs to a single Staff type
     * @return Returns array list with all staff data
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
     * Function to get the report id
     * @return The report id is passed
     */
    public int getReportId() {
        return reportId;
    }

    /**
     * Function to set the report id to the instance of object
     * @param reportId The report id is passed
     */
    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    /**
     * Function to get the created date
     * @return The date the report was created is returned
     */
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    /**
     * Function to set the date report is created
     * @param createdDate The date is passed
     */
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Function to return the id of the most ordered item
     * @return Id of item is passed
     */
    public int getMostOrderedItemid() {
        return mostOrderedItemid;
    }

    /**
     * Function to set the id for the most ordered item
     * @param mostOrderedItemid The id of item is passed
     */
    public void setMostOrderedItemid(int mostOrderedItemid) {
        this.mostOrderedItemid = mostOrderedItemid;
    }

    /**
     * Function to get the customer id of most active customer
     * @return Id of most active customer is returned
     */
    public int getMostActiveCustomerId() {
        return mostActiveCustomerId;
    }

    /**
     * Function to set the most active customers id
     * @param mostActiveCustomerId The id of customer is sent
     */
    public void setMostActiveCustomerId(int mostActiveCustomerId) {
        this.mostActiveCustomerId = mostActiveCustomerId;
    }

    /**
     * Function to get the most worked staffs ID
     * @return The id of the staff is returned
     */
    public int getMostWorkedStaffId() {
        return mostWorkedStaffId;
    }

    /**
     * Function to set the staff id
     * @param mostWorkedStaffId The id of staff is passed
     */
    public void setMostWorkedStaffId(int mostWorkedStaffId) {
        this.mostWorkedStaffId = mostWorkedStaffId;
    }

    /**
     * Constructor to create new instance of the report object
     * @param reportId The report id is passed
     * @param mostOrderedItemid The item id is passed
     * @param mostActiveCustomerId The customer id is passed
     * @param mostWorkedStaffId The staff id is passed
     * @param createdDate The date is passed
     */
    public Report(int reportId,int mostOrderedItemid, int mostActiveCustomerId, int mostWorkedStaffId,LocalDate createdDate) {
        this.reportId = reportId;
        this.mostOrderedItemid = mostOrderedItemid;
        this.mostActiveCustomerId = mostActiveCustomerId;
        this.mostWorkedStaffId = mostWorkedStaffId;
        this.createdDate = createdDate;
        setPopularCustomer(mostActiveCustomerId);
        setPopularItem(mostOrderedItemid);
        setBestStaff(mostWorkedStaffId);
    }

    /**
     * The toString for getting all the items in the Report class
     * @return Returns the string with all the report details
     */
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

    /**
     * Modifed vertion of toString for getting all the necessary items in the Report class
     * @return Returns the string with all the report details
     */
    public String getDescriptionForReportList(){
        return "Report id" + reportId +
                ", popularItem: " + popularItem.getItemName() +
                ", popularCustomer: " + popularCustomer.getFullName() +
                ", bestStaff: " + bestStaff.getFullName() +
                ", createdDate: " + createdDate;
    }

}
