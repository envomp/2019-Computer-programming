package ee.taltech.iti0202.parking.parkinglot;


import ee.taltech.iti0202.parking.car.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * This parking lot only accepts small cars (size 1).
 * Each parking slot only accepts one cr.
 */
public class SmallCarParkingLot extends ParkingLot {
    /**
     * Initialize the parking slot with the given width and height.
     *
     * @param height
     * @param width
     */
    public SmallCarParkingLot(int height, int width) {
        super(height, width);
    }

    @Override
    public void processQueue() {

        depark();

        List<Car> temp = new ArrayList<>(getQueueCars());

        for (Car car : temp) {
            if (!getQueueCars().isEmpty() && this.getSpaceAvailable() > getQueueCars().get(0).getSize()) {
                queueToLot(car, 2);
            }
        }

    }

    @Override
    public boolean accepts() {
        return this.getQueueCars().size() < 1;
    }
}