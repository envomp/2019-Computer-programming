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
        put("HIGHEST", 1);
        put("PRIORITY", 2);
        put("COMMON", 3);
    }};
    private PriorityStatus status;
    private int size;
    private boolean parked;

    @Override
    public int compareTo(Car o) {

        if (priority.get(this.getPriorityStatus().toString()) > priority.get(o.getPriorityStatus().toString()))
            return 1;
        else if (priority.get(this.getPriorityStatus().toString()) < priority.get(o.getPriorityStatus().toString()))
            return -1;
        else {
            if (this.getSize() > o.getSize())
                return 1;
            else if (this.getSize() < o.getSize())
                return -1;
            return 0;
        }
    }


    public Car(PriorityStatus status, int size) {
        this.parked = false;
        this.size = size;
        this.status = status;
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

            return true;
        }
        return false;
    }


    public void setParked(boolean state) {
        this.parked = state;
    }

    public boolean isParked() {
        return parked;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getSize());
    }

}
