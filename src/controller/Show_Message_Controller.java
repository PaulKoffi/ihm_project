package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Show_Message_Controller {
    @FXML
    private Button buttonOk;
    @FXML
    private Text Tmessage;

    public void showMessage(String message, Stage window){
        Tmessage.setText(message);
        buttonOk.setOnAction(event -> window.close());
    }
}
