package ee.taltech.iti0202.parking;

import ee.taltech.iti0202.parking.car.Car;
import ee.taltech.iti0202.parking.parkinglot.MultiLevelParkingLot;
import ee.taltech.iti0202.parking.parkinglot.ParkingLot;
import ee.taltech.iti0202.parking.parkinglot.PriorityParkingLot;
import ee.taltech.iti0202.parking.parkinglot.SmallCarParkingLot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


public class City {

    String name;
    Map<String, Integer> carsInLot;
    static Map<ParkingLot, List> parkingLots;

    public City(String name) {
        this.name = name;
        parkingLots = new HashMap<>();
    }

    /**
     * Adds a parking lot.
     * A parking lot can exist only once.
     *
     * @param parkingLot Parking lot to be added.
     * @return true if parking lot was added.
     */

    public boolean addParkingLot(ParkingLot parkingLot) {
        if (parkingLots.containsKey(parkingLot)) {
            return false;
        }
        parkingLots.computeIfAbsent(parkingLot, ignored -> new ArrayList<>());
        return true;

    }

    /**
     * Tries to send a car to a parking lot.
     * If the parking lot accepts this car
     * the car will be added to the queue of the parking lot.
     * The chosen parking lot is returned.
     * If several parking lots can take the car, use the one
     * with the smallest queue.
     * If several have the same size queue, use the one
     * which was added earlier.
     * Or empty in case the car cannot be parked anywhere
     * or the car has already been parked or is in queue.
     *
     * @param car Car to be sent to parking lot
     * @return Parking lot where the car will be sent into queue.
     * empty() in case no parking lot is suitable.
     */
    public Optional<ParkingLot> parkCar(Car car) {
        if (!getParkingLots().isEmpty() && getParkingLots().stream()
                .filter(x -> x.getQueueCars().contains(car)).noneMatch(x -> x.getParkedCars().contains(car)) && !car.isParked()) {
            getParkingLots().get(0).bufferQueue(car);
            List<ParkingLot> temp = getParkingLots().stream().filter(ParkingLot::accepts).collect(Collectors.toList());
            if (temp.isEmpty())
                return Optional.empty();
            Optional<ParkingLot> lotOptional = Optional.of(temp.stream()
                    .sorted((l, i) -> l.getQueueCars().size()).collect(Collectors.toList()).get(0));
            boolean success = lotOptional.get().addToQueue(car);
            lotOptional.get().processQueue();
            if (success) return lotOptional;
        }
        return Optional.empty();
    }

    /**
     * Gets all parking lots in a city.
     *
     * @return List of parking lots.
     */
    public static List<ParkingLot> getParkingLots() {
        return new ArrayList<>(parkingLots.keySet());
    }

    /**
     * Return a map where for every priority-size pair a count of cars is mapped.
     * Keys are in format XY
     * where X = {H, P, C} (highest, priority, common)
     * Y = {1, 2, 4} size
     *
     * @return map with priority-size counts
     */

    public Map<String, Integer> getParkedCarCountBySizeAndPriority() {
        return carsInLot;
    }

    /**
     * Gets car count in queue by priority status and size.
     *
     * @param priorityStatus (highest, priority, common)
     * @param size           (1, 2, 4)
     * @return Count of cars in queue.
     */
    public static int getCarCountInQueue(Car.PriorityStatus priorityStatus, int size) {
        int amount = 0;
        for (ParkingLot lot : getParkingLots()) {
            System.out.println(lot.getQueueCars());
            amount += lot.getQueueCars().stream()
                    .filter(c -> c.getSize() == size).filter(c -> c.getPriorityStatus() == priorityStatus).count();
        }
        return amount;
    }

    /**
     * Gets parked car count by priority status and size.
     *
     * @param priorityStatus (highest, priority, common)
     * @param size           (1, 2, 4)
     * @return Count of parked cars.
     */
    public static int getParkedCarCount(Car.PriorityStatus priorityStatus, int size) {
        int amount = 0;
        for (ParkingLot lot : getParkingLots()) {
            amount += lot.getParkedCars().stream()
                    .filter(c -> c.getSize() == size).filter(c -> c.getPriorityStatus() == priorityStatus).count();
        }
        return amount;
    }


    public static void main(String[] args) {
        City tallinn = new City("Tallinn");
        City tartu = new City("Tartu");

        Car ch1 = new Car(Car.PriorityStatus.HIGHEST, 1);

        SmallCarParkingLot europark = new SmallCarParkingLot(4, 2);
        tallinn.addParkingLot(europark);


        System.out.println(europark.addToQueue(ch1));
        //System.out.println(tallinn.parkCar(ch1));  // Optional[europark]
        System.out.println(getCarCountInQueue(Car.PriorityStatus.HIGHEST, 1));

    }

}
