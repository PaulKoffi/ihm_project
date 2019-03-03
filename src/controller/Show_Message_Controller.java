package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.View;

public class Show_Message_Controller {
    @FXML
    private ImageView imageView;
    @FXML
    private Text Tmessage;
    @FXML
    private Button buttonOk;

    public void showMessage(String message, Stage window){
        imageView.setImage(new Image(View.SHOW_MESSAGE_ERROR_IMG_FILE_PATH));
        Tmessage.setText(message);
        buttonOk.setOnAction(event -> window.close());
    }
}
