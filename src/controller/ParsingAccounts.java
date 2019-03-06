package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Account;
import model.Activity;
import model.Frequency;
import model.Importance;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ParsingAccounts {


    public static ObservableList<Account> getAccountListFromJSON(String jsonFile) {
        ObservableList<Account> accounts = FXCollections.observableArrayList();

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
                Double salary = Double.valueOf((Long)innerObj.get("salary"));
                accounts.add(new Account(name,firstName,email,password, salary));
            }

        } catch (IOException | ParseException | NullPointerException ex) {
            ex.printStackTrace();
        }

        return accounts;
    }
}
