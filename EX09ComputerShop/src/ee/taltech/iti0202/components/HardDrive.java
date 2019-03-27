package ee.taltech.iti0202.components;

public class HardDrive extends Product {

    private hardDriveConnection hardDriveConnection;
    private hardDriveType hardDriveType;


    HardDrive(String productName, Integer energyConsumption, hardDriveConnection connection, hardDriveType type, Integer cost) {
        super(productName, energyConsumption, cost);
        this.hardDriveConnection = connection;
        this.hardDriveType = type;
    }

    public Stock.hardDriveConnection getHardDriveConnection() {
        return hardDriveConnection;
    }

    public Stock.hardDriveType getHardDriveType() {
        return hardDriveType;
    }
}
