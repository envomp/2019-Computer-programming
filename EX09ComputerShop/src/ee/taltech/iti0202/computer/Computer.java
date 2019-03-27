package ee.taltech.iti0202.computer;

import ee.taltech.iti0202.components.*;

public class Computer {

    private GraphicsCard graphicsCard;
    private HardDrive hardDrive;
    private Motherboard motherboard;
    private Processor processor;
    private PSU psu;
    private RAM ram;
    private Integer budget;
    private Integer total_cost;
    private Integer PP;

    public Computer(GraphicsCard graphicsCard, HardDrive hardDrive, Motherboard motherboard, Processor processor, PSU psu, RAM ram, Integer budget, Integer total_cost, Integer PP) {
        this.graphicsCard = graphicsCard;
        this.hardDrive = hardDrive;
        this.motherboard = motherboard;
        this.processor = processor;
        this.psu = psu;
        this.ram = ram;
        this.budget = budget;
        this.total_cost = total_cost;
        this.PP = PP;
    }

    public GraphicsCard getGraphicsCard() {
        return graphicsCard;
    }

    public HardDrive getHardDrive() {
        return hardDrive;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public Processor getProcessor() {
        return processor;
    }

    public PSU getPsu() {
        return psu;
    }

    public RAM getRam() {
        return ram;
    }

    public Integer getBudget() {
        return budget;
    }

    public Integer getPP() {
        return PP;
    }

    public Integer getTotal_cost() {
        return total_cost;
    }

    @Override
    public String toString() {
        return String.format("%-20s%s\n%-20s%s\n%-20s%s\n%-20s%s\n%-20s%s\n%-20s%s\n%-20s%d\n%-20s%d\n%-20s%d\n",
                "Graphics card: ", graphicsCard, "RAM: ", ram, "Motherboard: ",
                motherboard, "Processor: ", processor, "PSU: ", psu, "Hard drive: ",
                hardDrive, "Budget: ", budget, "Total Cost: ", total_cost, "Performance point: ", PP);
    }
}
