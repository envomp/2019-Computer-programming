package ee.taltech.iti0202.computer;

import ee.taltech.iti0202.components.*;
import ee.taltech.iti0202.exceptions.noComputerInBudgetRange;

import java.util.List;
import java.util.stream.Collectors;

public class ComputerBuilder {

    private GraphicsCard graphicsCard;
    private HardDrive hardDrive;
    private Motherboard motherboard;
    private Processor processor;
    private PSU psu;
    private RAM ram;
    private Integer PP_temp = 0;
    private Integer total_cost;

    public Computer createComputer(Integer budget, purpose purpose, Stock stock) {
        System.out.println(String.format("Order received on %s computer\n", purpose));
        int loading_bar = 0;
        int goal = stock.getGraphicsCards().values().stream().flatMap(List::stream).collect(Collectors.toList()).size();
        for (GraphicsCard graphicsCard_temp : stock.getGraphicsCards().values().stream().flatMap(List::stream).collect(Collectors.toList())) {
            System.out.print(String.format("[%-25s]\r", "#".repeat(loading_bar * 27 / goal)));
            for (HardDrive hardDrive_temp : stock.getHardDrives().values().stream().flatMap(List::stream).collect(Collectors.toList()))
                for (Motherboard motherboard_temp : stock.getMotherboards().values().stream().flatMap(List::stream).collect(Collectors.toList()))
                    for (Processor processor_temp : stock.getProcessors().values().stream().flatMap(List::stream).collect(Collectors.toList()))
                        for (PSU psu_temp : stock.getPsus().values().stream().flatMap(List::stream).collect(Collectors.toList()))
                            for (RAM ram_temp : stock.getRams().values().stream().flatMap(List::stream).collect(Collectors.toList())) {
                                int temp_cost = graphicsCard_temp.getCost() + hardDrive_temp.getCost() + motherboard_temp.getCost() + processor_temp.getCost() + psu_temp.getCost() + ram_temp.getCost();
                                if (temp_cost > budget) continue;
                                if (graphicsCard_temp.getEnergyConsumption() + hardDrive_temp.getEnergyConsumption() + motherboard_temp.getEnergyConsumption() + processor_temp.getEnergyConsumption() + ram_temp.getEnergyConsumption() > psu_temp.getCapacity())
                                    continue;
                                if (motherboard_temp.getNumberOfSockets() < 16) continue;
                                if (motherboard_temp.getHardDriveConnection() != hardDrive_temp.getHardDriveConnection())
                                    continue;
                                if (motherboard_temp.getProcessorSocket() != processor_temp.getProcessorSocket())
                                    continue;
                                if (motherboard_temp.getRamGeneration() != ram_temp.getRamGeneration()) continue;
                                var temp = graphicsCard_temp.getPerfomancePoint() + hardDrive_temp.getPerfomancePoint() + motherboard_temp.getPerfomancePoint() + processor_temp.getPerfomancePoint() + psu_temp.getPerfomancePoint() + ram_temp.getPerfomancePoint();
                                if (temp > PP_temp) {
                                    total_cost = temp_cost;
                                    PP_temp = temp;
                                    graphicsCard = graphicsCard_temp;
                                    hardDrive = hardDrive_temp;
                                    motherboard = motherboard_temp;
                                    processor = processor_temp;
                                    psu = psu_temp;
                                    ram = ram_temp;
                                }
                            }
            loading_bar++;
        }
        try {
            stock.removeGraphicsCard(graphicsCard);
            stock.removeHardDrive(hardDrive);
            stock.removeMotherboard(motherboard);
            stock.removeProcessor(processor);
            stock.removePSU(psu);
            stock.removeRAM(ram);
        } catch (NullPointerException e) {
            throw new noComputerInBudgetRange("Sorry, we didnt find you a computer this time, please increase your budget or try again :)");
        }
        System.out.println();
        System.out.println();
        return new Computer(graphicsCard, hardDrive, motherboard, processor, psu, ram, budget, total_cost, PP_temp);
    }

    public ComputerBuilder setGraphicsCard(GraphicsCard graphicsCard) {
        this.graphicsCard = graphicsCard;
        return this;
    }

    public ComputerBuilder setHardDrive(HardDrive hardDrive) {
        this.hardDrive = hardDrive;
        return this;
    }

    public ComputerBuilder setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
        return this;
    }

    public ComputerBuilder setPsu(PSU psu) {
        this.psu = psu;
        return this;
    }

    public ComputerBuilder setProcessor(Processor processor) {
        this.processor = processor;
        return this;
    }

    public ComputerBuilder setRam(RAM ram) {
        this.ram = ram;
        return this;
    }

    public enum purpose {
        gaming,
        work
    }
}