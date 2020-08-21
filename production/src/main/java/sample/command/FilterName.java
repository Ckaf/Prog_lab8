package sample.command;

import GeneralTools.Information;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
            if (name.getText().equals(null)) ShowLabel.setText("Поле не может быть пустым!");
            else {
                information.cmdtype = "filter_starts_with_name";
                information.login = login;
                information.pass = password;
                information.name= name.getText();
                client.run(information);
            }
        });
    }

}
