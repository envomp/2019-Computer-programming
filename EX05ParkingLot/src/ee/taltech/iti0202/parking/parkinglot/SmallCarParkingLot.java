package ee.taltech.iti0202.parking.parkinglot;


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
        if (!getQueueCars().isEmpty() && this.getSpaceAvailable() > getQueueCars().get(0).getSize()) {
            queueToLot(getQueueCars().get(0), 2);
            queueToLot(getQueueCars().get(0), 2);
        }

        depark();
    }

    @Override
    public boolean accepts() {
        return this.getQueueCars().size() < 1;
    }
}
