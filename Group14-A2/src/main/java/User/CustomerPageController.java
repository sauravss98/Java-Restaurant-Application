package User;

import java.util.ArrayList;
import java.util.Objects;

public final class CustomerPageController {
    private static String activeUserEmail;
    private static ArrayList<Customer> customers = UserController.getCustomers();
    private static Customer activeCustomer;

    public String getActiveUserEmail() {
        return activeUserEmail;
    }

    public static void setActiveUserEmail(String activeUserEmailSent) {
        activeUserEmail = activeUserEmailSent;
        System.out.println("active user is "+activeUserEmail);
        setActiveCustomer(activeUserEmail);
    }
    public static void setActiveCustomer(String email){
        for(Customer customer:customers){
            if(Objects.equals(customer.getEmail(), email)){
                activeCustomer = customer;
                System.out.println("name of the customer "+activeCustomer.getFirstName());
            }
        }
    }

}
