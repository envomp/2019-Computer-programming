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

    public Computer createComputer(Stock stock) {
        return createComputer(1000000, purpose.pseudo, stock);
    }

    public Computer createComputer(purpose purpose, Stock stock) {
        return createComputer(1000000, purpose, stock);
    }

    public Computer createComputer(Integer budget, Stock stock) {
        return createComputer(budget, purpose.pseudo, stock);
    }


    public Computer createComputer(Integer budget, purpose purpose, Stock stock) {
        System.out.println(String.format("Order received on %s computer\n", purpose));
        loopPermutations(budget, stock);
        testIfMatchWasFound(stock);
        System.out.println();
        System.out.println();
        return new Computer(graphicsCard, hardDrive, motherboard, processor, psu, ram, budget, total_cost, PP_temp);
    }

    private void loopPermutations(Integer budget, Stock stock) {
        int loading_bar = 0;
        List<GraphicsCard> graphicsCardList = stock.getGraphicsCards().values().stream().flatMap(List::stream).collect(Collectors.toList());
        int goal = graphicsCardList.size();
        List<HardDrive> hardDriveList = stock.getHardDrives().values().stream().flatMap(List::stream).collect(Collectors.toList());
        List<Motherboard> motherboardList = stock.getMotherboards().values().stream().flatMap(List::stream).collect(Collectors.toList());
        List<Processor> processorList = stock.getProcessors().values().stream().flatMap(List::stream).collect(Collectors.toList());
        List<PSU> psuList = stock.getPsus().values().stream().flatMap(List::stream).collect(Collectors.toList());
        List<RAM> ramList = stock.getRams().values().stream().flatMap(List::stream).collect(Collectors.toList());
        for (GraphicsCard graphicsCard_temp : graphicsCardList) {
            System.out.print(String.format("[%-25s]\r", "#".repeat(loading_bar * 27 / goal)));
            for (HardDrive hardDrive_temp : hardDriveList)
                for (Motherboard motherboard_temp : motherboardList)
                    for (Processor processor_temp : processorList)
                        for (PSU psu_temp : psuList)
                            for (RAM ram_temp : ramList) {
                                testForMatch(budget, graphicsCard_temp, hardDrive_temp, motherboard_temp, processor_temp, psu_temp, ram_temp);
                            }
            loading_bar++;
        }
    }

    private void testIfMatchWasFound(Stock stock) {
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
    }

    private void testForMatch(Integer budget, GraphicsCard graphicsCard_temp, HardDrive hardDrive_temp, Motherboard motherboard_temp, Processor processor_temp, PSU psu_temp, RAM ram_temp) {
        int temp_cost = graphicsCard_temp.getCost() + hardDrive_temp.getCost() + motherboard_temp.getCost() + processor_temp.getCost() + psu_temp.getCost() + ram_temp.getCost();
        if (temp_cost > budget) return;
        if (graphicsCard_temp.getEnergyConsumption() + hardDrive_temp.getEnergyConsumption() + motherboard_temp.getEnergyConsumption() + processor_temp.getEnergyConsumption() + ram_temp.getEnergyConsumption() > psu_temp.getCapacity())
            return;
        if (motherboard_temp.getNumberOfSockets() < 16) return;
        if (motherboard_temp.getHardDriveConnection() != hardDrive_temp.getHardDriveConnection())
            return;
        if (motherboard_temp.getProcessorSocket() != processor_temp.getProcessorSocket())
            return;
        if (motherboard_temp.getRamGeneration() != ram_temp.getRamGeneration()) return;
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
        work,
        pseudo
    }
}