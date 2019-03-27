package ee.taltech.iti0202.components;

public class Processor extends Product {

    Stock.processorSocket processorSocket;

    Processor(String productName, Integer energyConsumption, processorSocket socket, Integer cost) {
        super(productName, energyConsumption, cost);
        this.processorSocket = socket;
    }

    public Stock.processorSocket getProcessorSocket() {
        return processorSocket;
    }
}
