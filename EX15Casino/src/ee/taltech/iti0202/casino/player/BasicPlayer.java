package ee.taltech.iti0202.casino.player;

import ee.taltech.iti0202.casino.CasinoRunner;

import java.util.ArrayList;
import java.util.List;

public class BasicPlayer extends Player {
    public BasicPlayer(String forName, String lastName, int age, int budget, List<Integer> tokens) {
        super(forName, lastName, age, budget, tokens);
    }

    @Override
    public boolean getBlackjackTurn(int nextInt, CasinoRunner casinoRunner) {
        cards.add(nextInt);
        if (casinoRunner.checkOut(cards) > 21) {
            cards = new ArrayList<>();
            return false;
        }
        return casinoRunner.checkOut(cards) < 17;
    }

    @Override
    public boolean getPokerTurn(int nextInt, CasinoRunner casinoRunner, List<Integer> board) {
        cards.add(nextInt);
        return true;
    }
}
