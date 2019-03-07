package ee.taltech.iti0202.parking.parkinglot;

import ee.taltech.iti0202.parking.car.Car;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Modern parking lot located under ground.
 * The parking lot has several levels.
 * Always prefer the smallest level (starting from 1).
 * If the car cannot fit to a level, then proceed to the next level.
 * <p>
 * This parking lot only accepts maximum 10 vehicle in the queue.
 * So, if there queue already has 10 cars, this parking lot should not
 * be available for new cars.
 */

public class MultiLevelParkingLot extends ParkingLot {

    /**
     * Initialize the parking slot with the given width and height.
     *
     * @param height Length of verical side.
     * @param width  Length of horizontal side.
     * @param levels Number of levels.
     */
    private Integer levels;

    public MultiLevelParkingLot(int height, int width, int levels) {
        super(height, width);
        this.levels = levels;
        this.setSpaceAvailable(height * width * levels * 2);
    }

    @Override
    public int getSize() {
        return getHeight() * getWidth() * levels;
    }

    @Override
    public void processQueue() {
//        depark();

        List<Car> temp = new LinkedList<>(getQueueCars(""));
        for (Car car : temp) {
            if (!car.isWantsToBe()) {
                depark();
            } else if (this.getSpaceAvailable() >= car.getSize()) {
                queueToLot(car);
            }
        }

//        depark();
    }


    /**
     * Here you have to override getTable() method.
     * The method gets a string for each level
     * separated by "---":
     * <p>
     * P3P3..
     * P3P3..
     * ......
     * ......
     * ---
     * ......
     * ......
     * ......
     * ......
     * <p>
     * This has 2 levels and there is a large (size 2) car on first level.
     *
     * @return String representation of multilevel parking lot
     */

    @Override
    public String getTable() {
        processQueue();
        this.setNeedUpdate(false);
        StringBuilder layers = new StringBuilder();
        List<Car> original = List.copyOf(getParkedCars(""));
        clearTemp();

        for (int i = 0; i < levels; i++) {
            layers.append(super.getTable());
            if (i + 1 < levels) {
                layers.append("---\n");
            }
            List<Car> temp = new ArrayList<>(getParkedCars(""));
            temp.removeAll(getTemp());
            setParkedCars(temp);
        }

        setParkedCars(original);
        this.setNeedUpdate(true);
        return layers.toString();

    }

    @Override
    public boolean accepts() {
        return this.getQueueCars().size() < 10 && !buffer.isParked();
    }
}
