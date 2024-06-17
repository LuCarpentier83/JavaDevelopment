import java.util.*;

public class Case {
    int x1;
    int y1;
    int x2;
    int y2;
    boolean isObstacle;
    public double g;  // cout depuis le depart
    public double h;    // cout restant
    public double f = g + h;
    public Case parent;

    public Case(int x1, int y1, int x2, int y2, boolean obs) {
        this.x2 = x2;
        this.y2 = y2;
        this.x1 = x1;
        this.y1 = y1;
        this.isObstacle = obs;
        this.g = Double.POSITIVE_INFINITY;
        this.f = Double.POSITIVE_INFINITY;
        this.h = 0;
        this.parent = null;
    }

    public double getFscore() {
        return this.f;
    }

    public String toString() {
        return "Case [x1=" + this.x1 + ", y1=" + this.y1 + ", x2=" + this.x2 + ", y2=" + this.y2 + ", isObstacle=" + this.isObstacle + "]";
    }
}
