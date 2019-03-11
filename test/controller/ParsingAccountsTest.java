package controller;

import model.Account;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

class ParsingAccountsTest {

    @Test
    void getAccountListFromJSON() {
        String prompt = "src/resources/json/accounts.json";
        ArrayList<Account> accounts = new ArrayList<>(AccountsJSONadmin.getAccountListFromJSON(prompt));
        Account a = new Account("a","a","a","a",1600); //We have this account in our Json File
        Account b = new Account("yo","hu","th","wtf",0); //Not in our Json

        assertTrue(accounts.contains(a));
        assertFalse(accounts.contains(b));
    }
}