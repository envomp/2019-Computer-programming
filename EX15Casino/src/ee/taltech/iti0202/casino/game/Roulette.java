package ee.taltech.iti0202.casino.game;

import ee.taltech.iti0202.casino.CasinoRunner;
import ee.taltech.iti0202.casino.player.Player;
import ee.taltech.iti0202.casino.player.PlayerBuilder;

import java.util.stream.Collectors;

public class Roulette extends Game {
    public Roulette(int minimumWage) {
        super(minimumWage);
    }

    public Roulette(int minimumWage, CasinoRunner casinoRunner) {
        super(minimumWage, casinoRunner);
    }

    @Override
    public void turn() {

        players = getPlayers().stream()
                .filter(x -> x.getBlackjackTurn(random.nextInt(14), casino))
                .collect(Collectors.toSet());
        house.add(random.nextInt(14));
        gameOn = false;

        makeProtocol();

    }

    @Override
    public void makeProtocol() {
        Player housePlayer = new PlayerBuilder.AdvancedPLayerBuilder()
                .setForName("The")
                .setLastName("House")
                .setBudget(casino.getBank())
                .createPlayer()
                .setCards(house);

        log.add(housePlayer);
        super.makeProtocol();
        casino.setBank(housePlayer.getBudget());
    }

    @Override
    public String getGame() {
        return "Roulete";
    }
}
