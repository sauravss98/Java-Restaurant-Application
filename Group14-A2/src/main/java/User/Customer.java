package User;

/**
 * Class for creating instance of customer
 * @author Saurav
 */
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

    /**
     * Function to get the customer id
     * @return Customer id is returned
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Function to display details of customers where required
     * @return
     */
    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", address='" + address + '\'' +
                ", isCustomer=" + isCustomer +
                '}'+super.toString();
    }

    /**
     * Function to return the address
     * @return Returns the address as string
     */
    public String getAddress() {
        return address;
    }

    /**
     * Function to set the customer id
     * @param customerID Passed the id as integer
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Function to set the address to the object
     * @param address Address is passed as string
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
