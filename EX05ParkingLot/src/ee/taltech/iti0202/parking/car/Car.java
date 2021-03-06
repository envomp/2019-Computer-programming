package ee.taltech.iti0202.parking.car;
import ee.taltech.iti0202.parking.City;
import ee.taltech.iti0202.parking.parkinglot.ParkingLot;

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

    private int value = 1;

    private ParkingLot parkingLot;

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
    private boolean wantsToBe;

    @Override
    public int compareTo(Car o) {

        if (priority.get(this.getPriorityStatus().toString()) > priority.get(o.getPriorityStatus().toString())) {
            return 1;
        } else {
            if (priority.get(this.getPriorityStatus().toString()) < priority.get(o.getPriorityStatus().toString())) {
                return -1;
            } else {
                if (this.getSize() > o.getSize()) {
                    return 1;
                } else if (this.getSize() < o.getSize()) {
                    return -1;
                }
                return 0;
            }
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

    public void setSize(int size) {
        this.size = size;
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

        if (getParkingLot() != null) {
            this.parked = false;
            this.wantsToBe = false;
            City.decreasePark(this);
            parkingLot.depark();
            this.setParkingLot(null);
            return true;
        }

        return false;
    }

    public boolean isWantsToBe() {
        return wantsToBe;
    }

    public void setWantsToBe(boolean bool) {
        this.wantsToBe = bool;
    }

    public void setParked(boolean state) {
        this.parked = state;
    }

    public boolean isParked() {
        return parked;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot lot) {
        this.parkingLot = lot;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int i) {
        this.value = i;
    }

    public int getSize() {
        return size;
    }

    public int getRelativeSize() {
        return value * size;
    }

    @Override
    public String toString() {
        return this.getPriorityStatus().toString().substring(0, 1) + this.getSize();
    }

}
