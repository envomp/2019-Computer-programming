package ee.taltech.iti0202.casino.player;

import ee.taltech.iti0202.casino.exeptions.NoPlayers;

import java.util.List;

public abstract class PlayerBuilder {
    protected String forName;
    protected String lastName;
    protected int age;
    protected int budget;
    protected List<Integer> tokens;

    public PlayerBuilder setForName(String forName) {
        this.forName = forName;
        return this;
    }

    public PlayerBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PlayerBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public PlayerBuilder setBudget(int budget) {
        this.budget = budget;
        return this;
    }

    public PlayerBuilder setTokens(List<Integer> tokens) {
        this.tokens = tokens;
        return this;
    }

    public Player createPlayer() {
        throw new NoPlayers("Selected player mode doesnt exist.");
    }

    public static class BasicPlayerBuilder extends PlayerBuilder {
        @Override
        public Player createPlayer() {
            return new BasicPlayer(forName, lastName, age, budget, tokens);
        }
    }

    public static class AdvancedPLayerBuilder extends PlayerBuilder {
        @Override
        public Player createPlayer() {
            return new AdvancedPlayer(forName, lastName, age, budget, tokens);
        }
    }

    public static class RichPLayerBuilder extends PlayerBuilder {
        @Override
        public Player createPlayer() {
            return new RichPlayer(forName, lastName, age, budget, tokens);
        }
    }
}