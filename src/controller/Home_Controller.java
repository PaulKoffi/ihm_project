package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Account;
import model.Activity;
import view.View;

import java.io.IOException;

public class Home_Controller {
    @FXML
    private Button BTdeconnection;

    @FXML
    private ImageView IMDec;

    @FXML
    private SubScene SSaccueil;
    @FXML
    private SubScene SSactivity;
    @FXML
    private SubScene SSsalary;
    @FXML
    private SubScene SSprofil;

    private Account currentAccount;

    private ObservableList<Activity> activities;

    public void init(Account account){
        this.currentAccount = account;
        this.IMDec.setImage(new Image(getClass().getResourceAsStream(View.DEC_IMG_PATH)));
        this.activities = ParsingActivities.getActivityListFromJSON(View.ACTIVITIES_JSON_FILE);

        try {
            FXMLLoader loader = new FXMLLoader();

            Parent root = loader.load(getClass().getResourceAsStream(View.ACTIVITY_LIST_TAB_XML_FILE_PATH));

            // String style= getClass().getResource("../resources/css/style.css").toExternalForm();
            // BTdeconnection.setId("button");
            // root.getStylesheets().add(style);
            BTdeconnection.getStylesheets().add(getClass().getResource("../resources/css/style.css").toExternalForm());
            //root.getStylesheets().add(View.ACTIVITY_LIST_TAB_CSS);

            ((Activity_List_Tab_Controller)loader.getController()).init(this.activities);

            this.SSactivity.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader();

            Parent root = loader.load(getClass().getResourceAsStream(View.PROFILE_XML_FILE_PATH));

            ((Profil_Controller)loader.getController()).init(account);

            this.SSprofil.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
