package User;

public class Staff extends User{
    private int staffID;
    private int hoursWorked;
    private int totalHours;
    private boolean isStaff = true;
    private boolean isActive = true;

    public Staff(int userId, String email, String firstName, String lastName, int staffID, int hoursWorked, int totalHours, boolean isStaff,String userType,boolean isLoggedIn,boolean isActive) {
        super(userId, email, firstName, lastName,userType,isLoggedIn);
        this.staffID = staffID;
        this.hoursWorked = hoursWorked;
        this.totalHours = totalHours;
        this.isStaff = isStaff;
        this.isActive = isActive;
    }

    @Override
    public String getStaffDescription() {
        return super.getStaffDescription() + " Hours Worked: " + hoursWorked+" Total Hours: "+ totalHours;
    }

    public void setIsActive(boolean isActive){
        this.isActive = isActive;
    }

    public boolean getIsActive(){
        return isActive;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
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
