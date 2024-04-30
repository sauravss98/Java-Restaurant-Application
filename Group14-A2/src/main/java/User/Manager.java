package User;

/**
 * Class to create instance of manager
 * @author Saurav
 */
public class Manager extends Staff{
    private boolean isManager = true;
    public boolean getIsManager() {
        return isManager;
    }

    /**
     * Constructor to create new instance of manager class
     * @param userId User id as integer is passed
     * @param email Email as string is passed
     * @param firstName First name as string is passed
     * @param lastName Last name as string is passed
     * @param staffID Staff id as int is passed
     * @param hoursWorked Hours worked as int is passed
     * @param totalHours Total hours to worked as int is passed
     * @param isStaff Staff status as boolean is passed
     * @param isManager Manager status as boolean is passed
     * @param userType User type is saved as string is passed
     * @param isLoggedIn Login status is saved as boolean is passed
     * @param isActive User active status is saved as boolean is passed
     */
    public Manager(int userId, String email, String firstName, String lastName, int staffID, int hoursWorked, int totalHours, boolean isStaff, boolean isManager,String userType,boolean isLoggedIn,boolean isActive) {
        super(userId, email, firstName, lastName, staffID, hoursWorked, totalHours, isStaff,userType,isLoggedIn,isActive);
        this.isManager = isManager;
    }
}
