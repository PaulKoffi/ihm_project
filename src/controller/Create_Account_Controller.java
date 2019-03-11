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

import static controller.AccountsJSONadmin.addNewAccountToJSON;

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

    public void init(ArrayList<Account> accounts, Stage scene) {
        this.accounts = accounts;
        //Setting the stage
        this.window = scene;


    }

    public void createAccount() {
        if (TFfirstName.getText().equals("") ||
                TFfamillytName.getText().equals("") ||
                TFemail.getText().equals("") ||
                PFpassword.getText().equals("") ||
                TFsalary.getText().equals("")) {
            showMessage("Veuillez remplir tous les champs Obligatoires !");
            return;
        } else {
            int emailIndex = TFemail.getText().trim().indexOf("@");
            int dotIndex = TFemail.getText().trim().lastIndexOf(".");
            if (emailIndex == 0 || (dotIndex - emailIndex) <= 1 || dotIndex == (TFemail.getText().trim().length()-1)) {
                showMessage("Email invalide, veuillez renseigner une bonne adresse mail !");
            } else {
                String password = PFpassword.getText().trim();
                if (password.length() < 8)
                    showMessage("Votre mot de passe doit contenir au moins 8 caractères.");
                else {
                    try {
                        Account newAccount = newAccount = new Account(
                                TFfirstName.getText(),
                                TFfamillytName.getText(),
                                TFemail.getText(),
                                PFpassword.getText(),
                                Double.valueOf(TFsalary.getText()));

                        if (accounts.contains(newAccount)) {
                            showMessage("Cet email correspond deja à un compte");
                            return;
                        }

                        addNewAccountToJSON(newAccount);

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
                            ((Connection_Controller) loader.getController()).init(accounts, scene);
                            scene.setTitle("MyBudget");
                            scene.show();


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        showMessage("Mauvais format de salaire !");
                        return;
                    }
                }
            }
            System.out.println(emailIndex);
            //showMessage("Salaire illisible");
        }

    }

    public void showMessage(String message) {
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = loader.load(getClass().getResourceAsStream(View.SHOW_MESSAGE_XML_FILE_PATH));

            Stage scene = new Stage();
            scene.setScene(new Scene(root, 300, 150));
            scene.setTitle(View.LABEL_ERROR);
            scene.getIcons().add(new Image("resources/img/warning.png"));
            scene.show();

            ((Show_Message_Controller) loader.getController()).showMessage(message, scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
