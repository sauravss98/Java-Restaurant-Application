package User;

/**
 * Class to create new instance of the waiter class
 * @author Saurav
 */
public class Waiter extends Staff{
    private boolean isWaiter = true;

    /**
     * Function to get if user type is waiter
     * @return Boolean value is returned
     */
    public boolean getIsWaiter() {
        return isWaiter;
    }

    /**
     * Constructor to create new instance of waiter class
     * @param userId User id as integer is passed
     * @param email Email as string is passed
     * @param firstName First name as string is passed
     * @param lastName Last name as string is passed
     * @param staffID Staff id as int is passed
     * @param hoursWorked Hours worked as int is passed
     * @param totalHours Total hours to worked as int is passed
     * @param isStaff Staff status as boolean is passed
     * @param isWaiter Waiter status as boolean is passed
     * @param userType User type is saved as string is passed
     * @param isLoggedIn Login status is saved as boolean is passed
     * @param isActive User active status is saved as boolean is passed
     */
    public Waiter(int userId, String email, String firstName, String lastName, int staffID, int hoursWorked, int totalHours, boolean isStaff,boolean isWaiter,String userType,boolean isLoggedIn,boolean isActive) {
        super(userId, email, firstName, lastName, staffID, hoursWorked, totalHours, isStaff,userType,isLoggedIn,isActive);
        this.isWaiter = isWaiter;
    }
}
