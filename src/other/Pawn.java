package other;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Pawn extends Circle {

    int x,y;
    Color color;

    public Pawn(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }



}
