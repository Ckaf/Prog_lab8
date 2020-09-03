package sample.tools;

import Cllient.ExecuteScript;
import Cllient.Students;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.SendCommand;

import java.io.File;
import java.io.IOException;

public class ExecuteAlert {
    @FXML
    private TextArea text;

    @FXML
    private Button n_but;

    @FXML
    private Button y_but;
    @FXML
    void initialize() {
        y_but.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Stage());
            if (file!=null) {
                try {
                    ExecuteScript.execute(file.getPath(), SendCommand.login,SendCommand.password,ExecuteScript.tab);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
       n_but.setOnAction(event -> {
         Stage stage= (Stage)y_but.getScene().getWindow();
         stage.close();
       });
    }
}
