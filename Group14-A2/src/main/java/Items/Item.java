package Items;

public class Item {
    private int itemID;
    private String itemName;
    private int price;
    private boolean isMade;

    public int getItemID() {
        return itemID;
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

    public boolean isMade() {
        return isMade;
    }

    public void setMade(boolean made) {
        isMade = made;
    }

    public Item(int itemID, String itemName, int price) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = price;
        this.isMade = false;
    }

    public String getDescriptionForList() {
        return "ID: "+itemID+" Item: " + itemName + " - price: "+price;
    }
}
