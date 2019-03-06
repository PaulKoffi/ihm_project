package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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


public class ParsingActivities {


    public static ObservableList<Activity> getActivityListFromJSON(String jsonFile) {
        ObservableList<Activity> activities = FXCollections.observableArrayList();

        try {
            // read the json file
            FileReader reader = new FileReader(jsonFile);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            // get an array from the JSON object
            JSONArray lang= (JSONArray) jsonObject.get("activities");


            DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
            // take each value from the json array separately
            for (Object o : lang) {
                JSONObject innerObj = (JSONObject) o;

                String name = (String) innerObj.get("name");
                Date endDate = format.parse((String)innerObj.get("endDate"));
                Frequency frequency = Frequency.valueOf((String)innerObj.get("frequency"));
                int minimumBudget = Integer.valueOf((innerObj.get("minimumBudget")).toString());
                int maximumBudget = Integer.valueOf((innerObj.get("maximumBudget")).toString());
                Importance importance = Importance.valueOf((String)innerObj.get("importance"));
                activities.add(new  Activity(name,endDate,frequency,minimumBudget, maximumBudget,importance));
            }

        } catch (IOException | ParseException | NullPointerException ex) {
            ex.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return activities;
    }
}
