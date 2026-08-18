package TireProject.ConsoleUI;

import TireProject.AccountEntity.Account;
import TireProject.ItemEntity.Item;
import TireProject.ItemEntity.Tire;
import TireProject.ItemEntity.Wheel;
import TireProject.VerifyService.PasswordService;

import java.util.HashMap;
import java.util.Scanner;

public class ConsoleUI implements MenuOptions {
    HashMap<String, Account> accounts;
    HashMap<Integer,Item> items;
    Scanner sc;
    UIService uiService;
    PasswordService passwordService;
    public ConsoleUI() {
        accounts = new HashMap<>();
        items = new HashMap<>();
        sc = new Scanner(System.in);
        uiService = new UIService();
        passwordService = new PasswordService();

        items = uiService.loadItemsFromFile();
        accounts = uiService.loadAccountsFromFile();

    }

    @Override
    public void showMenuForUser(Account account) {
        System.out.println("Welcome to Tire Shop");
        while (true) {
            System.out.println("Select the operation you want to perform:");
            System.out.println("1 - See all items");
            System.out.println("2 - See all items with filter");
            System.out.println("3 - Add item to shopping cart");
            System.out.println("4 - Check your shopping cart");
            System.out.println("5 - Remove item from shopping cart");
            System.out.println("6 - Buy items in shopping cart");
            System.out.println("7 - Exit");

            String operation = sc.nextLine();
            switch (operation) {
                case "1":
                    uiService.printItems(items);
                    break;
                case "2":
                    System.out.println("Which type of item would you like to see?");
                    System.out.println("1 - Wheel");
                    System.out.println("2 - Tire");

                    String answer = sc.nextLine();
                    if(answer.equals("1")){
                        uiService.printItemsWithFilter(items,0);
                    }else if(answer.equals("2")){
                        uiService.printItemsWithFilter(items,1);
                    }else{
                        System.out.println("Invalid input");
                    }
                    break;
                case "3":
                    uiService.printItems(items);
                    System.out.println("Which item would you like to add to shopping cart?");
                    String itemId = sc.nextLine();
                    int itemIdInt;
                    try{
                        itemIdInt = Integer.parseInt(itemId);
                        if(itemIdInt < 0){
                            throw new NumberFormatException("Invalid input");
                        }
                    }catch(NumberFormatException e){
                        System.out.println("Invalid input");
                        break;
                    }
                    if(items.containsKey(itemIdInt)) {
                        account.addItemToShoppingCard(itemIdInt);
                        System.out.println("Item added successfully");
                    }else{
                        System.out.println("Item not found");
                    }
                    break;
                case "4":
                    account.seeShoppingCard(items);
                    break;
                case "5":
                    account.seeShoppingCard(items);
                    System.out.println("Which item would you like to remove from shopping cart?");
                    String removeItemId = sc.nextLine();
                    int removeItemIdInt = 0;
                    try{
                        removeItemIdInt = Integer.parseInt(removeItemId);
                        if(removeItemIdInt < 0){
                            throw new NumberFormatException("Invalid input");
                        }
                    }catch(NumberFormatException e){
                        System.out.println("Invalid input");
                    }
                    account.removeItemFromShoppingCard(removeItemIdInt);
                    break;
                case "6":
                    items = account.buyItemsInShoppingCard(items);
                    uiService.saveItemToFile(items);
                    break;
                case "7":
                    uiService.saveAccountsToFile(accounts);
                    return;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }

    }

    @Override
    public void showMenuForAdmin(Account account) {
        System.out.println("Admin panel was loaded");
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
                        for (Item item : items.values()) {
                            System.out.println(item.toStringSmall());
                        }
                        System.out.println("Type item id which you want to manage");
                        String itemId = sc.nextLine();
                        int itemIdInt = -1;
                        try {
                            itemIdInt = Integer.parseInt(itemId);
                        }catch(NumberFormatException e){
                            System.out.println("Invalid item id");
                        }
                        if(!items.containsKey(itemIdInt)){
                            System.out.println("Item with id " + itemId + " not found");
                            break;
                        }
                        Item item = items.get(itemIdInt);
                        System.out.println("Type operation which you want to perform");
                        System.out.println("1 - Set New Quantity");
                        System.out.println("2 - Set New Price");
                        String operationType = sc.nextLine();

                        if(operationType.equals("1")){
                            System.out.println("Type new quantity");
                            int quantity = 0;
                            try {
                                quantity = Integer.parseInt(sc.nextLine());
                            }catch(NumberFormatException e){
                                System.out.println("Invalid quantity");
                            }
                            item.setQuantity(quantity);
                            System.out.println("Quantity successfully changed");
                        }else if(operationType.equals("2")){
                            System.out.println("Type new price");
                            double price = 0;
                            try{
                                price = Double.parseDouble(sc.nextLine());
                            }catch(NumberFormatException e){
                                System.out.println("Invalid price");
                            }
                            item.setPrice(price);
                            System.out.println("Price successfully changed");
                        }
                        uiService.saveItemToFile(items);
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

                            try {
                                System.out.println("Enter speed rating");
                                String speedRatingStr  = sc.nextLine();
                                if(speedRatingStr.isEmpty()){
                                    System.out.println("Invalid input");
                                    break;
                                }else{
                                    speedRating = speedRatingStr.charAt(0);
                                }

                                System.out.println("Enter price");
                                price = Double.parseDouble(sc.nextLine());

                                System.out.println("Enter quantity");
                                quantity = Integer.parseInt(sc.nextLine());

                            }catch(NumberFormatException | NullPointerException e){
                                System.out.println("Invalid input, try again");
                                break;
                            }

                            Tire tire;
                            if(quantity == 0 || price == 0){
                                tire = new Tire(name, description, tireSize, seasonType, speedRating);
                            }else{
                                tire = new Tire(name, description, price , quantity, tireSize, speedRating, seasonType);
                            }
                            items.put(tire.getId(), tire);
                            break;
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

                            try{
                                System.out.println("Enter price");
                                price = Double.parseDouble(sc.nextLine());

                                System.out.println("Enter quantity");
                                quantity = Integer.parseInt(sc.nextLine());

                            }catch(NumberFormatException | NullPointerException e){
                                System.out.println("Invalid input, try again");
                                break;
                            }
                            Wheel wheel;
                            if(quantity == 0 || price == 0){
                                wheel = new Wheel(name, description, diameter, width, boltPattern);
                            }else{
                                wheel = new Wheel(name, description, price , quantity, diameter, width, boltPattern);
                            }
                            items.put(wheel.getId(), wheel);
                            break;
                        }
                    }
                    uiService.saveItemToFile(items);
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
                    uiService.saveItemToFile(items);
                    for(Account acc : accounts.values()){
                        acc.removeItemFromShoppingCard(id);
                    }
                    System.out.println("Item was successfully deleted");
                    break;
                case "4":
                    uiService.printItems(items);
                    break;
                case "5":
                    uiService.saveItemToFile(items);
                    return;
                default:
                    System.out.println("Invalid choice, try again");
            }

        }
    }

    @Override
    public void userVerifyMenu() {
        while (true) {
            System.out.println("Welcome to Tire Shop");
            System.out.println("For login type - Login\n" +
                    "For Register type - Reg");
            String answer = sc.nextLine();

            /// User Interface for Login
            if (answer.equalsIgnoreCase("login")) {
                String name;
                    while (true) {
                        System.out.println("Enter your name; If you need to back type - back");
                        name = sc.nextLine();
                        if (accounts.containsKey(name)) {
                            break;
                        } else if(name.equals("back")){
                            return;
                        }
                        else {
                            System.out.println("This account does not exist, try again");
                        }
                    }
                    while (true) {
                        System.out.println("Enter your password");
                        String password = sc.nextLine();

                        Account account = accounts.get(name);

                        boolean verified = passwordService.verifyPassword(password,account.getHashedPassword());

                        if(verified){
                            if (account.getName().equals("Admin")) {
                                showMenuForAdmin(account);
                                break;
                            } else {
                                showMenuForUser(account);
                                break;
                            }
                        }else{
                            System.out.println("Incorrect password, try again");
                        }
//                            if (account.getHashedPassword().equals(HashedPassword)) {
//                                if (account.getName().equals("Admin")) {
//                                    showMenuForAdmin(account);
//                                } else {
//                                    showMenuForUser(account);
//                                }
//                                break;
//                            } else {
//                                System.out.println("Incorrect password, try again");
//                            }
                    }
                /// User Interface for Registration
            } else if (answer.equalsIgnoreCase("Reg")) {
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
                            return;
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
                    accounts.put(account.getName(), account);
                    uiService.saveAccountsToFile(accounts);

                    if(account.getName().equals("Admin")){
                        showMenuForAdmin(account);
                        break;
                    }else{
                        showMenuForUser(account);
                        break;
                    }
            } else {
                // Users bad input
                System.out.println("This operation doesnt exist, try again");
            }
        }
    }
}
