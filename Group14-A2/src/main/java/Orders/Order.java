package Orders;

import Items.Item;

import java.util.ArrayList;

public class Order {
    private int orderId;
    private String orderType;
    private ArrayList<Item> items = new ArrayList<>();
    private boolean isCompleted = false;
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

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(Item item) {
        items.add(item);
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Order(int orderId, String orderType, ArrayList<Item> items, boolean isCompleted) {
        this.orderId = orderId;
        this.orderType = orderType;
        this.items = items;
        this.isCompleted = isCompleted;
    }
}
