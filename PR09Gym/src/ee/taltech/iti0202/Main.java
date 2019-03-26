package ee.taltech.iti0202;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var x = new ClientBuilder().setClientAge(40).setClientName("Ago").setClientPhoneNumber(112).createClient();
        var y = new ClientBuilder().setClientName("Enrico").setClientAge(19).createClient();
        var z = new ClientBuilder().setClientAge(100).setClientName("Ago2").setClientPhoneNumber(56644052).createClient();
        var g = new ClientBuilder().setClientName("Tudeng").setClientPhoneNumber(911).createClient();

        y.setClientPhoneNumber(12345678);
        g.setClientAge(22);

        var a = TrainingClass.easyCrossTraining(TrainingClass.location.TALLINN, x);
        var b = TrainingClass.tartuTrainingSpinning(TrainingClass.difficulty.HARD, Arrays.asList(x, y));
        var c = TrainingClass.hardCrossTraining(TrainingClass.location.TALLINN, z);
        var d = TrainingClass.tallinnTrainingSpinning(TrainingClass.difficulty.HARD, Arrays.asList(z, g, x, y));

        d.removeTrainingClient(x);
        a.addTrainingClient(g);
        c.setTrainingLocation(TrainingClass.location.TARTU);
        d.setTrainingDifficulty(TrainingClass.difficulty.EASY);

        System.out.println("\n" + a + "\n" + b + "\n" + c + "\n" + d + "\n");
    }
}
