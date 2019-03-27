package ee.taltech.iti0202.components;

public class Motherboard extends Product {

    Integer numberOfSockets;
    hardDriveConnection hardDriveConnection;
    processorSocket processorSocket;
    ramGeneration ramGeneration;

    Motherboard(String productName, Integer numberOfSockets, hardDriveConnection hardDriveConnection, processorSocket processorSocket, ramGeneration ramGeneration, Integer cost) {
        super(productName, 0, cost);
        this.numberOfSockets = numberOfSockets;
        this.hardDriveConnection = hardDriveConnection;
        this.processorSocket = processorSocket;
        this.ramGeneration = ramGeneration;
    }

    public Integer getNumberOfSockets() {
        return numberOfSockets;
    }

    public Stock.hardDriveConnection getHardDriveConnection() {
        return hardDriveConnection;
    }

    public Stock.processorSocket getProcessorSocket() {
        return processorSocket;
    }

    public Stock.ramGeneration getRamGeneration() {
        return ramGeneration;
    }
}
