package User;

/**
 * Class to create insttances of Chef
 * @author Saurav
 */
public class Chef extends Staff{
    private  boolean isChef = true;

    /**
     * Function to check if staff is chef
     * @return
     */
    public boolean getIsChef() {
        return isChef;
    }

    public Chef(int userId, String email, String firstName, String lastName, int staffID, int hoursWorked, int totalHours, boolean isStaff,boolean isChef,String userType,boolean isLoggedIn,boolean isActive) {
        super(userId, email, firstName, lastName, staffID, hoursWorked, totalHours, isStaff,userType,isLoggedIn,isActive);
        this.isChef = isChef;
    }
}
