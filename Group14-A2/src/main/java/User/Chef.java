package User;

public class Chef extends Staff{
    private  boolean isChef = true;
    private boolean foodIsPrepared = false;

    public boolean getIsChef() {
        return isChef;
    }

    public boolean getFoodIsPrepared() {
        return foodIsPrepared;
    }

    public Chef(int userId, String email, String firstName, String lastName, int staffID, int hoursWorked, int totalHours, boolean isStaff,boolean isChef,String userType,boolean isLoggedIn) {
        super(userId, email, firstName, lastName, staffID, hoursWorked, totalHours, isStaff,userType,isLoggedIn);
        this.isChef = isChef;
    }

    public void setFoodIsPrepared(boolean foodIsPrepared) {
        this.foodIsPrepared = foodIsPrepared;
    }
}
