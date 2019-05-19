package ee.taltech.iti0202.casino.exeptions;

public class IncorrectStake extends RuntimeException {

    public IncorrectStake(String msg) {
        super(msg);
    }
}
