package main;

import controller.Activity_List_Tab_Controller;
import controller.Connection_Page_Controller;
import controller.Home_Controller;
import controller.ParsingAccounts;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Account;
import view.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Creating a list of accounts
        ParsingAccounts parsingAccounts= new ParsingAccounts();
        final String ACCOUNT_JSON_FILE = "src/resources/json/accounts.json";
        ObservableList<Account> accounts = parsingAccounts.getAccountListFromJSON(ACCOUNT_JSON_FILE);

        //create a loader
        FXMLLoader loader = new FXMLLoader();

        Parent root = loader.load(getClass().getResourceAsStream(View.CONNECTION_XML_FILE_PATH));

        root.getStylesheets().add(View.CSS);

        ((Connection_Page_Controller)loader.getController()).init(accounts);

        primaryStage.setScene(new Scene(root, View.HOMEWIDTH, View.HOMEHEIGHT));
        primaryStage.setTitle("MyBudget");

        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
