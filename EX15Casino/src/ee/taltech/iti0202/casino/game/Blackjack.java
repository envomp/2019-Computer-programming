package ee.taltech.iti0202.casino.game;

import ee.taltech.iti0202.casino.CasinoRunner;
import ee.taltech.iti0202.casino.player.Player;
import ee.taltech.iti0202.casino.player.PlayerBuilder;

import java.util.stream.Collectors;

public class Blackjack extends Game {

    public Blackjack(int minimumWage) {
        super(minimumWage);
    }

    public Blackjack(int minimumWage, CasinoRunner casinoRunner) {
        super(minimumWage, casinoRunner);
    }

    public void turn() {
        while (true) {
            if (!gameOn) {
                break;
            } else {
                if (players.isEmpty()) {
                    Integer card = random.nextInt(12);
                    if (casino.checkOut(house) + card > 21) {
                        gameOn = false;
                    } else {
                        house.add(card);
                    }
                    if (casino.checkOut(house) > log.stream().mapToInt(Player::getCardsValue).max().getAsInt()) {
                        break;
                    } else {
                        continue;
                    }
                }
            }
            players = getPlayers().stream()
                    .filter(x -> x.getBlackjackTurn(random.nextInt(14), casino))
                    .collect(Collectors.toSet());
        }
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
        return "Blackjack";
    }
}
