package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jdk.nashorn.internal.parser.JSONParser;
import model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import view.View;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static controller.AddingAccounts.addNewAccounttoJSON;

public class Create_Account_Controller {

    @FXML
    private TextField TFFirstName;
    @FXML
    private TextField TFFamillytName;
    @FXML
    private TextField TFEmail;
    @FXML
    private PasswordField TFPassword;
    @FXML
    private Button BTcreateAccount;

    private Stage window;

    private ArrayList<Account> accounts;

    public void init(ArrayList<Account> accounts, Stage scene){
        this.accounts = accounts;
        //Setting the stage
        this.window = scene;



    }
    public void createAccount() throws IOException {
        Account newAccount = new Account(
                TFFirstName.getText(),
                TFFamillytName.getText(),
                TFEmail.getText(),
                TFPassword.getText(),
                0) ;

        //Checking required fields
        if (newAccount.getFirstName().equals("")) {
            showMessage("Veuillez remplir tous les champs Obligatoires !");
            return;
        }

        addNewAccounttoJSON(newAccount);

        this.window.close();

        FXMLLoader loader = new FXMLLoader();
        try {
            Parent root = loader.load(getClass().getResourceAsStream(View.CONNECTION_XML_FILE_PATH));
            Stage scene = new Stage();
            scene.setScene(new Scene(root));
            root.getStylesheets().add(View.CSSR);
            scene.getIcons().add(new Image("resources/img/appli.jpg"));
            scene.setResizable(false);
            ((Connection_Controller)loader.getController()).init(accounts,scene);
            scene.setTitle("MyBudget");
            scene.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void showMessage(String message){
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
