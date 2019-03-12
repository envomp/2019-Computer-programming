import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Random;

public class KeyAnimation {
    KeyCode code;
    Label label;
    int posX;
    int posY;
    boolean remove = false;

    public KeyAnimation(KeyCode key) {
        this.code = key;
        this.label = new Label(code.toString());
        label.setFont(Font.font("Comic Sans", 80));
        label.setAlignment(Pos.CENTER);
        label.setTextFill(Color.web(GlobalVars.COLORS[new Random().nextInt(GlobalVars.COLORS.length)]));
    }

    public KeyCode getKey() {
        return code;
    }

    public Label getLabel() {
        return label;
    }

    public void delete() {
        remove = true;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
