package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import view.View;

import java.io.IOException;

public class Home_Controller {
    @FXML
    private Button BTdeconnection;

    @FXML
    private SubScene SSaccueil;
    @FXML
    private SubScene SSactivity;
    @FXML
    private SubScene SSsalary;
    @FXML
    private SubScene SSprofil;

    public void init(){

        try {
            FXMLLoader loader = new FXMLLoader();

            Parent root = loader.load(getClass().getResourceAsStream(View.ACTIVITY_LIST_TAB_XML_FILE_PATH));

            root.getStylesheets().add(View.ACTIVITY_LIST_TAB_CSS);

            ((Activity_List_Tab_Controller)loader.getController()).init();

            this.SSactivity.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader();

            Parent root = loader.load(getClass().getResourceAsStream(View.PROFILE_XML_FILE_PATH));

            this.SSprofil.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
