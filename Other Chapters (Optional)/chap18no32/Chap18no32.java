package c18n32;

import javafx.application.Application;
import java.io.File;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Chap18no32 extends Application {
    private static final int SIZE = 8;
    private int startX = 0;
    private int startY = 0;
    private ArrayList<Point2D> moves = null;

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        Board board = new Board();
        pane.setCenter(board);
        Button solveButton = new Button("Solve");
        pane.setBottom(solveButton);
        BorderPane.setAlignment(solveButton, Pos.CENTER);

        Scene scene = new Scene(pane, 250, 250);
        primaryStage.setTitle(getClass().getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        board.draw();

        solveButton.setOnAction(e -> {
            boolean[][] movess = new boolean[SIZE][SIZE];
            movess[startX][startY] = true;
            resetMoves();
            addMove(startX, startY);
            solvePuzzle(movess, 1, startX, startY);
            board.draw();
        });

    }

    private boolean solvePuzzle(boolean[][] moves, int numMoves, int x, int y) {
        int nextX = 0;
        int nextY = 0;
        int bestMoveX = 0;
        int bestMoveY = 0;
        int bestMoveX2 = 0;
        int bestMoveY2 = 0;
        int minMoveCount = SIZE;
        int moveCount = 0;

        for (int i = 2; i >= -2; i += -4) {
            for (int j = 1; j >= -1; j += -2) {
                nextX = x + i;
                nextY = y + j;
                if (nextX >= 0 && nextX <= SIZE - 1 && nextY >= 0 && nextY <= SIZE - 1
                        && !moves[nextX][nextY]) {
                    moveCount = lookAheadCount(moves, nextX, nextY);
                    if (moveCount <= minMoveCount) {
                        minMoveCount = moveCount;
                        bestMoveX2 = bestMoveX;
                        bestMoveY2 = bestMoveY;
                        bestMoveX = nextX;
                        bestMoveY = nextY;
                    }
                }

                nextX = x + j;
                nextY = y + i;
                if (nextX >= 0 && nextX <= SIZE - 1 && nextY >= 0 && nextY <= SIZE - 1
                        && !moves[nextX][nextY]) {
                    moveCount = lookAheadCount(moves, nextX, nextY);
                    if (moveCount <= minMoveCount) {
                        minMoveCount = moveCount;
                        bestMoveX2 = bestMoveX;
                        bestMoveY2 = bestMoveY;
                        bestMoveX = nextX;
                        bestMoveY = nextY;
                    }
                }
            }
        }
        moves[bestMoveX][bestMoveY] = true;
        addMove(bestMoveX, bestMoveY);
        numMoves++;
        if (numMoves == (SIZE * SIZE))
            return true;
        if (moveCount > 0 && solvePuzzle(moves, numMoves, bestMoveX, bestMoveY)) {
            return true;
        }
        moves[bestMoveX][bestMoveY] = false;
        moves[bestMoveX2][bestMoveY2] = true;
        removeLastMoveHistory();
        addMove(bestMoveX2, bestMoveY2);
        if (moveCount > 1 && solvePuzzle(moves, numMoves, bestMoveX2, bestMoveY2)) {
            return true;
        }
        moves[bestMoveX2][bestMoveY2] = false;
        removeLastMoveHistory();
        numMoves--;
        return false;
    }

    private int lookAheadCount(boolean[][] moves, int x, int y) {
        int maxCount = 0;
        for (int i = -2; i <= 2; i += 4) {
            for (int j = -1; j <= 1; j += 2) {
                int nextX = x + i;
                int nextY = y + j;
                if (nextX >= 0 && nextX <= SIZE - 1 && nextY >= 0 && nextY <= SIZE - 1
                        && !moves[nextX][nextY]) {
                    maxCount++;
                }

                nextX = x + j;
                nextY = y + i;
                if (nextX >= 0 && nextX <= SIZE - 1 && nextY >= 0 && nextY <= SIZE - 1
                        && !moves[nextX][nextY]) {
                    maxCount++;
                }
            }
        }
        return maxCount;
    }

    public void resetMoves() {
        moves = new ArrayList(63);
    }

    public void addMove(int x, int y) {
        moves.add(new Point2D(x, y));
    }

    public void removeLastMoveHistory() {
        moves.remove(moves.size() - 1);
    }

    private class Board extends Pane {
        
        File file = new File("C:\\Users\\Ella\\Desktop\\PROG\\Other Chapters\\src\\c18n32\\knight.jpg");
        ImageView theKnight = new ImageView(file.toURI().toString());

        Board() {
            this.setOnMouseClicked(e -> {
                startX = (int) (e.getX() / (getWidth() / SIZE));
                startY = (int) (e.getY() / (getHeight() / SIZE));
                resetMoves();
                draw();
            });
        }

        protected void draw() {
            this.getChildren().clear();

            this.getChildren().add(theKnight);
            theKnight.setX(startX * getWidth() / SIZE);
            theKnight.setY(startY * getHeight() / SIZE);
            theKnight.setFitWidth(getWidth() / SIZE);
            theKnight.setFitHeight(getHeight() / SIZE);

            for (int i = 1; i <= SIZE; i++) {
                this.getChildren().add(
                        new Line(0, i * getHeight() / SIZE, getWidth(), i * getHeight() / SIZE));
                this.getChildren().add(
                        new Line(i * getWidth() / SIZE, 0, i * getWidth() / SIZE, getHeight()));
            }

            if (moves != null) {
                for (int i = 1; i < moves.size(); i++) {
                    Point2D p1 = moves.get(i - 1);
                    Point2D p2 = moves.get(i);
                    this.getChildren().add(
                            new Line(p1.getX() * (getWidth() / SIZE) + getWidth() / SIZE / 2,
                                    p1.getY() * (getHeight() / SIZE) + (getHeight() / SIZE / 2),
                                    p2.getX() * (getWidth() / SIZE) + getWidth() / SIZE / 2,
                                    p2.getY() * (getHeight() / SIZE) + getHeight() / SIZE / 2));
                }
            }
        }
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}