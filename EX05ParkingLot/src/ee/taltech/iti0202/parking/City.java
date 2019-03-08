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

import static java.lang.System.out; //sout


public class City {

    static Map<String, Integer> carsInLot;
    static Map<ParkingLot, List> parkingLots;
    String name;

    public City(String name) {
        this.name = name;
        parkingLots = new HashMap<>();
        carsInLot = new HashMap<>() {{
            put("H1", 0);
            put("H2", 0);
            put("H4", 0);
            put("P1", 0);
            put("P2", 0);
            put("P4", 0);
            put("C1", 0);
            put("C2", 0);
            put("C4", 0);
        }};
    }

    /**
     * Gets all parking lots in this city.
     *
     * @return List of parking lots.
     **/
    public static List<ParkingLot> getParkingLots() {
        return new ArrayList<>(parkingLots.keySet());
    }

    public static void decreasePark(Car car) {
        carsInLot.put(car.toString(), carsInLot.get(car.toString()) - 1);
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
            out.println(lot.getQueueCars());
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
        Car c1 = new Car(Car.PriorityStatus.PRIORITY, 2);
        Car c2 = new Car(Car.PriorityStatus.PRIORITY, 2);
        Car c3 = new Car(Car.PriorityStatus.HIGHEST, 2);
        Car c4 = new Car(Car.PriorityStatus.HIGHEST, 2);
        Car c5 = new Car(Car.PriorityStatus.HIGHEST, 2);
        Car c6 = new Car(Car.PriorityStatus.COMMON, 2);
        Car c7 = new Car(Car.PriorityStatus.COMMON, 2);

        PriorityParkingLot medium = new PriorityParkingLot(1, 0);
        PriorityParkingLot medium2 = new PriorityParkingLot(1, 0);
        tallinn.addParkingLot(medium);
        tallinn.addParkingLot(medium2);

        tallinn.parkCar(c1);
        tallinn.parkCar(c2);
        medium(medium);
        medium(medium2);
        tallinn.parkCar(c3);
        tallinn.parkCar(c4);
        medium(medium);
        medium(medium2);
        tallinn.parkCar(c5);
        tallinn.parkCar(c6);
        medium(medium);
        medium(medium2);
        tallinn.parkCar(c7);
        medium(medium);
        medium(medium2);
        //List<Car> allCars = new ArrayList<>(List.of(c1, c2, c3, c4, c5, c6, c7));
        //long startTime = System.nanoTime();
        //int tot = 0;
        //for (int i = 0; i < 10; i++) {
        //    Collections.shuffle(allCars);
        //    for (Car car: allCars) {
        //        tallinn.parkCar(car);
        //    }
        //    medium(medium);
        //    medium.processQueue();
        //    tot += medium.getQueueLen();
        //    for (Car car: allCars) {
        //        car.unpark();
        //    }
        //}
        //long endTime   = System.nanoTime();
        //long totalTime = endTime - startTime;
        //if (tot != 0) throw new RuntimeException("no");
        //else out.println("you did good, million tests in(sec): " + totalTime / 1000000000);
    }

    private static void parkAll(City tallinn, Car c1, Car ch2, Car ch3, Car ch4) {

        out.print(tallinn.parkCar(c1));
        out.print("\t");
        out.print(tallinn.parkCar(ch2));
        out.print("\t");
        out.print(tallinn.parkCar(ch3));
        out.print("\t");
        out.print(tallinn.parkCar(ch4));
        out.print("\n");
    }

    private static void multiParkingLotAddThenRemoveAndAddAgain(City tallinn, Car c1, Car ch2,
                                                                Car ch3, Car ch4, MultiLevelParkingLot multi) {
        tallinn.addParkingLot(multi);

        out.println(c1.getRelativeSize());
        out.println(c1.getParkingLot());
        out.println(c1.isParked());
        out.println(c1.isWantsToBe());

        parkAll(tallinn, c1, ch2, ch3, ch4);

        multi(multi);

        out.println(c1.unpark());
        out.println(ch3.unpark());
        out.println(ch2.unpark());

        multi(multi);

        parkAll(tallinn, c1, ch2, ch3, ch4);


        out.println(c1.getRelativeSize());
        out.println(c1.getParkingLot());
        out.println(c1.isParked());
        out.println(c1.isWantsToBe());

        multi(multi);
    }

    private static void multi(MultiLevelParkingLot multiLevelParkingLot) {
        out.println();
        out.println("Table: ");
        out.println(multiLevelParkingLot.getTable());
        out.print("Parked cars:   ");
        out.println(multiLevelParkingLot.getParkedCars());
        out.print("Queue cars:\t");
        out.println(multiLevelParkingLot.getQueueCars());
        out.println();
    }


    private static void small(SmallCarParkingLot smallCarParkingLot) {
        out.println();
        out.println("Table: ");
        out.println(smallCarParkingLot.getTable());
        out.print("Parked cars:    ");
        out.println(smallCarParkingLot.getParkedCars());
        out.print("Queue cars:    ");
        out.println(smallCarParkingLot.getQueueCars());
        out.println();
    }

    private static void medium(PriorityParkingLot priorityParkingLot) {
        out.println();
        out.println("Table: ");
        out.println(priorityParkingLot.getTable());
        out.print("Parked cars:\t");
        out.println(priorityParkingLot.getParkedCars());
        out.print("Queue cars:\t");
        out.println(priorityParkingLot.getQueueCars());
        out.println();
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
        car.setWantsToBe(true);
        if (!getParkingLots().isEmpty() && getParkingLots().stream()
                .filter(x -> x.getQueueCars().contains(car))
                .noneMatch(x -> x.getParkedCars().contains(car)) && !car.isParked()) {
            getParkingLots().forEach(x -> x.buffer = car);
            List<ParkingLot> temp = getParkingLots().parallelStream().filter(ParkingLot::accepts)
                    .collect(Collectors.toList());
            if (temp.isEmpty()) {
                return Optional.empty();
            }
            ParkingLot best = null;
            for (ParkingLot lot : temp) {
                if (best == null) {
                    best = lot;
                } else if (best.getSpaceAvailable() - car.getRelativeSize() < 0
                        && lot.getSpaceAvailable() - car.getRelativeSize() >= 0) {
                    best = lot;
                } else if (best.getQueueLen() > lot.getQueueLen()) {
                    best = lot;
                }
            }
            Optional<ParkingLot> lotOptional = Optional.of(best);
            boolean success = lotOptional.get().addToQueue(car);
            best.processQueue();
            if (success) {
                car.setParkingLot(best);
                return lotOptional;
            }
        }
        return Optional.empty();
    }
}
