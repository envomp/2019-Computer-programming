package ee.taltech.iti0202.casino.game;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ee.taltech.iti0202.casino.CasinoRunner;
import ee.taltech.iti0202.casino.exeptions.IncorrectStake;
import ee.taltech.iti0202.casino.player.AdvancedPlayer;
import ee.taltech.iti0202.casino.player.BasicPlayer;
import ee.taltech.iti0202.casino.player.PlayerBuilder;
import ee.taltech.iti0202.casino.player.RichPlayer;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameTest {

    private static final CasinoRunner casinoRunner = new CasinoRunner();
    private static final InitPlayers ip = new InitPlayers().invoke();


    @Test
    public void ZAllCasinoTest() {
        System.out.println("Casino------------------------------------------------------------------------");
        System.out.println(casinoRunner);
        prettyJson(casinoRunner.getAllGames());
        prettyJson(casinoRunner.getAllPlayers());
    }

    @Test
    public void CasinoTest() {
        assert casinoRunner.makeStake(100, List.of(10, 30, 20, 50, 50)).stream().mapToInt(Integer::intValue).sum() == 100;
        assert casinoRunner.makeStake(50, List.of(10, 30, 20, 20, 10)).stream().mapToInt(Integer::intValue).sum() == 50;
        assert casinoRunner.makeStake(50, List.of(10, 30, 20, 10, 10)).stream().mapToInt(Integer::intValue).sum() == 50;

    }

    @Test
    public void GenericGameTests() {
        IncorrectStake stake = null;
        try {
            new Blackjack(1, casinoRunner);
        } catch (IncorrectStake e) {
            stake = e;
        }
        assertNotNull(stake);
    }

    @Test
    public void BlackjackTest() {
        System.out.println("Blackjack--------------------------------------------------------------------");
        Blackjack blackjack = new Blackjack(100, casinoRunner);
        blackjack.start(new HashSet<>(Arrays.asList(ip.player1, ip.player2, ip.player3, ip.player4, ip.player5)));
        prettyJson(blackjack.getEndProtocol());
        assert !blackjack.getPlayers().contains(ip.player1);
        blackjack = new Blackjack(100, casinoRunner);
        blackjack.start(new HashSet<>(Arrays.asList(ip.player1, ip.player2, ip.player3, ip.player4, ip.player5)));
        prettyJson(blackjack.getEndProtocol());
    }

    @Test
    public void RouletteTest() {
        System.out.println("Roulette--------------------------------------------------------------------");
        Roulette roulette= new Roulette(100, casinoRunner);
        roulette.start(new HashSet<>(Arrays.asList(ip.player1, ip.player2, ip.player3, ip.player4, ip.player5)));
        prettyJson(roulette.getEndProtocol());
        assert !roulette.getPlayers().contains(ip.player1);
        roulette = new Roulette(100, casinoRunner);
        roulette.start(new HashSet<>(Arrays.asList(ip.player1, ip.player2, ip.player3, ip.player4, ip.player5)));
        prettyJson(roulette.getEndProtocol());
    }

    @Test
    public void PokerTest() {
        System.out.println("Poker--------------------------------------------------------------------");
        Poker poker= new Poker(100, casinoRunner);
        poker.start(new HashSet<>(Arrays.asList(ip.player1, ip.player2, ip.player3, ip.player4, ip.player5)));
        prettyJson(poker.getEndProtocol());
        assert !poker.getPlayers().contains(ip.player1);
        poker = new Poker(100, casinoRunner);
        poker.start(new HashSet<>(Arrays.asList(ip.player1, ip.player2, ip.player3, ip.player4, ip.player5)));
        prettyJson(poker.getEndProtocol());
    }

    private void prettyJson(JsonObject casinoRunner) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(casinoRunner);
        System.out.println(jsonOutput);
    }

    public static class InitPlayers {
        public BasicPlayer player1;
        public AdvancedPlayer player2;
        public AdvancedPlayer player3;
        public RichPlayer player4;
        public BasicPlayer player5;

        public InitPlayers invoke() {
            player1 = (BasicPlayer) new PlayerBuilder.BasicPlayerBuilder()
                    .setAge(18)
                    .setBudget(1000)
                    .setForName("Taat")
                    .setLastName("Paan")
                    .createPlayer();

            player2 = (AdvancedPlayer) new PlayerBuilder.AdvancedPLayerBuilder()
                    .setAge(21)
                    .setBudget(2000)
                    .setForName("Jaat")
                    .setLastName("Naan")
                    .createPlayer();

            player3 = (AdvancedPlayer) new PlayerBuilder.AdvancedPLayerBuilder()
                    .setAge(57)
                    .setBudget(1000)
                    .setForName("Paat")
                    .setLastName("Laan")
                    .createPlayer();

            player4 = (RichPlayer) new PlayerBuilder.RichPLayerBuilder()
                    .setAge(90)
                    .setBudget(10000)
                    .setForName("Jaap")
                    .setLastName("Raap")
                    .createPlayer();

            player5 = (BasicPlayer) new PlayerBuilder.BasicPlayerBuilder()
                    .setAge(21)
                    .setBudget(800)
                    .setForName("Laat")
                    .setLastName("Taan")
                    .createPlayer();
            return this;
        }
    }
}
