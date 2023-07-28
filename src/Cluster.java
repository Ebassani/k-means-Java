import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private final List<Point> points;
    private Point centroid;

    public Cluster(Point[] points, Point centroid) {
        List<Point> list = List.of(points);
        this.points = new ArrayList<>(list);
        this.centroid = centroid;
    }

    public Cluster(List<Point> points, Point centroid) {
        this.points = points;
        this.centroid = centroid;
    }

    public Cluster(Point centroid) {
        this.points = new ArrayList<>();
        this.centroid = centroid;
    }

    public List<Point> getPoints() {
        return points;
    }

    public Point getCentroid() {
        return centroid;
    }

    public void delete(int index) {
        points.remove(index);
    }

    public void delete(Point point) {
        points.remove(point);
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void updateCentroid() {
        if (points.isEmpty()) {
            return;
        }

        int amountOfIndexes = points.get(0).amount();
        double[] newCentroid = new double[amountOfIndexes];
        int length = points.size();
        for (int i = 0; i < amountOfIndexes; i++) {
            double num = 0;

            for (Point point : this.points) {
                num += point.getValues()[i];
            }

            newCentroid[i] = (num/length);
        }
        this.centroid = new Point(newCentroid);

    }

    public double distanceError() {
        double sumDistances = 0;
        for (Point point:points) {
            double distance = Kmeans.getDistance(point,centroid);
            distance = Math.pow(distance, 2);
            sumDistances += distance;
        }
        return sumDistances;
    }


    @Override
    public String toString() {
        StringBuilder group = new StringBuilder("{");
        for (Point p : points) {
            group.append(p);
        }
        group.append("}");
        return group.toString();
    }
}
