package ee.taltech.iti0202.game;

public class Player {
    private int cookies;
    private int cookiesEverProduced;

    public Player() {
        cookies = 0;
        cookiesEverProduced = 0;
    }

    public int getCookies() {
        return cookies;
    }

    public int getCookiesEverProduced() {
        return cookiesEverProduced;
    }

    public void receiveCookies(int value) {
        cookies += value;
        cookiesEverProduced += value;
    }

    public boolean pay(int value) {
        if (value <= cookies) {
            cookies -= value;
            return true;
        } else {
            return false;
        }
    }
}

