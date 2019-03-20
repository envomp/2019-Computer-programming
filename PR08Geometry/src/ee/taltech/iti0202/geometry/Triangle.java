package ee.taltech.iti0202.geometry;

public class Triangle extends Shape {
    /**
     * recrangle or a triangle
     * <p>
     * Triangle has 3 vertices and is triangle
     *
     * @param size of square side
     * @param c    color of the square
     */
    Triangle(double size, colors c) {
        super(3, size, shapes.Triangle, c);
    }

    public void changeSize(double delta) {
        super.changeSize(delta);
    }
}
