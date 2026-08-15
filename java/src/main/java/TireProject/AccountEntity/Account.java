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
    ArrayList<Integer> idItemsShoppingCard = new ArrayList<>();

    private static int idCount = 1;
    /// Getters
    public Account(String name, String password, double balance) {
        this.id = idCount++;
        this.name = name;
        PasswordService passwordService = new PasswordService();
        this.hashedPassword = passwordService.hashPassword(password);
        this.balance = balance;
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
        Account.idCount = idCount;
    }

    /// Users option
    public void addItemToShoppingCard(int id) {
        this.idItemsShoppingCard.add(id);
    }
    public void removeItemFromShoppingCard(int id) {
        this.idItemsShoppingCard.remove(id);
    }

    public double getTotalForCart(ArrayList<Item> items) {
        double total = 0;
//        for(int i = 0; i <idItemsShoppingCard.size(); i++){
//            if(items.containsKey(idItemsShoppingCard.get(i))){
//                arrayOfKeys[count++] = idItemsShoppingCard.get(i);
//            }
//        }
        for(Item item : items){
            total += item.getPrice();
        }
        return total;
    }
    public void seeShoppingCard(HashMap<Integer, Item> items) {
        ArrayList<Item> arrayForItemObjects = new ArrayList<>();
        for (Integer id : idItemsShoppingCard) {
            Item item = items.get(id);
            arrayForItemObjects.add(item);
        }
        double total = getTotalForCart(arrayForItemObjects);

        for (Item item : arrayForItemObjects) {
            System.out.println(item);
        }
        System.out.println("Total for cart: " + total);
    }

    public void setIdCount(Account account) {
        idCount = account.getId();
    }
}
