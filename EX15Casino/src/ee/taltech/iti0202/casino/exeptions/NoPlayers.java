package ee.taltech.iti0202.casino.exeptions;

public class NoPlayers extends RuntimeException {
    public NoPlayers(String msg) {
        super(msg);
    }
}
