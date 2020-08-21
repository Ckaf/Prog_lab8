package sample.command;

import GeneralTools.Information;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.tools.ErorAlert;

import static sample.command.AddController.CheckNumericField;
import static sample.SendCommand.*;

public class UpdateController {

    @FXML
    private TextField name;

    @FXML
    private TextField count;

    @FXML
    private ChoiceBox<String> exp;

    @FXML
    private ChoiceBox<String> form;

    @FXML
    private TextField adminName;

    @FXML
    private ChoiceBox<String> semester;

    @FXML
    private TextField height;

    @FXML
    private ChoiceBox<String> eyeColor;

    @FXML
    private TextField weight;

    @FXML
    private TextField x;

    @FXML
    private TextField y;

    @FXML
    private Button UpdateButton;

    @FXML
    private TextField id;

    @FXML
    void initialize() {
        ObservableList<String> SemesterList = FXCollections.observableArrayList("5", "6", "7", "8");
        ObservableList<String> ColorList = FXCollections.observableArrayList("RED", "BLACK", "ORANGE", "BROWN");
        ObservableList<String> ExpList = FXCollections.observableArrayList("yes", "no");
        ObservableList<String> FormList = FXCollections.observableArrayList("Distance", "Full time", "Evening");
        semester.setItems(SemesterList);
        eyeColor.setItems(ColorList);
        exp.setItems(ExpList);
        form.setItems(FormList);
        Information information = new Information();
        UpdateButton.setOnAction(event -> {
            try {
                CheckNumericField(id.getText());
                CheckNumericField(count.getText());
                CheckNumericField(height.getText());
                CheckNumericField(weight.getText());
                CheckNumericField(x.getText());
                CheckNumericField(y.getText());
                information.idstr=id.getText();
                information.setParametrs(name.getText(), count.getText(), exp.getValue(), form.getValue(), semester.getValue(), adminName.getText(),
                        height.getText(), weight.getText(), eyeColor.getValue(), x.getText(), y.getText());
                information.cmdtype = "update";
                information.login = login;
                information.pass = password;
                information.isUpdate=true;
                client.run(information);
            } catch (Exception er) {
                er.printStackTrace();
                ErorAlert.alert("Неправильно заполнены поля");
            }
        });
    }
}
