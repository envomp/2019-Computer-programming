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

        depark();

        List<Car> temp = new ArrayList<>(getQueueCars());
        boolean possible;
        Car remove = null;
        for (Car car : temp) {
            possible = true;
            if (car.getPriorityStatus() == Car.PriorityStatus.HIGHEST && getParkedCars().stream().noneMatch(c -> c.getPriorityStatus() == Car.PriorityStatus.HIGHEST) && !(this.getParkedCars().isEmpty() && this.getSpaceAvailable() < car.getSize())) {
                if (car.getSize() == 1) car.setSize(2);
                if (this.getSpaceAvailable() <= car.getSize()) {
                    while (this.getSpaceAvailable() < car.getSize()) {
                        List<Car> all = getParkedCars().stream()
                                .filter(x -> x.getPriorityStatus() == Car.PriorityStatus.COMMON)
                                .sorted(Comparator.comparing(Car::getSize).reversed())
                                .collect(Collectors.toList());
                        if (!all.isEmpty()) remove = all.get(0);
                        if (remove == null) {
                            possible = false;
                            break;
                        }
                        if (possible) {
                            if (remove.getSize() == 1) lotToQueue(remove, 1);
                            else lotToQueue(remove, 1);
                        }
                    }
                }
                if (possible) queueToLot(car, 1);
            } else if (this.getSpaceAvailable() >= car.getSize()) queueToLot(car, 1);

        }
    }

    @Override
    public boolean accepts() {
        return this.getQueueCars().size() < 5 && !(this.getParkedCars().isEmpty() && this.getSpaceAvailable() < buffer.getSize());
    }
}
