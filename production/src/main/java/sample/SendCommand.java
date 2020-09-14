package sample;

import Cllient.Client;
import Cllient.ExecuteScript;
import Cllient.Students;
import GeneralTools.Information;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.tools.ExecuteAlert;


import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class SendCommand {
    public static String password;
    public static String login;
    static Information information = new Information();
    public static Client client = new Client();
    public static ServerSocket ss;
    static {
        try {
            ss = new ServerSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Autorizaton(String pass, String log) {
        information.cmdtype = "connect";
        information.regtype = "aut";
        information.login = log;
        information.pass = pass;
        information.address=generateAddress();
        information.locale=Main.bundle.getLocale();
        client.run(information);
        password = pass;
        login = log;
    }

    public static void Registration(String pass, String log) {
        information.cmdtype = "connect";
        information.regtype = "reg";
        information.address=generateAddress();
        information.login = log;
        information.pass = pass;
        information.locale=Main.bundle.getLocale();
        client.run(information);
        password = pass;
        login = log;
    }

    public static void help() {
        information.cmdtype = "help";
        information.login = login;
        information.pass = password;
        information.locale=Main.bundle.getLocale();
        client.run(information);
    }

    public static void info() {
        information.cmdtype = "info";
        information.login = login;
        information.pass = password;
        information.locale=Main.bundle.getLocale();
        client.run(information);
    }

    public static void show(TableView<Students> table) {
        information.cmdtype = "show";
        information.login = login;
        information.pass = password;
        information.locale=Main.bundle.getLocale();
        client.run(information, table);
    }

    public static void add(TableView<Students> table) {
        show("/visual/add.fxml");
    }

    public static void update(TableView<Students> table) {
        show("/visual/update.fxml");
    }

    public static void remove_by_id(TableView<Students> table) {
        show("/visual/remove.fxml");
    }

    public static void clear(TableView<Students> table) {
        information.cmdtype = "clear";
        information.login = login;
        information.pass = password;
        information.locale=Main.bundle.getLocale();
        client.run(information);
    }

    public static void head() {
        information.cmdtype = "head";
        information.login = login;
        information.pass = password;
        information.locale=Main.bundle.getLocale();
        client.run(information);
    }

    public static void remove_head(TableView<Students> table) {
        information.cmdtype = "remove_head";
        information.login = login;
        information.pass = password;
        information.isUpdate=true;
        information.locale=Main.bundle.getLocale();
        client.run(information);
        information.isUpdate=false;
    }

    public static void remove_lower(TableView<Students> table) {
        show("/visual/remove_lower.fxml");
    }

    public static void remove_any_by_form_education(TableView<Students> table) {
        show("/visual/remove_form.fxml");
    }

    public static void filter_strats_with_name(TableView<Students> table) {
        show("/visual/filter_name.fxml");
    }

    public static void filter_greater_than_students_count(TableView<Students> table) {
        show("/visual/filter_count.fxml");
    }

    public static void getColor(Rectangle ColorRect){
        information.cmdtype="getColor";
        information.login = login;
        information.pass = password;
        client.run(information,ColorRect);
    }
    public static void execute(Stage stage,TableView<Students> table) throws IOException, ClassNotFoundException {
        FileChooser fileChooser=new FileChooser();
        File file=fileChooser.showOpenDialog(stage);
        if (file!=null){
            ExecuteScript.execute(file.getPath(),login,password,table);
        }
    }
    public static void execute_1(Stage stage,TableView<Students> table) throws IOException, ClassNotFoundException {
        show("/visual/execute.fxml");
    }
    public static void show(String path) {
        FXMLLoader fxmlLoader = new FXMLLoader(SendCommand.class.getResource(path),Main.bundle);
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Lab 8");
        stage.setScene(new Scene(root1));
        stage.showAndWait();
    }

    public static InetSocketAddress generateAddress(){

            try {
                ss.bind(new InetSocketAddress(0));
            } catch (IOException e) {

            }
        InetSocketAddress socketAddress=new InetSocketAddress("localhost",ss.getLocalPort());

        return socketAddress;
    }
}
