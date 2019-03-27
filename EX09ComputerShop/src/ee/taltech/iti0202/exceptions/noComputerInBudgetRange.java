package ee.taltech.iti0202.exceptions;

public class noComputerInBudgetRange extends RuntimeException {
    public noComputerInBudgetRange(String msg) {
        super(msg);
    }
}
