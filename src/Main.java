public class Main {
    public static void main(String[] args) {

        Point p1 = new Point(new double[]{1,1});
        Point p2 = new Point(new double[]{2,1});
        Point p3 = new Point(new double[]{4,3});
        Point p4 = new Point(new double[]{5,4});

        Point centroid = new Point(new double[]{1,1});

        Point[] points = {p1,p2,p3,p4};

        Point[] centroids = Utilities.centroids(points,2);

        double[] distances = Utilities.distances(centroid, points);

        for (double i:distances){
            System.out.println(i);
        }

        Group group = new Group(points);
        System.out.println(group);

        group.delete(1);

        System.out.println(group);
    }
}