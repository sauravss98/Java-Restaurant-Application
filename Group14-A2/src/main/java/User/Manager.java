package User;

public class Manager extends Staff{
    private boolean isManager = true;

    public Manager(int userId, String email, String firstName, String lastName, int staffID, int hoursWorked, int totalHours, boolean isStaff, boolean isManager) {
        super(userId, email, firstName, lastName, staffID, hoursWorked, totalHours, isStaff);
        this.isManager = isManager;
    }
}
