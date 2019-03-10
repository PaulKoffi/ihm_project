package controller;

import javafx.collections.ObservableList;
import model.Account;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ParsingAccountsTest {

    @Test
    void getAccountListFromJSON() {
        ParsingAccounts abc =new ParsingAccounts();
        String prompt = "src/resources/json/accounts.json";
        ArrayList<Account> accounts = new ArrayList<>(abc.getAccountListFromJSON(prompt));
        Account a = new Account("a","a","a","a",1600); //We have this account in our Json File
        Account b = new Account("yo","hu","th","wtf",0); //Not in our Json

        assertTrue(accounts.contains(a));
        assertFalse(accounts.contains(b));
    }
}