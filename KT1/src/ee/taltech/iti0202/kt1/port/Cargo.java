package ee.taltech.iti0202.kt1.port;

public class Cargo {
    private String name;
    private Integer weight;
    private Integer size;

    public Cargo(String name, int weight, int size) {
        this.name = name;
        this.weight = weight;
        this.size = size;
    }

    public int getTotalWeight() {
        if (weight < 10 || size < 10) {
            return 10;
        } return weight * size * size;
    }

    public int getWeight() {
        return weight;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Total: %d", this.name, this.getTotalWeight());
    }
}
