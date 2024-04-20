package Items;

import java.util.ArrayList;

public final class ItemDataController {
    private static ArrayList<Item> items = new ArrayList<>();
    public static ArrayList<Item> getItems() {
        return items;
    }
    public static void addItems(Item item){
        items.add(item);
    }

    public static Item getItemById(int itemId) {
        for (Item item : items) {
            if (item.getItemID() == itemId) {
                return item;
            }
        }
        return null;
    }
}
