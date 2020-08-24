package sample.tools;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import sample.Main;

public class MessageAlert {
    public static void showMessage(String cmd) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Main.bundle.getString("message"));

        VBox dialogPaneContent = new VBox();

        Label label = new Label(Main.bundle.getString("message")+":");

        String stackTrace = cmd;
        TextArea textArea = new TextArea();
        textArea.setText(stackTrace);

        dialogPaneContent.getChildren().addAll(label,textArea);

        alert.getDialogPane().setContent(dialogPaneContent);

        alert.showAndWait();
    }
}
