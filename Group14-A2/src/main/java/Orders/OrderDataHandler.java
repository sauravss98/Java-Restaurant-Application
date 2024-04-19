package Orders;
import java.util.ArrayList;

public class OrderDataHandler {
    private static ArrayList<Order> orders = new ArrayList<>();

    public static ArrayList<Order> getOrders() {
        return orders;
    }

    public static void addOrder(Order order){
        orders.add(order);
    }
}
