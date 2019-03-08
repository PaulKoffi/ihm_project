package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import model.Activity;
import view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Activity_Element_Controller {
    @FXML
    private Label LBLname;
    @FXML
    private ImageView IVsuppr;

    @FXML
    private BorderPane BPcenter;

    @FXML
    private Label LBLimportance;
    @FXML
    private Label LBLendDate;
    @FXML
    private Label LBLfini;
    @FXML
    private Label LBLfrequency;
    @FXML
    private Label LBLminBudget;
    @FXML
    private Label LBLmaxBudget;

    private ObservableList<Activity> activities;

    private Activity activity;

    private Activity_List_Tab_Controller activity_List_Tab_Controller;

    public void init(ObservableList<Activity> activities, Activity activity, Activity_List_Tab_Controller activity_List_Tab_Controller){
        this.activity_List_Tab_Controller = activity_List_Tab_Controller;
        this.activities = activities;
        this.activity = activity;
        this.activity_List_Tab_Controller = activity_List_Tab_Controller;

        this.blackTrash();

        this.LBLname.setText(activity.getName());
        if (activity.getEndDate() == null){
            this.LBLfini.setText("");
            this.LBLendDate.setText("Infini");
        } else {
            this.LBLendDate.setText(new SimpleDateFormat("dd/MM/yy").format(activity.getEndDate()));
        }
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

    public void redTrash(){
        this.IVsuppr.setImage(new Image(getClass().getResourceAsStream(View.RED_TRASH_IMG_PATH)));
    }

    public void blackTrash(){
        this.IVsuppr.setImage(new Image(getClass().getResourceAsStream(View.BLACK_TRASH_IMG_PATH)));
    }

    public void delete(){
        this.activities.remove(activity);
        this.activity_List_Tab_Controller.refreshGridPane();
    }
}
