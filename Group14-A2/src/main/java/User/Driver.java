package User;

public class Driver extends Staff{
    private boolean isDriver = true;
    private boolean isDelivered;

    public boolean getIsDriver() {
        return isDriver;
    }
    public boolean getIsDelivered() {
        return isDelivered;
    }

    public Driver(int userId, String email, String firstName, String lastName, int staffID, int hoursWorked, int totalHours, boolean isStaff, boolean isDriver,String userType,boolean isLoggedIn,boolean isActive) {
        super(userId, email, firstName, lastName, staffID, hoursWorked, totalHours, isStaff,userType,isLoggedIn,isActive);
        this.isDriver = isDriver;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }
}
