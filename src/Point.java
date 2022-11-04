public class Point {
    private final double[] points;
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

    @Override
    public String toString() {
        StringBuilder array = new StringBuilder("[");
        for (int i=0; i<points.length; i++) {
            if (i != 0) { array.append(","); }
            array.append(points[i]);
        }
        array.append("]");
        return array.toString();
    }
}
