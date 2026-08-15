package TireProject.ConsoleUI;

import TireProject.ItemEntity.Item;

import java.util.HashMap;

public class UIService {

    public void printItems(HashMap<Integer, Item> items) {
        for(Item item : items.values()) {
            System.out.println(item);
        }
    }
    public void printItemsWithFilter(HashMap<Integer, Item> items, int typeItemId) {
        if(typeItemId == 0) {
            for (Item item : items.values()) {
                if (item.getTypeItemId() == 0) {
                    System.out.println(item);
                }
            }
        }
        if(typeItemId == 1) {
            for (Item item : items.values()) {
                if (item.getTypeItemId() == 1) {
                    System.out.println(item);
                }
            }
        }
    }
}
