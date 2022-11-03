public class Main {
    public static void main(String[] args) {

        Point p1 = new Point(new double[]{1,1});
        Point p2 = new Point(new double[]{2,1});
        Point p3 = new Point(new double[]{4,3});
        Point p4 = new Point(new double[]{5,4});
        Point p5 = new Point(new double[]{6,4});
        Point p6 = new Point(new double[]{5,5});
        Point p7 = new Point(new double[]{5,3});
        Point p8 = new Point(new double[]{6,3});
        Point p9 = new Point(new double[]{7,6});
        Point p0 = new Point(new double[]{5,7});

        Point[] points = {p1,p2,p3,p4,p5,p6,p7,p8,p9,p0};

        float[] sse = Utilities.sse(points);
        for (float num: sse){
            System.out.println(num);
        }


        Cluster[] clusters = Utilities.clustering(2,points);
        for (Cluster cluster: clusters){
            System.out.println(cluster);
            System.out.println(cluster.getCentroid());
            System.out.println(cluster.avgDistance());
        }

    }
}