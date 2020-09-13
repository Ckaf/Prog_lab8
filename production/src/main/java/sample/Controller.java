package sample;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import Cllient.Client;
import GeneralTools.UTF8Control;
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
import sample.tools.ErrorAlert;

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
        final ResourceBundle[] bundle = {Main.bundle};
        ObservableList<String> lang = FXCollections.observableArrayList(bundle[0].getString("rus_lang"), bundle[0].getString("rs_lang"), bundle[0].getString("al_lang"), bundle[0].getString("es_lang"));
        if (bundle[0].getLocale().toString().equals("ru")) language.setValue(bundle[0].getString("rus_lang"));
        if (bundle[0].getLocale().toString().equals("rs")) language.setValue(bundle[0].getString("rs_lang"));
        if (bundle[0].getLocale().toString().equals("al")) language.setValue(bundle[0].getString("al_lang"));
        if (bundle[0].getLocale().toString().equals("es")) language.setValue(bundle[0].getString("es_lang"));
        language.setItems(lang);
        language.setOnAction(event -> {
            if (language.getValue().equals(bundle[0].getString("rus_lang"))) {
                bundle[0] = (ResourceBundle.getBundle("locals", Locale.forLanguageTag("RU"), new UTF8Control()));
            }
            if (language.getValue().equals(bundle[0].getString("rs_lang"))) {
                bundle[0] = (ResourceBundle.getBundle("locals", Locale.forLanguageTag("RS"), new UTF8Control()));
            }
            if (language.getValue().equals(bundle[0].getString("al_lang"))) {
                bundle[0] = (ResourceBundle.getBundle("locals", Locale.forLanguageTag("AL"), new UTF8Control()));
            }
            if (language.getValue().equals(bundle[0].getString("es_lang"))) {
                bundle[0] = (ResourceBundle.getBundle("locals", Locale.forLanguageTag("ES"), new UTF8Control()));
            }
            Stage mainStage = (Stage) registration.getScene().getWindow();
            Main.bundle = bundle[0];
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/visual/sample.fxml"), bundle[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root, 700, 400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            mainStage.close();
        });
        entrance.setOnAction(event -> {
            if (!pass_field.getText().isEmpty() && !login_field.getText().isEmpty()) {
                SendCommand.Autorizaton(pass_field.getText(), login_field.getText());
                if (Client.reconnection_flag==true){
                    Client.reconnection_flag=false;
                    Client.connect(Main.host,Main.port);
                    return;
                }
                Stage stage = (Stage) registration.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/visual/work.fxml"), Main.bundle);
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
                Stage mainStage = (Stage) registration.getScene().getWindow();
                mainStage.close();
            } else {
                ErrorAlert.alert(bundle[0].getString("error_null"));
            }
        });
        registration.setOnAction(event -> {

            if (!pass_field.getText().isEmpty() && !login_field.getText().isEmpty()) {
                if (Client.reconnection_flag==true){
                    Client.reconnection_flag=false;
                    Client.connect(Main.host,Main.port);
                    return;
                }
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
                ErrorAlert.alert(bundle[0].getString("error_null"));
            }
        });

    }
}
