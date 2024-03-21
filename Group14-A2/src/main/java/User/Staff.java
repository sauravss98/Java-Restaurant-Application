package User;

public class Staff extends User{
    private int staffID;
    private int hoursWorked;
    private int totalHours;

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
