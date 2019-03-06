package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import model.Activity;
import view.View;

import java.io.File;


public class Activity_Element_Controller {
    @FXML
    private Label LBLname;
    @FXML
    private Button BTsuppr;
    @FXML
    private ImageView IVsuppr;

    @FXML
    private Label LBLduration;
    @FXML
    private Label LBLfrequency;
    @FXML
    private Label LBLminBudget;
    @FXML
    private Label LBLmaxBudget;

    private Activity activity;

    public void init(Activity activity){
        this.activity = activity;

        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("../resources/img/corbeille_318-10194.jpg")));
        icon.setPreserveRatio(false);
        icon.setFitWidth(20);
        icon.setFitHeight(20);
        BTsuppr.setGraphic(icon);
        BTsuppr.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        this.LBLname.setText(activity.getName());
        this.LBLduration.setText(activity.getDuration().toString());
        this.LBLfrequency.setText(activity.getFrequency().toString());
        this.LBLminBudget.setText(activity.getMinimumBudget().toString());
        this.LBLmaxBudget.setText(activity.getMaximumBudget().toString());

    }
}
