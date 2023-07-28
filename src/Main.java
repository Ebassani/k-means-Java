public class Main {
    public static void main(String[] args) {
        Point[] points = CSVReader.redToPoints("src/exampleCsv/boxes.csv").toArray(new Point[0]);

        int optimal = Kmeans.optimalK(points,9);

        Cluster[] clusters = Kmeans.generateClusters(points,optimal);


        Plot.plotPoints(clusters);
    }
}