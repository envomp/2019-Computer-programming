package ee.taltech.iti0202.parking.car;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a car with priority and size.
 * The size can be one of 1, 2, 4 (the code doesn't have to validate it).
 * This class implements Comparable interface.
 * This allows objects to be sorted in priority queue (or for sorting in general).
 * Cars with highest priority will be taken first, then with the "priority" priority
 * and then all the common cars.
 * If there are cars with the same priority, prefer cars with lower size.
 * So highest-1 (priority status-size) comes before highest-2 which comes before priority-1.
 */
public class Car implements Comparable<Car> {

    public enum PriorityStatus {
        HIGHEST, PRIORITY, COMMON
    }

    private final Map<String, Integer> priority = new HashMap<>() {{
        put("H", 1);
        put("P", 2);
        put("C", 3);
    }};
    private PriorityStatus status;
    private int size;
    private boolean parked;
    private boolean wantsToBe;

    @Override
    public int compareTo(Car o) {
        final Integer car_priority = priority.get(o.toString().substring(0, 1).toUpperCase());
        final Integer this_priority = priority.get(this.toString().substring(0, 1).toUpperCase());
        if (this_priority > car_priority)
            return 1;
        else if (this_priority < car_priority)
            return -1;
        else {
            final int car_size = Integer.parseInt(o.toString().substring(1));
            final int this_size = Integer.parseInt(this.toString().substring(1));
            if (this_size > car_size)
                return 1;
            else if (this_size < car_size)
                return -1;
            return 0;
        }
    }


    public Car(PriorityStatus status, int size) {
        this.parked = false;
        this.size = size;
        this.status = status;
        this.wantsToBe = true;
    }

    /**
     * Gets the priority of the car.
     *
     * @return PriorityStatus
     */
    public PriorityStatus getPriorityStatus() {
        return status;
    }

    /**
     * Gets the size of the car.
     *
     * @return Size.
     */
    public int getSize() {
        return size;
    }

    /**
     * Finish parking. This car has finished parking.
     * The car should be removed from parking lot
     * (its slots will be empty).
     * Returns false, if the car is not parked currently.
     * Otherwise returns true.
     *
     * @return True if the car was parking, false otherwise.
     */
    public boolean unpark() {
        if (this.parked) {
            this.parked = false;
            this.wantsToBe = false;

            // TODO: FUCK THE SYSTEM!
            //priorityParkingLot.processQueue();
            //priorityParkingLot.processQueue();

            return true;
        }
        return false;
    }

    public boolean isWantsToBe() {
        return wantsToBe;
    }

    public void setParked(boolean state) {
        this.parked = state;
    }

    public boolean isParked() {
        return parked;
    }

    public String toString() {
        return this.status.toString().substring(0, 1) + this.size;
    }
}
