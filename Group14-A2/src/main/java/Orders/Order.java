package Orders;

import Items.Item;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int orderId;
    private String orderType;
    private ArrayList<Integer> items = new ArrayList<>();
    private ArrayList<Item> itemsObjects = new ArrayList<>();
    private int price;

    private String orderStatus;
    private boolean isCompleted = false;
    private int customerId;
    private Date orderDate;
    private Timestamp orderCreatedTime;
    private Timestamp orderCompletedTime;

    private ArrayList<OrderItem> orderItems = new ArrayList<>();

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setItems(ArrayList<Integer> items) {
        this.items = items;
    }

    public ArrayList<Item> getItemsObjects() {
        return itemsObjects;
    }

    public void setItemsObjects(ArrayList<Item> itemsObjects) {
        this.itemsObjects = itemsObjects;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Timestamp getOrderCreatedTime() {
        return orderCreatedTime;
    }

    public void setOrderCreatedTime(Timestamp orderCreatedTime) {
        this.orderCreatedTime = orderCreatedTime;
    }

    public Timestamp getOrderCompletedTime() {
        return orderCompletedTime;
    }

    public void setOrderCompletedTime(Timestamp orderCompletedTime) {
        this.orderCompletedTime = orderCompletedTime;
    }

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public ArrayList<Integer> getItems() {
        return items;
    }

    public void addToItem(Integer item){
        items.add(item);
    }

    public void setItems(int itemId, Item item) {
        items.add(itemId);
        boolean found = false;
        for (Item currentItem : itemsObjects) {
            if (currentItem.getItemID() == itemId) {
                currentItem.addQuantity(); // Increase quantity if the item is already in the order
                found = true;
                break;
            }
        }
        if (!found) {
            itemsObjects.add(item); // Add new item if it's not in the order
        }
    }

    public void addItem(Item item, int quantity) {
        boolean found = false;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getItem().getItemID() == item.getItemID()) {
                orderItem.setQuantity(orderItem.getQuantity() + quantity);
                found = true;
                break;
            }
        }
        if (!found) {
            orderItems.add(new OrderItem(item, quantity));
        }
    }

    public void removeItem(int itemId) {
        orderItems.removeIf(orderItem -> orderItem.getItem().getItemID() == itemId);
        for (int i = items.size() - 1; i >= 0; i--) {
            if (items.get(i) == itemId) {
                items.remove(i);
            }
        }
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }


    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    // Customer direcly orders
    public Order(int orderId, String orderType, ArrayList<Integer> items, boolean isCompleted,String orderStatus, int customerId) {
        this.orderId = orderId;
        this.orderType = orderType;
        this.items = items;
        this.itemsObjects = itemsObjects;
        this.isCompleted = isCompleted;
        this.orderStatus = orderStatus;
        this.customerId = customerId;
    }

//    @Override
    public String toStringtest() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderType='" + orderType + '\'' +
                ", items=" + items +
                ", items=" + itemsObjects +
                ", isCompleted=" + isCompleted +
                '}';
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId).append(", ");
        sb.append("orderType='").append(orderType).append("', ");
        sb.append("items=").append(items).append(", ");
        sb.append("itemsObjects=[");
        for (Item item : itemsObjects) {
            sb.append("{").append(item.getItemName()).append(", Quantity=").append(item.getQuantity()).append("}, ");
        }
        sb.delete(sb.length() - 2, sb.length()); // Remove the trailing ", "
        sb.append("], ");
        sb.append("isCompleted=").append(isCompleted);
        sb.append(" CustomerId = ").append(customerId);
        sb.append("}");
        return sb.toString();
    }

    public String getDescriptionForOrderList() {
        return "ID: "+orderId+" Order Status : " + orderStatus + " - price: "+price;
    }
}
