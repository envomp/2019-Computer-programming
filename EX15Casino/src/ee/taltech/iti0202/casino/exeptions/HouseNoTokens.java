package ee.taltech.iti0202.casino.exeptions;

public class HouseNoTokens extends RuntimeException {
    public HouseNoTokens(String msh) {
        super(msh);
    }
}
