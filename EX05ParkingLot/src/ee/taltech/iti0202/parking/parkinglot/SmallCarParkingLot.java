package ee.taltech.iti0202.parking.parkinglot;


import ee.taltech.iti0202.parking.car.Car;

/**
 * This parking lot only accepts small cars (size 1).
 * Each parking slot only accepts one car.
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
        if (!getQueue().isEmpty()) {
            Car car = getQueue().get(0);
            if (!getQueue().isEmpty() && this.getSpaceAvailable() > car.getSize()) {
                queueToLot(car, 2);
            }
        }

    }

    @Override
    public boolean accepts() {
        return getParkedCars().size() + getQueue().size() < getSize();
    }
}
