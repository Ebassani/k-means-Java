import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utilities {
    static double distance(Point point1, Point point2) {
        double[] points1 = point1.getPoints();
        double[] points2 = point2.getPoints();
        double distance = 0;
        for (int i = 0; i < point1.amount(); i++) {
            distance += Math.pow((points2[i] - points1[i]), 2);
        }
        distance = Math.sqrt(distance);
        return distance;
    }

    static Point[] centroids(Point[] points, int amount) {
        Point[] centroids = new Point[amount];

        Random rand = new Random();
        List<Point> list = Arrays.asList(points);
        List<Point> givenList = new ArrayList<>(list);

        for (int i = 0; i < amount; i++) {
            int randomIndex = rand.nextInt(givenList.size());
            centroids[i] = givenList.get(randomIndex);
            givenList.remove(randomIndex);
        }

        return centroids;
    }

    static double[] distances(Point centrid, Point[] points) {
        double[] distances = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            distances[i] = distance(points[i],centrid);
        }
        return distances;
    }

    static void grouping(){

    }

}
