package TireProject.ConsoleUI;

import TireProject.ItemEntity.Item;

import java.io.*;
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
    public void saveItemToFile(HashMap<Integer, Item> items) {
        File file = new File("src/main/java/TireProject/InformationFiles/Items.dat");
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            for(Item item : items.values()) {
                oos.writeObject(item);
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage() + "Some problems to write objects to ");
        }
    }
    public int getMaxId(HashMap<Integer, Item> items){
        int maxId = 0;
        for(Item item : items.values()) {
            if(item.getId() > maxId) {
                maxId = item.getId();
            }
        }
        return maxId;
    }
}
