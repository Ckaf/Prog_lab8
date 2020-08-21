package sample;

import Cllient.Students;
import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ResourceBundle;


public class Paint {

    private static Canvas canvas;
    static GraphicsContext context;

    public static void setCanvas(Canvas can) {
        canvas = can;
        context = canvas.getGraphicsContext2D();
    }

    public static void drawAxis() {
        context.setStroke(Color.BLACK);
        context.setLineWidth(2);
        double w = canvas.getWidth();
        double h = canvas.getHeight();
        context.strokeLine(w / 2, 0, w / 2, h);
        context.strokeLine(0, h / 2, w, h / 2);

        //y arrow
        context.strokeLine(w / 2, 0, w / 2 - 5, 5);
        context.strokeLine(w / 2, 0, w / 2 + 5, 5);
        //x arrow
        context.strokeLine(w, h / 2, w - 5, h / 2 + 5);
        context.strokeLine(w, h / 2, w - 5, h / 2 - 5);
        context.setFill(Color.BLACK);
        drawStepsX(50);
        drawStepsY(50);

    }

    public static void drawStepsX(int step) {
        double w = context.getCanvas().getWidth();
        double h = context.getCanvas().getHeight();

        for (int i = 0; i * step <= 350; i++) {
            int label = (i * step / 2);
            context.strokeLine(w / 2 + i * step, h / 2 - 5, w / 2 + i * step, h / 2 + 5);
            context.fillText(Integer.toString(label), w / 2 + i * step, h / 2 + 15);
            label = (-i * step / 2);
            context.strokeLine(w / 2 - i * step, h / 2 - 5, w / 2 - i * step, h / 2 + 5);
            context.fillText(Integer.toString(label), w / 2 - i * step, h / 2 + 15);
        }


    }

    public static void drawStepsY(int step) {
        double w = context.getCanvas().getWidth();
        double h = context.getCanvas().getHeight();

        for (int i = 1; i * step <= 350; i++) {
            int label = (-i * step / 2);
            context.strokeLine(w / 2 - 5, h / 2 + i * step, w / 2 + 5, h / 2 + i * step);
            context.fillText(Integer.toString(label), w / 2 + 15, h / 2 + i * step);
            label = (i * step / 2);
            context.strokeLine(w / 2 - 5, h / 2 - i * step, w / 2 + 5, h / 2 - i * step);
            context.fillText(Integer.toString(label), w / 2 + 15, h / 2 - i * step);
        }
    }

    public static void DrawElement(TableView<Students> table) {
        ObservableList<AnimationClass> list = FXCollections.observableArrayList();
        table.getItems().stream().forEach((element) -> {
            list.add(new AnimationClass(Double.parseDouble(element.getWeight()), Double.parseDouble(element.getHeight())
                    , Double.parseDouble(element.getX()), Double.parseDouble(element.getY()), element.getUserColor()));
        });
        DrawCircle(list);
    }

    public static void DrawCircle(ObservableList<AnimationClass> list) {
        int size = list.size();
        final int[] i = {0};
//        final double wight = Double.parseDouble(strWight) / 2;
//        final double height = Double.parseDouble(strHeight) / 4;
//        final double x = Double.parseDouble(strX) * 2 - wight / 2;
//        final double y = Double.parseDouble(strY) * 2 + height / 2;
        list.forEach(element -> {
            DoubleProperty xtime = new SimpleDoubleProperty();
            DoubleProperty ytime = new SimpleDoubleProperty();
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0),
                            new KeyValue(xtime, 0),
                            new KeyValue(ytime, 0)
                    ),
                    new KeyFrame(Duration.seconds(3),
                            new KeyValue(xtime, context.getCanvas().getWidth() / 2 + element.x),
                            new KeyValue(ytime, context.getCanvas().getHeight() / 2 - element.y)
                    ));
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    i[0]++;
                    context.setFill(javafx.scene.paint.Paint.valueOf(element.color));
                    context.fillOval(xtime.doubleValue(), ytime.doubleValue(), element.wight, element.height);
                    if (xtime.doubleValue()==context.getCanvas().getWidth() / 2 + element.x){
                        stop();
                    }
                   else if (i[0] == size+1) {
                        i[0] = 0;
                        context.setFill(javafx.scene.paint.Paint.valueOf("#87CEFA"));
                        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        drawAxis();
                    }
                }
            };

            timeline.play();
            timer.start();
        });
//
    }

    public static void CheckFigure(double x, double y, TableView<Students> table) {
        double finalX = (x - canvas.getWidth() / 2) / 2;
        double finalY = (-y + canvas.getHeight() / 2) / 2;
        table.getItems().stream().forEach(element -> {
            double elementX = Double.parseDouble(element.getX());
            double elementY = Double.parseDouble(element.getY());
            double height = Double.parseDouble(element.getHeight()) / 4;
            double wight = Double.parseDouble(element.getWeight()) / 2;
            if ((((Math.pow(finalX - elementX, 2) / Math.pow(wight / 4, 2)) + ((Math.pow(finalY - elementY, 2)) / Math.pow(height / 4, 2))) <= 1)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info");
                alert.setContentText(element.getStudents(element));
                alert.showAndWait();
            }
        });
    }
}