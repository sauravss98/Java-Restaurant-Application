package Items;

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
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * setter function for quantity
     * @param quantity quantity value
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    /**
     * getter function for item id
     * @return item id
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * getter function for quantity
     * @return special status
     */
    public boolean isSpecialItem() {
        return isSpecialItem;
    }

    /**
     * setter function for special item status
     * @param specialItem special item status
     */
    public void setSpecialItem(boolean specialItem) {
        isSpecialItem = specialItem;
    }

    /**
     * setter function for item id
     * @param itemID item id as int
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    /**
     * getter function for quantity
     * @return item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * setter function for item name
     * @param itemName item name as string
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * getter function for quantity
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * setter function for price
     * @param price value of price as int
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * getter function for quantity
     * @return active status
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * setter function for food made status
     * @param made boolean status
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
     */
    public String getDescriptionForMenuList() {
        return "ID: " + itemID + " Item: " + itemName + " - price: " + price;
    }

    /**
     * function for getting description for the list view
     * @return string with description
     */
    public String getDescriptionForList() {
        return "ID: "+itemID+" Item: " + itemName + " - price: "+price;
    }

    /**
     * setter function for quantity
     */
    public void addQuantity(){
        this.quantity = quantity+1;
    }

    /**
     * getter function for item active status
     * @return item active status
     */
    public boolean isItemIsActive() {
        return itemIsActive;
    }

    /**
     * setter function for quantity
     * @param itemIsActive send item active status
     */
    public void setItemIsActive(boolean itemIsActive) {
        this.itemIsActive = itemIsActive;
    }
}
