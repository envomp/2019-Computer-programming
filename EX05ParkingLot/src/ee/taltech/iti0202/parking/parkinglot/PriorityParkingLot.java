package ee.taltech.iti0202.parking.parkinglot;

import ee.taltech.iti0202.parking.car.Car;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Accepts all cars if the queue size is less than 5.
 * Small car (size 1) with the highest priority can park alone.
 * Otherwise small cars (size 1) can share a slot if they have the same priority.
 * If there are cars with highest priority in the queue, then cars with common priority (if parked)
 * will be sent to the queue to make room for highest priority cars (life is unfair).
 */
public class PriorityParkingLot extends ParkingLot {
    /**
     * Initialize the parking slot with the given width and height.
     *
     * @param height Length of vertical side.
     * @param width  Length of horizontal side.
     */
    public PriorityParkingLot(int height, int width) {
        super(height, width);
    }

    @Override
    public void processQueue() {

        //depark();

        List<Car> temp = new ArrayList<>(getQueueCars("Hi dad!"));
        boolean possible;
        Car remove = null;
        for (Car car : temp) {
            possible = true;

            if (car.getRelativeSize() == 1 && car.getPriorityStatus() == Car.PriorityStatus.HIGHEST) {
                car.setValue(2);
            }

            if (car.getPriorityStatus() == Car.PriorityStatus.HIGHEST && getParkedCars("")
                    .stream().anyMatch(c -> c.getPriorityStatus() != Car.PriorityStatus.HIGHEST)
                    && this.getSpaceAvailable() < car.getRelativeSize()) {
                if (this.getSpaceAvailable() < car.getRelativeSize()) {
                    while (this.getSpaceAvailable() < car.getRelativeSize()) {
                        List<Car> all = getParkedCars("").stream()
                                .filter(x -> x.getPriorityStatus() == Car.PriorityStatus.COMMON)
                                .sorted(Comparator.comparing(Car::getSize).reversed())
                                .collect(Collectors.toList());
                        if (!all.isEmpty()) {
                            remove = all.get(0);
                        }
                        if (remove == null) {
                            possible = false;
                            break;
                        }
                        lotToQueue(remove);
                    }
                }
                if (possible) queueToLot(car);
            } else {
                if (this.getSpaceAvailable() >= car.getRelativeSize()) queueToLot(car);
            }

        }

        //depark();
    }

    @Override
    public boolean accepts() {
        return this.getQueueCars().size() < 5 && !buffer.isParked();
    }
}
