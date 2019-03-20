package ee.taltech.iti0202.game;

import java.util.Random;

public class Bubblenumber {

    private int value;
    private int x;
    private int y;

    Bubblenumber(int value) {
        this.value = value;
        this.y = 768;
        Random random = new Random();
        this.x = random.nextInt(600);
    }

    public void update() {
        this.y--;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }
}
