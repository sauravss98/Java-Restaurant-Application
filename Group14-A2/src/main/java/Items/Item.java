package Items;

public class Item {
    private int itemID;
    private String itemName;
    private int price;
    private boolean isActive;
    private boolean isSpecialItem;

    private boolean itemIsActive;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public int getItemID() {
        return itemID;
    }

    public boolean isSpecialItem() {
        return isSpecialItem;
    }

    public void setSpecialItem(boolean specialItem) {
        isSpecialItem = specialItem;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setMade(boolean made) {
        isActive = made;
    }

    public Item(int itemID, String itemName, int price,boolean isSpecialItem,boolean isActive) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = price;
        this.isActive = isActive;
        this.quantity = 1;
        this.itemIsActive = true;
        this.isSpecialItem = isSpecialItem;
    }

    public String getDescriptionForMenuList() {
        return "ID: " + itemID + " Item: " + itemName + " - price: " + price;
    }
    public String getDescriptionForList() {
        return "ID: "+itemID+" Item: " + itemName + " - price: "+price;
    }
    public void addQuantity(){
        this.quantity = quantity+1;
    }

    public boolean isItemIsActive() {
        return itemIsActive;
    }

    public void setItemIsActive(boolean itemIsActive) {
        this.itemIsActive = itemIsActive;
    }
}
