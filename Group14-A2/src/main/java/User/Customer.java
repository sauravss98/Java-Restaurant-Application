package User;

public class Customer extends User{
    private int customerID;
    private String address;
    private boolean isCustomer = true;
    public Customer(int userId, String email, String password, String firstName, String lastName, int customerID, String address, boolean isCustomer) {
        super(userId, email, password, firstName, lastName);
        this.customerID = customerID;
        this.address = address;
        this.isCustomer = isCustomer;
    }

    public int getCustomerID() {
        return customerID;
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
