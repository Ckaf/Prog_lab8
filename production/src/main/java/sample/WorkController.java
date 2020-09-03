package sample;

import Cllient.Students;
import GeneralTools.UTF8Control;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sample.tools.ErorAlert;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

import static sample.Paint.CheckFigure;
import static sample.SendCommand.*;
import static sample.SendCommand.client;
import static sample.command.AddController.CheckNumericField;


public class WorkController {
    @FXML
    private Button help;

    @FXML
    private Button show;

    @FXML
    private Button info;

    @FXML
    private Button add;

    @FXML
    private Button update;

    @FXML
    private Button remove_by_id;

    @FXML
    private Button clear;

    @FXML
    private Button head;

    @FXML
    private Button remove_head;

    @FXML
    private Button remove_lower;

    @FXML
    private Button remove_any_by_form_education;

    @FXML
    private Button filter_strats_with_name;

    @FXML
    private Button filter_greater_than_students_count;

    @FXML
    private Button exit;

    @FXML
    public TableView<Students> table;

    @FXML
    public TableColumn<Students, String> id_column;

    @FXML
    public TableColumn<Students, String> name_column;

    @FXML
    public TableColumn<Students, String> count_column;

    @FXML
    public TableColumn<Students, String> exp_column;

    @FXML
    public TableColumn<Students, String> form_column;

    @FXML
    public TableColumn<Students, String> semester_column;

    @FXML
    public TableColumn<Students, String> admin_name_column;

    @FXML
    public TableColumn<Students, String> height_column;

    @FXML
    public TableColumn<Students, String> weight_column;

    @FXML
    public TableColumn<Students, String> eye_color_column;

    @FXML
    public TableColumn<Students, String> x_column;

    @FXML
    public TableColumn<Students, String> y_column;

    @FXML
    private Canvas canvas;

    @FXML
    private Label UserLabel;

    @FXML
    private Rectangle ColorRect;

    @FXML
    private TableColumn<Students, String> login_column;

    @FXML
    public ChoiceBox<String> langBox;
    @FXML
    private Button execute;
    @FXML
    void initialize() {
        final ResourceBundle[] bundle = {Main.bundle};
        ObservableList<String> lang = FXCollections.observableArrayList(bundle[0].getString("rus_lang"), bundle[0].getString("rs_lang"), bundle[0].getString("al_lang"), bundle[0].getString("es_lang"));
        if(bundle[0].getLocale().toString().equals("ru"))langBox.setValue(bundle[0].getString("rus_lang"));
        if(bundle[0].getLocale().toString().equals("rs"))langBox.setValue(bundle[0].getString("rs_lang"));
        if(bundle[0].getLocale().toString().equals("al"))langBox.setValue(bundle[0].getString("al_lang"));
        if(bundle[0].getLocale().toString().equals("es"))langBox.setValue(bundle[0].getString("es_lang"));
        langBox.setItems(lang);
        langBox.setOnAction(event -> {
            if (langBox.getValue().equals(bundle[0].getString("rus_lang"))) bundle[0] =(ResourceBundle.getBundle("locals", Locale.forLanguageTag("RU"), new UTF8Control()));
            if (langBox.getValue().equals(bundle[0].getString("rs_lang"))) bundle[0] =(ResourceBundle.getBundle("locals", Locale.forLanguageTag("RS"), new UTF8Control()));
            if (langBox.getValue().equals(bundle[0].getString("al_lang"))) bundle[0] =(ResourceBundle.getBundle("locals", Locale.forLanguageTag("AL"), new UTF8Control()));
            if (langBox.getValue().equals(bundle[0].getString("es_lang"))) bundle[0] =(ResourceBundle.getBundle("locals", Locale.forLanguageTag("ES"), new UTF8Control()));
            Stage mainStage = (Stage) ColorRect.getScene().getWindow();
            Main.bundle=bundle[0];
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/visual/work.fxml"),bundle[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene=new Scene(root, 1035, 400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            mainStage.close();
        });

        UserLabel.setText(login);
        Runnable task = () -> {
            Synchronization.synchronization(table);
        };
        Thread thread = new Thread(task);
        thread.start();

        getColor(ColorRect);
        CanvasWork();
        canvas.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();
            CheckFigure(x, y, table);
        });
        columnSettings();

        UserLabel.setText(login);
        help.setOnAction(event -> {
            SendCommand.help();
        });
        info.setOnAction(event -> {
            SendCommand.info();
        });
        show.setOnAction(event -> {
            SendCommand.show(table);
        });
        add.setOnAction(event -> {
            SendCommand.add(table);
        });
        update.setOnAction(event -> {
            SendCommand.update(table);
        });
        remove_by_id.setOnAction(event -> {
            SendCommand.remove_by_id(table);
        });
        clear.setOnAction(event -> {
            SendCommand.clear(table);
        });
        head.setOnAction(event -> {
            SendCommand.head();
        });
        remove_head.setOnAction(event -> {
            SendCommand.remove_head(table);
        });
        remove_lower.setOnAction(event -> {
            SendCommand.remove_lower(table);
        });
        remove_any_by_form_education.setOnAction(event -> {
            SendCommand.remove_any_by_form_education(table);
        });
        filter_strats_with_name.setOnAction(event -> {
            SendCommand.filter_strats_with_name(table);
        });
        filter_greater_than_students_count.setOnAction(event -> {
            SendCommand.filter_greater_than_students_count(table);
        });
        exit.setOnAction(event -> {
            System.exit(0);
        });
        table.setEditable(true);
        EditTable();

        execute.setOnAction(event -> {
            try {
                SendCommand.execute(new Stage(),table);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }


    public void FillTable(LinkedList list, TableView<Students> table) {
        table.setItems(getStudents(list));
        Paint.DrawElement(table);
    }

    public static ObservableList<Students> getStudents(LinkedList<String> list) {
        ObservableList<Students> students = FXCollections.observableArrayList();
        list.stream().forEach(element -> {
            String[] str = element.split(",");
            students.add(new Students(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7],
                    str[8], str[9], str[10], str[11], str[12], str[13]));
        });

        return students;
    }

    public void columnSettings() {
        id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        count_column.setCellValueFactory(new PropertyValueFactory<>("count"));
        exp_column.setCellValueFactory(new PropertyValueFactory<>("exp"));
        form_column.setCellValueFactory(new PropertyValueFactory<>("form"));
        semester_column.setCellValueFactory(new PropertyValueFactory<>("semester"));
        admin_name_column.setCellValueFactory(new PropertyValueFactory<>("admin_name"));
        height_column.setCellValueFactory(new PropertyValueFactory<>("height"));
        weight_column.setCellValueFactory(new PropertyValueFactory<>("weight"));
        eye_color_column.setCellValueFactory(new PropertyValueFactory<>("eyeColor"));
        x_column.setCellValueFactory(new PropertyValueFactory<>("x"));
        y_column.setCellValueFactory(new PropertyValueFactory<>("y"));
        login_column.setCellValueFactory(new PropertyValueFactory<>("User"));
    }

    public void CanvasWork() {
        Paint.setCanvas(canvas);
        Paint.drawAxis();
    }

    public void setColor(String color, Rectangle ColorRect) {
        ColorRect.setFill(javafx.scene.paint.Paint.valueOf(color));
    }

    public void EditTable() {
        name_column.setCellFactory(TextFieldTableCell.forTableColumn());
        count_column.setCellFactory(TextFieldTableCell.forTableColumn());
        exp_column.setCellFactory(TextFieldTableCell.forTableColumn());
        form_column.setCellFactory(TextFieldTableCell.forTableColumn());
        semester_column.setCellFactory(TextFieldTableCell.forTableColumn());
        admin_name_column.setCellFactory(TextFieldTableCell.forTableColumn());
        height_column.setCellFactory(TextFieldTableCell.forTableColumn());
        weight_column.setCellFactory(TextFieldTableCell.forTableColumn());
        eye_color_column.setCellFactory(TextFieldTableCell.forTableColumn());
        x_column.setCellFactory(TextFieldTableCell.forTableColumn());
        y_column.setCellFactory(TextFieldTableCell.forTableColumn());

        name_column.setOnEditCommit(commit -> {
            Students students=commit.getTableView().getItems().get(commit.getTablePosition().getRow());
            students.setName(commit.getNewValue());
            SendUpdate(students);
        });
        count_column.setOnEditCommit(commit -> {
            Students students=commit.getTableView().getItems().get(commit.getTablePosition().getRow());
            students.setCount(commit.getNewValue());
            SendUpdate(students);
        });
        exp_column.setOnEditCommit(commit -> {
            Students students=commit.getTableView().getItems().get(commit.getTablePosition().getRow());
            students.setExp(commit.getNewValue());
            SendUpdate(students);
        });
        form_column.setOnEditCommit(commit -> {
            Students students=commit.getTableView().getItems().get(commit.getTablePosition().getRow());
            students.setForm(commit.getNewValue());
            SendUpdate(students);
        });
        semester_column.setOnEditCommit(commit -> {
            Students students=commit.getTableView().getItems().get(commit.getTablePosition().getRow());
            students.setSemester(commit.getNewValue());
            SendUpdate(students);
        });
        admin_name_column.setOnEditCommit(commit -> {
            Students students=commit.getTableView().getItems().get(commit.getTablePosition().getRow());
            students.setAdmin_name(commit.getNewValue());
            SendUpdate(students);
        });
        height_column.setOnEditCommit(commit -> {
            Students students=commit.getTableView().getItems().get(commit.getTablePosition().getRow());
            students.setHeight(commit.getNewValue());
            SendUpdate(students);
        });
        weight_column.setOnEditCommit(commit -> {
            Students students=commit.getTableView().getItems().get(commit.getTablePosition().getRow());
            students.setWeight(commit.getNewValue());
            SendUpdate(students);
        });
        eye_color_column.setOnEditCommit(commit -> {
            Students students=commit.getTableView().getItems().get(commit.getTablePosition().getRow());
            students.setEyeColor(commit.getNewValue());
            SendUpdate(students);
        });
        x_column.setOnEditCommit(commit -> {
            Students students=commit.getTableView().getItems().get(commit.getTablePosition().getRow());
            students.setX(commit.getNewValue());
            SendUpdate(students);
        });
        y_column.setOnEditCommit(commit -> {
            Students students=commit.getTableView().getItems().get(commit.getTablePosition().getRow());
            students.setY(commit.getNewValue());
            SendUpdate(students);
        });
    }

    public void SendUpdate(Students students) {
        try {
            CheckNumericField(students.getCount());
            CheckNumericField(students.getHeight());
            CheckNumericField(students.getWeight());
            CheckNumericField(students.getX());
            CheckNumericField(students.getY());
            if (CheckForm(students)==true&&CheckEyeColor(students)==true&&CheckSemester(students)==true&&CheckExp(students)==true) {
                information.idstr = students.getId();
                information.setParametrs(students.getName(), students.getCount(), students.getExp(), students.getForm(), students.getSemester(), students.getAdmin_name(),
                        students.getHeight(), students.getWeight(), students.getEyeColor(), students.getX(), students.getY());
                information.cmdtype = "update";
                information.login = login;
                information.pass = password;
                information.isUpdate = true;
                client.run(information);
            }
        } catch (Exception er) {
            er.printStackTrace();
            ErorAlert.alert(Main.bundle.getString("error_field"));
        }
        SendCommand.show(table);
    }
    public boolean CheckForm(Students students){
        if (!students.getForm().equals("DISTANCE_EDUCATION")&& !students.getForm().equals("FULL_TIME_EDUCATION")&&!students.getForm().equals("EVENING_CLASSES")) {
            ErorAlert.alert(Main.bundle.getString("error_field_form"));
            return false;
        }
        else return true;
    }
    public boolean CheckEyeColor(Students students){
        if (!students.getEyeColor().equals("RED")&&!students.getEyeColor().equals("BLACK")&&!students.getEyeColor().equals("ORANGE")&&!students.getEyeColor().equals("BROWN")){
            ErorAlert.alert(Main.bundle.getString("error_field_eye"));
            return false;
        }
        else return true;
    }
    public boolean CheckSemester(Students students){
        if(!students.getSemester().trim().equals("FIFTH")&& !students.getSemester().trim().equals("SIXTH")&& !students.getSemester().trim().equals("SEVENTH")&& !students.getSemester().trim().equals("EIGHTH")
                && !students.getSemester().trim().equals("5")&& !students.getSemester().trim().equals("6")&& !students.getSemester().trim().equals("7")&& !students.getSemester().trim().equals("8")){
            ErorAlert.alert(Main.bundle.getString("error_field_semester"));
            return false;
        }
        else return true;
    }
    public boolean CheckExp(Students students){
        if (!students.getExp().equals("yes")&&!students.getExp().equals(Main.bundle.getString("exp_y"))&&!students.getExp().equals(Main.bundle.getString("exp_n"))&&students.getExp().equals("yes")){
            ErorAlert.alert(Main.bundle.getString("error_field"));
            return false;
        }
        else return true;
    }
}