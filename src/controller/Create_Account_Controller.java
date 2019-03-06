package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import view.View;

import java.io.IOException;

public class Create_Account_Controller {

    @FXML
    private TextField TFFirstName;
    @FXML
    private TextField TFFamillytName;
    @FXML
    private TextField TFEmail;
    @FXML
    private TextField TFPassword;
    @FXML
    private Button BTcreateAccount;

    private Stage window;

    public void init(ObservableList<Account> accounts, Stage scene){

        //Setting the stage
        this.window = scene;
        BTcreateAccount.getStylesheets().add(getClass().getResource("../resources/css/style.css").toExternalForm());


        //add listener to button
        BTcreateAccount.setOnAction(event -> createAccount(accounts));

    }
    public void createAccount(ObservableList<Account> accounts){
        Account newAccount = new Account(
                TFFirstName.getText(),
                TFFamillytName.getText(),
                TFEmail.getText(),
                TFPassword.getText() ) ;

        //Checking required fields
        if (newAccount.getFirstName().equals("")) {
            showMessage("Veuillez remplir tous les champs Obligatoires !");
            return;
        }

        //Checking if account already exist
        if (accounts.contains(newAccount)){
            showMessage("Il existe déjà un compte associé a cette adresse email, veuillez modifier votre saisie !");
            return;
        }

        accounts.add(newAccount);
        this.window.close();

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
