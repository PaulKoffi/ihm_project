package main;

import controller.Connection_Controller;
import controller.ParsingAccounts;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Account;
import view.View;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Creating a list of accounts
        ParsingAccounts parsingAccounts= new ParsingAccounts();
        final String ACCOUNT_JSON_FILE = "src/resources/json/accounts.json";
        ArrayList<Account> accounts = new ArrayList<>(parsingAccounts.getAccountListFromJSON(ACCOUNT_JSON_FILE));

        //create a loader
        FXMLLoader loader = new FXMLLoader();

        Parent root = loader.load(getClass().getResourceAsStream(View.CONNECTION_XML_FILE_PATH));

        root.getStylesheets().add(View.CSS);

        ((Connection_Controller)loader.getController()).init(accounts, primaryStage);

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("MyBudget");

        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
