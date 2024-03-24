package User;

public class Customer extends User{
    private int customerID;
    private String address;
    private boolean isCustomer = true;
    public Customer(int userId, String email, String firstName, String lastName, String address, boolean isCustomer,String userType,boolean isLoggedIn) {
        super(userId, email, firstName, lastName,userType,isLoggedIn);
        this.customerID = userId;
        this.address = address;
        this.isCustomer = isCustomer;
    }

    public int getCustomerID() {
        return customerID;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", address='" + address + '\'' +
                ", isCustomer=" + isCustomer +
                '}'+super.toString();
    }

    public String getAddress() {
        return address;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
