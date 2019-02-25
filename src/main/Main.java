package main;

import controller.New_Activity_Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.Activity_List_Controller;
import view.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //create a loader
        FXMLLoader loader = new FXMLLoader();
        Activity_List_Controller controller = new Activity_List_Controller();
        loader.setController(controller);
        Parent root = loader.load(getClass().getResourceAsStream(View.LIST_ACTIVITY_XML_FILE));

        //attach css file
        //root.getStylesheets().add(View.CSS);

        //initialize the controller
        controller.init();

        //create the view
        primaryStage.setScene(new Scene(root, View.WIDTH, View.HEIGHT));
        primaryStage.setTitle(View.LABEL_LIST_ACTIVITY);

        //show the view
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

}
