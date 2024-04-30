package User;

/**
 * Class to create new instance of Staff
 * @author Saurav
 */
public class Staff extends User{
    private int staffID;
    private int hoursWorked;
    private int totalHours;
    private boolean isStaff = true;
    private boolean isActive = true;


    /**
     * Constructor to create new instance of Staff
     * @param userId User id as integer is passed
     * @param email Email as string is passed
     * @param firstName First name as string is passed
     * @param lastName Last name as string is passed
     * @param staffID Staff id as int is passed
     * @param hoursWorked Hours worked as int is passed
     * @param totalHours Total hours to worked as int is passed
     * @param isStaff Staff status as boolean is passed
     * @param userType User type is saved as string is passed
     * @param isLoggedIn Login status is saved as boolean is passed
     * @param isActive User active status is saved as boolean is passed
     */
    public Staff(int userId, String email, String firstName, String lastName, int staffID, int hoursWorked, int totalHours, boolean isStaff,String userType,boolean isLoggedIn,boolean isActive) {
        super(userId, email, firstName, lastName,userType,isLoggedIn);
        this.staffID = staffID;
        this.hoursWorked = hoursWorked;
        this.totalHours = totalHours;
        this.isStaff = isStaff;
        this.isActive = isActive;
    }

    /**
     * Function to get the description of the staff
     * @return Description is sent as String
     */
    @Override
    public String getStaffDescription() {
        return super.getStaffDescription() + " Hours Worked: " + hoursWorked;
    }

    /**
     * Function to set users active status
     * @param isActive Boolean value of the status is passed
     */
    public void setIsActive(boolean isActive){
        this.isActive = isActive;
    }

    /**
     * Function to return the user active status
     * @return Returns boolean value to show if user is active
     */
    public boolean getIsActive(){
        return isActive;
    }

    /**
     * Function to get the staff id
     * @return Returns staff id
     */
    public int getStaffID() {
        return staffID;
    }

    /**
     * Function to set the staff id
     * @param staffID The staff id is passed
     */
    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    /**
     * Function to get the worked hours
     * @return Returns the worked time
     */
    public int getHoursWorked() {
        return hoursWorked;
    }

    /**
     * Function to get the total hours
     * @return Returns the total hours to work
     */
    public int getTotalHours() {
        return totalHours;
    }

    /**
     * Function to set the worked hours
     * @param hoursWorked The number of hours worked is passed
     */
    public void setHoursWorked(Integer hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    /**
     * Function to set the total hours to work
     * @param totalHours The total hours is passed
     */
    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }
}
