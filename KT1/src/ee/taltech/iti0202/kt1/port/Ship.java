package ee.taltech.iti0202.kt1.port;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private String name;
    private List<String> restrictions;
    private List<Cargo> cargos;
    private Integer capacity;

    public Ship(String name, List<String> restrictions, int capacity) {
        this.name = name;
        this.restrictions = restrictions;
        this.capacity = capacity;
        this.cargos = new ArrayList<>();
    }

    public boolean addCargo(Cargo cargo) {
        if (cargo == null || cargos.contains(cargo) || cargo.getSize() == capacity ||
                restrictions.contains(cargo.getName())) {
            return false;
        }
        cargos.add(cargo);
        return true;
    }

    public int getCurrentCapacityPercentage() {
        Integer total = 0;
        for (Cargo cargo : cargos) {
            total += cargo.getTotalWeight();
        }
        return (int) (Math.floor(total * 100 / capacity));
    }

    public void addRestriction(String restriction) {
        if (restrictions.contains(restriction)) {
            restrictions.remove(restriction);
        } else {
            restrictions.add(restriction);
        }
    }

    public String getName() {
        return name;
    }

    public List<String> getRestrictions() {
        return restrictions;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public List<Cargo> getCargoList() {
        return cargos;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
