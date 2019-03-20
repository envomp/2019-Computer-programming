package ee.taltech.iti0202.game;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Button {

    public int isHovering;
    private float button_x;
    private float button_y;
    private String text;
    private Group root;
    private Label t;
    private String name;
    private HBox hbox;

    Button(float x, float y, String text, Group root, String name) {
        this.button_x = x;
        this.button_y = y;
        this.text = text;
        this.root = root;
        this.name = name;
        isHovering = 0;

        init_button(x, y, text);
    }

    private void init_button(float x, float y, String text) {
        root.getChildren().remove(hbox);
        hbox = new HBox();
        Label t = new Label(text);
        t.setFont(Font.font("Comic Sans", 20));
        if (isHovering > 0) {
            t.setTextFill(Color.web("#F70027"));
            t.setStyle("-fx-border-color: red;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;");
        } else {
            t.setTextFill(Color.web("#FFFFFF"));
            t.setStyle("-fx-border-color: white;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;");
        }
        t.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().remove(hbox);
        this.t = t;
        hbox.setSpacing(10);
        hbox.setLayoutX(x);
        hbox.setLayoutY(y);
        hbox.getChildren().add(t);
        this.root.getChildren().addAll(
                hbox
        );
    }


    public void update() { // fine

        t.setOnMouseDragEntered(event -> {
            isHovering = 2;
            init_button(button_x, button_y, text);
        });

        t.setOnMouseClicked(event -> {
            isHovering = 2;
            init_button(button_x, button_y, text);
        });

        t.setOnMouseDragged(event -> {
            isHovering = 2;
            init_button(button_x, button_y, text);
        });
        if (isHovering > 0) {
            isHovering--;
        } else if (isHovering == 0) {
            init_button(button_x, button_y, text);
            isHovering--;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
