package ee.taltech.iti0202.game;


public class Clicker {
    private Player player;
    private int clicker;
    private int cursors;
    private int cursorPrice;
    private int clickerPrice;

    Clicker(Player player) {
        this.player = player;
        this.clicker = 0;
        this.cursors = 1;
        this.cursorPrice = 10;
        this.clickerPrice = 50;
    }

    public int getClicker() {
        return clicker;
    }

    public int getCursors() {
        return cursors;
    }

    public void buyClicker() {
        if (player.pay(clickerPrice)) {
            clickerPrice += 50;
            clicker++;
        }
    }

    public void buyCursor() {
        if (player.pay(cursorPrice)) {
            cursorPrice += 10;
            cursors++;
        }
    }

    public int getClickerPrice() {
        return clickerPrice;
    }

    public int getCursorPrice() {
        return cursorPrice;
    }
}
