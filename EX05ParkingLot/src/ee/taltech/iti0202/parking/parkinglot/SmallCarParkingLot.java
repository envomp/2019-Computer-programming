package ee.taltech.iti0202.parking.parkinglot;

import ee.taltech.iti0202.parking.car.Car;

import java.util.List;

/**
 * This parking lot only accepts small cars (size 1).
 * Each parking slot only accepts one car.
 */
public class SmallCarParkingLot extends ParkingLot {
    /**
     * Initialize the parking slot with the given width and height.
     *  @param height
     * @param width
     */

    public SmallCarParkingLot(int height, int width) {
        super(height, width);
    }

    @Override
    public void processQueue() {

    }

    @Override
    public boolean accepts() {
        return getParkedCars().size() + getQueue().size() < getSize();
    }
}
