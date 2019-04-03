package ee.taltech.iti0202.kt1.port;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Port {
    private String name;
    private List<Ship> ships;
    private List<Cargo> cargos;

    public Port(String name, List<Ship> ships) {
        this.name = name;
        this.ships = ships;
        this.cargos = new ArrayList<>();
    }

    public Port(String name) {
        this.name = name;
        this.ships = new ArrayList<>();
        this.cargos = new ArrayList<>();
    }

    public List<Cargo> getCargoStorage() {
        return cargos;

    }

    public void addShip(Ship ship) {
        if (!ships.contains(ship)) {
            ships.add(ship);
        }
    }

    public void addCargo(List<Cargo> cargoList) {
        for (Cargo cargo : cargoList) {
            if (!cargos.contains(cargo)) {
                cargos.add(cargo);
            }
        }
    }

    public String getName() {
        return name;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public int emptyCargoStorage() {
        int i = 0;
        List<Cargo> temp = new ArrayList<>(cargos);
        for (Cargo cargo : cargos) {
            List<Ship> sortedShips = ships;
            sortedShips.sort(Comparator.comparing(a -> a.getCargoList().size()));
            for (Ship ship : sortedShips) {
                if (ship.addCargo(cargo)) {
                    temp.remove(cargo);
                    i++;
                    break;
                }
            }
        }
        cargos = temp;
        return i;
    }
}
