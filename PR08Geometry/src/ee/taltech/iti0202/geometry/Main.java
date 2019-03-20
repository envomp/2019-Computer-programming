package ee.taltech.iti0202.geometry;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        Canvas canvas = new Canvas();
        tripleNipple(canvas);
        shuffleRuffle(canvas);
    }

    private static void shuffleRuffle(Canvas canvas) {
        canvas.getShapes().forEach(x -> System.out.println(x.toString()));
        System.out.println();
        canvas.getShapes().forEach(x -> x.changeSize(ThreadLocalRandom.current().nextDouble(-10, 10)));
        System.out.println();
    }

    private static void tripleNipple(Canvas canvas) {
        canvas.draw(Shape.shapes.Triangle, ThreadLocalRandom.current().nextDouble(1000, 2000), Shape.colors.Green);
        canvas.draw(Shape.shapes.Square, ThreadLocalRandom.current().nextDouble(1000, 2000), Shape.colors.Black);
        canvas.draw(Shape.shapes.Circle, ThreadLocalRandom.current().nextDouble(1000, 2000), Shape.colors.Yellow);
    }
}
