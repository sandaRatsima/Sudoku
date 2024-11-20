package main;

import handlers.NumberHandlerHandler;



import handlers.ButtonsHandler;
import handlers.GridHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import modeles.Sudoku;
import modeles.SudokuSolver;


public class MainWindow extends Application{
    static int[][] numbers;
    static int[][] solution;
    static StackPane[][] SudokuGrid = new StackPane[9][9];
    static int[][] gridUpdate;
    static GridPane head;
    static GridPane grid;
    static GridPane buttons;
    static GridPane bottomButtons;
    static int timeValue;
    static int nbErrors = 0;

    @Override
    public void start(Stage primaryStage) {
        //Taille et Titre de la fenetre principale
        primaryStage.setTitle("Sudoku");
        primaryStage.setHeight(1000);
        primaryStage.setWidth(1000);

        GridPane main = new GridPane();
        head = setHeadGridPane();
        grid = setGrid();
        buttons = setButtons();
        bottomButtons = setBottomButtons();

        head.setAlignment(Pos.TOP_RIGHT);
        grid.setAlignment(Pos.TOP_CENTER);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        bottomButtons.setAlignment(Pos.BOTTOM_CENTER);

        main.add(head, 0, 0);
        main.add(grid, 0, 1);
        main.add(buttons, 0, 2);
        main.add(bottomButtons, 0, 3);

        main.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(main);
        
        
        
        
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static GridPane setGrid(){
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: solid black;");

        Sudoku sudoku = new Sudoku(3);
        numbers = sudoku.getGrid();
        solution = sudoku.getSolution();

        SudokuSolver.printBoard(solution);
        SudokuSolver.printBoard(numbers);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SudokuGrid[i][j] = new StackPane();
                Rectangle box = new Rectangle();
                box.setWidth(70);
                box.setHeight(70);
                box.setFill(javafx.scene.paint.Color.rgb(250, 234, 237));


                //ajouter les handlers au grid
                box.setOnMouseClicked(GridHandler.setOnMouseClicked());
                box.setOnMouseEntered(GridHandler.setOnMouseEntered());

                Label label = new Label();
                if(numbers[i][j] == 0){
                    label.setText("");
                }
                else{
                    label.setText(Integer.toString(numbers[i][j]));
                }
                label.setFont(Font.font("Lucida Console", javafx.scene.text.FontWeight.BOLD, 27));
                label.setTextFill(javafx.scene.paint.Color.rgb(71,4,42));


                SudokuGrid[i][j].getChildren().add(box);
                SudokuGrid[i][j].getChildren().add(label);
                gridPane.add(SudokuGrid[i][j],j,i);

            }
        }

        
        stylePane();
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setAlignment(Pos.TOP_CENTER);
        return gridPane;
    }

    public static GridPane setButtons(){
        GridPane Buttons = new GridPane();
        ToggleGroup toggleGroup = new ToggleGroup();
        Buttons.setHgap(10);
        for (int i = 0; i < SudokuGrid.length; i++) {
            ToggleButton button = new ToggleButton(Integer.toString(i+1));
            button.setBackground(null);
            button.setFont(Font.font("Lucida Console", javafx.scene.text.FontWeight.EXTRA_BOLD, 37));

            //ajouter les handlers
            button.setOnMouseEntered(NumberHandlerHandler.setMouseHover());
            button.setOnMouseExited(NumberHandlerHandler.setMouseExit());
            button.setOnAction(NumberHandlerHandler.setSelected());

            //ajouter à la fenetre principale
            button.setToggleGroup(toggleGroup);
            Buttons.add(button,i,0);
           
        }

        Buttons.setPadding(new Insets(10));
        return Buttons;
    }

    public static StackPane[][] getSudokuGrid(){
        return SudokuGrid;
    } 
    
    public static int[][] getFirstGrid(){
        return numbers;
    }

    public static int[][] getSolution(){
        return solution;
    }

    public static void stylePane(){
        StackPane[][] grid = SudokuGrid;
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j].setBorder(new Border(new BorderStroke(
                    Color.BLACK,
                    BorderStrokeStyle.SOLID,
                    new CornerRadii(5),
                    new BorderWidths(1)
                )));
            }
        }
    }

    public static GridPane setBottomButtons(){
        GridPane buttons = new GridPane();
        buttons.setHgap(15);

        Button supprimer = new Button("SUPPRIMER");
        Button indice = new Button("INDICE");
        Button abandonner = new Button("ABANDONNER");

        supprimer.setOnMouseClicked(ButtonsHandler.deleteNumber());
        indice.setOnMouseClicked(ButtonsHandler.giveHints());

        buttons.add(supprimer, 0, 0);
        buttons.add(indice, 1, 0);
        buttons.add(abandonner, 2, 0);

        return buttons;
    }
    
    public static GridPane setHeadGridPane(){
        GridPane main = new GridPane();
        Label label = new Label("Timer : "+timeValue);
        Label errors = new Label("Erreurs : "+nbErrors+"/3");

        label.setFont(Font.font("Lucida Console", javafx.scene.text.FontWeight.EXTRA_BOLD, 37));
        errors.setFont(Font.font("Lucida Console", javafx.scene.text.FontWeight.EXTRA_BOLD, 37));

        Timeline timeLine = new Timeline(
            new KeyFrame(Duration.seconds(1.0), e ->{
                timeValue++;
                label.setText("Timer : "+timeValue);
            })
        );

        

        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();

        main.add(errors,0,0);
        main.add(label,1,0);
        main.setPadding(new Insets(10,10,10,10));
        main.setHgap(100);
        return main;
    }
    public static void main(String[] args) {
        launch(args);
    }

    public static void setNbErrors(int nombre){
        nbErrors = nombre;
        GridPane root = getHead();
        Label label = (Label)root.getChildren().get(0);
        label.setText("Erreurs : "+nbErrors+"/3");
    }

    public static int getNbErrors(){
        return nbErrors;
    }

    public static GridPane getHead(){
        return head;
    }

    public static int[][] getGridUpdated(){
        gridUpdate = new int[9][9];
        StackPane[][] grid = MainWindow.SudokuGrid;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                StackPane box = grid[i][j];
                Label label = (Label) box.getChildren().get(1);
                int number = Integer.parseInt(label.getText());
                gridUpdate[i][j] = number;
            }
        }

        return gridUpdate;
    }
}
