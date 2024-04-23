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
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    /**
     * @return
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * @return
     */
    public boolean isSpecialItem() {
        return isSpecialItem;
    }

    /**
     * @param specialItem
     */
    public void setSpecialItem(boolean specialItem) {
        isSpecialItem = specialItem;
    }

    /**
     * @param itemID
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    /**
     * @return
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * @param made
     */
    public void setMade(boolean made) {
        isActive = made;
    }

    /**
     * @param itemID
     * @param itemName
     * @param price
     * @param isSpecialItem
     * @param isActive
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
     * @return
     */
    public String getDescriptionForMenuList() {
        return "ID: " + itemID + " Item: " + itemName + " - price: " + price;
    }

    /**
     * @return
     */
    public String getDescriptionForList() {
        return "ID: "+itemID+" Item: " + itemName + " - price: "+price;
    }

    /**
     *
     */
    public void addQuantity(){
        this.quantity = quantity+1;
    }

    /**
     * @return
     */
    public boolean isItemIsActive() {
        return itemIsActive;
    }

    /**
     * @param itemIsActive
     */
    public void setItemIsActive(boolean itemIsActive) {
        this.itemIsActive = itemIsActive;
    }
}
