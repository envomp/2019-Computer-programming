package ee.taltech.iti0202;

import java.util.ArrayList;
import java.util.List;

public abstract class TrainingClass {
    private location trainingLocation;
    private difficulty trainingDifficulty;
    private List<Client> clients;

    TrainingClass(location location, difficulty difficulty, Client client) {
        this.trainingLocation = location;
        this.trainingDifficulty = difficulty;
        this.clients = new ArrayList<>();
        this.clients.add(client);
    }

    TrainingClass(location location, difficulty difficulty, List<Client> client) {
        this.trainingLocation = location;
        this.trainingDifficulty = difficulty;
        this.clients = client;
    }

    public static TrainingClass tallinnTrainingSpinning(difficulty difficulty, Client client) {
        return new SpinningClass(location.TALLINN, difficulty, client);
    }

    public static TrainingClass tartuTrainingSpinning(difficulty difficulty, Client client) {
        return new SpinningClass(location.TARTU, difficulty, client);
    }

    public static TrainingClass tallinnCrossTraining(difficulty difficulty, Client client) {
        return new CrossTrainingClass(location.TALLINN, difficulty, client);
    }

    public static TrainingClass tartuCrossTraining(difficulty difficulty, Client client) {
        return new CrossTrainingClass(location.TARTU, difficulty, client);
    }

    public static TrainingClass easyTrainingSpinning(location location, Client client) {
        return new SpinningClass(location, difficulty.EASY, client);
    }

    public static TrainingClass hardTrainingSpinning(location location, Client client) {
        return new SpinningClass(location, difficulty.HARD, client);
    }

    public static TrainingClass easyCrossTraining(location location, Client client) {
        return new CrossTrainingClass(location, difficulty.EASY, client);
    }

    public static TrainingClass hardCrossTraining(location location, Client client) {
        return new CrossTrainingClass(location, difficulty.HARD, client);
    }

    public static TrainingClass tallinnTrainingSpinning(difficulty difficulty, List<Client> client) {
        return new SpinningClass(location.TALLINN, difficulty, client);
    }

    public static TrainingClass tartuTrainingSpinning(difficulty difficulty, List<Client> client) {
        return new SpinningClass(location.TARTU, difficulty, client);
    }

    public static TrainingClass tallinnCrossTraining(difficulty difficulty, List<Client> client) {
        return new CrossTrainingClass(location.TALLINN, difficulty, client);
    }

    public static TrainingClass tartuCrossTraining(difficulty difficulty, List<Client> client) {
        return new CrossTrainingClass(location.TARTU, difficulty, client);
    }

    public static TrainingClass easyTrainingSpinning(location location, List<Client> client) {
        return new SpinningClass(location, difficulty.EASY, client);
    }

    public static TrainingClass hardTrainingSpinning(location location, List<Client> client) {
        return new SpinningClass(location, difficulty.HARD, client);
    }

    public static TrainingClass easyCrossTraining(location location, List<Client> client) {
        return new CrossTrainingClass(location, difficulty.EASY, client);
    }

    public static TrainingClass hardCrossTraining(location location, List<Client> client) {
        return new CrossTrainingClass(location, difficulty.HARD, client);
    }

    public void addTrainingClient(Client client) {
        List<Client> temp = new ArrayList<>(clients);
        temp.add(client);
        this.clients = temp;
    }

    public void removeTrainingClient(Client client) {
        List<Client> temp = new ArrayList<>(clients);
        temp.remove(client);
        this.clients = temp;
    }

    public difficulty getTrainingDifficulty() {
        return trainingDifficulty;
    }

    public void setTrainingDifficulty(difficulty trainingDifficulty) {
        this.trainingDifficulty = trainingDifficulty;
    }

    public location getTrainingLocation() {
        return trainingLocation;
    }

    public void setTrainingLocation(location trainingLocation) {
        this.trainingLocation = trainingLocation;
    }

    public List<Client> getClient() {
        return clients;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public enum location {
        TALLINN,
        TARTU
    }

    public enum difficulty {
        EASY,
        HARD
    }
}
