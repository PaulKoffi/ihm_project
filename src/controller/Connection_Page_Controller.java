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

public class Connection_Page_Controller {


    @FXML
    private TextField TFmail;
    @FXML
    private TextField TFpassword;
    @FXML
    private CheckBox CBremember;
    @FXML
    private Button BTconnection;
    @FXML
    private Label LnewAccount;
    @FXML
    private Label Lforgot;


    private Stage window;

    public void init(ObservableList<Account> accounts) {

        String id = TFmail.getText();
        String password = TFpassword.getText();


        BTconnection.setOnAction(event -> pushButtonConnection(accounts,id,password));

        this.window.close();

    }


    public void pushButtonConnection(ObservableList<Account> accounts,String id, String password) {
        //Checking required fields
        if (id.equals("") || password.equals("")) {
            showMessage("Veuillez remplir tous les champs Obligatoires !");
            return;
        }

        //Checking if account already exist
        for ( Account account: accounts) {
            if (account.getEmail().equals(id)){
                if (account.getPassword().equals(password)){

                    FXMLLoader loader = new FXMLLoader();
                    Home_Controller controller = new Home_Controller();
                    loader.setController(controller);
                    Parent root = null;
                    try {
                        root = loader.load(getClass().getResourceAsStream("../resources/fxml/Home.fxml"));
                        Stage scene = new Stage();
                        scene.setScene(new Scene(root, 600, 450));

                        controller.init(account);
                        scene.setTitle("MyBudget - Home");
                        scene.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }



    }


    public void showMessage(String message) {
        FXMLLoader loader = new FXMLLoader();
        Show_Message_Controller controller = new Show_Message_Controller();
        loader.setController(controller);
        Parent root = null;
        try {
            root = loader.load(getClass().getResourceAsStream(View.SHOW_MESSAGE_XML_FILE_PATH));

            Stage scene = new Stage();
            scene.setScene(new Scene(root, 300, 150));
            scene.setTitle(View.LABEL_ERROR);
            scene.show();

            controller.showMessage(message, scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



