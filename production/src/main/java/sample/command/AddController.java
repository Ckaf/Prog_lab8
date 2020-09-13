package sample.command;

import GeneralTools.Information;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.Main;
import sample.tools.ErrorAlert;

import static sample.SendCommand.*;

public class AddController {

    @FXML
    private TextField name;

    @FXML
    private TextField count;

    @FXML
    private ChoiceBox<String> exp;

    @FXML
    private TextField adminName;

    @FXML
    private ChoiceBox<String> semester;

    @FXML
    private ChoiceBox<String> form;

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
    private Button AddButton;

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
        AddButton.setOnAction(event -> {
            try {
                CheckNumericField(count.getText());
                CheckNumericField(height.getText());
                CheckNumericField(weight.getText());
                CheckNumericField(x.getText());
                CheckNumericField(y.getText());
                information.setParametrs(name.getText(), count.getText(), exp.getValue(), form.getValue(), semester.getValue(), adminName.getText(),
                        height.getText(), weight.getText(), eyeColor.getValue(), x.getText(), y.getText());
                information.cmdtype = "add";
                information.login = login;
                information.pass = password;
                information.isUpdate = true;
                information.locale = Main.bundle.getLocale();
                if (CheckNull(information)) client.run(information);
                else ErrorAlert.alert(Main.bundle.getString("error_field"));
            } catch (Exception er) {
                ErrorAlert.alert(Main.bundle.getString("error_field"));
            }

        });
    }

    public static void CheckNumericField(String value) {
        try {
            int IntValue = Integer.parseInt(value);
        } catch (Exception e) {

            value = value.replace(",", ".");
                float FloatValue = Float.parseFloat(value);
        }
    }

    public static boolean CheckNull(Information information) {
        if (information.name.isEmpty() || information.count.isEmpty() || information.exp.isEmpty() || information.semestr.isEmpty() ||
                information.form.isEmpty() || information.groupAdmin.isEmpty() || information.height.isEmpty() || information.weight.isEmpty() || information.eyeColor.isEmpty() ||
                information.X.isEmpty() || information.Y.isEmpty()) return false;
        return true;
    }
}

