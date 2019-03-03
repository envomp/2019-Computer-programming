package ee.taltech.iti0202.parking;
import ee.taltech.iti0202.parking.car.Car;
import ee.taltech.iti0202.parking.parkinglot.ParkingLot;
import ee.taltech.iti0202.parking.parkinglot.PriorityParkingLot;
import ee.taltech.iti0202.parking.parkinglot.SmallCarParkingLot;

import java.util.*;
import java.util.stream.Collectors;

public class City {
    private List<ParkingLot> parkingLots;

    public City(String name) {
        parkingLots = new ArrayList<>();
    }

    public static void main(String[] args) {
        City tallinn = new City("Tallinn");
        City tartu = new City("Tartu");
        SmallCarParkingLot europark = new SmallCarParkingLot(4, 2);
        tallinn.addParkingLot(europark);
        Car ch1 = new Car(Car.PriorityStatus.HIGHEST, 1);
        Car ch2 = new Car(Car.PriorityStatus.HIGHEST, 2);
        Car ch4 = new Car(Car.PriorityStatus.HIGHEST, 4);

        europark.processQueue();

        System.out.println(tartu.parkCar(ch1));  // Optional.empty

        europark.processQueue();

        System.out.println(tallinn.parkCar(ch2));  // Optional.empty

        europark.processQueue();

        System.out.println(tallinn.parkCar(ch1));  // Optional[europark]

        europark.processQueue();

        System.out.println(tallinn.parkCar(ch1));  // Optional.empty

        europark.processQueue();

        System.out.println(europark.getParkedCars()); //[H1]

        PriorityParkingLot priorityParkingLot = new PriorityParkingLot(1, 3);

        tallinn.addParkingLot(priorityParkingLot);

        System.out.println(tallinn.parkCar(ch4)); // Optional[priorityParkingLot]
        Car cc4 = new Car(Car.PriorityStatus.COMMON, 4);
        Car cp4 = new Car(Car.PriorityStatus.PRIORITY, 4);
        System.out.println(tallinn.parkCar(cc4)); // Optional[priorityParkingLot]
        System.out.println(tallinn.parkCar(cp4)); // Optional[priorityParkingLot]
        Car ch42 = new Car(Car.PriorityStatus.HIGHEST, 4);
        System.out.println(tallinn.parkCar(ch42)); // Optional[priorityParkingLot]

        System.out.println(priorityParkingLot.getTable());
        /*
        H4H4..
        H4H4..

        */

        // let's send one car home
        System.out.println(ch4.unpark()); // true

        // now another H4 parks
        System.out.println(priorityParkingLot.getTable());
        /*
        H4H4..
        H4H4..

        */

        System.out.println(ch4.unpark());  // false, there's no such car parked
        System.out.println(ch42.unpark());  // true

        System.out.println(priorityParkingLot.getTable());
        /*
        P4P4..
        P4P4..

 */
    }

    /**
     * Adds a parking lot.
     * A parking lot can exist only once.
     * @param parkingLot Parking lot to be added.
     * @return true if parking lot was added.
     */
    public boolean addParkingLot(ParkingLot parkingLot) {
        if (!parkingLots.contains(parkingLot)) {
            parkingLots.add(parkingLot);
            return true;
        }
        return false;
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
     * @param car Car to be sent to parking lot
     * @return Parking lot where the car will be sent into queue.
     *          empty() in case no parking lot is suitable.
     */
    public Optional<ParkingLot> parkCar(Car car) {
        if (!getParkingLots().isEmpty() && getParkingLots().stream()
                .filter(x -> x.getParkedCars().contains(car)).noneMatch(x -> x.getParkedCars().contains(car)) && !car.isParked()) {
            List<ParkingLot> temp = getParkingLots().stream().filter(ParkingLot::accepts).collect(Collectors.toList());
            if (temp.isEmpty())
                return Optional.empty();
            Optional<ParkingLot> lotOptional = Optional.of(temp.stream()
                    .sorted((l, i) -> l.getQueue().size()).collect(Collectors.toList()).get(0));
            lotOptional.get().addToQueue(car);
            return lotOptional;
        }
        return Optional.empty();
    }

    /**
     * Gets all parking lots in a city.
     * @return List of parking lots.
     */
    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    /**
     * Return a map where for every priority-size pair a count of cars is mapped.
     * Keys are in format XY
     * where X = {H, P, C} (highest, priority, common)
     * Y = {1, 2, 4} size
     * @return map with priority-size counts
     */
    public Map<String, Integer> getParkedCarCountBySizeAndPriority() {
        return null;
    }

    /**
     * Gets car count in queue by priority status and size.
     * @param priorityStatus (highest, priority, common)
     * @param size (1, 2, 4)
     * @return Count of cars in queue.
     */
    public int getCarCountInQueue(Car.PriorityStatus priorityStatus, int size) {
        return -1;
    }

    /**
     * Gets parked car count by priority status and size.
     * @param priorityStatus (highest, priority, common)
     * @param size (1, 2, 4)
     * @return Count of parked cars.
     */
    public int getParkedCarCount(Car.PriorityStatus priorityStatus, int size) {
        return -1;
    }
}