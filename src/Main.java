public class Main {
    public static void main(String[] args) {

        Point p1 = new Point(new double[]{1,1});
        Point p2 = new Point(new double[]{2,1});
        Point p3 = new Point(new double[]{4,3});
        Point p4 = new Point(new double[]{5,4});

        Point[] points = {p1,p2,p3,p4};

        Cluster[] clusters = Utilities.clustering(2,points);
        for (Cluster cluster: clusters){
            System.out.println(cluster);
            System.out.println(cluster.getCentroid());
            System.out.println(cluster.avgDistance());
        }

    }
}