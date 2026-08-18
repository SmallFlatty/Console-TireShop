package TireProject.Services;

import TireProject.Entities.Account;
import TireProject.Entities.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingCardService {
    public double getItemPrice(Item item) {
        double price;
        if(item.getQuantity() > 0){
            price = item.getPrice();
        }else{
            System.out.println("Sorry, but item: " + item.getId() +" is out of stock, you cannot buy this item now");
            price = 0;
        }
        return price;
    }
    public void seeShoppingCard(HashMap<Integer, Item> items, Account account) {
        ArrayList<Item> arrayForItemObjects = getItemArray(items, account);
        double total = 0;
        for(Item item : arrayForItemObjects){
            double itemPrice = getItemPrice(item);
            total += itemPrice;
            if(itemPrice != 0){
                System.out.println(item);
            }
        }

        if(arrayForItemObjects.isEmpty()){
            System.out.println("Your shopping card is empty");
        }else {
            System.out.println("Total for cart: " + total);
        }
    }

    public HashMap<Integer,Item> buyItemsInShoppingCard(HashMap<Integer, Item> items, Account account) {
        ArrayList<Integer> arrayForItemObjects = new ArrayList<>(account.getIdItemsShoppingCard());
        for(Integer id : arrayForItemObjects){
            if(items.containsKey(id)){
                Item item = items.get(id);
                if(item.getQuantity() > 0 && item.getPrice() <= account.getBalance()){
                    item.setQuantity(item.getQuantity() - 1);
                    account.setBalance(account.getBalance() - item.getPrice());
                    account.removeItemFromShoppingCard(id);
                    System.out.println("Item " + id + " bought successfully");
                }else if(item.getQuantity() == 0){
                    System.out.println("Item " + id + " is out of stock, you cannot buy this item now");
                }else if(item.getPrice() >= account.getBalance()){
                    System.out.println("You cant buy this item" + item.getId() + "now, not enough money");
                }
            }
        }
        System.out.println("Your balance is: " + account.getBalance());
        return items;
    }

    private ArrayList<Item> getItemArray(HashMap<Integer, Item> items, Account account){
        ArrayList<Item> arrayForItemObjects = new ArrayList<>();
        for (Integer id : account.getIdItemsShoppingCard()) {
            if(items.get(id) != null) {
                Item item = items.get(id);
                arrayForItemObjects.add(item);
            }
        }
        return arrayForItemObjects;
    }

}
