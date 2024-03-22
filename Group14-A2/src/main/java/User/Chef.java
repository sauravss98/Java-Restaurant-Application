package User;

public class Chef extends Staff{
    private boolean foodIsPrepared = false;

    public boolean getFoodIsPrepared() {
        return foodIsPrepared;
    }

    public Chef(int userId, String email, String firstName, String lastName, int staffID, int hoursWorked, int totalHours, boolean isStaff, boolean foodIsPrepared) {
        super(userId, email, firstName, lastName, staffID, hoursWorked, totalHours, isStaff);
        this.foodIsPrepared = foodIsPrepared;
    }

    public void setFoodIsPrepared(boolean foodIsPrepared) {
        this.foodIsPrepared = foodIsPrepared;
    }
}
