package ee.taltech.iti0202.components;

import java.util.Random;

public abstract class Product extends Stock {

    String productName;
    Integer energyConsumption;
    Integer cost;
    Integer perfomancePoint;

    Product(String name, Integer energyConsumption, Integer cost) {
        this.energyConsumption = energyConsumption;
        this.productName = name;
        this.cost = cost;
        this.perfomancePoint = (new Random().nextInt(100) + cost - 50) + (cost > 1000 ? 50 : 0);
    }

    public String getProductName() {
        return productName;
    }

    public Integer getEnergyConsumption() {
        return energyConsumption;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getPerfomancePoint() {
        return perfomancePoint;
    }

    @Override
    public String toString() {
        return String.format("%-30s", this.productName) + "\tPerfomance points: " + perfomancePoint;
    }
}
