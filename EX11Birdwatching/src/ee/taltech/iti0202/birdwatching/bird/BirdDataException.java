package ee.taltech.iti0202.birdwatching.bird;

public class BirdDataException extends Exception {
    public BirdDataException(Exception exe) {
        super("Error handling bird data", exe);
    }
}
