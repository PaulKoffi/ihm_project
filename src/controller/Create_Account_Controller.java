package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.*;
import view.View;

import java.io.IOException;
import java.util.ArrayList;

import static controller.AddingAccounts.addNewAccounttoJSON;

public class Create_Account_Controller {

    @FXML
    private TextField TFfirstName;
    @FXML
    private TextField TFfamillytName;
    @FXML
    private TextField TFemail;
    @FXML
    private PasswordField PFpassword;
    @FXML
    private TextField TFsalary;

    private Stage window;

    private ArrayList<Account> accounts;

    public void init(ArrayList<Account> accounts, Stage scene){
        this.accounts = accounts;
        //Setting the stage
        this.window = scene;



    }
    public void createAccount() {
        Account newAccount;
        try{
            newAccount = new Account(
                    TFfirstName.getText(),
                    TFfamillytName.getText(),
                    TFemail.getText(),
                    PFpassword.getText(),
                    Double.valueOf(TFsalary.getText())) ;
        } catch (Exception e){
            showMessage("Salaire illisible");
            return;
        }

        //Checking required fields
        if (newAccount.getFirstName().equals("") ||
                newAccount.getName().equals("") ||
                newAccount.getEmail().equals("") ||
                newAccount.getPassword().equals("") ||
                TFsalary.getText().equals("")) {
            showMessage("Veuillez remplir tous les champs Obligatoires !");
            return;
        }

        if (accounts.contains(newAccount)){
            showMessage("Cet email correspond deja a un compte");
            return;
        }

        addNewAccounttoJSON(newAccount);

        accounts.add(newAccount);

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
