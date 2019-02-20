package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.*;

public class Controller {
    /*@FXML
    private Button addButton;
    @FXML
    private Button minusButton;
    @FXML
    private Button timesButton;
    @FXML
    private Button divideButton;
    @FXML
    private TextField val1;
    @FXML
    private TextField val2;
    @FXML
    private Text result;*/
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

    /*
    public void valid(char operator){
        int value1 = Integer.parseInt(val1.getText());
        int value2 = Integer.parseInt(val2.getText());
        Model calculator = new Model(value1,value2);
        result.setText( String.valueOf( calculator.compute(operator) ));
    }*/




    public void init() {
        /*//add istner to validButton
        addButton.setOnAction( event -> valid('+'));
        minusButton.setOnAction( event -> valid('-'));    //add a listner to validButton
        timesButton.setOnAction( event -> valid('x'));    //add a listner to validButton
        divideButton.setOnAction( event -> valid('/'));    //add a listner to validButton*/
        name.setCellValueFactory(new PropertyValueFactory<Activity, String>("name"));
        duration.setCellValueFactory(new PropertyValueFactory<Activity, Duration>("duration"));
        frequency.setCellValueFactory(new PropertyValueFactory<Activity, Frequency>("frequency"));
        minimumBudget.setCellValueFactory(new PropertyValueFactory<Activity, Integer>("minimumBudget"));
        maximumBudget.setCellValueFactory(new PropertyValueFactory<Activity, Integer>("maximumBudget"));
        importance.setCellValueFactory(new PropertyValueFactory<Activity, Importance>("importance"));

        activities = FXCollections.observableArrayList();
        tableView.setItems(getActivities());

        BTaddActivity.setOnAction(event -> this.activities.add(new Activity("Vide", null, null, 0, 0, null)));
    }

    public ObservableList<Activity> getActivities(){
        ObservableList<Activity> activities = this.activities;

        activities.add(new Activity("Raclette", Duration.oneMounth, Frequency.oneByWeek, 6, 15, Importance.Low));

        activities.add(new Activity("5 Legumes", Duration.infiny, Frequency.oneByDay, 3, 8, Importance.High));

        activities.add(new Activity("5 Fruits", Duration.infiny, Frequency.oneByDay, 5, 10, Importance.High));

        return activities;
    }
}
