package sample;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.tools.ErorAlert;

public class Controller {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField pass_field;

    @FXML
    private Button entrance;

    @FXML
    private Button registration;

    @FXML
    public ChoiceBox<String> language;

    @FXML
    void initialize() throws IOException {
         ResourceBundle bundle = ResourceBundle.getBundle("locals",Locale.getDefault(), new UTF8Control());

       // ResourceBundle bundle = ResourceBundle.getBundle("locals", Locale.forLanguageTag("RU"), new UTF8Control());
        ObservableList<String> lang = FXCollections.observableArrayList(bundle.getString("rus_lang"), bundle.getString("is_lang"), bundle.getString("pl_lang"), bundle.getString("es_lang"));
        language.setItems(lang);

        System.out.println(lang);
        entrance.setOnAction(event -> {
            if (!pass_field.getText().isEmpty() && !login_field.getText().isEmpty()) {
                SendCommand.Autorizaton(pass_field.getText(), login_field.getText());
                Stage stage = (Stage) registration.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/visual/work.fxml"));
                Parent root1 = null;
                try {
                    root1 = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Lab 8");
                stage.setScene(new Scene(root1));
                stage.show();
            } else {
                ErorAlert.alert("Ошибка ввода данных, поля не могут быть пустыми");
            }
        });
        registration.setOnAction(event -> {
            if (!pass_field.getText().isEmpty() && !login_field.getText().isEmpty()) {
                SendCommand.Registration(pass_field.getText(), login_field.getText());

                Stage stage = (Stage) registration.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/visual/work.fxml"));
                Parent root1 = null;
                try {
                    root1 = (Parent) fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Lab 8");
                stage.setScene(new Scene(root1));
                stage.show();
            } else {
                ErorAlert.alert("Ошибка ввода данных, поля не могут быть пустыми");
            }
        });

    }
}
