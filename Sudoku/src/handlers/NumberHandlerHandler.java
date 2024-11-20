package handlers;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.MainWindow;
import modeles.Sudoku;

public class NumberHandlerHandler {
    public static javafx.event.EventHandler<? super MouseEvent> setMouseHover() {
        return event ->{
            ToggleButton button = (ToggleButton)event.getSource();
            button.setCursor(Cursor.HAND);
            button.setScaleX(1.5);
            button.setScaleY(1.5);
        };
    }

    public static javafx.event.EventHandler<? super MouseEvent> setMouseExit() {
        return event ->{
            ToggleButton button = (ToggleButton)event.getSource();
            button.setCursor(Cursor.DEFAULT);
            button.setScaleX(1);
            button.setScaleY(1);
        };
    }

   public static javafx.event.EventHandler<ActionEvent> setSelected(){
        return event ->{
            ToggleButton button = (ToggleButton) event.getSource();
            ToggleGroup group = button.getToggleGroup();
        
            for (Toggle toggle : group.getToggles()) {
                ToggleButton tb = (ToggleButton)toggle;
                tb.setOpacity(1);
            }
            if(button.isSelected()){
                button.setOpacity(0.4);
                String number = button.getText();
                placeNumber(number);
            }else{
                button.setOpacity(1);
            }
        };
   }

    public static void placeNumber(String number){
        StackPane[][] grid = MainWindow.getSudokuGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                Rectangle rectangle = (Rectangle)grid[i][j].getChildren().get(0);
                Color color = (Color)rectangle.getFill();
                if(color.equals(javafx.scene.paint.Color.rgb(165, 116, 124))){
                    Label label = (Label)grid[i][j].getChildren().get(1);
                    String text = label.getText();

                    if(text.equals("")){
                        label.setText(number);
                        GameEventsHandler.verifyAnswer(grid[i][j]);
                        GameEventsHandler.showGameWon();
                        GameEventsHandler.showGameLost();
                    }
                    else{
                        if(!(Sudoku.isInGrid(Integer.parseInt(label.getText()), i, j, MainWindow.getFirstGrid()))){
                            label.setText(number);
                            GameEventsHandler.verifyAnswer(grid[i][j]);
                            GameEventsHandler.showGameWon();
                            GameEventsHandler.showGameLost();
                        }
                        else{
                            ButtonsHandler.showNotModifiable();
                        }
                    }
                }
            }
        }
    }


    
}

    

