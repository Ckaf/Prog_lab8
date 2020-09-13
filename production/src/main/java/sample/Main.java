package sample;


import Cllient.Client;
import GeneralTools.UTF8Control;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    public static String host="localhost";
    public static int port=8000;
    public static ResourceBundle bundle;
    @Override
    public void start(Stage primaryStage) throws Exception{
        bundle =ResourceBundle.getBundle("locals", Locale.forLanguageTag("RU"), new UTF8Control());
        Client.connect(host,port);
        Parent root = FXMLLoader.load(getClass().getResource("/visual/sample.fxml"),bundle);
        primaryStage.setTitle("Lab_8");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
