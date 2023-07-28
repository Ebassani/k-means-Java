import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Kmeans {
    static double getDistance(Point point1, Point point2) {
        double[] points1 = point1.getValues();
        double[] points2 = point2.getValues();
        double distance = 0;
        for (int i = 0; i < point1.amount(); i++) {
            distance += Math.pow((points2[i] - points1[i]), 2);
        }
        distance = Math.sqrt(distance);
        return distance;
    }

    /**
     *
     * @param centroid point of reference for the distances
     * @param points points whose distance from the centroid will be calculated
     * @return An array containing the distance between each point and the centroid
     */
    static double[] getDistances(Point[] points, Point centroid) {
        double[] distances = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            distances[i] = getDistance(points[i], centroid);
        }
        return distances;
    }

    static Point[] centroids(Point[] points, int k) {
        Point[] centroids = new Point[k];

        Random rand = new Random();
        List<Point> list = Arrays.asList(points);
        List<Point> givenList = new ArrayList<>(list);

        for (int i = 0; i < k; i++) {
            int randomIndex = rand.nextInt(givenList.size());
            centroids[i] = givenList.get(randomIndex);
            givenList.remove(randomIndex);
        }

        return centroids;
    }


    /**
     * @param k      Number of cluster to be formed
     * @param points All the points to be clustered
     * @return       An array of Clusters (Cluster[]), each possessing its points and a centroid, final clusters of this iteration
     */
    static Cluster[] generateClusters(Point[] points, int k) {
        Cluster[] clusters = new Cluster[k];
        Point[] initialCentroids = centroids(points, k);

        clusters[0] = new Cluster(points, initialCentroids[0]);

        for (int i = 1; i < k; i++) {
            clusters[i] = new Cluster(initialCentroids[i]);
        }

        return updateCluster(clusters);
    }

    /**
     *
     * @param clusters Array of clusters
     * @return         This method return the final clusters generated
     */
    static Cluster[] updateCluster(Cluster[] clusters) {

        boolean change;

        do {
            change = false;
            for (Cluster currentGroup : clusters) {

                for (Cluster groupToCompare : clusters) {
                    if (groupToCompare == currentGroup) {
                        continue;
                    }

                    Point centroidGroup = currentGroup.getCentroid();

                    Point centroidToCompare = groupToCompare.getCentroid();

                    Point[] points = currentGroup.getPoints().toArray(new Point[0]);

                    double[] distancesFromGroupCentroid = getDistances(points, centroidGroup);
                    double[] distancesFromOtherCentroid = getDistances(points, centroidToCompare);

                    for (int i = 0; i < distancesFromGroupCentroid.length; i++) {
                        if (distancesFromOtherCentroid[i] >= distancesFromGroupCentroid[i]) {
                            continue;
                        }

                        groupToCompare.addPoint(points[i]);
                        currentGroup.delete(points[i]);
                        groupToCompare.updateCentroid();
                        currentGroup.updateCentroid();
                        change = true;

                    }
                }
            }
        } while (change);

        return clusters;
    }

    static float inertia(Cluster[] clusters) {
        float sumOfErrors = 0;

        for (Cluster cluster: clusters){
            sumOfErrors += (float) cluster.distanceError();
        }

        return sumOfErrors;
    }

    static float[] sse(Point[] points, int maxK) {
        float[] sse = new float[maxK];
        for (int i=0;i<maxK;i++) {
            Cluster[] clusters = generateClusters(points, i+1);

            sse[i] = inertia(clusters);
        }

        return sse;
    }

    static int optimalK(Point[] points, int maxK) {
        float[] sse = sse(points, maxK);

        int x1 = 1;
        double y1 = sse[0];

        int x2 = sse.length;
        double y2 = sse[sse.length-1];

        double sseMin = 0;
        int optimal = 0;

        for (int i = 0; i< sse.length;i++) {

            double distance = Math.abs((x2-x1) * (y1 - sse[i]) - (1-i) * (y2-y1)) /
                    Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));

            if (distance <= sseMin) {
                continue;
            }

            sseMin = distance;
            optimal = i+1;
        }

        return optimal;

    }

}
