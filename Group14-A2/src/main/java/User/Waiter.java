package User;

public class Waiter extends Staff{
    private boolean orderTaken = false;
    private boolean isWaiter = true;

    public boolean getIsWaiter() {
        return isWaiter;
    }

    public boolean getIsOrderTaken() {
        return orderTaken;
    }

    public void setOrderTaken(boolean orderTaken) {
        this.orderTaken = orderTaken;
    }

    public Waiter(int userId, String email, String firstName, String lastName, int staffID, int hoursWorked, int totalHours, boolean isStaff, boolean orderTaken,boolean isWaiter,String userType,boolean isLoggedIn) {
        super(userId, email, firstName, lastName, staffID, hoursWorked, totalHours, isStaff,userType,isLoggedIn);
        this.orderTaken = orderTaken;
        this.isWaiter = isWaiter;
    }
}
