package ee.taltech.iti0202.casino;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ee.taltech.iti0202.casino.exeptions.HouseNoTokens;
import ee.taltech.iti0202.casino.player.Player;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class CasinoRunner {

    public final int TEN = 10;
    public final int TWENTY = 20;
    public final int THIRTY = 30;
    public final int FIFTY = 50;

    protected int bank = 1000;
    protected List<Integer> tokens = new ArrayList<>();
    protected JsonObject allGames = new JsonObject();
    protected Set<Player> allPlayers = new HashSet<>();

    public CasinoRunner() {
        IntStream.range(0, 15).forEach(i -> {
            tokens.add(TEN);
            tokens.add(TWENTY);
            tokens.add(THIRTY);
            tokens.add(FIFTY);
        });
    }

    public List<Integer> makeStake(int sum, List<Integer> stack) {
        long N = (long) Math.pow(2d, (double) stack.size());
        return LongStream.range(1, N)
                .mapToObj(i -> Long.toBinaryString((N | i)).substring(1))
                .map(code -> IntStream.range(0, stack.size())
                        .filter(j -> code.charAt(j) == '1')
                        .mapToObj(stack::get)
                        .collect(Collectors.toList()))
                .filter(temp -> temp.stream().mapToInt(Integer::intValue).sum() == sum)
                .findFirst().orElse(null);
    }

    public int checkOut(List<Integer> tokens) {
        return tokens.stream().mapToInt(Integer::intValue).sum();
    }

    public List<Integer> joinGame(Player player, int price) {
        List<Integer> tok = makeStake(price, new ArrayList<>(tokens));
        if (tok == null) {
            throw new HouseNoTokens("House is out of tokens!");
        }
        tokens.remove(tok);
        allPlayers.add(player);
        return tok;
    }

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public List<Integer> getTokens() {
        return tokens;
    }

    public JsonObject getAllGames() {
        return allGames;
    }

    public JsonObject getAllPlayers() {
        JsonArray jsonArray = new JsonArray();
        for (Player player : allPlayers) {
            JsonObject pl = new JsonObject();
            pl.addProperty("Name", player.toString());
            pl.addProperty("Budget", player.getBudget());
            pl.addProperty("Age", player.getAge());
            jsonArray.add(pl);
        }
        JsonObject pla = new JsonObject();
        pla.add("Players", jsonArray);
        return pla;
    }

    @Override
    public String toString() {
        return "Possible games are Blackjack, Poker and Roulette";
    }
}
