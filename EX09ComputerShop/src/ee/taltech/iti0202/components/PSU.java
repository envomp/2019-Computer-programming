package ee.taltech.iti0202.components;

public class PSU extends Product {

    Integer capacity;

    PSU(String productName, Integer capacity, Integer cost) {
        super(productName, 0, cost);
        this.capacity = capacity;
    }

    public Integer getCapacity() {
        return capacity;
    }
}
