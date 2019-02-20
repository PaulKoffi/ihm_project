package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import model.Activity;
import model.Duration;
import model.Frequency;

public class New_Activity_Controller {

    @FXML
    private ComboBox CBduration;
    @FXML
    private ComboBox CBfrequency;
    @FXML
    private Button BTcreateActivity;

    public void init(){
        CBduration.getItems().addAll(Duration.values());
        CBfrequency.getItems().addAll(Frequency.values());
    }
}
