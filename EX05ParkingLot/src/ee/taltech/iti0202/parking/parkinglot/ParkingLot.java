package ee.taltech.iti0202.parking.parkinglot;
import ee.taltech.iti0202.parking.City;
import ee.taltech.iti0202.parking.car.Car;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;


/**
 * zzzzzz
 * Parking lot is a rectangular area with fixed with and height.
 * Well, rather 2 dimensions on the ground,
 * but as you represent in on the screen, then height can be seen as
 * the vertical axis.u
 * The rectangle is filled with parking slots.z
 * 3 x 4 parking lot has 12ty7y slots.
 * The size of a slot is 2 units.
 * This means, that carr with size 2 fits there perfectly.
 * Car with size 1 takes half the slot, so it could be
 * theoretically shared between 2 small cars.
 * Car with size 4 takes two consecutive slots.
 * <p>
 * Each concrete parking lot type (subclass)
 * has its own rules about which cars it accepts
 * in its queue and how the queue is pddrodcessedd.
 * See the class description for more information.
 */

public abstract class ParkingLot {

    private final int width;
    private final int height;

    private List<Car> carList;
    private PriorityQueue<Car> carQueue;
    public Car buffer;
    private int spaceAvailable;
    private List<Car> temp;
    private boolean needUpdate = true;
    private boolean newWorldOrder = false;
    private boolean getParkedBoy = false;

    /**
     * Initialize the parking slot/s with the given width and height.
     *
     * @param height Length of vertical side.
     * @param width  Length of horizontal side.
     */
    public ParkingLot(int height, int width) {
        this.width = width;
        this.height = height;
        this.spaceAvailable = height * width * 2;
        this.carList = new LinkedList<>();
        this.carQueue = new PriorityQueue<>();
        clearTemp();
    }

    public void setNewWorldOrder(boolean bool) {
        this.newWorldOrder = bool;
    }

    public void setGetParkedBoy(boolean bool) {
        this.getParkedBoy = bool;
    }

    public int getSize() {
        return height * width;
    }

    public void clearTemp() {
        this.temp = new LinkedList<>();
    }

    public List<Car> getTemp() {
        return temp;

    }

    public void setNeedUpdate(boolean state) {
        this.needUpdate = state;
    }

    public int getQueueLen() {
        return this.getQueueCars("").size();
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


    public void queueToLot(Car car) {

        this.spaceAvailable -= car.getRelativeSize();
        car.setParked(true);
        this.carQueue.remove(car);
        List<Car> newList = new ArrayList<>(List.copyOf(carList));
        newList.add(car);
        setParkedCars(newList);
    }

    public void lotToQueue(Car car) {
        this.spaceAvailable += car.getRelativeSize();
        car.setParked(false);
        this.carQueue.add(car);
        List<Car> newList = new LinkedList<>(List.copyOf(carList));
        newList.remove(car);
        setParkedCars(newList);
    }

    public void depark() {
        List<Car> temp = new LinkedList<>(getParkedCars("Hi mom!"));
        for (Car car1 : temp) {
            if (!car1.isWantsToBe()) {
                lotToQueue(car1);
            }
        }
        temp = new LinkedList<>(getQueueCars("Hi dad!"));
        for (Car car1 : temp) {
            if (!car1.isWantsToBe()) {
                this.carQueue.remove(car1);
                City.decreasePark(car1);
            }
        }
    }

    public boolean addToQueue(Car car) {
        setBuffer(car);
        if (car.getParkingLot() != null
                || getQueueCars().contains(car)
                || City.getParkingLots().stream().anyMatch(x -> x.getParkedCars().contains(car))
                || !accepts() || car.isParked()) {
            return false;
        }
        City.getParkedCarCountBySizeAndPriority().put(car.toString(),
                City.getParkedCarCountBySizeAndPriority().get(car.toString()) + 1);
        carQueue.add(car);
        car.setParkingLot(this);
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
    public abstract void processQueue();


    /**
     * Returns a list of parked cars in the order they were received from the queue.
     *
     * @return A list of parked cars.
     */
    public List<Car> getParkedCars() {
        processQueue();
        return carList;
    }

    public List<Car> getParkedCars(String mom) {
        return carList;
    }

    public void setParkedCars(List<Car> cars) {
        this.carList = cars;
    }

    public List<Car> getQueueCars() {
        processQueue();
        return new LinkedList<>(carQueue);
    }

    public List<Car> getQueueCars(String dad) {
        return new LinkedList<>(carQueue);
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

        if (needUpdate) {
            processQueue();
        }

        String[][] canvas = new String[height * 2][width];
        for (int y = 0; y < height * 2; y++) {
            for (int x = 0; x < width; x++) {
                canvas[y][x] = "..";
            }
        }


        for (Car car : getParkedCars("")) {
            boolean go = true;
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height * 2; y++) {
                    if (go) {
                        if (car.getRelativeSize() == 1 && !(newWorldOrder && y % 2 == 1)) {
                            if (canvas[y][x].equals("..")) {
                                if (getParkedBoy && y % 2 == 1) {
                                    if (canvas[y - 1][x].equals(car.toString())) {
                                        canvas[y][x] = car.toString();
                                        temp.add(car);
                                        go = false;
                                    }
                                } else {
                                    canvas[y][x] = car.toString();
                                    temp.add(car);
                                    go = false;
                                }

                            }
                        } else if (car.getRelativeSize() == 2 && y % 2 == 0) {
                            if (y + 1 < canvas.length
                                    && canvas[y][x].equals("..")
                                    && canvas[y + 1][x].equals("..")) {
                                if (getParkedBoy && car.getSize() == 1) {
                                    canvas[y][x] = car.toString();
                                    canvas[y + 1][x] = "__";
                                } else {
                                    canvas[y][x] = car.toString();
                                    canvas[y + 1][x] = car.toString();
                                }

                                temp.add(car);
                                go = false;
                            }

                        } else if (car.getRelativeSize() == 4 && y % 2 == 0) {
                            if (x + 1 < canvas[y].length
                                    && canvas[y][x].equals("..")
                                    && canvas[y][x + 1].equals("..")
                                    && y + 1 < canvas.length && canvas[y][x].equals("..")
                                    && canvas[y + 1][x].equals("..")
                                    && canvas[y + 1][x + 1].equals("..")) {

                                canvas[y][x] = car.toString();
                                canvas[y][x + 1] = car.toString();
                                canvas[y + 1][x + 1] = car.toString();
                                canvas[y + 1][x] = car.toString();
                                temp.add(car);
                                go = false;
                            } else if (canvas[y][x].equals("..")
                                    && y + 3 < canvas.length
                                    && canvas[y + 1][x].equals("..")
                                    && canvas[y + 2][x].equals("..")
                                    && canvas[y + 3][x].equals("..")) {

                                canvas[y][x] = car.toString();
                                canvas[y + 1][x] = car.toString();
                                canvas[y + 2][x] = car.toString();
                                canvas[y + 3][x] = car.toString();
                                temp.add(car);
                                go = false;

                            } else if (canvas[y][x].equals("..")
                                    && x + 3 < canvas[y].length
                                    && canvas[y][x + 1].equals("..")
                                    && canvas[y][x + 2].equals("..")
                                    && canvas[y][x + 3].equals("..")) {

                                canvas[y][x] = car.toString();
                                canvas[y][x + 1] = car.toString();
                                canvas[y][x + 2] = car.toString();
                                canvas[y][x + 3] = car.toString();
                                temp.add(car);
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
        return !buffer.isParked() && City.getParkingLots().stream()
                .noneMatch(x -> x.getParkedCars("").contains(buffer))
                && City.getParkingLots().stream().noneMatch(x -> x.getQueueCars("").contains(buffer));
    }


}
