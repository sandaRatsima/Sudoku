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
    
            setGrid(primaryStage);// ajout de la grille de Sudoku
            
            
            
            
            primaryStage.show();
    
    
        }
    
        public static void setGrid(Stage primaryStage){
            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.TOP_CENTER);
            gridPane.setHgap(10);
            gridPane.setVgap(10);
    
            Sudoku sudoku = new Sudoku(1);
            numbers = sudoku.getGrid();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SudokuGrid[i][j] = new StackPane();
                Rectangle box = new Rectangle();
                box.setWidth(70);
                box.setHeight(70);
                box.setFill(javafx.scene.paint.Color.rgb(250, 234, 237));
                box.setStyle("-fx-arc-height: 10; -fx-arc-width: 10;");

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
        Scene scene = new Scene(gridPane, 900, 900);
        scene.setFill(javafx.scene.paint.Color.BEIGE);
        primaryStage.setScene(scene);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
