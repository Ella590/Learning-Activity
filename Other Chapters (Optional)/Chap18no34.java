package c18n34;

import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Chap18no34 extends Application {
    private static final int SIZE = 8;
    private int[] queens = new int[SIZE];
    @Override
    public void start(Stage primaryStage) {
        search(0);
        ChessBoard chessboard = new ChessBoard();
        Scene scene = new Scene(chessboard, 500, 500);
        primaryStage.setTitle(getClass().getName());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        chessboard.paint();
    }
    private boolean isValid(int row, int column) {
        for (int i = 1; i <= row; i++)
            if (queens[row - i] == column
                    || queens[row - i] == column - i
                    || queens[row - i] == column + i)
                return false;
        return true;
    }
    private boolean search(int row) {
        if (row == SIZE)
            return true;
        for (int column = 0; column < SIZE; column++) {
            queens[row] = column;
            if (isValid(row, column) && search(row + 1))
                return true;
        }
        return false;
    }
    private class ChessBoard extends Pane {
        File file = new File("C:\\Users\\Ella\\Desktop\\PROG\\Other Chapters\\src\\c18n35\\imagequeen.jpg");
        Image queenImage = new Image(file.toURI().toString());
        public void paint() {
            this.getChildren().clear();
            for (int i = 0; i < SIZE; i++) {
                ImageView queenImageView = new ImageView(queenImage);
                this.getChildren().add(queenImageView);
                int j = queens[i];
                queenImageView.setX(j * getWidth() / SIZE);
                queenImageView.setY(i * getHeight() / SIZE);
                queenImageView.setFitWidth(getWidth() / SIZE);
                queenImageView.setFitHeight(getHeight() / SIZE);
            }
            for (int i = 1; i <= SIZE; i++) {
                this.getChildren().add(
                        new Line(0, i * getHeight() / SIZE, getWidth(), i * getHeight() / SIZE));
                this.getChildren().add(
                        new Line(i * getWidth() / SIZE, 0, i * getWidth() / SIZE, getHeight()));
            }
        }
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}