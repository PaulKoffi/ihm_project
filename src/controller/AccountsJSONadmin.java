package controller;


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


public class AccountsJSONadmin {

    public static void modifyAccountToJSON(String mail, Account account) {

        String ACCOUNT_JSON_FILE = "src/resources/json/accounts.json";
        ArrayList<Account> accounts = new ArrayList<>(AccountsJSONadmin.getAccountListFromJSON(ACCOUNT_JSON_FILE));

        try {
            FileWriter file = new FileWriter("src/resources/json/accounts.json");
            JSONArray comptes= new JSONArray();
            for (Account compte: accounts) {
                if (compte.getEmail().equals(mail))
                    continue;

                JSONObject profile = new JSONObject();
                profile.put("name", compte.getName());
                profile.put("firstName", compte.getFirstName());
                profile.put("email", compte.getEmail());
                profile.put("password", compte.getPassword());
                profile.put("salary", compte.getSalary());

                comptes.add(profile);
            }
            JSONObject profile = new JSONObject();
            profile.put("name", account.getName());
            profile.put("firstName", account.getFirstName());
            profile.put("email", account.getEmail());
            profile.put("password", account.getPassword());
            profile.put("salary", account.getSalary());
            comptes.add(profile);

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

    public static void addNewAccountToJSON(Account account) {

        String ACCOUNT_JSON_FILE = "src/resources/json/accounts.json";
        ArrayList<Account> accounts = new ArrayList<>(AccountsJSONadmin.getAccountListFromJSON(ACCOUNT_JSON_FILE));
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

    public static ArrayList<Account> getAccountListFromJSON(String jsonFile) {
        ArrayList<Account> accounts = new ArrayList<>();

        try {
            // read the json file
            FileReader reader = new FileReader(jsonFile);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            // get an array from the JSON object
            JSONArray lang= (JSONArray) jsonObject.get("accounts");


            // take each value from the json array separately
            for (Object o : lang) {
                JSONObject innerObj = (JSONObject) o;

                String name = (String) innerObj.get("name");
                String firstName = (String) innerObj.get("firstName");
                String email = (String) innerObj.get("email");
                String password = (String) innerObj.get("password");
                double salary = Double.valueOf((double)innerObj.get("salary"));
                accounts.add(new Account(name,firstName,email,password, salary));
            }

        } catch (IOException | ParseException | NullPointerException ex) {
            ex.printStackTrace();
        }

        return accounts;
    }
}


