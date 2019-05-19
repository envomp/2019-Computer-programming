package ee.taltech.iti0202.casino.game;

import ee.taltech.iti0202.casino.CasinoRunner;
import ee.taltech.iti0202.casino.player.Player;
import ee.taltech.iti0202.casino.player.PlayerBuilder;

import java.util.stream.Collectors;

public class Poker extends Game { ;

    public Poker(int minimumWage) {
        super(minimumWage);
    }

    public Poker(int minimumWage, CasinoRunner casinoRunner) {
        super(minimumWage, casinoRunner);
    }

    @Override
    public void turn() {

        house.add(random.nextInt(5));
        players = getPlayers().stream()
                .filter(x -> x.getPokerTurn(random.nextInt(5), casino, house)).collect(Collectors.toSet());
        log.forEach(player -> {
            if (!players.contains(player)) {
                player.getCards().remove(0);
                player.getCards().add(0);
                player.addBudget(minimumWage >> 1);
                casino.setBank(casino.getBank() + minimumWage >> 1);
            } else if (player.getCards().get(0).equals(house.get(0))) {
                player.getCards().add(player.getCards().remove(0) * 2);
            }
        });

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
        return "Poker";
    }
}
