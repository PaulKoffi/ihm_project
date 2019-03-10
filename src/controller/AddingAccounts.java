package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Account;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class AddingAccounts {

    public static void addNewAccounttoJSON(Account account) {

        ParsingAccounts parsingAccounts= new ParsingAccounts();
        String ACCOUNT_JSON_FILE = "src/resources/json/accounts.json";
        ArrayList<Account> accounts = new ArrayList<>(parsingAccounts.getAccountListFromJSON(ACCOUNT_JSON_FILE));
        accounts.add(account);

        try {
            FileWriter file = new FileWriter("src/resources/json/accounts.json");
            JSONArray comptes= new JSONArray();
            for (Account compte: accounts) {
                JSONObject profile = new JSONObject();
                profile.put("name", compte.getName());
                profile.put("firstName", compte.getFirstName());
                profile.put("email", compte.getEmail());
                profile.put("password", compte.getPassword());
                profile.put("salary", compte.getSalary());

                comptes.add(profile);
            }
            JSONObject content = new JSONObject();
            content.put("accounts",comptes);
            file.write(content.toJSONString());

            file.flush();
            file.close();
            } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    }


