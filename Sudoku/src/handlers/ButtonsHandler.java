package handlers;

import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.MainWindow;
import modeles.Sudoku;

public class ButtonsHandler {
    public static EventHandler<? super MouseEvent> deleteNumber(){
        return event ->{
            StackPane[][] grid = MainWindow.getSudokuGrid();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    Rectangle rectangle = (Rectangle)grid[i][j].getChildren().get(0);
                    Color color = (Color)rectangle.getFill();
                    if(color.equals(javafx.scene.paint.Color.rgb(165, 116, 124))){
                        Label label = (Label)grid[i][j].getChildren().get(1);
                        if(!(Sudoku.isInGrid(Integer.parseInt(label.getText()),i,j,MainWindow.getFirstGrid()))){
                            label.setText("");
                        }
                        else{
                            showNotModifiable();
                        }
                    }
                }
            }
        };
    }

    public static void showNotModifiable(){
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Erreur");
        alert.setContentText("Vous ne pouvez modifier cette case"); 
        alert.showAndWait(); 
    }

    public static EventHandler<? super MouseEvent> giveHints(){
        return event->{
            oneHint();
        };
    }
    
    public static void oneHint() {
        Random random = new Random();
        StackPane[][] sudoku = MainWindow.getSudokuGrid();
        int[][] grid = MainWindow.getGridUpdated();
        int[][] solution = MainWindow.getSolution();
    
   
        int i, j;
        do {
            i = random.nextInt(9); 
            j = random.nextInt(9); 
        } while (grid[i][j] != 0); 
    
        
        int indice = solution[i][j];
        Label box = (Label) sudoku[i][j].getChildren().get(1);
        box.setText(Integer.toString(indice));
    }
    
}
