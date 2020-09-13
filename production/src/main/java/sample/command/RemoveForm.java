package sample.command;

import GeneralTools.Information;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import sample.Main;
import sample.tools.ErrorAlert;

import static sample.SendCommand.*;

public class RemoveForm {

    @FXML
    private ChoiceBox<String> form;

    @FXML
    private Button RemoveButton;

    @FXML
    void initialize() {
        ObservableList<String> FormList = FXCollections.observableArrayList("Distance", "Full time", "Evening");
        form.setItems(FormList);
        Information information=new Information();
        RemoveButton.setOnAction(event -> {
            information.cmdtype = "remove_any_by_form_of_education";
            information.login = login;
            information.pass = password;
            information.form=form.getValue();
            information.locale= Main.bundle.getLocale();
            information.isUpdate=true;
           if (information.form.isEmpty()) ErrorAlert.alert(Main.bundle.getString("error_null"));
               else client.run(information);
        });
    }

}
