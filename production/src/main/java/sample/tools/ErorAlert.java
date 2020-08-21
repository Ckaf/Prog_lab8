package sample.tools;

import javafx.scene.control.Alert;

public class ErorAlert {
    public static void alert(String string) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Беды с исполнением");
        alert.setHeaderText("Ошибка:");
        alert.setContentText(string);

        alert.showAndWait();
    }
}
