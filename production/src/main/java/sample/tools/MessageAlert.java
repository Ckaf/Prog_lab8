package sample.tools;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class MessageAlert {
    public static void showMessage(String cmd) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");

        VBox dialogPaneContent = new VBox();

        Label label = new Label("Stack Trace:");

        String stackTrace = cmd;
        TextArea textArea = new TextArea();
        textArea.setText(stackTrace);

        dialogPaneContent.getChildren().addAll(label,textArea);

        alert.getDialogPane().setContent(dialogPaneContent);

        alert.showAndWait();
    }
}
