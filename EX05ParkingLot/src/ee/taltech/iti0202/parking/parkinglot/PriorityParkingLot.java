package ee.taltech.iti0202.parking.parkinglot;
/**
 * Accepts all cars if the queue size is less than 5.
 * Small car (size 1) with the highest priority can park alone.
 * Otherwise small cars (size 1) can share a slot if they have the same priority.
 * If there are cars with highest priority in the queue, then cars with common priority (if parked)
 * will be sent to the queue to make room for highest priority cars (life is unfair).
 *
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

    }
}
