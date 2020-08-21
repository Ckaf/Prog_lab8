package sample.command;

import GeneralTools.Information;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.SendCommand;

import static sample.SendCommand.*;


public class Remove {

    @FXML
    private TextField id;

    @FXML
    private Button RemoveButton;

    @FXML
    private Label RemoveLabel;

    @FXML
    void initialize() {
        Information information=new Information();
        RemoveButton.setOnAction(event -> {
           try {
               int ch= Integer.parseInt(id.getText());
               information.cmdtype = "remove_by_id";
               information.login = login;
               information.pass = password;
               information.idstr=id.getText();
               information.isUpdate=true;
               client.run(information);
           }
           catch (Exception e){
               RemoveLabel.setText("Неправильно заполнено поле");
           }
        });
    }
}
