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
        try {
            int amountOfIndexes = points.get(0).amount();
            double[] newCentroid = new double[amountOfIndexes];
            for (int i = 0; i < amountOfIndexes; i++) {
                int count;
                double num = 0;
                for (count = 0; count<points.size(); count++) {
                    num += points.get(count).getPoints()[i];
                }
                newCentroid[i] = (num/count);
            }
            this.centroid = new Point(newCentroid);

        } catch (Exception e) {
            // This is only here because
        }
    }

    public double distance() {
        double sumDistances = 0;
        for (Point point:points) {
            double dis = Kmeans.distance(point,centroid);
            dis= Math.pow(dis, 2);
//            System.out.println(dis);
            sumDistances += dis;
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
