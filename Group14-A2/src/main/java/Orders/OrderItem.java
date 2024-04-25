package Orders;

import Items.Item;

/**
 * Class to hold data about the items in the order
 * @author Saurav
 */
public class OrderItem {
    private Item item;
    private int quantity;

    /**
     * Constructor to create an order item
     * @param item The item object is passed
     * @param quantity The quantity value is passed
     */
    public OrderItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Function to get the Item object
     * @return Item object
     */
    public Item getItem() {
        return item;
    }

    /**
     * Function to get the quantity value
     * @return Returns the quantity of each item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Function to set the quantity for the item
     * @param quantity The quantity value is passed
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Function to increment the quantity of the item by 1
     */
    public void incrementQuantity() {
        this.quantity++;
    }
}
