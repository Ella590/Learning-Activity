package c18n35;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Chap18no35 extends Application {
    private final double WIDTH = 500;
    private final double HEIGHT = 500;
    private final Label mainLabel = new Label("ENTER AN ORDER");
    private int FractalOrder = 0;
    Pane drawPane = new Pane();
    ObservableList<Node> FRACTAL = drawPane.getChildren();
    @Override
    public void start(Stage primaryStage) {
        VBox mainBox = new VBox(5);
        mainBox.setAlignment(Pos.CENTER);
        VBox.setMargin(drawPane, new Insets(15, 0, 0, 0));
        mainBox.getChildren().add(drawPane);
        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER);
        TextField inputField = new TextField();
        inputField.setPrefWidth(100);
        hBox.getChildren().addAll(mainLabel, inputField);
        HBox.setMargin(mainLabel, new Insets(5, 5, 10, 10));
        HBox.setMargin(inputField, new Insets(5, 5, 10, 3));
        drawPane.setCenterShape(true);
        drawPane.setPrefHeight(HEIGHT - hBox.getHeight());
        mainBox.getChildren().add(hBox);
        inputField.textProperty().addListener((observable, oldValue, newValue) -> {
            FRACTAL.clear();
            if (!newValue.isEmpty()) {
                FractalOrder = Integer.parseInt(newValue);
                double HSize = HEIGHT / 2 - 50;
                double centerX = drawPane.getWidth() / 2 - HSize / 2;
                double centerY = drawPane.getHeight() / 2 - HSize / 2;
                drawHTree(FractalOrder, centerX, centerY, HSize);
            }
        });
        Scene scene = new Scene(mainBox, WIDTH, HEIGHT);
        primaryStage.setTitle(getClass().getName());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    private void drawH(double x, double y, double size) {
        Line LeftVertical = new Line(x, y, x, y + size);
        Line RightVertical = new Line(x + size, y, x + size, y + size);
        Line Horizontal = new Line(x, y + size / 2.0, x + size, y + size / 2.0);
        FRACTAL.addAll(LeftVertical, RightVertical, Horizontal);
    }
    private void drawHTree(int order, double x, double y, double size) {
        drawH(x, y, size);
        if (order > 0) {
            drawHTree(order - 1, x - size / 4, y - size / 4, size / 2);
            drawHTree(order - 1, x + size - size / 4, y - size / 4, size / 2);
            drawHTree(order - 1, x - size / 4, y + size - size / 4, size / 2);
            drawHTree(order - 1, x + size - size / 4, y + size - size / 4, size / 2);
        }
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
