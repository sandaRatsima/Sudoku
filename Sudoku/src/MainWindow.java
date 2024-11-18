import org.w3c.dom.css.Rect;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class MainWindow extends Application{
    static int[][] numbers;
    static StackPane[][] SudokuGrid = new StackPane[9][9];

    @Override
    public void start(Stage primaryStage) {
        //Taille et Titre de la fenetre principale
        primaryStage.setTitle("le S");
        primaryStage.setHeight(1000);
        primaryStage.setWidth(1000);

        GridPane main = new GridPane();

        GridPane grid = setGrid();
        GridPane buttons = setButtons();
        main.add(grid, 0, 0);
        main.add(buttons, 0, 1);
        main.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(main);
        
        
        
        
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static GridPane setGrid(){
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-border-color: solid black;");

        Sudoku sudoku = new Sudoku(3);
        numbers = sudoku.getGrid();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SudokuGrid[i][j] = new StackPane();
                Rectangle box = new Rectangle();
                box.setWidth(70);
                box.setHeight(70);
                box.setFill(javafx.scene.paint.Color.rgb(250, 234, 237));

                Label label = new Label();
                if(numbers[i][j] == 0){
                    label.setText("");
                }
                else{
                    label.setText(Integer.toString(numbers[i][j]));
                }
                label.setFont(Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 27));
                label.setTextFill(javafx.scene.paint.Color.rgb(71,4,42));


                SudokuGrid[i][j].getChildren().add(box);
                SudokuGrid[i][j].getChildren().add(label);
                gridPane.add(SudokuGrid[i][j],i,j);

            }
        }

        

        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setAlignment(Pos.TOP_CENTER);
        return gridPane;
    }

    public static GridPane setButtons(){
        GridPane Buttons = new GridPane();
        Buttons.setHgap(10);
        for (int i = 0; i < SudokuGrid.length; i++) {
            Button button = new Button(Integer.toString(i+1));
            button.setBackground(null);
            button.setFont(Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 27));
            Buttons.add(button, i, 0);
        }

        Buttons.setPadding(new Insets(10));
        return Buttons;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
