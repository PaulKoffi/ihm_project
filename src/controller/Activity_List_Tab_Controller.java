package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.layout.GridPane;
import model.Activity;
import view.View;

import java.io.IOException;

public class Activity_List_Tab_Controller {
    @FXML
    private GridPane gridPane;

    private double widthHeight = 125.0;

    private ObservableList<Activity> activities;

    final String JSON_FILE = "src/resources/json/activities.json";

    public void init() {

        if (this.activities == null){
            this.activities = ParsingActivities.getActivityListFromJSON(JSON_FILE);
        }

        int nbOfColumns = 4;
        int nbOfLines = this.activities.size()/4 + 1;

        Activity activity[][] = new Activity[nbOfColumns][nbOfLines];

        for (int i = 0; i < nbOfColumns; ++i) {
            for (int j = 0; j < nbOfLines; ++j) {
                activity[i][j] = null;
            }
        }
        for (int i = 0; i < this.activities.size(); ++i){
            activity[i%nbOfColumns][i/nbOfColumns] = this.activities.get(i);
        }

        try {
            for (int y = 0; y < nbOfLines; ++y){
                SubScene subScenes[] = new SubScene[nbOfColumns];

                for (int x = 0; x < nbOfColumns; ++x){
                    Parent root;

                    if(activity[x][y] != null){
                        FXMLLoader loader = new FXMLLoader();

                        Activity_Element_Controller controller = new Activity_Element_Controller();
                        loader.setController(controller);

                        root = loader.load(getClass().getResourceAsStream(View.ACTIVITY_ELEMENT_XML_FILE_PATH));

                        controller.init(activity[x][y]);
                    } else {
                        root = new Parent(){};
                    }




                    subScenes[x] = new SubScene(root, this.widthHeight, this.widthHeight);

                }

                this.gridPane.addRow(y, subScenes);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
