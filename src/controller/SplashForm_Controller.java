package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashForm_Controller implements Initializable {
    @FXML
    private static StackPane splashPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //pgBar.setProgress(0.0);
    }

    /*public static class bg_Thread implements Runnable{
        public void run(){
            for (int i=0; i<100; i++){
                try {
                    pgBar.setProgress(i/100.0);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
}
