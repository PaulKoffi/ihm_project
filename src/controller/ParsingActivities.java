package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Activity;
import model.Duration;
import model.Frequency;
import model.Importance;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;


public class ParsingActivities {


    public static ObservableList<Activity> getActivityListFromJSON(String jsonFile) {
        ObservableList<Activity> activities = FXCollections.observableArrayList();

        try {
            // read the json file
            FileReader reader = new FileReader(jsonFile);
            JSONParser jsonParser = new JSONParser();
            //System.out.println("okki");
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            //System.out.println("not okki");
            // get an array from the JSON object
            JSONArray lang= (JSONArray) jsonObject.get("activities");

            // take each value from the json array separately
            for (Object o : lang) {
                JSONObject innerObj = (JSONObject) o;

                String name = (String) innerObj.get("name");
                Duration duration = Duration.valueOf((String)innerObj.get("duration"));
                Frequency frequency = Frequency.valueOf((String)innerObj.get("frequency"));
                int minimumBudget = Integer.valueOf((innerObj.get("minimumBudget")).toString());
                int maximumBudget = Integer.valueOf((innerObj.get("maximumBudget")).toString());
                Importance importance = Importance.valueOf((String)innerObj.get("importance"));
                //System.out.println(" ===" +name+" "+duration+" "+frequency+" "+minimumBudget+" "+maximumBudget+" "+importance);
                activities.add(new  Activity(name,duration,frequency,minimumBudget, maximumBudget,importance));
            }

        } catch (IOException | ParseException | NullPointerException ex) {
            ex.printStackTrace();
        }

        return activities;
    }
}
