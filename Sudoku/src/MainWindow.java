import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class MainWindow extends Application{

    public void start(Stage primaryStage) {
        primaryStage.setTitle("le S");
        primaryStage.setHeight(1000);
        primaryStage.setWidth(1000);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        for(int i = 0; i< 9; i++){
            for (int j = 0; j < 9; j++) {
                Rectangle rec = new Rectangle(70,70);
                rec.setFill(javafx.scene.paint.Color.BLACK);
                rec.setStyle("-fx-arc-height: 10; -fx-arc-width: 10;");
                rec.applyCss();
                gridPane.add(rec,i,j);
            }
        }
        
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setPadding(new Insets(10,10,10,10));
        Scene scene = new Scene(gridPane, 900, 900);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
