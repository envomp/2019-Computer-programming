package ee.taltech.iti0202.parking.parkinglot;


import ee.taltech.iti0202.parking.car.Car;

import java.util.LinkedList;
import java.util.List;

/**
 * This parking lot only accepts smal cars (size 1).
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

        //depark();

        List<Car> temp = new LinkedList<>(getQueueCars(""));

        for (Car car : temp) {
            if (!getQueueCars("").isEmpty() && this.getSpaceAvailable() > getQueueCars("").get(0).getSize()) {
                car.setValue(2);
                queueToLot(car);
            }
        }

        //depark();
    }

    @Override
    public boolean accepts() {
        return this.getQueueCars().size() < 2 && buffer.getSize() == 1 && !buffer.isParked();
    }
}
