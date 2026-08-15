package TireProject.ConsoleUI;

import TireProject.AccountEntity.Account;

public interface MenuOptions {
    void showMenuForUser(Account account);
    void showMenuForAdmin(Account account);
    void userVerifyMenu();
}
