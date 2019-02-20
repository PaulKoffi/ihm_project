package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.Controller;
import model.Activity;
import model.Duration;
import model.Frequency;
import view.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //create a loader
        FXMLLoader loader = new FXMLLoader();

        //create a controller
        Controller controller = new Controller();

        //attach controller
        loader.setController(controller);

        //attach XML file
        Parent root = loader.load(getClass().getResourceAsStream(View.XML_FILE));

        //attach css file
        //root.getStylesheets().add(View.CSS);

        //initialize the controller
        controller.init();

        //create the view
        primaryStage.setScene(new Scene(root, View.WIDTH, View.HEIGHT));
        primaryStage.setTitle(View.LABEL);

        //show the view
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }

}
