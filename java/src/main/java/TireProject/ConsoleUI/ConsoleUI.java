package TireProject.ConsoleUI;

import TireProject.AccountEntity.Account;
import TireProject.VerifyService.PasswordService;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleUI implements MenuOptions {

    @Override
    public void showMenuForUser(Account account) {

    }

    @Override
    public void showMenuForAdmin(Account account) {

    }

    @Override
    public void userVerifyMenu() {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Account> accounts = new HashMap<>();
        PasswordService passwordService = new PasswordService();

        File file = new File("src/main/java/TireProject/InformationFiles/Accounts.dat");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    Account account = (Account) ois.readObject();
                    String nameForArray = account.getName();
                    accounts.put(nameForArray, account);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage() + "Something wrong with loading information about account object In Account file");
            } catch (Exception e) {
                System.out.println("Something wrong with reading file Accounts");
            }
        }
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
                                    showMenuForAdmin(account);
                                } else {
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
                                showMenuForAdmin(account);
                            } else {
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
