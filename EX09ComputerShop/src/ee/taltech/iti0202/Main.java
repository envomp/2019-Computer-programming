package ee.taltech.iti0202;

import ee.taltech.iti0202.components.Stock;
import ee.taltech.iti0202.computer.Computer;
import ee.taltech.iti0202.computer.ComputerBuilder;

import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Stock stock = new Stock();
        IntStream.range(0, 3).forEach(x -> fillStock(stock));

        System.out.println("Graphics cards: ");
        System.out.println(stock.getGraphicsCards().keySet());
        System.out.println();
        System.out.println("RAM sticks: ");
        System.out.println(stock.getRams().keySet());
        System.out.println();
        System.out.println("Hard drives: ");
        System.out.println(stock.getHardDrives().keySet());
        System.out.println();
        System.out.println("PSU's: ");
        System.out.println(stock.getPsus().keySet());
        System.out.println();
        System.out.println("Motherboards: ");
        System.out.println(stock.getMotherboards().keySet());
        System.out.println();
        System.out.println("Processors: ");
        System.out.println(stock.getProcessors().keySet());
        System.out.println();
        System.out.println();

        Computer computer = new ComputerBuilder().createComputer(2000, ComputerBuilder.purpose.gaming, stock);
        System.out.println(computer);

        Computer computer2 = new ComputerBuilder().createComputer(ComputerBuilder.purpose.gaming, stock);
        System.out.println(computer2);

        Computer computer3 = new ComputerBuilder().createComputer(2000, stock);
        System.out.println(computer3);

        Computer computer4 = new ComputerBuilder().createComputer(stock);
        System.out.println(computer4);

    }


    private static void fillStock(Stock stock) {
        stock.stockAddGraphicsCards("Nvidia GeForce RTX 2080 Ti", 140, 999, 3);
        stock.stockAddGraphicsCards("Nvidia GeForce GTX-1060", 110, 600, 1);
        stock.stockAddGraphicsCards("AMD Radeon RX 570 4GB", 60, 149, 4);

        stock.stockAddRAMs("Kingston HyperX Predator", 50, Stock.ramGeneration.DDR3, 90, 2);
        stock.stockAddRAMs("G.Skill Trident Z RGB", 60, Stock.ramGeneration.DDR4, 110, 3);
        stock.stockAddRAMs("2X Kingston HyperX Predator", 100, Stock.ramGeneration.DDR3, 180, 2);
        stock.stockAddRAMs("2X G.Skill Trident Z RGB", 120, Stock.ramGeneration.DDR4, 220, 3);

        stock.stockAddHDDHardDrives("WD Black 1TB", 45, Stock.hardDriveConnection.SATA, 69, 2);
        stock.stockAddSSDHardDrives("WD Black SN750 250GB", 35, Stock.hardDriveConnection.PCIe, 55, 2);
        stock.stockAddSSDHardDrives("Samsung 860 Evo 1TB", 35, Stock.hardDriveConnection.SATA, 70, 3);

        stock.stockAddPSUs("Bitfenix BF450G", 450, 59, 4);
        stock.stockAddPSU("Corsair RM550x", 550, 105);

        Random random = new Random();
        stock.stockAddMotherboards("Gigabyte Z390 Aorus Ultra", 64, Stock.hardDriveConnection.values()[random.nextInt(Stock.hardDriveConnection.values().length)], Stock.processorSocket.values()[random.nextInt(Stock.processorSocket.values().length)], Stock.ramGeneration.values()[random.nextInt(Stock.ramGeneration.values().length)], 350, 16);
        stock.stockAddMotherboards("ASUS ROG Maximus XI Hero Wi-Fi", 48, Stock.hardDriveConnection.values()[random.nextInt(Stock.hardDriveConnection.values().length)], Stock.processorSocket.values()[random.nextInt(Stock.processorSocket.values().length)], Stock.ramGeneration.values()[random.nextInt(Stock.ramGeneration.values().length)], 260, 12);

        stock.stockAddProcessors("Intel Core i9-9900K", 70, Stock.processorSocket.LGA1151, 700, 1);
        stock.stockAddProcessors("Intel Core i7-9700K", 110, Stock.processorSocket.LGA1150, 400, 4);
        stock.stockAddProcessors("AMD Ryzen 5 2600X", 150, Stock.processorSocket.AM4, 200, 3);
    }
}
