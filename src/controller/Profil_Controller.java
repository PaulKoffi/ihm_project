package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import model.Account;


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

    private Account account;

    public void init(Account account){
        this.account = account;

        TFname.setText(account.getName());
        TFfirstname.setText(account.getFirstName());
        TFmail.setText(account.getEmail());
        TFpassword.setText(account.getPassword());
        SPsalary.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10000, account.getSalary(), 10));
    }

    public void save(){
        this.account.setName(TFname.getText());
        this.account.setFirstName(TFfirstname.getText());
        this.account.setEmail(TFmail.getText());
        this.account.setPassword(TFpassword.getText());
        this.account.setSalary(SPsalary.getValue());
    }
}
