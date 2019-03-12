import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BubbleCircle {
    Circle circle;
    int frameCount1;
    int lposx;
    int lposy;
    double stroke;

    public BubbleCircle(Color cl) {
        javafx.scene.shape.Circle circle = new javafx.scene.shape.Circle(0, 0, 0, Color.TRANSPARENT);
        circle.setStroke(cl);
        this.stroke = 4.0;
        this.frameCount1 = 100;
        this.circle = circle;
    }

    public Circle getCircle() {
        return circle;
    }

    public void updatePos() {
        circle.setStrokeWidth(stroke);
        circle.setCenterX(lposx);
        circle.setCenterY(lposy);
        circle.setRadius(frameCount1);
    }

    public void disopate() {
        this.stroke = 0;
    }

    public void update(int x, int y) {
        this.lposx = x;
        this.lposy = y;
    }

    public void setFrameCount1(int frameCount1) {
        this.frameCount1 = frameCount1;
    }

    public void render() {
        if (frameCount1 != 0) {
            if (frameCount1 > 0) {
                frameCount1 -= 5;
            }
        } else {
            this.disopate();
        }
        updatePos();
    }
}
