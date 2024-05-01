package User;

/**
 * Class to create insttances of Chef
 * @author Saurav
 */
public class Chef extends Staff{
    private  boolean isChef = true;

    /**
     * Function to check if staff is chef
     * @return Return the chef status
     */
    public boolean getIsChef() {
        return isChef;
    }

    /**
     * Constructor to create new instance of chef class
     * @param userId User id as integer is passed
     * @param email Email as string is passed
     * @param firstName First name as string is passed
     * @param lastName Last name as string is passed
     * @param staffID Staff id as int is passed
     * @param hoursWorked Hours worked as int is passed
     * @param totalHours Total hours to worked as int is passed
     * @param isStaff Is staff status as boolean is passed
     * @param isChef Is chef status as boolean is passed
     * @param userType User type is saved as string is passed
     * @param isLoggedIn Login status is saved as boolean is passed
     * @param isActive User active status is saved as boolean is passed
     */
    public Chef(int userId, String email, String firstName, String lastName, int staffID, int hoursWorked, int totalHours, boolean isStaff,boolean isChef,String userType,boolean isLoggedIn,boolean isActive) {
        super(userId, email, firstName, lastName, staffID, hoursWorked, totalHours, isStaff,userType,isLoggedIn,isActive);
        this.isChef = isChef;
    }
}
