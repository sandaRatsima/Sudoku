package handlers;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import main.MainWindow;

public class GridHandler {

    public static EventHandler<? super MouseEvent> setOnMouseClicked(){
        return event ->{
            Rectangle rectangle = (Rectangle)event.getSource();
            StackPane stackPane = (StackPane)rectangle.getParent();
            fullColor(stackPane);
            rectangle.setFill(javafx.scene.paint.Color.rgb(165, 116, 124));

            
        };
    }

    public static void colorColumn(StackPane box){
        StackPane[][] grid = MainWindow.getSudokuGrid();
        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid.length; j++) {
                if(grid[i][j] == box){
                    for (int j2 = 0; j2 < grid.length; j2++) {
                        Rectangle rectangle = (Rectangle)grid[i][j2].getChildren().get(0);
                        rectangle.setFill(javafx.scene.paint.Color.PINK);
                    }
                }
            }
        }
    }

    public static void colorLine(StackPane box){
        StackPane[][] grid = MainWindow.getSudokuGrid();
        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid.length; j++) {
                if(grid[i][j] == box){
                    for (int i2 = 0; i2 < grid.length; i2++) {
                        Rectangle rectangle = (Rectangle)grid[i2][j].getChildren().get(0);
                        rectangle.setFill(javafx.scene.paint.Color.PINK);
                    }
                }
            }
        }
    }

    public static void resetColor(){
        StackPane[][] grid = MainWindow.getSudokuGrid();
        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid.length; j++) {
                Rectangle rectangle = (Rectangle)grid[i][j].getChildren().get(0);
                rectangle.setFill(javafx.scene.paint.Color.rgb(250, 234, 237));
            }
        }
    }
    
    public static void colorBox(StackPane box){
        StackPane[][] grid = MainWindow.getSudokuGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if(grid[i][j] == box){
                    int localBoxRow = i - i%3;
                    int localBoxColumn = j - j%3;
                    for (int k = localBoxRow; k < localBoxRow+3; k++) {
                        for (int k2 = localBoxColumn; k2 < localBoxColumn + 3; k2++) {
                            Rectangle rectangle = (Rectangle)grid[k][k2].getChildren().get(0);
                            rectangle.setFill(javafx.scene.paint.Color.PINK);
                        }
                    }
                }            
            }
        }
    }

    public static void colorSameNumber(StackPane box){
        StackPane[][] grid = MainWindow.getSudokuGrid();
        Label label = (Label)box.getChildren().get(1);  
        if(!(label.getText().equals(""))){
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    Label currentLabel = (Label)grid[i][j].getChildren().get(1);
                    if(currentLabel.getText().equals(label.getText())){
                        Rectangle rectangle = (Rectangle)grid[i][j].getChildren().get(0);
                        rectangle.setFill(javafx.scene.paint.Color.rgb(209 , 161, 169));
                    }
                }
            }
        } 
    }
    
    public static void fullColor(StackPane box){
        resetColor();
        colorColumn(box);
        colorLine(box);
        colorBox(box);
        colorSameNumber(box);
    }
    
    public static EventHandler<? super MouseEvent> setOnMouseEntered() {
        return event->{
            Rectangle rectangle = (Rectangle)event.getSource();
            rectangle.setCursor(Cursor.HAND);
        };
    }

}
