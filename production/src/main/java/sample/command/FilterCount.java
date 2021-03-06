package sample.command;

import GeneralTools.Information;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;
import sample.tools.ErrorAlert;

import static sample.SendCommand.*;
import static sample.command.AddController.CheckNumericField;

public class FilterCount {

    @FXML
    private TextField count;

    @FXML
    private Button ShowButton;

    @FXML
    private Label ShowLabel;

    @FXML
    void initialize() {
        ShowButton.setOnAction(event -> {
            Information information=new Information();
        try {
            CheckNumericField(count.getText());
            information.cmdtype = "filter_greater_than_students_count";
            information.login = login;
            information.pass = password;
            information.count=count.getText();
            information.locale= Main.bundle.getLocale();
            client.run(information);
        }
        catch (Exception e){
            ErrorAlert.alert("Неправильно заполнено поле");
        }
        });
    }
}
