package ee.taltech.iti0202.parking.parkinglot;


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
        if (!getQueueCars().isEmpty()) queueToLot(getQueueCars().get(0), 2);
        depark();
    }

    @Override
    public boolean accepts() {
        if (getCarBuffer() == null)
            return false;
        return getCarBuffer().getSize() == 1 && getParkedCars().size() + getQueueCars().size() < getSize();
    }
}
