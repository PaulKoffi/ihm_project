package controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import view.View;

import java.io.IOException;

public class Activity_List_Controller {
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Activity, String> name;
    @FXML
    private TableColumn<Activity, Duration> duration;
    @FXML
    private TableColumn<Activity, Frequency> frequency;
    @FXML
    private TableColumn<Activity, Integer> minimumBudget;
    @FXML
    private TableColumn<Activity, Integer> maximumBudget;
    @FXML
    private TableColumn<Activity, Importance> importance;
    @FXML
    private Button BTaddActivity;

    private ObservableList<Activity> activities;

    final String JSON_FILE = "src/resources/json/activities.json";

    public void init() {
        name.setCellValueFactory(new PropertyValueFactory<Activity, String>("name"));
        duration.setCellValueFactory(new PropertyValueFactory<Activity, Duration>("duration"));
        frequency.setCellValueFactory(new PropertyValueFactory<Activity, Frequency>("frequency"));
        minimumBudget.setCellValueFactory(new PropertyValueFactory<Activity, Integer>("minimumBudget"));
        maximumBudget.setCellValueFactory(new PropertyValueFactory<Activity, Integer>("maximumBudget"));
        importance.setCellValueFactory(new PropertyValueFactory<Activity, Importance>("importance"));

        if (activities == null){
            activities = ParsingActivities.getActivityListFromJSON(JSON_FILE);
        }
        tableView.setItems(activities);

        BTaddActivity.setOnAction(event -> pushButtonAddActivity());
    }

    public void pushButtonAddActivity(){
        FXMLLoader loader = new FXMLLoader();
        New_Activity_Controller controller = new New_Activity_Controller();
        loader.setController(controller);
        Parent root = null;
        try {
            root = loader.load(getClass().getResourceAsStream(View.NEW_ACTIVITY_XML_FILE_PATH));

            Stage scene = new Stage();
            scene.setScene(new Scene(root, View.TABWIDTH, View.TABHEIGHT));
            scene.show();
            scene.setResizable(false);
            controller.init(this.activities, scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
