package sample.tools;

import javafx.scene.control.Alert;
import sample.Main;

public class ErorAlert {
    public static void alert(String string) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Main.bundle.getString("error"));
        alert.setHeaderText(Main.bundle.getString("error") +":");
        alert.setContentText(string);

        alert.showAndWait();
    }
}
