package ee.taltech.iti0202.game;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Controller {

    public int isHovering;
    private float button_x;
    private float button_y;
    private String text;
    private Group root;
    private Label t;
    private String name;
    private HBox hbox;
    private int value;
    private Player player;
    private Clicker clicker;

    Controller(float x, float y, String text, Group root, String name, int value, Player player) {
        this.button_x = x;
        this.button_y = y;
        this.text = text;
        this.root = root;
        this.name = name;
        this.value = value;
        this.player = player;
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
            if (clicker == null || name.equals("clicker") && player.getCookies() < value || name.equals("cursor") && player.getCookies() < value) {
                t.setTextFill(Color.web("#444444"));
            } else {
                t.setTextFill(Color.web("#FFFFFF"));
            }
            t.setStyle("-fx-border-color: white;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;");
        }
        if (name.equals("cookie")) {
            t.setBackground(new Background(new BackgroundImage(new Image("file:///C:/Users/Enrico/IdeaProjects/iti0202-2019/EX08CookieClicker/smallCookie.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        } else
            t.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
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

    public void register(Clicker clicker) {
        this.clicker = clicker;

        t.setOnMouseDragEntered(event -> isHovering = 2);

        t.setOnMouseExited(event -> isHovering = 2);

        if (isHovering > 0) {
            isHovering--;
        } else if (isHovering == 0) {
            isHovering--;
        }
        if (name.equals("clicker") && value < clicker.getClickerPrice()) value = clicker.getClickerPrice();
        if (name.equals("cursor") && value < clicker.getCursorPrice()) value = clicker.getCursorPrice();
        init_button(button_x, button_y, text);

    }


    @Override
    public String toString() {
        return name;
    }
}
