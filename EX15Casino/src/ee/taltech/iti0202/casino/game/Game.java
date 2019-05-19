package ee.taltech.iti0202.casino.game;

import com.google.gson.JsonObject;
import ee.taltech.iti0202.casino.CasinoRunner;
import ee.taltech.iti0202.casino.exeptions.IncorrectStake;
import ee.taltech.iti0202.casino.exeptions.NoPlayers;
import ee.taltech.iti0202.casino.player.Player;
import ee.taltech.iti0202.casino.player.PlayerBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Game {

    public final int minimumWage;
    protected JsonObject endProtocol = new JsonObject();
    protected CasinoRunner casino;
    protected Set<Player> log;
    protected Set<Player> players;
    protected List<Integer> house = new ArrayList<>();
    protected final Random random = new Random();
    protected boolean gameOn = true;

    public Game(int minimumWage) {
        this(minimumWage, new CasinoRunner());
    }

    public Game(int minimumWage, CasinoRunner casinoRunner) {
        this.casino = casinoRunner;
        if (minimumWage % casinoRunner.TEN != 0) {
            throw new IncorrectStake("Start wage is wrong");
        }
        this.minimumWage = minimumWage;

    }

    public void start(Set<Player> players) {
        this.players = players.stream()
                .filter(x -> x.getAge() >= 21)
                .filter(x -> x.joinGame(minimumWage, casino))
                .collect(Collectors.toSet());
        log = this.players;
        if (this.players.size() == 0) {
            throw new NoPlayers("There are no valid players to play selected game!");
        }

        turn();
    }

    public abstract void turn();

    public void makeProtocol() {
        List<Player> leaderboard = new ArrayList<>(log);
        leaderboard.sort(Comparator.comparingInt(Player::getCardsValue).reversed());
        leaderboard.get(0).addBudget(log.size() * minimumWage);

        int i = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        JsonObject curGame = new JsonObject();
        for (Player player : leaderboard) {
            i++;
            player.dockBudget(minimumWage);
            JsonObject playerScore = new JsonObject();
            playerScore.addProperty("Name", player.toString());
            playerScore.addProperty("Card Score", player.getCardsValue());
            playerScore.addProperty("budget delta", player.getBudget() - player.getStartingBudget());
            endProtocol.add(i == 1 ? "Winner" : Integer.toString(i), playerScore);
        }
        curGame.add(dtf.format(now), endProtocol);
        casino.getAllGames().add(getGame() + "  " + String.valueOf(random.nextDouble()).substring(2), endProtocol);
        log.forEach(Player::nullCards);
        log.forEach(x -> x.addBudget(casino.checkOut(x.getTokens())));
    }

    public JsonObject getEndProtocol() {
        return endProtocol;
    }

    public CasinoRunner getCasino() {
        return casino;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public abstract String getGame();
}
