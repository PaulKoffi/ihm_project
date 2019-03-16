package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Account;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import view.View;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


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
    @FXML
    private Button BTmodify;
    @FXML
    private Button BTsave;

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
        this.BTsave.setDisable(true);
    }

    public void save(){
        String oldMail = account.getEmail();

        for (Account account : AccountsJSONadmin.getAccountListFromJSON(View.ACCOUNT_JSON_FILE)){
            if (!this.account.getEmail().equals(TFmail.getText()) && account.getEmail().equals(TFmail.getText())) {
                this.showMessage("Adresse mail deja utilisée");
                return;
            }
        }

        //POur le remember account
        try {
            FileReader reader = new FileReader(View.ACCOUNT_REMEMBER_JSON_FILE);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            // get an array from the JSON object
            String email = (String) jsonObject.get("email");

            //Si on se souvenez de l'ancien email
            if (email.equals(oldMail) && !oldMail.equals(TFmail.getText())){
                JSONObject json = new JSONObject();
                json.put("email", TFmail.getText());
                FileWriter file = new FileWriter(View.ACCOUNT_REMEMBER_JSON_FILE);
                file.write(json.toJSONString());
                file.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.account.setName(TFname.getText());
        this.account.setFirstName(TFfirstname.getText());
        this.account.setEmail(TFmail.getText());
        this.account.setPassword(TFpassword.getText());
        this.password = TFpassword.getText();
        this.account.setSalary(SPsalary.getValue());

        AccountsJSONadmin.modifyAccountToJSON(oldMail, this.account);

        this.TFpassword.setText(getCryptyPassword(this.password.length()));
        this.GPform.setDisable(true);
        this.BTmodify.setDisable(false);
        this.BTsave.setDisable(true);
    }



    public void modify(){
        this.GPform.setDisable(false);
        this.TFpassword.setText(this.password);
        this.BTmodify.setDisable(true);
        this.BTsave.setDisable(false);
    }

    private String getCryptyPassword(int size){
        String str = "";
        for (int i = 0; i < size; ++i) str += "\u2022";
        return str;
    }

    public void showMessage(String message) {
        try {
            FXMLLoader loaderr = new FXMLLoader();
            Parent root = loaderr.load(getClass().getResourceAsStream(View.SHOW_MESSAGE_XML_FILE_PATH));
            root.getStylesheets().add(getClass().getResource("../resources/css/style.css").toExternalForm());
            Stage scene = new Stage();
            scene.setScene(new Scene(root, 300, 150));
            scene.setTitle(View.LABEL_ERROR);
            scene.setResizable(false);
            scene.getIcons().add(new Image("resources/img/warning.png"));
            scene.show();

            ((Show_Message_Controller)loaderr.getController()).showMessage(message, scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
