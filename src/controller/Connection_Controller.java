package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.*;
import view.View;

import java.io.IOException;
import java.util.ArrayList;

public class Connection_Controller {


    @FXML
    private TextField TFmail;

    @FXML
    private PasswordField TFpassword;

    @FXML
    private  TextField TFpass;

    @FXML
    private CheckBox CBremember;
    @FXML
    private Hyperlink HLcreateAccount;
    @FXML
    private Hyperlink HLforgetPassword;

    @FXML
    private ImageView IVkeyPassword;
    @FXML
    private ImageView IVUser;
    private String password;


    private Stage thisWindows;
    private ArrayList<Account> accounts;

    public void init(ArrayList<Account> accounts, Stage thisWindows) {
        this.accounts = accounts;
        this.thisWindows = thisWindows;
        this.TFmail.setMaxWidth(200);
        this.TFpassword.setMaxWidth(200);
        this.TFpass.setMaxWidth(200);
        this.TFpass.setManaged(false);
        this.TFpass.setVisible(false);
        TFpass.textProperty().bindBidirectional(TFpassword.textProperty());
        this.IVkeyPassword.setImage(new Image(getClass().getResourceAsStream(View.KEY_PASSWORD_IMG_PATH)));
        this.IVUser.setImage(new Image(getClass().getResourceAsStream(View.USER_IMG_PATH)));
    }


    public void connexion() {
        String id = TFmail.getText();
        String password = TFpassword.getText();;

        //Checking required fields
        if (id.equals("") || password.equals("")) {
            showMessage("Veuillez remplir tous les champs Obligatoires !");
            TFmail.setText("");
            TFpassword.setText("");
            return;
        }

        boolean connected = false;
        //Checking if account already exist
        for (Account account: accounts) {
            if (account.getEmail().equals(id)){
                if (account.getPassword().equals(password)){
                    connected = true;
                    TFmail.setText("");
                    TFpassword.setText("");
                    FXMLLoader loader = new FXMLLoader();
                    try {
                        Parent root = loader.load(getClass().getResourceAsStream("../resources/fxml/Home.fxml"));
                        Stage scene = new Stage();
                        scene.setScene(new Scene(root, View.HOMEWIDTH, View.HOMEHEIGHT));
                        root.getStylesheets().add(View.ACTIVITY_LIST_TAB_CSS);
                        scene.getIcons().add(new Image("resources/img/act.jpg"));
                        scene.setResizable(false);
                        ((Home_Controller)loader.getController()).init(account,thisWindows,scene);
                        scene.setTitle("MyBudget");
                        scene.show();

                        thisWindows.hide();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if(!connected){
            showMessage("Vos paramètres de connexion sont incorrects !");
            TFmail.setText("");
            TFpassword.setText("");
        }

    }

    public void showMessage(String message) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResourceAsStream(View.SHOW_MESSAGE_XML_FILE_PATH));
            root.getStylesheets().add(getClass().getResource("../resources/css/style.css").toExternalForm());
            Stage scene = new Stage();
            scene.setScene(new Scene(root, 300, 150));
            scene.getIcons().add(new Image("resources/img/warning.png"));
            scene.setTitle(View.LABEL_ERROR);
            scene.setResizable(false);
            scene.show();

            ((Show_Message_Controller)loader.getController()).showMessage(message, scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void pushButtonPassword(){
        this.TFpass.setManaged(true);
        this.TFpass.setVisible(true);
        this.TFpassword.setManaged(false);
        this.TFpassword.setVisible(false);
    }

    public void releasedButtonPassword(){
        this.TFpass.setManaged(false);
        this.TFpass.setVisible(false);
        this.TFpassword.setManaged(true);
        this.TFpassword.setVisible(true);
    }

}



