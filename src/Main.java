public class Main {
    public static void main(String[] args) {
        Point[] points = CSVReader.redToPoints("src/exampleCsv/basic1.csv").toArray(new Point[0]);

//        int optimal = Kmeans.optimalK(points,10);
//        System.out.println(optimal);

        Cluster[] clusters = Kmeans.generateClusters(points,4);
//        for (Cluster cluster: clusters){
//            System.out.println(cluster);
//            System.out.println(cluster.getCentroid());
//        }

        Plot.plotPoints(clusters);
    }
}