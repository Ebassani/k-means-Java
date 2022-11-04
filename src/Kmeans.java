import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Kmeans {
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
     * @return This method return the final clusters genarated
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

    static float inertia(Cluster[] clusters) {
        float sumOfErrors = 0;

        for (Cluster cluster: clusters){
            sumOfErrors += cluster.distance();
        }

        return sumOfErrors;
    }

    static float[] sse(Point[] points) {
        int max = points.length/2;

        float[] sse = new float[max];
        for (int i=0;i<max;i++) {
            Cluster[] clusters = clustering(i+1, points);

            sse[i] = inertia(clusters);
        }

        return sse;
    }

    static int optimalK(Point[] points) {
        float[] sse = sse(points);

        int x1 = 1;
        double y1 = sse[0];

        int x2 = sse.length;
        double y2 = sse[sse.length-1];

        double sseMin = 0;
        int optimal = 0;

        for (int i = 0; i< sse.length;i++) {

            double distance =Math.abs((x2-x1) * (y1 - sse[i]) - (1-i) * (y2-y1)) /
                    Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));

            if (distance> sseMin) {
                sseMin = distance;
                optimal = i+1;
            }

        }

        return optimal;

    }

}
