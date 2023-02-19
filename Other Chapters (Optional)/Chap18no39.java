package c18n39;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Chap18no39 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        TreePane treepane = new TreePane();
        primaryStage.setScene(new Scene(treepane, 400, 500));
        primaryStage.show();
        treepane.draw();
    }
    public class TreePane extends Pane {
        final int angle = 25;
        double length = 50;
        int order = 7;
        double startX;
        double startY;
        public TreePane() {
            setMinSize(300, 300);
            setOnMousePressed(e -> {
                startX = e.getX();
                startY = e.getY();
            });
            setOnMouseDragged(e -> {
                setTranslateX(getTranslateX() + e.getX() - startX);
                setTranslateY(getTranslateY() + e.getY() - startY);
            });
        }
        public void draw() {
            getChildren().clear();
            Line line = new Line(150, 400, 150, 300);
            getChildren().add(line);
            draw(line.getEndX(), line.getEndY(), 90, length, order);
        }
        private void draw(double x, double y, double ANGLE, double length, int order) {
            if (order <= 0) {
                return;
            }
            double x2 = x + length * Math.cos(Math.toRadians(ANGLE + angle));
            double y2 = y - length * Math.sin(Math.toRadians(ANGLE + angle));
            double x3 = x + length * Math.cos(Math.toRadians(ANGLE - angle));
            double y3 = y - length * Math.sin(Math.toRadians(ANGLE - angle));
            getChildren().add(new Line(x, y, x2, y2));
            getChildren().add(new Line(x, y, x3, y3));
            draw(x2, y2, ANGLE + angle, length * 0.8, order - 1);
            draw(x3, y3, ANGLE - angle, length * 0.8, order - 1);
        }
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
