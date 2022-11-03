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

    /**
     *
     * @param centroid point of reference for the distances
     * @param points points whose distance from the centroid will be calculated
     * @return An array containig the distance between each point and the centroid
     */
    static double[] distances(Point centroid, Point[] points) {
        double[] distances = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            distances[i] = distance(points[i], centroid);
        }
        return distances;
    }

    /**
     * @param k      should be at least 2
     * @param points initial point, no clustering yet
     * @return returns all the final clusters
     */
    static Cluster[] clustering(int k, Point[] points) {
        Cluster[] clusters = new Cluster[k];
        Point[] initialCentroids = centroids(points, k);

        clusters[0] = new Cluster(points, initialCentroids[0]);

        for (int i = 1; i < k; i++) {
            clusters[i] = new Cluster(initialCentroids[i]);
        }

        clusters = updateCluster(clusters);

        return clusters;
    }

    /**
     *
     * @param clusters Array of clusters
     * @return
     */
    static Cluster[] updateCluster(Cluster[] clusters) {
        boolean change = false;
        for (int currentGroup = 0; currentGroup < clusters.length; currentGroup++) {
            Point centroidGroup = clusters[currentGroup].getCentroid();

            for (int groupToCompare = 0; groupToCompare < clusters.length; groupToCompare++) {
                if (groupToCompare != currentGroup) {
                    Point centroidDiff = clusters[groupToCompare].getCentroid();

                    Point[] points = clusters[currentGroup].getPoints().toArray(new Point[0]);

                    double[] distancesfromgroupcentroid = distances(centroidGroup, points);
                    double[] distancesfromothercentroid = distances(centroidDiff, points);

                    for (int i = 0; i < distancesfromgroupcentroid.length; i++) {
                        if (distancesfromothercentroid[i] < distancesfromgroupcentroid[i]) {
                            clusters[groupToCompare].addPoint(points[i]);
                            clusters[currentGroup].delete(points[i]);
                            change = true;
                        }
                    }
                }
            }
        }

        if (change) {
            for (Cluster cluster : clusters) {
                cluster.updateCentroid();
            }

            clusters = updateCluster(clusters);
        }

        return clusters;
    }

}
