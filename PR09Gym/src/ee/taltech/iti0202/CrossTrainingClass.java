package ee.taltech.iti0202;

import java.util.List;
import java.util.stream.Collectors;

public class CrossTrainingClass extends TrainingClass {

    CrossTrainingClass(location location, difficulty difficulty, Client client) {
        super(location, difficulty, client);
    }

    CrossTrainingClass(location location, difficulty difficulty, List<Client> client) {
        super(location, difficulty, client);
    }

    @Override
    public String toString() {
        return String.format("%shas a %s class in %s with difficulty %s.\n\n\n", getClient().stream().map(Client::toString).collect(Collectors.joining("")), "cross training", getTrainingLocation().toString().toLowerCase(), getTrainingDifficulty().toString().toLowerCase());
    }
}
