package ee.taltech.iti0202.casino.player;

import ee.taltech.iti0202.casino.CasinoRunner;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    protected String forName;
    protected String lastName;
    protected int age;
    protected Integer budget;
    protected Integer startingBudget;
    protected List<Integer> tokens;
    protected List<Integer> cards = new ArrayList<>();

    public Player(String forName, String lastName, int age, int budget, List<Integer> tokens) {
        this.forName = forName;
        this.lastName = lastName;
        this.age = age;
        this.startingBudget = budget;
        this.budget = budget;
        if (tokens == null) {
            this.tokens = new ArrayList<>();
        } else {
            this.tokens = tokens;
        }
    }

    public boolean joinGame(int price, CasinoRunner casinoRunner) {
        this.startingBudget = budget;
        if (price > budget) {
            return false;
        }
        if (tokens == null) {
            tokens = casinoRunner.joinGame(this, price);
        } else {
            tokens.addAll(casinoRunner.joinGame(this, price));
        }
        return true;
    }

    public abstract boolean getBlackjackTurn(int nextInt, CasinoRunner casinoRunner);

    public abstract boolean getPokerTurn(int nextInt, CasinoRunner casinoRunner, List<Integer> board);


    public String getForName() {
        return forName;
    }


    public String getLastName() {
        return lastName;
    }


    public int getAge() {
        return age;
    }

    public int getBudget() {
        return budget;
    }

    public Integer getStartingBudget() {
        return startingBudget;
    }

    public void addBudget(int sum) {
        budget += sum;
    }

    public void dockBudget(int sum) {
        budget -= sum;
    }

    public void nullCards() {
        this.cards = new ArrayList<>();
    }

    public List<Integer> getTokens() {
        return tokens;
    }

    public List<Integer> getCards() {
        return cards;
    }

    public Player setCards(List<Integer> cards) {
        this.cards = cards;
        return this;
    }

    public int getCardsValue() {
        return cards.stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public String toString() {
        return forName + " " + lastName;
    }
}
