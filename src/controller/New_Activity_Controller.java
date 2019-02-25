package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Activity;
import model.Duration;
import model.Frequency;
import model.Importance;
import view.View;

import java.io.IOException;

public class New_Activity_Controller {

    @FXML
    private TextField TFname;
    @FXML
    private ComboBox CBduration;
    @FXML
    private ComboBox CBfrequency;
    @FXML
    private TextField TFminbudget;
    @FXML
    private TextField TFmaxbudget;
    @FXML
    private RadioButton RBlowimport;
    @FXML
    private RadioButton RBmediumimport;
    @FXML
    private RadioButton RBhighimport;
    @FXML
    private Button BTcreateActivity;

    private Stage window;

    public void init(ObservableList<Activity> activities, Stage scene) {
        //Setting the stage
        this.window = scene;
        //Add items in combobox
        CBduration.getItems().addAll(Duration.values());
        CBfrequency.getItems().addAll(Frequency.values());
        //Set default combobox values
        CBduration.setValue(Duration.oneDay);
        CBfrequency.setValue(Frequency.oneByDay);
        //Set default budget values
        TFminbudget.setText("0");
        TFmaxbudget.setText("0");
        //Set default Radio button
        RBlowimport.setSelected(true);
        //add listener to other radio buttons
        RBlowimport.setOnAction(event -> setLow());
        RBmediumimport.setOnAction(event -> setMedium());
        RBhighimport.setOnAction(event -> setHigh());
        //add listener to button
        BTcreateActivity.setOnAction(event -> createNewActivity(activities));
        //BTcreateActivity.setOnAction(event -> showMessage("Veuillez remplir tous les champs Obligatoires !"));
    }

    private void setHigh() {
        if (RBlowimport.isSelected()) RBlowimport.setSelected(false);
        if (RBmediumimport.isSelected()) RBmediumimport.setSelected(false);
        if (!RBhighimport.isSelected()) RBhighimport.setSelected(true);
    }

    private void setLow() {
        if (RBhighimport.isSelected()) RBhighimport.setSelected(false);
        if (RBmediumimport.isSelected()) RBmediumimport.setSelected(false);
        if (!RBlowimport.isSelected()) RBlowimport.setSelected(true);
    }

    private void setMedium() {
        if (RBhighimport.isSelected()) RBhighimport.setSelected(false);
        if (RBlowimport.isSelected()) RBlowimport.setSelected(false);
        if (!RBmediumimport.isSelected()) RBmediumimport.setSelected(true);
    }

    private Importance getImportance(){
        if(RBlowimport.isSelected()) return Importance.Low;
        else if (RBmediumimport.isSelected()) return Importance.Medium;
        else return Importance.High;
    }

    private void createNewActivity(ObservableList<Activity> activities) {
        //getting all values
        String name = String.valueOf(TFname.getText());
        Duration duration = (Duration) CBduration.getValue();
        Frequency frequency = (Frequency) CBfrequency.getValue();
        int minimumBudget = Integer.valueOf(TFminbudget.getText());
        int maximumBudget = Integer.valueOf(TFmaxbudget.getText());
        Importance importance = getImportance();

        //boolean to verify if activity is valid
        boolean activityIsValid = true;
        //Checking required fields
        if (name.equals("")) {
            showMessage("Veuillez remplir tous les champs Obligatoires !");
            activityIsValid = false;
        }
        else {
            //Checking if activity already exist
            for (Activity activity:activities) {
                if (activity.getName().trim().toLowerCase().equals(name.trim().toLowerCase())) {
                    showMessage("Il existe déjà une activité de ce nom, veuillez modifier votre saisie !");
                    activityIsValid = false;
                    break;
                }
            }
            //add to list of activities
            if (activityIsValid){
                activities.add(new Activity(name, duration, frequency, minimumBudget, maximumBudget, importance));
                closeWindow();
            }
        }
    }

    private void closeWindow() {
        this.window.close();
    }

    /**
     * Dialog box to notify a message to user.
     * @param message
     */
    private void showMessage(String message) {
        //System.out.println(message);
        FXMLLoader loader = new FXMLLoader();
        Show_Message_Controller controller = new Show_Message_Controller();
        loader.setController(controller);
        Parent root = null;
        try {
            root = loader.load(getClass().getResourceAsStream(View.SHOW_MESSAGE_XML_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage scene = new Stage();
        scene.setScene(new Scene(root, 300, 150));
        scene.setTitle(View.LABEL_ERROR);
        scene.show();

        controller.showMessage(message, scene);

    }
}
