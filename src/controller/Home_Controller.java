package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
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

    // page de connexion
    private Stage thisWindows;
    // Page de base
    private Stage scene;


    public void init(Account account, Stage thisWindows, Stage scene){
        this.thisWindows = thisWindows;
        this.scene = scene;
        this.currentAccount = account;
        this.IMDec.setImage(new Image(getClass().getResourceAsStream(View.DEC_IMG_PATH)));
        // this.IMDec.setFitHeight(30);
        // this.IMDec.setFitWidth(30);
        this.activities = ParsingActivities.getActivityListFromJSON(View.ACTIVITIES_JSON_FILE);

        try {
            FXMLLoader loader = new FXMLLoader();

            Parent root = loader.load(getClass().getResourceAsStream(View.ACTIVITY_LIST_TAB_XML_FILE_PATH));
            root.getStylesheets().add(View.CSS);
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
            root.getStylesheets().add(View.CSS);
            ((Profil_Controller)loader.getController()).init(account);

            this.SSprofil.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void voidD(){
        this.scene.close();
        this.thisWindows.show();
    }
}
