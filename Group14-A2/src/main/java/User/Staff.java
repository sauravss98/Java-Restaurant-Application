package User;

public class Staff extends User{
    private int staffID;
    private int hoursWorked;
    private int totalHours;
    private boolean isStaff = true;

    public Staff(int userId, String email, String password, String firstName, String lastName, int staffID, int hoursWorked, int totalHours, boolean isStaff) {
        super(userId, email, password, firstName, lastName);
        this.staffID = staffID;
        this.hoursWorked = hoursWorked;
        this.totalHours = totalHours;
        this.isStaff = isStaff;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setHoursWorked(Integer hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }
}
