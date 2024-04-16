package Items;

public class Item {
    private int itemID;
    private String itemName;
    private int price;
    private boolean isMade;

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
        this.quantity = 1;
    }

    public String getDescriptionForMenuList() {
        return "ID: "+itemID+" Item: " + itemName + " - price: "+price;
    }
    public String getDescriptionForList() {
        return "ID: "+itemID+" Item: " + itemName + " - price: "+price + "- quantity: "+quantity;
    }
    public void addQuantity(){
        this.quantity = quantity+1;
    }
}
