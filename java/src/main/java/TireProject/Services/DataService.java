package TireProject.Services;


import TireProject.Entities.Account;
import TireProject.Entities.Item;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class DataService {
    private final String itemsURL = "java/src/main/java/TireProject/InformationFiles/Items.dat";
    private final String accountsURL = "java/src/main/java/TireProject/InformationFiles/Accounts.dat";

    public int getMaxIdForItem(HashMap<Integer, Item> items){
        int maxId = 0;
        for(Item item : items.values()) {
            if(item.getId() > maxId) {
                maxId = item.getId();
            }
        }
        return maxId;
    }

    public int getMaxIdForAccount(HashMap<String, Account> accounts){
        int maxId = 0;
        for(Account account : accounts.values()) {
            if(account.getId() > maxId) {
                maxId = account.getId();
            }
        }
        return maxId;
    }

    public void saveAccountsToFile(HashMap<String, Account> accounts) {
        File file = new File(accountsURL);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            for(Account acc : accounts.values()) {
                oos.writeObject(acc);
            }
        }catch(IOException e){
            System.out.println(e.getMessage() + "Some problems to write objects to accounts file");
        }
    }

    public void saveItemToFile(HashMap<Integer, Item> items) {
        File file = new File(itemsURL);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            for(Item item : items.values()) {
                oos.writeObject(item);
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage() + "Some problems to write objects to ");
        }
    }

    public HashMap<Integer, Item> loadItemsFromFile() {
        HashMap<Integer, Item> items = new HashMap<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(itemsURL))){
            while(true){
                Item item = (Item) ois.readObject();
                items.put(item.getId(), item);
            }
        } catch(Exception ignored){}

        int maxId = getMaxIdForItem(items);
        Item.setInexOfId(maxId+1);

        return items;
    }

    public HashMap<String, Account> loadAccountsFromFile() {
        HashMap<String, Account> accounts = new HashMap<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(accountsURL))){
            while(true){
                Account acc = (Account) ois.readObject();
                accounts.put(acc.getName(), acc);
            }
        } catch(Exception ignored){
        }

        int maxId = getMaxIdForAccount(accounts);
        Account.setIdCount(maxId+1);

        return accounts;
    }
}
