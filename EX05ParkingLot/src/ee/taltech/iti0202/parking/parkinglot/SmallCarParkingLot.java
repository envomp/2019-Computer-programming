package ee.taltech.iti0202.parking.parkinglot;
import ee.taltech.iti0202.parking.car.Car;

import java.util.ArrayList;
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
        System.gc();
        setSpaceAvailable(getSpaceAvailable() / 2);
    }

    @Override
    public void processQueue() {
        //depark();
        if (isProcessQueue()) {
            times += 1;

            List<Car> temp = new ArrayList<>(getQueueCars(""));

            for (Car car : temp) {
                if (this.getSpaceAvailable() >= getQueueCars("").get(0).getSize()) {
                    queueToLot(car);
                }
            }
        }

    }

    @Override
    public boolean accepts() {
        return buffer != null && buffer.getSize() == 1 && super.accepts();
    }

    @Override
    public String getTable() {
        setNewWorldOrder(true);
        String table = super.getTable();
        setNewWorldOrder(false);
        return table;

    }
}
