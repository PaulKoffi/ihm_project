package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Activity;
import model.Frequency;
import model.Importance;
import view.View;

import java.io.IOException;
import java.time.DayOfWeek;
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
    private ToggleGroup importanceToggleGroup;
    @FXML
    private TextField TFminbudget;
    @FXML
    private TextField TFmaxbudget;

    private ObservableList<Activity> activities;
    private Stage window;
    private Activity_List_Tab_Controller activityListTabController;

    // Factory to create Cell of DatePicker
    private Callback<DatePicker, DateCell> getDayCellFactory() {

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        // Disable Monday, Tueday, Wednesday.
                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }

    public void init(ObservableList<Activity> activities, Stage scene, Activity_List_Tab_Controller activityListTabController) {
        this.activities = activities;
        //Setting the stage
        this.window = scene;
        this.activityListTabController = activityListTabController;
        scene.setResizable(false);

        //Add items in combobox
        CBfrequency.getItems().addAll(Frequency.values());

        //Set default combobox values
        CBfrequency.setValue(Frequency.oneByDay);

        //Set default budget values
        TFminbudget.setText("0");
        TFmaxbudget.setText("0");


        DPdate.setValue(LocalDate.now());
        Callback<DatePicker, DateCell> dayCellFactory= this.getDayCellFactory();
        DPdate.setDayCellFactory(dayCellFactory);
    }

    public void infinyChange(){
        DPdate.disableProperty().setValue(CBinfiny.isSelected());
    }

    public void createActivity() {
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
            showMessage("Veuillez remplir tous les champs Obligatoires !",300);
            return;
        }

        if (newActivity.getMinimumBudget() > newActivity.getMaximumBudget()){
            showMessage("Minimum budget doit etre inférieur a maximum  maximum budget !",410);
            return;
        }

        //Checking if activity already exist
        if (this.activities.contains(newActivity)){
            showMessage("Il existe déjà une activité portant ce nom, veuillez modifier votre saisie !",430);
            return;
        }
        /* FIn Verification*/
        this.activities.add(newActivity);
        this.activityListTabController.refreshGridPane();

        this.window.close();
    }

    /**
     * Dialog box to notify a message to user.
     * @param message
     */
    private void showMessage(String message, int widthFen) {
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = loader.load(getClass().getResourceAsStream(View.SHOW_MESSAGE_XML_FILE_PATH));

            Stage scene = new Stage();
            scene.setScene(new Scene(root, widthFen, 150));
            scene.setTitle(View.LABEL_ERROR);
            scene.getIcons().add(new Image("resources/img/warning.png"));
            scene.show();

            ((Show_Message_Controller)loader.getController()).showMessage(message, scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
