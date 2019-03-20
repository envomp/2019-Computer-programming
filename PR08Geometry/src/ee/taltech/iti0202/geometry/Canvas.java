package ee.taltech.iti0202.geometry;

import java.util.ArrayList;
import java.util.List;

public class Canvas {

    private ArrayList<Shape> shapes;

    Canvas() {
        shapes = new ArrayList<>();
    }

    public void draw(Shape.shapes s, double size, Shape.colors c) {
        Shape shape;
        switch (s) {
            case Triangle:
                shape = new Triangle(size, c);
                break;
            case Circle:
                shape = new Circle(size, c);
                break;
            case Square:
                shape = new Square(size, c);
                break;
            default:
                return;
        }
        shapes.add(shape);
    }

    public List<Shape> getShapes() {
        return shapes;
    }
}
