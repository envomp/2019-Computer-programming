package ee.taltech.iti0202.parking.parkinglot;

import ee.taltech.iti0202.parking.City;
import ee.taltech.iti0202.parking.car.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


/**
 * Parking lot is a rectangular area with fixed with and height.
 * Well, rather 2 dimensions on the ground,
 * but as you represent in on the screen, then height can be seen as
 * the vertical axis.
 * The rectangle is filled with parking slots.
 * 3 x 4 parking lot has 12 slots.
 * The size of a slot is 2 units.
 * This means, that car with size 2 fits there perfectly.
 * Car with size 1 takes half the slot, so it could be
 * theoretically shared between 2 small cars.
 * Car with size 4 takes two consecutive slots.
 * <p>
 * Each concrete parking lot type (subclass)
 * has its own rules about which cars it accepts
 * in its queue and how the queue is processed.
 * See the class description for more information.
 */

abstract public class ParkingLot {

    private final int width;
    private final int height;

    private List<Car> carList;
    private PriorityQueue<Car> carQueue;
    public Car buffer;
    private int spaceAvailable;

    /**
     * Initialize the parking slot with the given width and height.
     *
     * @param height Length of vertical side.
     * @param width  Length of horizontal side.
     */
    public ParkingLot(int height, int width) {
        this.width = width;
        this.height = height;
        this.spaceAvailable = height * width * 2;
        this.carList = new ArrayList<>();
        this.carQueue = new PriorityQueue<>();
    }

    public int getSize() {
        return height * width;
    }

    public int getQueueLen() {
        return this.getQueueCars().size();
    }

    public void setBuffer(Car car) {
        this.buffer = car;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpaceAvailable() {
        return this.spaceAvailable;
    }

    public void setSpaceAvailable(Integer integer) {
        this.spaceAvailable = integer;
    }


    public void queueToLot(Car car, int important) {
        this.spaceAvailable -= car.getSize() * important;
        car.setParked(true);
        this.carQueue.remove(car);
        this.carList.add(car);
    }

    public void lotToQueue(Car car, int important) {
        this.spaceAvailable += car.getSize() * important;
        car.setParked(false);
        this.carQueue.add(car);
        this.carList.remove(car);
    }

    public void depark() {
        List<Car> temp = new ArrayList<>(getParkedCars());
        for (Car car1 : temp) if (!car1.isWantsToBe()) lotToQueue(car1, 1);
        temp = new ArrayList<>(getQueueCars());
        for (Car car1 : temp)
            if (!car1.isWantsToBe()) {
                this.carQueue.remove(car1);
                City.decreasePark(car1);
            }
    }

    public boolean addToQueue(Car car) {
        if (carQueue.contains(car) || car.isParked())
            return false;
        carQueue.add(car);
        return true;
    }

    /**
     * Processes the queue.
     * <p>
     * The cars are taken from the queue in specified order.
     * If the first car in the queue cannot be parked
     * the process will wait. Also, if the queue is empty, process waits.
     * Otherwise the process should be "running" all the time.
     * In reality you should call this method from other methods
     * which could initialize the process.
     */
    abstract public void processQueue();


    /**
     * Returns a list of parked cars in the order they were received from the queue.
     *
     * @return A list of parked cars.
     */
    public List<Car> getParkedCars() {
        return carList;
    }

    public List<Car> getQueueCars() {
        return new ArrayList<>(carQueue);
    }

    /**
     * Returns string presentation of the parking lot.
     * <p>
     * Each slot takes 2x2 chars.
     * Size 1 is represented by 1x2 (height, width) area
     * Empty slot is represented by dots (.):
     * <p>
     * Empty table with width 3, height 2:
     * ......
     * ......
     * ......
     * ......
     * <p>
     * One large priority car:
     * P3P3..
     * P3P3..
     * ......
     * ......
     * <p>
     * + one small highest priority car:
     * P3P3H1
     * P3P3..
     * ......
     * ......
     * <p>
     * + medium common car:
     * P3P3H1
     * P3P3..
     * C2....
     * C2....
     *
     * @return String representation of the parking lot
     */

    public String getTable() {

        processQueue();


        String[][] canvas = new String[height][width * 2];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width * 2; x++) {
                canvas[y][x] = ".";
            }
        }


        for (Car car : getParkedCars()) {
            boolean go = true;
            for (int y = 0; y < height * 2; y++) {
                for (int x = 0; x < width * 2; x++) {
                    if (go) {
                        if (car.getSize() == 1) {
                            if (canvas[y][x].equals(".")) {
                                canvas[y][x] = car.toString();
                                go = false;
                            }
                        } else if (car.getSize() == 2) {
                            if (x + 1 < canvas[y].length && canvas[y][x].equals(".") && canvas[y][x + 1].equals(".")) {
                                canvas[y][x] = car.toString();
                                canvas[y][x + 1] = car.toString();
                                go = false;
                            } else if (y + 1 < canvas.length && canvas[y][x].equals(".") && canvas[y + 1][x].equals(".")) {
                                canvas[y][x] = car.toString();
                                canvas[y + 1][x] = car.toString();
                                go = false;
                            }
                        } else if (car.getSize() == 4) {
                            if (x + 1 < canvas[y].length && canvas[y][x].equals(".") && canvas[y][x + 1].equals(".") &&
                                    y + 1 < canvas.length && canvas[y][x].equals(".") &&
                                    canvas[y + 1][x].equals(".") && canvas[y + 1][x + 1].equals(".")) {
                                canvas[y][x] = car.toString();
                                canvas[y][x + 1] = car.toString();
                                canvas[y + 1][x + 1] = car.toString();
                                canvas[y + 1][x] = car.toString();
                                go = false;
                            }
                        }


                    }
                }


            }
        }

        StringBuilder sb = new StringBuilder();
        for (String[] s : canvas) {
            for (String c : s) {
                sb.append(c);

            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public boolean accepts() {
        return true;
    }


}
