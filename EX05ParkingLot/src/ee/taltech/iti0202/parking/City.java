package ee.taltech.iti0202.parking;

import ee.taltech.iti0202.parking.car.Car;
import ee.taltech.iti0202.parking.parkinglot.MultiLevelParkingLot;
import ee.taltech.iti0202.parking.parkinglot.ParkingLot;
import ee.taltech.iti0202.parking.parkinglot.PriorityParkingLot;
import ee.taltech.iti0202.parking.parkinglot.SmallCarParkingLot;

import java.util.*;
import java.util.stream.Collectors;


public class City {

    static Map<String, Integer> carsInLot;
    static Map<ParkingLot, List> parkingLots;
    String name;

    public City(String name) {
        this.name = name;
        parkingLots = new HashMap<>();
        carsInLot = new HashMap<>() {{
            put(Car.PriorityStatus.HIGHEST.toString(), 0);
            put(Car.PriorityStatus.PRIORITY.toString(), 0);
            put(Car.PriorityStatus.COMMON.toString(), 0);
        }};
    }

    /**
     * Gets all parking lots in a city.
     *
     * @return List of parking lots.
     */
    public static List<ParkingLot> getParkingLots() {
        return new ArrayList<>(parkingLots.keySet());
    }

    public static void decreasePark(Car car) {
        carsInLot.put(car.getPriorityStatus().toString(), carsInLot.get(car.getPriorityStatus().toString()) - 1);
    }

    /**
     * Return a map where for every priority-size pair a count of cars is mapped.
     * Keys are in format XY
     * where X = {H, P, C} (highest, priority, common)
     * Y = {1, 2, 4} size
     *
     * @return map with priority-size counts
     */

    public static Map<String, Integer> getParkedCarCountBySizeAndPriority() {
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
        Car ch2 = new Car(Car.PriorityStatus.HIGHEST, 1);

        SmallCarParkingLot europark = new SmallCarParkingLot(1, 1);
        PriorityParkingLot park = new PriorityParkingLot(4, 4);
        tallinn.addParkingLot(europark);
        tallinn.addParkingLot(park);

        tallinn.parkCar(ch1);
        tallinn.parkCar(ch2);
        System.out.println(europark.getParkedCars());
        System.out.println(europark.getQueueCars());
        System.out.println(park.getParkedCars());


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
            List<ParkingLot> temp = getParkingLots().stream().filter(ParkingLot::accepts).collect(Collectors.toList());
            if (temp.isEmpty())
                return Optional.empty();
            Optional<ParkingLot> lotOptional = Optional.of(temp.stream()
                    .sorted(Comparator.comparing(ParkingLot::getQueueLen)).collect(Collectors.toList()).get(0));
            boolean success = lotOptional.get().addToQueue(car);
            System.out.println(lotOptional.get());
            carsInLot.put(car.getPriorityStatus().toString(), carsInLot.get(car.getPriorityStatus().toString()) + 1);
            if (success) return lotOptional;
        }
        return Optional.empty();
    }

}
