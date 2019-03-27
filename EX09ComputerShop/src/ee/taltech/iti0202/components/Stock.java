package ee.taltech.iti0202.components;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Stock {

    HashMap<String, List<GraphicsCard>> graphicsCards;
    HashMap<String, List<HardDrive>> hardDrives;
    HashMap<String, List<Motherboard>> motherboards;
    HashMap<String, List<Processor>> processors;
    HashMap<String, List<PSU>> psus;
    HashMap<String, List<RAM>> rams;

    public Stock() {
        graphicsCards = new HashMap<>();
        hardDrives = new HashMap<>();
        motherboards = new HashMap<>();
        processors = new HashMap<>();
        psus = new HashMap<>();
        rams = new HashMap<>();
    }

    // graphics cards
    public List<GraphicsCard> stockAddGraphicsCards(String productName, Integer energyConsumption, Integer cost, Integer amount) {
        return IntStream.range(0, amount).mapToObj(i -> stockAddGraphicsCard(productName, energyConsumption, cost)).collect(Collectors.toList());
    }

    public GraphicsCard stockAddGraphicsCard(String productName, Integer energyConsumption, Integer cost) {
        GraphicsCard graphicsCard = new GraphicsCard(productName, energyConsumption, cost);
        graphicsCards.putIfAbsent(graphicsCard.getProductName(), new ArrayList<>());
        graphicsCards.get(graphicsCard.getProductName()).add(graphicsCard);
        return graphicsCard;
    }

    public void removeGraphicsCard(GraphicsCard graphicsCard) {
        graphicsCards.get(graphicsCard.getProductName()).remove(graphicsCard);
        if (graphicsCards.get(graphicsCard.getProductName()).isEmpty())
            graphicsCards.remove(graphicsCard.getProductName());
    }

    // hard drive
    public List<HardDrive> stockAddSSDHardDrives(String productName, Integer energyConsumption, hardDriveConnection connection, Integer cost, Integer amount) {
        return IntStream.range(0, amount).mapToObj(i -> stockAddHardDrive(productName, energyConsumption, connection, hardDriveType.SSD, cost)).collect(Collectors.toList());
    }

    public List<HardDrive> stockAddHDDHardDrives(String productName, Integer energyConsumption, hardDriveConnection connection, Integer cost, Integer amount) {
        return IntStream.range(0, amount).mapToObj(i -> stockAddHardDrive(productName, energyConsumption, connection, hardDriveType.HDD, cost)).collect(Collectors.toList());
    }

    public HardDrive stockAddHardDrive(String productName, Integer energyConsumption, hardDriveConnection connection, hardDriveType type, Integer cost) {
        HardDrive hardDrive = new HardDrive(productName, energyConsumption, connection, type, cost);
        hardDrives.putIfAbsent(hardDrive.getProductName(), new ArrayList<>());
        hardDrives.get(hardDrive.getProductName()).add(hardDrive);
        return hardDrive;
    }

    public void removeHardDrive(HardDrive hardDrive) {
        hardDrives.get(hardDrive.getProductName()).remove(hardDrive);
        if (hardDrives.get(hardDrive.getProductName()).isEmpty()) hardDrives.remove(hardDrive.getProductName());
    }

    // motherboard
    public List<Motherboard> stockAddMotherboards(String productName, Integer numberOfSockets, hardDriveConnection hardDriveConnection, processorSocket processorSocket, ramGeneration ramGeneration, Integer cost, Integer amount) {
        return IntStream.range(0, amount).mapToObj(i -> stockAddMotherboard(productName, numberOfSockets, hardDriveConnection, processorSocket, ramGeneration, cost)).collect(Collectors.toList());
    }

    public Motherboard stockAddMotherboard(String productName, Integer numberOfSockets, hardDriveConnection hardDriveConnection, processorSocket processorSocket, ramGeneration ramGeneration, Integer cost) {
        Motherboard motherboard = new Motherboard(productName, numberOfSockets, hardDriveConnection, processorSocket, ramGeneration, cost);
        motherboards.putIfAbsent(motherboard.getProductName(), new ArrayList<>());
        motherboards.get(motherboard.getProductName()).add(motherboard);
        return motherboard;
    }

    public void removeMotherboard(Motherboard motherboard) {
        motherboards.get(motherboard.getProductName()).remove(motherboard);
        if (motherboards.get(motherboard.getProductName()).isEmpty()) motherboards.remove(motherboard.getProductName());
    }

    // processor
    public List<Processor> stockAddProcessors(String productName, Integer energyConsumption, processorSocket socket, Integer cost, Integer amount) {
        return IntStream.range(0, amount).mapToObj(i -> stockAddProcessor(productName, energyConsumption, socket, cost)).collect(Collectors.toList());
    }

    public Processor stockAddProcessor(String productName, Integer energyConsumption, processorSocket socket, Integer cost) {
        Processor processor = new Processor(productName, energyConsumption, socket, cost);
        processors.putIfAbsent(processor.getProductName(), new ArrayList<>());
        processors.get(processor.getProductName()).add(processor);
        return processor;
    }

    public void removeProcessor(Processor processor) {
        processors.get(processor.getProductName()).remove(processor);
        if (processors.get(processor.getProductName()).isEmpty()) processors.remove(processor.getProductName());
    }

    // psu
    public List<PSU> stockAddPSUs(String productName, Integer capacity, Integer cost, Integer amount) {
        return IntStream.range(0, amount).mapToObj(i -> stockAddPSU(productName, capacity, cost)).collect(Collectors.toList());
    }

    public PSU stockAddPSU(String productName, Integer capacity, Integer cost) {
        PSU psu = new PSU(productName, capacity, cost);
        psus.putIfAbsent(psu.getProductName(), new ArrayList<>());
        psus.get(psu.getProductName()).add(psu);
        return psu;
    }

    public void removePSU(PSU psu) {
        psus.get(psu.getProductName()).remove(psu);
        if (psus.get(psu.getProductName()).isEmpty()) psus.remove(psu.getProductName());
    }

    // ram
    public List<RAM> stockAddRAMs(String productName, Integer energyConsumption, ramGeneration ramGeneration, Integer cost, Integer amount) {
        return IntStream.range(0, amount).mapToObj(i -> stockAddRAM(productName, energyConsumption, ramGeneration, cost)).collect(Collectors.toList());
    }

    public RAM stockAddRAM(String productName, Integer energyConsumption, ramGeneration ramGeneration, Integer cost) {
        RAM ram = new RAM(productName, energyConsumption, ramGeneration, cost);
        rams.putIfAbsent(ram.getProductName(), new ArrayList<>());
        rams.get(ram.getProductName()).add(ram);
        return ram;
    }

    public void removeRAM(RAM ram) {
        rams.get(ram.getProductName()).remove(ram);
        if (rams.get(ram.getProductName()).isEmpty()) rams.remove(ram.getProductName());
    }

    public HashMap<String, List<GraphicsCard>> getGraphicsCards() {
        return graphicsCards;
    }

    public HashMap<String, List<HardDrive>> getHardDrives() {
        return hardDrives;
    }

    public HashMap<String, List<Motherboard>> getMotherboards() {
        return motherboards;
    }

    public HashMap<String, List<Processor>> getProcessors() {
        return processors;
    }

    // getters

    public HashMap<String, List<PSU>> getPsus() {
        return psus;
    }

    public HashMap<String, List<RAM>> getRams() {
        return rams;
    }

    public enum hardDriveConnection {
        SATA,
        M2,
        PCIe
    }

    public enum hardDriveType {
        HDD,
        SSD
    }

    public enum processorSocket {
        LGA1151,
        LGA1150,
        AM4,
        AM1
    }

    public enum ramGeneration {
        DDR3,
        DDR4
    }

}
