package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import model.Activity;
import view.View;


public class Activity_Element_Controller {
    @FXML
    private Label LBLname;
    @FXML
    private Button BTsuppr;
    @FXML
    private ImageView IVsuppr;

    @FXML
    private BorderPane BPcenter;

    @FXML
    private Label LBLimportance;
    @FXML
    private Label LBLendDate;
    @FXML
    private Label LBLfrequency;
    @FXML
    private Label LBLminBudget;
    @FXML
    private Label LBLmaxBudget;

    private Activity activity;

    public void init(Activity activity){
        this.activity = activity;

        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream(View.CORBEILLE_IMG_PATH)));
        icon.setPreserveRatio(false);
        icon.setFitWidth(20);
        icon.setFitHeight(20);
        BTsuppr.setGraphic(icon);
        BTsuppr.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        this.LBLname.setText(activity.getName());
        this.LBLendDate.setText(activity.getEndDate() == null ? "Infini" : activity.getEndDate().toString());
        this.LBLfrequency.setText(activity.getFrequency().toString());
        this.LBLminBudget.setText(activity.getMinimumBudget().toString());
        this.LBLmaxBudget.setText(activity.getMaximumBudget().toString());
        this.LBLimportance.setText(activity.getImportance().toString());

        switch (activity.getImportance()){
            case Low:
                this.BPcenter.setStyle("-fx-background-color: #" + View.LOW_IMPORTANCE_BACKGROUND_COLOR + "; -fx-border-color: #" + View.LOW_IMPORTANCE_BORDER_COLOR + "; " + this.BPcenter.getStyle());
                break;
            case Medium:
                this.BPcenter.setStyle("-fx-background-color: #" + View.MEDIUM_IMPORTANCE_BACKGROUND_COLOR + "; -fx-border-color: #" + View.MEDIUM_IMPORTANCE_BORDER_COLOR + "; " + this.BPcenter.getStyle());
                break;
            case High:
                this.BPcenter.setStyle("-fx-background-color: #" + View.HIGH_IMPORTANCE_BACKGROUND_COLOR + "; -fx-border-color: #" + View.HIGH_IMPORTANCE_BORDER_COLOR + "; " + this.BPcenter.getStyle());
                break;
        }

    }
}
