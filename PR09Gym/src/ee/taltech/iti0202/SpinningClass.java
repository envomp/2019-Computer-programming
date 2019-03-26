package ee.taltech.iti0202;

import java.util.List;
import java.util.stream.Collectors;

public class SpinningClass extends TrainingClass {

    SpinningClass(location location, difficulty difficulty, Client client) {
        super(location, difficulty, client);
    }

    SpinningClass(location location, difficulty difficulty, List<Client> client) {
        super(location, difficulty, client);
    }

    @Override
    public String toString() {
        return String.format("%shas a %s class in %s with difficulty %s.\n\n\n", getClient().stream().map(Client::toString).collect(Collectors.joining("")), "spinning", getTrainingLocation().toString().toLowerCase(), getTrainingDifficulty().toString().toLowerCase());
    }
}
