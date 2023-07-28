public class Point {
    private final double[] values;
    // Should be this order : [x,y,z,...]

    public Point(double[] values) {
        this.values = values;
    }

    public double[] getValues() {
        return values;
    }

    public int amount() {
        return this.values.length;
    }

    @Override
    public String toString() {
        StringBuilder array = new StringBuilder("[");
        for (int i=0; i<values.length; i++) {
            if (i != 0) { array.append(","); }
            array.append(values[i]);
        }
        array.append("]");
        return array.toString();
    }
}
