package User;

public class Waiter extends Staff{
    public boolean orderTaken = false;

    public Waiter(int userId, String email, String password, String firstName, String lastName, int staffID, int hoursWorked, int totalHours, boolean isStaff, boolean orderTaken) {
        super(userId, email, password, firstName, lastName, staffID, hoursWorked, totalHours, isStaff);
        this.orderTaken = orderTaken;
    }
}
