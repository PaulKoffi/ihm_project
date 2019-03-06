package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Activity;
import model.Frequency;
import model.Importance;
import view.View;

import java.io.IOException;
import java.time.LocalDate;

public class New_Activity_Controller {

    @FXML
    private TextField TFname;
    @FXML
    private CheckBox CBinfiny;
    @FXML
    private DatePicker DPdate;
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
    private ToggleGroup importanceToggleGroup;
    @FXML
    private Button BTcreateActivity;

    private Stage window;

    public void init(ObservableList<Activity> activities, Stage scene) {
        //Setting the stage
        this.window = scene;
        scene.setResizable(false);

        //Add items in combobox
        CBfrequency.getItems().addAll(Frequency.values());

        //Set default combobox values
        CBfrequency.setValue(Frequency.oneByDay);

        //Set default budget values
        TFminbudget.setText("0");
        TFmaxbudget.setText("0");

        //add listener to button
        //BTcreateActivity.getStylesheets().add(getClass().getResource("../resources/css/style.css").toExternalForm());
        BTcreateActivity.setOnAction(event -> createNewActivity(activities));
        DPdate.setValue(LocalDate.now());
        CBinfiny.setOnMouseClicked(event -> {
            DPdate.disableProperty().setValue(CBinfiny.isSelected());
        });
    }

    private void createNewActivity(ObservableList<Activity> activities) {
        Activity newActivity;
        if (CBinfiny.isSelected()){
            newActivity = new Activity(
                    TFname.getText(),
                    null,
                    (Frequency) CBfrequency.getValue(),
                    Integer.valueOf(TFminbudget.getText()),
                    Integer.valueOf(TFmaxbudget.getText()),
                    Importance.getImportance( ((RadioButton) importanceToggleGroup.getSelectedToggle()).getText() ));
        }
         else {
            newActivity = new Activity(
                    TFname.getText(),
                    null,
                    (Frequency) CBfrequency.getValue(),
                    Integer.valueOf(TFminbudget.getText()),
                    Integer.valueOf(TFmaxbudget.getText()),
                    Importance.getImportance( ((RadioButton) importanceToggleGroup.getSelectedToggle()).getText() ));
        }

        //Checking required fields
        if (newActivity.getName().equals("")) {
            showMessage("Veuillez remplir tous les champs Obligatoires !");
            return;
        }

        if (newActivity.getMinimumBudget() > newActivity.getMaximumBudget()){
            showMessage("Minimum budget doit etre inférieur a maximum  maximum budget !");
            return;
        }

        //Checking if activity already exist
        if (activities.contains(newActivity)){
            showMessage("Il existe déjà une activité portant ce nom, veuillez modifier votre saisie !");
            return;
        }

        activities.add(newActivity);
        this.window.close();
    }

    /**
     * Dialog box to notify a message to user.
     * @param message
     */
    private void showMessage(String message) {
        //System.out.println(message);
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = loader.load(getClass().getResourceAsStream(View.SHOW_MESSAGE_XML_FILE_PATH));

            Stage scene = new Stage();
            scene.setScene(new Scene(root, 300, 150));
            scene.setTitle(View.LABEL_ERROR);
            scene.show();

            ((Show_Message_Controller)loader.getController()).showMessage(message, scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
