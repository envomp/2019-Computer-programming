package ee.taltech.iti0202.geometry;


public abstract class Shape {

    private shapes shape;
    private double nrOfVertices;
    private colors color;
    private double size;

    /**
     * recrangle or a triangle or a circle
     *
     * @param nrOfVertices
     * @param size         of a side or a radius accordingly to shape
     * @param s            shape
     * @param c            color
     */
    Shape(double nrOfVertices, double size, shapes s, colors c) {

        this.shape = s;
        this.color = c;
        this.nrOfVertices = nrOfVertices;
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format("Shape: %s, Number of angles: %.0f, Color: %s, Size: %.4f", this.shape, this.nrOfVertices, this.color, this.size);
    }

    void changeSize(double delta) {
        this.size += delta;
        System.out.println(String.format("Shape %s resized from %.4f to %.4f points", this.shape, this.size - delta, this.size));
    }

    enum shapes {
        Square,
        Circle,
        Triangle
    }

    enum colors {
        Blue,
        Green,
        Red,
        Yellow,
        Black
    }
}
