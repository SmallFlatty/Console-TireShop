package TireProject.AccountEntity;

import TireProject.ItemEntity.Item;
import TireProject.VerifyService.PasswordService;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Account implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /// Properties
    private final int id;
    private String name;
    private String hashedPassword;
    private double balance;
    ArrayList<Integer> idItemsShoppingCard;

    private static int idCount = 1;
    /// Getters
    public Account(String name, String password, double balance) {
        this.id = idCount++;
        this.name = name;
        PasswordService passwordService = new PasswordService();
        this.hashedPassword = passwordService.hashPassword(password);
        this.balance = balance;
        this.idItemsShoppingCard = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Integer> getIdItemsShoppingCard() {
        return idItemsShoppingCard;
    }

    public double getBalance() {
        return balance;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getName() {
        return name;
    }
    /// Setters
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static void setIdCount(int idCount) {
        Account.idCount = idCount + 1;
    }

    /// Users option
    public void addItemToShoppingCard(int id) {
        this.idItemsShoppingCard.add(id);
    }
    public void removeItemFromShoppingCard(int id) {
        if(this.idItemsShoppingCard.contains(id)) {
            this.idItemsShoppingCard.remove(this.idItemsShoppingCard.indexOf(id));
            System.out.println("Item removed successfully");
        }else{
            System.out.println("Item not found in your shopping card");
        }
    }

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
    public void seeShoppingCard(HashMap<Integer, Item> items) {
        ArrayList<Item> arrayForItemObjects = getItemArray(items);
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

    public HashMap<Integer,Item> buyItemsInShoppingCard(HashMap<Integer, Item> items) {
        for(Integer id : idItemsShoppingCard){
            if(items.containsKey(id)){
                Item item = items.get(id);
                if(item.getQuantity() > 0 && item.getPrice() <= balance){
                    item.setQuantity(item.getQuantity() - 1);
                    balance -= item.getPrice();
                    idItemsShoppingCard.remove(id);
                    System.out.println("Item " + id + " bought successfully");
                }else if(item.getQuantity() == 0){
                    System.out.println("Item " + id + " is out of stock, you cannot buy this item now");
                }else if(item.getPrice() >= balance){
                    System.out.println("You cant buy this item" + item.getId() + "now, not enough money");
                }
            }
        }
        System.out.println("Your balance is: " + balance);
        return items;
    }

    private ArrayList<Item> getItemArray(HashMap<Integer, Item> items){
        ArrayList<Item> arrayForItemObjects = new ArrayList<>();
        for (Integer id : idItemsShoppingCard) {
            if(items.get(id) != null) {
                Item item = items.get(id);
                arrayForItemObjects.add(item);
            }
        }
        return arrayForItemObjects;
    }
}
