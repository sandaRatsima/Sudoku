import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class ButtonHandler {
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
            }else{
                button.setOpacity(1);
            }
        };
   }
}

    

