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
        setSpaceAvailable(getSpaceAvailable() / 2);
    }

    @Override
    public void processQueue() {

        //depark();

        List<Car> temp = new LinkedList<>(getQueueCars(""));

        for (Car car : temp) {
            if (car != null) {
                if (!getQueueCars("").isEmpty()
                        && this.getSpaceAvailable() >= getQueueCars("").get(0).getSize()) {
                    queueToLot(car);
                }
            }

        }

        //depark();
    }

    @Override
    public boolean accepts() {
        if (buffer != null) {
            return buffer.getSize() == 1 && !buffer.isParked();
        }
        return false;
    }

    @Override
    public String getTable() {
        setNewWorldOrder(true);
        String table = super.getTable();
        setNewWorldOrder(false);
        return table;

    }
}
