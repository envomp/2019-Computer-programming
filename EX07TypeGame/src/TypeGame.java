import javafx.animation.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;


public class TypeGame extends Application implements EventHandler<KeyEvent> {
    final int WIDTH = 1024;
    final int HEIGHT = 768;
    final ArrayList<KeyCode> randomWords = new ArrayList<>(List.of(KeyCode.A,
            KeyCode.B, KeyCode.C, KeyCode.D, KeyCode.E, KeyCode.F,
            KeyCode.H, KeyCode.O, KeyCode.I, KeyCode.J, KeyCode.K,
            KeyCode.L, KeyCode.M, KeyCode.N, KeyCode.P, KeyCode.Q,
            KeyCode.R, KeyCode.S, KeyCode.T, KeyCode.U, KeyCode.V,
            KeyCode.W, KeyCode.X, KeyCode.Y, KeyCode.Z));
    Random rand;
    Group root;
    int points = 0;
    int high_score = 0;
    int speed = 0;
    int state = 0;
    ArrayList<KeyAnimation> keys;
    ArrayList<BubbleCircle> circles;
    ArrayList<KeyAnimation> temp;

    int posX;
    int posY;


    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Epilepsy is now 70% off");
        keys = new ArrayList<>();
        circles = new ArrayList<>();

        //init lib
        root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.LIGHTGREEN);
        stage.setScene(scene);
        Background bg = new Background();
        Timeline timeline = bg.background(root, scene);
        timeline.play();

        // text part
        rand = new Random();
        HBox hbox = new HBox();
        initKey();

        Label t = new Label(String.format("Your score: %d", points));
        t.setFont(Font.font("Comic Sans", 20));

        hbox.setSpacing(10);
        hbox.setLayoutX(10);
        hbox.setLayoutY(10);
        hbox.getChildren().add((t));

        final Box keyboardNode = new Box();
        keyboardNode.setFocusTraversable(true);
        keyboardNode.requestFocus();
        keyboardNode.setOnKeyPressed(this);

        root.getChildren().addAll(
                hbox,
                keyboardNode
        );

        stage.show();

        AnimationTimer animator = new AnimationTimer() {

            @Override
            public void handle(long arg0) {

                if (keys.size() < 20) {
                    // UPDATE
                    if (speed == 26) {
                        speed = high_score / 3;
                        initKey();
                    } else {
                        speed += 1;
                    }


                    // RENDER
                    rendercircles();

                    for (BubbleCircle circle : circles) {
                        circle.render();
                    }

                    renderKeys(t);
                } else {
                    root.getChildren().remove(hbox);
                    root.getChildren().remove(keyboardNode);
                    timeline.play();

                    HBox hbox = new HBox();
                    Label t = new Label(String.format("   Game over!\nYour best score: %d", high_score));
                    t.setFont(Font.font("Comic Sans", 60));
                    t.setTextFill(Color.web(GlobalVars.COLORS[new Random().nextInt(GlobalVars.COLORS.length)]));
                    hbox.setSpacing(10);
                    hbox.setLayoutX(WIDTH / 4f);
                    hbox.setLayoutY(HEIGHT / 3f);
                    hbox.getChildren().add((t));
                    root.getChildren().addAll(
                            hbox
                    );

                }
            }
        };

        animator.start();
    }

    public void rendercircles() {
        if (state == 1) {
            for (KeyAnimation del : temp) {
                BubbleCircle bubbleCircle = new BubbleCircle(Color.GREEN);
                bubbleCircle.update(del.getPosX(), del.getPosY());
                root.getChildren().addAll(bubbleCircle.getCircle());
                circles.add(bubbleCircle);
                state = 0;
            }
        }
    }

    public void renderKeys(Label t) {
        t.setText(String.format("Your score: %d", points));
        t.setTextFill(Color.web(GlobalVars.COLORS[rand.nextInt(GlobalVars.COLORS.length)]));
        List<KeyAnimation> toRemove = keys.stream().filter(x -> x.remove).collect(Collectors.toList());
        if (!toRemove.isEmpty()) {
            keys.remove(toRemove.get(0));
            toRemove.forEach(x -> x.getLabel().setText(""));
        }
    }

    public void initKey() {
        KeyAnimation key = new KeyAnimation(randomWords.get(rand.nextInt(randomWords.size())));
        keys.add(key);
        HBox kbox = new HBox();
        kbox.setSpacing(10);
        kbox.getChildren().add((key.getLabel()));

        posX = ThreadLocalRandom.current().nextInt(50, WIDTH - 70 + 1);
        posY = ThreadLocalRandom.current().nextInt(50, HEIGHT - 70 + 1);
        key.setPosX(posX);
        key.setPosY(posY);
        kbox.setLayoutX(posX);
        kbox.setLayoutY(posY);
        root.getChildren().addAll(kbox);

    }


    @Override
    public void handle(KeyEvent arg0) {
        temp = (ArrayList<KeyAnimation>)
                keys.stream().filter(x -> x.getKey() == arg0.getCode()).collect(Collectors.toList());
        if (!temp.isEmpty()) {
            points += 1;
            if (points > high_score) high_score = points;
            state = 1;
            temp.get(0).delete();
        } else {
            points -= 1;
        }
    }
}
