package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Account;
import view.View;


public class Profil_Controller {
    @FXML
    private TextField TFname;
    @FXML
    private TextField TFfirstname;
    @FXML
    private TextField TFmail;
    @FXML
    private TextField TFpassword;
    @FXML
    private Spinner<Double> SPsalary;
    @FXML
    private GridPane GPform;
    @FXML
    private ImageView imgU;

    private String password;

    private Account account;

    public void init(Account account){
        this.account = account;

        this.password = account.getPassword();
        this.TFpassword.setText(getCryptyPassword(this.password.length()));

        this.TFname.setText(account.getName());
        this.TFfirstname.setText(account.getFirstName());
        this.TFmail.setText(account.getEmail());
        this.SPsalary.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10000, account.getSalary(), 10));

        this.imgU.setImage(new Image(getClass().getResourceAsStream(View.UTILISATEUR_IMG_PATH)));
        this.GPform.setDisable(true);
    }

    public void save(){
        this.account.setName(TFname.getText());
        this.account.setFirstName(TFfirstname.getText());
        this.account.setEmail(TFmail.getText());
        this.account.setPassword(TFpassword.getText());
        this.account.setSalary(SPsalary.getValue());
        this.TFpassword.setText(getCryptyPassword(this.password.length()));
        this.GPform.setDisable(true);
    }

    public void modify(){
        this.GPform.setDisable(false);
        this.TFpassword.setText(this.password);
    }

    private String getCryptyPassword(int size){
        String str = "";
        for (int i = 0; i < size; ++i) str += "\u2022";
        return str;
    }
}
