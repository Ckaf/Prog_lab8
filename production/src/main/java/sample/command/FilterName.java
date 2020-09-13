package sample.command;

import GeneralTools.Information;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;

import static sample.SendCommand.*;

public class FilterName {

    @FXML
    private TextField name;

    @FXML
    private Button ShowButton;

    @FXML
    private Label ShowLabel;

    @FXML
    void initialize() {
        ShowButton.setOnAction(event -> {
            Information information=new Information();
                information.cmdtype = "filter_starts_with_name";
                information.login = login;
                information.pass = password;
                information.name= name.getText();
                information.locale= Main.bundle.getLocale();
                if (information.name.isEmpty())ShowLabel.setText("Поле не может быть пустым!");
               else client.run(information);
        });
    }

}
