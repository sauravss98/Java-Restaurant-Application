package User;

public class Driver extends Staff{
    private boolean isDelivered;

    public boolean getIsDelivered() {
        return isDelivered;
    }

    public Driver(int userId, String email, String password, String firstName, String lastName, int staffID, int hoursWorked, int totalHours, boolean isStaff, boolean isDelivered) {
        super(userId, email, password, firstName, lastName, staffID, hoursWorked, totalHours, isStaff);
        this.isDelivered = isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }
}
