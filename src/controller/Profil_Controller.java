package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ContentDisplay;
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
    private ImageView IVkeyPassword;
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

        this.IVkeyPassword.setImage(new Image(getClass().getResourceAsStream(View.KEY_PASSWORD_IMG_PATH)));
        this.imgU.setImage(new Image(getClass().getResourceAsStream(View.U_IMG_PATH)));
        this.GPform.setDisable(false);
        this.disable(true);
    }

    public void save(){
        this.account.setName(TFname.getText());
        this.account.setFirstName(TFfirstname.getText());
        this.account.setEmail(TFmail.getText());
        this.account.setPassword(TFpassword.getText());
        this.account.setSalary(SPsalary.getValue());
        this.disable(true);
    }

    public void modify(){
        this.disable(false);
    }

    public void pushButtonPassword(){
        this.TFpassword.setText(password);
    }


    public void releasedButtonPassword(){
        this.password = this.TFpassword.getText();
        this.TFpassword.setText(getCryptyPassword(this.password.length()));
    }

    private String getCryptyPassword(int size){
        String str = "";
        for (int i = 0; i < size; ++i) str += "\u2022";
        return str;
    }

    public void keyPressed(){
        String cryptedPassword = this.TFpassword.getText();
        for (int i = 0; i < cryptedPassword.length(); ++i){
            if (cryptedPassword.charAt(i) != '\u2022'){
                String tmpPassword = "";
                for (int j = 0; j < i; ++j){
                    tmpPassword += this.password.charAt(j);
                }
                tmpPassword += cryptedPassword.charAt(i);
                for (int j = i; j < this.password.length(); ++j){
                    tmpPassword += this.password.charAt(j);
                }
                this.password = tmpPassword;
            }
        }
        this.TFpassword.setText(getCryptyPassword(this.password.length()));
    }

    private void disable(boolean bool){
        this.TFname.setDisable(bool);
        this.TFfirstname.setDisable(bool);
        this.TFmail.setDisable(bool);
        this.TFpassword.setDisable(bool);
        this.SPsalary.setDisable(bool);
    }
}
