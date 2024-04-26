package Items;

/**
 * Class for Item
 * It is used to create the objects for items
 * @author Saurav
 */
public class Item {
    private int itemID;
    private String itemName;
    private int price;
    private boolean isActive;
    private boolean isSpecialItem;

    private boolean itemIsActive;
    private int quantity;

    /**
     * getter function for quantity
     * @return quantity
     * @author Saurav
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * setter function for quantity
     * @param quantity quantity value
     * @author Saurav
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    /**
     * getter function for item id
     * @return item id
     * @author Saurav
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * getter function for quantity
     * @return special status
     * @author Saurav
     */
    public boolean isSpecialItem() {
        return isSpecialItem;
    }

    /**
     * setter function for special item status
     * @param specialItem special item status
     * @author Saurav
     */
    public void setSpecialItem(boolean specialItem) {
        isSpecialItem = specialItem;
    }

    /**
     * setter function for item id
     * @param itemID item id as int
     * @author Saurav
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    /**
     * getter function for quantity
     * @return item name
     * @author Saurav
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * setter function for item name
     * @param itemName item name as string
     * @author Saurav
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * getter function for quantity
     * @return price
     * @author Saurav
     */
    public int getPrice() {
        return price;
    }

    /**
     * setter function for price
     * @param price value of price as int
     * @author Saurav
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * getter function for quantity
     * @return active status
     * @author Saurav
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * setter function for food made status
     * @param made boolean status
     * @author Saurav
     */
    public void setMade(boolean made) {
        isActive = made;
    }

    /**
     * It is the constructor that is used to create a new instance of the Item
     * @param itemID item id as int
     * @param itemName item name as string
     * @param price item price as int
     * @param isSpecialItem item special status as boolean
     * @param isActive item is active status as boolean
     * @author Saurav
     */
    public Item(int itemID, String itemName, int price,boolean isSpecialItem,boolean isActive) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = price;
        this.isActive = isActive;
        this.quantity = 1;
        this.itemIsActive = true;
        this.isSpecialItem = isSpecialItem;
    }

    /**
     * function for getting description for the menu
     * @return string with description
     * @author Saurav
     */
    public String getDescriptionForMenuList() {
        return "ID: " + itemID + " Item: " + itemName + " - price: " + price;
    }

    /**
     * function for getting description for the list view
     * @return string with description
     * @author Saurav
     */
    public String getDescriptionForList() {
        return "ID: "+itemID+" Item: " + itemName + " - price: "+price;
    }

    /**
     * Setter function for quantity
     * @author Saurav
     */
    public void addQuantity(){
        this.quantity = quantity+1;
    }

    /**
     * getter function for item active status
     * @return item active status
     * @author Saurav
     */
    public boolean isItemIsActive() {
        return itemIsActive;
    }

    /**
     * setter function for quantity
     * @param itemIsActive send item active status
     * @author Saurav
     */
    public void setItemIsActive(boolean itemIsActive) {
        this.itemIsActive = itemIsActive;
    }
}
