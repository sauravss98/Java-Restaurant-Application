package User;

/**
 * Class to create instance of the driver
 * @author Saurav
 */
public class Driver extends Staff{
    private boolean isDriver = true;

    /**
     * Function to get if user is driver
     * @return Boolean value.True if driver
     */
    public boolean getIsDriver() {
        return isDriver;
    }

    public Driver(int userId, String email, String firstName, String lastName, int staffID, int hoursWorked, int totalHours, boolean isStaff, boolean isDriver,String userType,boolean isLoggedIn,boolean isActive) {
        super(userId, email, firstName, lastName, staffID, hoursWorked, totalHours, isStaff,userType,isLoggedIn,isActive);
        this.isDriver = isDriver;
    }
}
