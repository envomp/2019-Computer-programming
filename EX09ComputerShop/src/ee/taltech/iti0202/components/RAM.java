package ee.taltech.iti0202.components;

public class RAM extends Product {

    Stock.ramGeneration ramGeneration;

    RAM(String productName, Integer energyConsumption, ramGeneration ramGeneration, Integer cost) {
        super(productName, energyConsumption, cost);
        this.ramGeneration = ramGeneration;
    }

    public Stock.ramGeneration getRamGeneration() {
        return ramGeneration;
    }
}
