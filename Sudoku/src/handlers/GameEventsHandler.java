package handlers;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import main.MainWindow;

public class GameEventsHandler {
    private static boolean isCorrectlyPlaced(int number, int row, int column){
        StackPane[][] grid = MainWindow.getSudokuGrid();
        int[][] solution = MainWindow.getSolution();

        StackPane box = grid[row][column];
        Label labelInBox = (Label)box.getChildren().get(1);
        int numberInBox = Integer.parseInt(labelInBox.getText());

        return numberInBox == solution[row][column] && number == numberInBox;
    }

    public static void verifyAnswer(StackPane box){
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
                        MainWindow.setNbErrors(MainWindow.getNbErrors()+1);

                    }
                }
            }
        }
    }

    private static boolean isWonGame(){
        StackPane[][] grid = MainWindow.getSudokuGrid();
        int[][] solution = MainWindow.getSolution();

        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution.length; j++) {
                StackPane box = grid[i][j];
                Label labelInBox = (Label)box.getChildren().get(1);
                int numberInBox = Integer.parseInt(labelInBox.getText());
                if(!isCorrectlyPlaced(numberInBox, i, j)){
                    return false;
                }
            }
        }
        return true;
    }

    public static void showGameWon(){
        if(isWonGame()){
            Alert alert = new Alert(AlertType.NONE);
            alert.setTitle("Vous avez gagné");
            alert.setContentText("Vous avez gagné la partie"); 
            alert.showAndWait(); 
            Platform.exit();
        }
    }

    public static void showGameLost(){
        if(MainWindow.getNbErrors()>=3){
            Alert alert = new Alert(AlertType.NONE);
            alert.setTitle("Vous avez perdu");
            alert.setContentText("Vous avez pardu la partie"); 
            alert.showAndWait(); 
            Platform.exit();
        }
    }
}
