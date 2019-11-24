package ee.taltech.iti0202.geometry;

public class Square extends Shape {
    /**
     * square
     * square has 4 vertices
     *
     * @param size of the square side
     * @param c    color of the square
     */
    Square(double size, colors c) {
        super(4, size, shapes.Square, c);
    }

    public void changeSize(double delta) {
        super.changeSize(delta);
    }
}

