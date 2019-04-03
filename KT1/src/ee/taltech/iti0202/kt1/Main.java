package ee.taltech.iti0202.kt1;

import ee.taltech.iti0202.kt1.port.Cargo;
import ee.taltech.iti0202.kt1.port.Port;
import ee.taltech.iti0202.kt1.port.Ship;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Port paldiski = new Port("Paldiski");

        Ship abruka = new Ship("Abruka", new ArrayList<>(), 20000);
        List<String> juustud = new ArrayList<>();
        juustud.add("Juust");
        Ship moonsund = new Ship("Moonsund", juustud, 10000);

        List<Cargo> cargoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cargoList.add(new Cargo("Liha", 10, 10));
            cargoList.add(new Cargo("Juust", 10, 10));
        }

        paldiski.addShip(abruka);
        paldiski.addShip(moonsund);

        paldiski.addCargo(cargoList);

        System.out.println(paldiski.getShips()); // abruka, moonsund
        System.out.println(abruka.getCargoList()); // []
        System.out.println(moonsund.getCurrentCapacityPercentage()); // 0

        System.out.println();
        System.out.println(abruka.getCurrentCapacityPercentage()); // 0
        System.out.println(abruka.addCargo(new Cargo("Leib", 20, 10))); // true
        System.out.println(abruka.getCurrentCapacityPercentage()); // 10

        System.out.println();
        System.out.println(paldiski.emptyCargoStorage()); // 20

        System.out.println(moonsund.getCargoList());
        System.out.println(abruka.getCargoList());

        System.out.println(moonsund.getCargoList().size()); // 10
        System.out.println(abruka.getCargoList().size()); // 11
        System.out.println(paldiski.getCargoStorage()); // []
        System.out.println();

        List<String> saiad = new ArrayList<>();
        saiad.add("Sai");
        Ship neptun = new Ship("Neptun", saiad, 10000);

        Cargo juust = new Cargo("Juust", 10, 10);
        Cargo sai = new Cargo("Sai", 10, 10);
        Cargo maja = new Cargo("Maja", 10, 1000);

        neptun.addCargo(sai);
        neptun.addCargo(maja);
        System.out.println(neptun.getCargoList()); // []
        neptun.addCargo(juust);
        System.out.println(neptun.getCargoList()); // [Name: Juust, Total: 1000]
    }
}
