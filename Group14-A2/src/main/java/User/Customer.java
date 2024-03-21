package User;

public class Customer extends User{
    private int customerID;
    private String address;

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
