package main;

import controller.Connection_Controller;
import controller.ParsingAccounts;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Account;
import view.View;

import java.util.ArrayList;

public class Main extends Application {

    public static Boolean isSplashLoaded = false;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Creating a list of accounts
        ParsingAccounts parsingAccounts= new ParsingAccounts();
        ArrayList<Account> accounts = new ArrayList<>(parsingAccounts.getAccountListFromJSON(View.ACCOUNT_JSON_FILE));

        //create a loader
        FXMLLoader loader = new FXMLLoader();

        Parent root = loader.load(getClass().getResourceAsStream(View.CONNECTION_XML_FILE_PATH));

        //Style
        root.getStylesheets().add(View.CSSR);

        ((Connection_Controller)loader.getController()).init(accounts, primaryStage);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("MyBudget");

        // Set the application icon.
        primaryStage.getIcons().add(new Image("resources/img/appli.jpg"));

        primaryStage.setResizable(false);

        //If people dont connect directly with the remember
        if (!((Connection_Controller)loader.getController()).isDirectlyConnect()) {
            primaryStage.show();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

}
