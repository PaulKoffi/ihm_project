package main;

import controller.Activity_List_Tab_Controller;
import controller.Home_Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //create a loader
        FXMLLoader loader = new FXMLLoader();

        Home_Controller controller = new Home_Controller();
        loader.setController(controller);

        Parent root = loader.load(getClass().getResourceAsStream(View.HOME_XML_FILE_PATH));

        root.getStylesheets().add(View.CSS);

        controller.init();

        primaryStage.setScene(new Scene(root, View.HOMEWIDTH, View.HOMEHEIGHT));
        primaryStage.setTitle("MyBudget");

        /*FXMLLoader loader = new FXMLLoader();

        Activity_List_Tab_Controller controller = new Activity_List_Tab_Controller();
        loader.setController(controller);

        Parent root = loader.load(getClass().getResourceAsStream(View.ACTIVITY_LIST_TAB_XML_FILE_PATH));

        root.getStylesheets().add(View.CSS);

        controller.init();

        primaryStage.setScene(new Scene(root, View.TABWIDTH, View.TABHEIGHT));
        primaryStage.setTitle("MyBudget");*/

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
