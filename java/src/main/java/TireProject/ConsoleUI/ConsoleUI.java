package TireProject.ConsoleUI;

import TireProject.AccountEntity.Account;
import TireProject.ItemEntity.Item;
import TireProject.ItemEntity.Tire;
import TireProject.ItemEntity.Wheel;
import TireProject.VerifyService.PasswordService;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleUI implements MenuOptions {
    HashMap<String, Account> accounts = new HashMap<>();
    HashMap<Integer, Item> items = new HashMap<>();
    @Override
    public void showMenuForUser(Account account) {

    }

    @Override
    public void showMenuForAdmin(Account account) {
        File file = new File("src/main/java/TireProject/InformationFiles/Items.dat");
        int maxId = 0;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            while(true){
                Item item = (Item)ois.readObject();
                int itemId = item.getId();
                items.put(itemId, item);
                if(maxId < itemId){
                    maxId = itemId;
                }
            }
        }catch(IOException e){
            System.out.println("Error in opening file");
        }catch(Exception e){
            System.out.println(e.getMessage() + "Something went wrong with reading file");
        }
        Item.setInexOfId(maxId + 1);
        System.out.println("Admin panel was loaded");
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Enter your choice");
            System.out.println("1 - Manage exists items");
            System.out.println("2 - Add new item");
            System.out.println("3 - Delete item");
            System.out.println("4 - See all items");
            System.out.println("5 - Exit");

            String operation = sc.nextLine();
            switch (operation) {
                case "1":

                    break;
                case "2":
                    System.out.println("Which type of item would you like to create: Tire / Wheel");
                    String itemType = sc.nextLine();

                    /// Item properties
                    String name;
                    String description;
                    double price;
                    int quantity;

                    if(!itemType.equals("Tire") && !itemType.equals("Wheel")){
                        System.out.println("Invalid input");
                    }else if(itemType.equals("Tire")){
                        /// Tire properties
                        String tireSize;
                        String seasonType;
                        char speedRating;

                        while(true){
                            System.out.println("Enter name");

                            name = sc.nextLine();
                            System.out.println("Enter description");
                            description = sc.nextLine();

                            System.out.println("Enter tire size");
                            tireSize = sc.nextLine();

                            System.out.println("Enter season type");
                            seasonType = sc.nextLine();

                            System.out.println("Enter speed rating");
                            speedRating = sc.nextLine().charAt(0);


                            System.out.println("Enter price");
                            try{
                                price = Double.parseDouble(sc.nextLine());
                            }catch(NumberFormatException | NullPointerException e){
                                price = 0;
                            }
                            System.out.println("Enter quantity");
                            try{
                                quantity = Integer.parseInt(sc.nextLine());
                            }catch(NumberFormatException | NullPointerException e){
                                quantity = 0;
                            }

                            if(quantity == 0 || price == 0){
                                Tire tire = new Tire(name, description, tireSize, seasonType, speedRating);
                                int tireId = tire.getId();
                                items.put(tireId, tire);
                                break;
                            }else{
                                Tire tire = new Tire(name, description, price , quantity, tireSize, speedRating, seasonType);
                                int tireId = tire.getId();
                                items.put(tireId, tire);
                                break;
                            }
                        }
                    }else if(itemType.equals("Wheel")){
                        /// Wheel Properties
                        String diameter;
                        String width;
                        String boltPattern;

                        while(true){
                            System.out.println("Enter name");
                            name = sc.nextLine();

                            System.out.println("Enter description");
                            description = sc.nextLine();

                            System.out.println("Enter diameter");
                            diameter = sc.nextLine();

                            System.out.println("Enter width");
                            width = sc.nextLine();

                            System.out.println("Enter bolt pattern");
                            boltPattern = sc.nextLine();

                            System.out.println("Enter price");
                            try{
                                price = Double.parseDouble(sc.nextLine());
                            }catch(NumberFormatException | NullPointerException e){
                                price = 0;
                            }
                            System.out.println("Enter quantity");
                            try{
                                quantity = Integer.parseInt(sc.nextLine());
                            }catch(NumberFormatException | NullPointerException e){
                                quantity = 0;
                            }
                            if(quantity == 0 || price == 0){
                                Wheel wheel = new Wheel(name, description, diameter, width, boltPattern);
                                int wheelId = wheel.getId();
                                items.put(wheelId, wheel);
                                break;
                            }else{
                                Wheel wheel = new Wheel(name, description, price , quantity, diameter, width, boltPattern);
                                int wheelId = wheel.getId();
                                items.put(wheelId, wheel);
                                break;
                            }
                        }
                    }
                    break;
                case "3":
                    for(Item i : items.values()){
                        System.out.println(i.toStringSmall());
                    }
                    System.out.println("Type items id, you need to delete");
                    String answer = sc.nextLine();
                    int id = 0;
                    try{
                        id = Integer.parseInt(answer);
                    }catch(NumberFormatException e){
                        System.out.println("Invalid input");
                    }
                    try{
                        items.remove(id);
                    }catch(Exception e){
                        System.out.println("Item with id " + id + " not found");
                    }
                    try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
                        for(Item i : items.values()){
                            oos.writeObject(i);
                        }
                        Item.setInexOfId(id-1);
                    }catch(IOException e){
                        System.out.println("Error in opening file");
                    }catch(Exception e){
                        System.out.println(e.getMessage() + "Something went wrong with reading file");
                    }
                    System.out.println("Item was successfully deleted");
                    break;
                case "4":
                    for(Item item : items.values()){
                        System.out.println(item.toString());
                    }
                    break;
                case "5":
                    try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
                        for(Item i : items.values()){
                            oos.writeObject(i);
                        }
                    }catch(IOException e){
                        System.out.println("Error in opening file");
                    }catch(Exception e){
                        System.out.println(e.getMessage() + "Something went wrong with writing to file");
                    }
                    userVerifyMenu();
                    break;
                default:
                    System.out.println("Invalid choice, try again");
            }

        }
    }

    @Override
    public void userVerifyMenu() {
        Scanner sc = new Scanner(System.in);
        PasswordService passwordService = new PasswordService();
        File file = new File("src/main/java/TireProject/InformationFiles/Accounts.dat");
        /// Variable for maxId
        int maxId = 0;

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    Account account = (Account) ois.readObject();
                    String nameForArray = account.getName();
                    accounts.put(nameForArray, account);
                    /// Searching max Id
                    if(maxId < account.getId()){
                        maxId = account.getId();
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage() + "Something wrong with loading information about account object In Account file");
            } catch (Exception e) {
                System.out.println("Something wrong with reading file Accounts");
            }
        }
        /// Send this id to Account object
        Account.setIdCount(maxId);

        while (true) {
            System.out.println("Welcome to Tire Shop");
            System.out.println("For login type - Login\n" +
                    "For Register type - Reg");
            String answer = sc.nextLine();

            /// User Interface for Login
            if (answer.equals("Login")) {
                String name;
                int countOfTriesPassword = 0;
                    while (true) {
                        System.out.println("Enter your name");
                        name = sc.nextLine();
                        if (accounts.containsKey(name)) {
                            break;
                        } else {
                            System.out.println("This account does not exist, try again");
                        }
                    }
                    while (true) {
                        System.out.println("Enter your password");
                        String password = sc.nextLine();

                        String HashedPassword = passwordService.hashPassword(password);

                        Account account = accounts.get(name);
                        if (countOfTriesPassword == 3) {
                            if (account.getHashedPassword().equals(HashedPassword)) {
                                if (account.getName().equals("Admin")) {
                                    sc.close();
                                    showMenuForAdmin(account);
                                } else {
                                    sc.close();
                                    showMenuForUser(account);
                                }
                                break;
                            } else {
                                System.out.println("Incorrect password, try again");
                                countOfTriesPassword++;
                            }

                        }else{
                            System.out.println("You already use all tries");
                            userVerifyMenu();
                        }
                    }
                /// User Interface for Registration
            } else if (answer.equals("Reg")) {
                String name;
                String balance;
                double balanceDouble;
                String password;
                /// Name Checking
                    while (true) {
                        System.out.println("Enter your name");
                        name = sc.nextLine();

                        if (name.isEmpty() || !name.matches("^[A-Za-z]+$")) {
                            System.out.println("Enter valid name");
                        }else if(accounts.containsKey(name)){
                            System.out.println("This account already exists");
                            userVerifyMenu();
                        }
                        else {
                            break;
                        }
                    }
                /// Balance Checking
                    while (true) {
                        System.out.println("Enter your balance");
                        balance = sc.nextLine();
                        try {
                            balanceDouble = Double.parseDouble(balance);
                            if(balanceDouble < 0){
                                System.out.println("Balance cannot be negative");
                            }else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Enter valid balance");
                        }catch (NullPointerException e){
                            System.out.println("Your balance cannot be null");
                        }
                    }
                /// Password Checking
                    while (true) {
                        System.out.println("Enter your password");
                        password = sc.nextLine();
                        if (password.length() < 12) {
                            System.out.println("Type bigger password");
                        } else {
                            break;
                        }
                    }
                /// Code part with writing account to file
                    //Create new account for new user
                    Account account = new Account(name, password, balanceDouble);

                    if (file.exists()) {
                        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                            accounts.put(name, account);
                            for (Account account1 : accounts.values()) {
                                oos.writeObject(account1);
                            }
                            if (account.getName().equals("Admin")) {
                                sc.close();
                                showMenuForAdmin(account);
                            } else {
                                sc.close();
                                showMenuForUser(account);

                            }
                        } catch (IOException e) {
                            System.out.println(e.getMessage() + "Something wrong with writing account object In Account file");
                        }
                    }
            } else {
                // Users bad input
                System.out.println("This operation doesnt exist, try again");
            }
        }
    }
}
