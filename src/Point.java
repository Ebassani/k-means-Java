import java.util.Arrays;

public class Point {
    private double[] points;
    // Should be this order : [x,y,z,...]

    public Point(double[] points) {
        this.points = points;
    }

    public double[] getPoints() {
        return points;
    }

    public int amount() {
        return this.points.length;
    }

    public void setPoints(double[] newPoints) {
        this.points = newPoints;
    }

    @Override
    public String toString() {
        String array = "[";
        for (int i=0; i<points.length; i++) {
            if (i != 0) { array+=","; }
            array += points[i];
        }
        array += "]";
        return array;
    }
}
