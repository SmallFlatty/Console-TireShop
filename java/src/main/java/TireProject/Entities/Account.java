package TireProject.Entities;

import TireProject.Services.PasswordService;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

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

    /// Constructor
    public Account(String name, String password, double balance) {
        this.id = idCount++;
        this.name = name;
        PasswordService passwordService = new PasswordService();
        this.hashedPassword = passwordService.hashPassword(password);
        this.balance = balance;
        this.idItemsShoppingCard = new ArrayList<>();
    }
    /// Getters
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
    ///
    public static void setIdCount(int idCount) {
        Account.idCount = idCount + 1;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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
}
