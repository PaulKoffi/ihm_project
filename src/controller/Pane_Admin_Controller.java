package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Account;
import model.Activity;
import org.json.simple.JSONObject;
import view.View;

import java.io.FileWriter;
import java.io.IOException;

public class Pane_Admin_Controller {
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
    private Stage connectionWindow;
    // Page de base
    private Stage scene;


    public void init(Account account, Stage connectionWindow, Stage scene){
        this.connectionWindow = connectionWindow;
        this.scene = scene;
        this.currentAccount = account;
        this.IMDec.setImage(new Image(getClass().getResourceAsStream(View.DEC_IMG_PATH)));
        // this.IMDec.setFitHeight(30);
        // this.IMDec.setFitWidth(30);
        this.activities = FXCollections.observableArrayList(ParsingActivities.getActivityListFromJSON(View.ACTIVITIES_JSON_FILE));
        BTdeconnection.getStylesheets().add(getClass().getResource("../resources/css/style.css").toExternalForm());

        try {
            FXMLLoader loader = new FXMLLoader();

            Parent root = loader.load(getClass().getResourceAsStream(View.HOME_XML_FILE_PATH));

            ((Home_Controller)loader.getController()).init(this.activities, this.currentAccount);

            this.SSaccueil.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader();

            Parent root = loader.load(getClass().getResourceAsStream(View.ACTIVITY_LIST_TAB_XML_FILE_PATH));

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

    public void voidD(){
        try {
            JSONObject json = new JSONObject();
            json.put("email", "empty");
            FileWriter file = new FileWriter(View.ACCOUNT_REMEMBER_JSON_FILE);
            file.write(json.toJSONString());
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        this.scene.close();
        this.connectionWindow.show();
    }
}
