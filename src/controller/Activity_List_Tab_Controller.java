package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Activity;
import model.Activity_Caracteristic;
import sun.rmi.runtime.Log;
import sun.security.ssl.Debug;
import view.View;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Activity_List_Tab_Controller {
    @FXML
    private ComboBox CBsort;
    @FXML
    private ToggleGroup croissDecr;
    @FXML
    private RadioButton RBdecr;
    @FXML
    private RadioButton RBcroiss;
    @FXML
    private TextField TFresearch;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button BTaddActivity;


    private ObservableList<Activity> activities;
    private ArrayList<Activity> activitiesAfterSorting;

    final String JSON_FILE = "src/resources/json/activities.json";

    public void init() {
        CBsort.getItems().addAll(Activity_Caracteristic.values());
        CBsort.getSelectionModel().select(Activity_Caracteristic.importance.toString());

        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream(View.NEW_ACTIVITY_IMG_PATH)));
        icon.setPreserveRatio(false);
        icon.setFitWidth(110);
        icon.setFitHeight(110);
        BTaddActivity.setGraphic(icon);
        BTaddActivity.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        BTaddActivity.setOnAction(event -> {
            pushButtonAddActivity();
            this.gridPaneInit();
        });

        RBcroiss.setOnAction(event -> this.gridPaneInit());
        RBdecr.setOnAction(event -> this.gridPaneInit());

        TFresearch.setOnKeyReleased(event -> this.gridPaneInit());

        CBsort.setOnAction(event -> this.gridPaneInit());

        this.gridPaneInit();
    }

    private void gridPaneInit(){
        if (this.activities == null){
            this.activities = ParsingActivities.getActivityListFromJSON(JSON_FILE);
        }

        this.activitiesAfterSorting = new ArrayList<>(this.activities);
        this.sortActivitiesList();
        this.removeUnmatchingActivities();


        System.out.println(activitiesAfterSorting.toString());

        int nbOfColumns = 4;
        int nbOfLines = (this.activitiesAfterSorting.size() + 1)/4 + 1;

        Activity activity[][] = new Activity[nbOfColumns][nbOfLines];

        for (int i = 0; i < nbOfColumns; ++i) {
            for (int j = 0; j < nbOfLines; ++j) {
                activity[i][j] = null;
            }
        }
        for (int i = 0; i < this.activitiesAfterSorting.size(); ++i){
            activity[(i + 1)%nbOfColumns][(i + 1)/nbOfColumns] = this.activitiesAfterSorting.get(i);
        }

        try {
            for (int y = 0; y < nbOfLines; ++y){
                SubScene subScenes[];

                //If it's the first line -> button
                if (y == 0) {
                    subScenes = new SubScene[nbOfColumns - 1];
                } else {//Or an other line
                    subScenes = new SubScene[nbOfColumns];
                }

                for (int x = 0; x < nbOfColumns; ++x){
                    if (x == 0 && y == 0)
                        continue;

                    Parent root;

                    if(activity[x][y] != null){
                        FXMLLoader loader = new FXMLLoader();

                        root = loader.load(getClass().getResourceAsStream(View.ACTIVITY_ELEMENT_XML_FILE_PATH));

                        root.getStylesheets().add(View.ACTIVITY_ELEMENT_CSS);

                        ((Activity_Element_Controller)loader.getController()).init(activity[x][y]);
                    } else {
                        root = new Parent(){};
                    }

                    //If it's the first line -> button
                    if (y == 0) {
                        subScenes[x - 1] = new SubScene(root, View.ELEMENT_HEIGHT_WIDTH, View.ELEMENT_HEIGHT_WIDTH);
                    } else {//Or an other line
                        subScenes[x] = new SubScene(root, View.ELEMENT_HEIGHT_WIDTH, View.ELEMENT_HEIGHT_WIDTH);
                    }
                }
                this.gridPane.addRow(y, subScenes);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeUnmatchingActivities() {

        for (Activity activity : this.activities){
            if (!activity.match(this.TFresearch.getText())){
                activitiesAfterSorting.remove(activity);
            }
        }
    }

    private void sortActivitiesList() {
        //Sort from the sort comboxbox
        this.activities.sort(new Comparator<Activity>() {
            @Override
            public int compare(Activity o1, Activity o2) {
                if (CBsort.getSelectionModel().getSelectedItem().toString().equals(Activity_Caracteristic.name.toString())){
                    if (croissDecr.getSelectedToggle() == RBcroiss) {
                        return o1.getName().compareTo(o2.getName());
                    } else {
                        return o2.getName().compareTo(o1.getName());
                    }
                } else if (CBsort.getSelectionModel().getSelectedItem().toString().equals(Activity_Caracteristic.importance.toString())){
                    if (croissDecr.getSelectedToggle() == RBcroiss) {
                        return o1.getImportance().getLevel() - o2.getImportance().getLevel();
                    } else {
                        return o2.getImportance().getLevel() - o1.getImportance().getLevel();
                    }
                } else if (CBsort.getSelectionModel().getSelectedItem().toString().equals(Activity_Caracteristic.averageBudget.toString())){
                    if (croissDecr.getSelectedToggle() == RBcroiss) {
                        return (o1.getMinimumBudget() + o1.getMaximumBudget()) - (o2.getMinimumBudget() + o2.getMaximumBudget());
                    } else {
                        return (o2.getMinimumBudget() + o1.getMaximumBudget()) - (o1.getMinimumBudget() + o2.getMaximumBudget());
                    }
                } else if (CBsort.getSelectionModel().getSelectedItem().toString().equals(Activity_Caracteristic.duration.toString())){
                    if (croissDecr.getSelectedToggle() == RBcroiss) {
                        return o1.getFrequency().getLevel() - o2.getFrequency().getLevel();
                    } else {
                        return o2.getFrequency().getLevel() - o1.getFrequency().getLevel();
                    }
                } else if (CBsort.getSelectionModel().getSelectedItem().toString().equals(Activity_Caracteristic.frequency.toString())){
                    if (croissDecr.getSelectedToggle() == RBcroiss) {
                        return o1.getFrequency().getLevel() - o2.getFrequency().getLevel();
                    } else {
                        return o2.getFrequency().getLevel() - o1.getFrequency().getLevel();
                    }
                } else {
                    return 0;
                }
            }
        });
    }

    public void pushButtonAddActivity() {
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = loader.load(getClass().getResourceAsStream("../resources/fxml/New_Activity.fxml"));
            Stage scene = new Stage();
            scene.setScene(new Scene(root, 600, 450));


            ((New_Activity_Controller)loader.getController()).init(this.activities,scene);
            scene.setTitle("MyBudget - Activity");
            scene.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
