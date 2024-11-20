package handlers;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import main.MainWindow;

public class GameEventsHandler {
    public static boolean isCorrectlyPlaced(int number, int row, int column){
        StackPane[][] grid = MainWindow.getSudokuGrid();
        int[][] solution = MainWindow.getSolution();

        StackPane box = grid[row][column];
        Label labelInBox = (Label)box.getChildren().get(1);
        int numberInBox = Integer.parseInt(labelInBox.getText());

        return numberInBox == solution[row][column] && number == numberInBox;
    }

    public static void setColorPlaced(StackPane box){
        StackPane[][] grid = MainWindow.getSudokuGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if(grid[i][j]  == box){
                    Label label = (Label)grid[i][j].getChildren().get(1);
                    String text = label.getText();
                    int number = Integer.parseInt(text);
                    if(isCorrectlyPlaced(number, i, j)){
                        label.setTextFill(javafx.scene.paint.Color.BLUE);
                    }
                    else{
                        label.setTextFill(javafx.scene.paint.Color.RED);
                    }
                }
            }
        }
    }

}
