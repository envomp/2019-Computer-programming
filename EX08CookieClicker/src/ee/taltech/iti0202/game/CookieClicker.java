package ee.taltech.iti0202.game;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;

import java.lang.*;

import java.util.HashSet;

import static javafx.application.Platform.exit;


public class CookieClicker extends Application { //git test

    final int WIDTH = 1024;
    final int HEIGHT = 768;
    private boolean isDown;
    private boolean wasDown;
    private boolean infoTag = false;
    private HashSet<Controller> controllers;
    private HashSet<HBox> drawedNumbers;
    private HBox[] hBoxes;
    private HBox infoT;
    private long startTime;
    private HashSet<Bubblenumber> numbers;
    private AudioClip pop;
    private AudioClip crunch;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();
        Player player = new Player();
        Clicker clicker = new Clicker(player);
        this.controllers = new HashSet<>();
        this.hBoxes = new HBox[3];
        this.infoT = new HBox();
        this.drawedNumbers = new HashSet<>();
        this.numbers = new HashSet<>();
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);

        // dat fancy music
        pop = new AudioClip(new File("pop.mp3").toURI().toString());
        crunch = new AudioClip(new File("crunch.mp3").toURI().toString());

        // dat gray rectangle
        Rectangle rect = new Rectangle(WIDTH / 2f, HEIGHT / 9f, WIDTH / 2.5f, HEIGHT / 1.25f);
        rect.setFill(Color.web("#303030"));
        root.getChildren().add(rect);

        // dem controllers
        Controller cross = new Controller(WIDTH - 75, 5, "     X     ", root, "exit", -1, player);
        Controller cursor = new Controller(WIDTH / 1.83f, HEIGHT / 1.9f, "                    Buy Cursor                    ", root, "cursor", clicker.getCursorPrice(), player);
        Controller clickr = new Controller(WIDTH / 1.83f, HEIGHT / 1.7f, "                    Buy Clicker                    ", root, "clicker", clicker.getClickerPrice(), player);
        Controller cookie = new Controller(WIDTH / 8f - 10, HEIGHT / 2.5f - 10, "\n                                                            ".repeat(10), root, "cookie", -1, player);
        Controller info = new Controller(15, 15, "     Info     ", root, "info", -1, player);
        controllers.add(cross);
        controllers.add(cursor);
        controllers.add(clickr);
        controllers.add(cookie);
        controllers.add(info);

        // lööp
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer animator = new AnimationTimer() {

            @Override
            public void handle(long arg0) {
                startTime++;
                if (Math.max(300 - clicker.getClicker() * 6, 60) / startTime == 0) {
                    startTime = 0;
                    player.receiveCookies(clicker.getCursors());
                    appendNumbers(clicker);
                }

                handleCases();
                displayInformation();
                handleLabels();

                wasDown = isDown;
            }

            private void handleCases() {
                scene.setOnMousePressed(event -> isDown = event.getButton() == MouseButton.PRIMARY);
                isDown = (!isDown && wasDown || isDown && !wasDown);
                for (Controller button : controllers) {
                    button.register(clicker);
                    if (isDown && button.isHovering > 0) {
                        switch (button.toString()) {
                            case "exit":
                                exit();
                                break;
                            case "cursor":
                                pop.play();
                                clicker.buyCursor();
                                break;
                            case "clicker":
                                pop.play();
                                clicker.buyClicker();
                                break;
                            case "cookie":
                                crunch.play();
                                player.receiveCookies(clicker.getCursors());
                                appendNumbers(clicker);
                                break;
                            case "info":
                                pop.play();
                                infoTag = !infoTag;
                        }
                    }
                }
            }

            private void handleLabels() {
                //draw bubblenumbers
                drawedNumbers.forEach(root.getChildren()::remove);

                for (Bubblenumber bubblenumber : numbers) {
                    bubblenumber.update();
                    HBox temp = new HBox();
                    Label z = new Label("+" + bubblenumber.getValue());
                    z.setFont(Font.font("Comic Sans", 40));
                    z.setTextFill(Color.web("#FFFFFF"));
                    temp.setSpacing(10);
                    temp.setLayoutX(bubblenumber.getX());
                    temp.setLayoutY(bubblenumber.getY());
                    temp.getChildren().add(z);
                    drawedNumbers.add(temp);
                    root.getChildren().add(temp);
                }

                //lables
                for (HBox box : hBoxes) root.getChildren().remove(box);

                hBoxes[1] = new HBox();
                Label z = new Label(String.format("Price:\n%d\n%d", clicker.getCursorPrice(), clicker.getClickerPrice()));
                z.setFont(Font.font("Comic Sans", 40));
                z.setTextFill(Color.web("#FFFFFF"));
                hBoxes[1].setSpacing(10);
                hBoxes[1].setLayoutX(WIDTH / 1.3f);
                hBoxes[1].setLayoutY(HEIGHT / 5.74f);
                hBoxes[1].getChildren().add(z);

                hBoxes[2] = new HBox();
                Label t = new Label(String.format("Cursors: %d\nClickers: %d", clicker.getCursors(), clicker.getClicker()));
                t.setFont(Font.font("Comic Sans", 40));
                t.setTextFill(Color.web("#FFFFFF"));
                hBoxes[2].setSpacing(10);
                hBoxes[2].setLayoutX(WIDTH / 1.9f);
                hBoxes[2].setLayoutY(HEIGHT / 4f);
                hBoxes[2].getChildren().add(t);

                hBoxes[0] = new HBox();
                Label k = new Label(String.format("Current points: %d\nTotal points: %d", player.getCookies(), player.getCookiesEverProduced()));
                k.setFont(Font.font("Comic Sans", 40));
                k.setTextFill(Color.web("#FFFFFF"));
                hBoxes[0].setSpacing(10);
                hBoxes[0].setLayoutX(WIDTH / 1.9f);
                hBoxes[0].setLayoutY(HEIGHT / 1.5f);
                hBoxes[0].getChildren().add(k);

                root.getChildren().addAll(
                        hBoxes[2],
                        hBoxes[1],
                        hBoxes[0]
                );
            }

            private void appendNumbers(Clicker clicker) {
                numbers.add(new Bubblenumber(clicker.getCursors()));
            }

            private void displayInformation() {
                root.getChildren().remove(infoT);
                if (infoTag) {
                    infoT = new HBox();
                    Label z = new Label("Authors name: Enrico Vompa\nDate of manufacture: Today");
                    z.setFont(Font.font("Comic Sans", 20));
                    z.setTextFill(Color.web("#FFFFFF"));
                    infoT.setSpacing(10);
                    infoT.setLayoutX(15);
                    infoT.setLayoutY(55);
                    infoT.getChildren().add(z);
                    root.getChildren().add(infoT);
                }
            }
        };
        animator.start();
    }
}
