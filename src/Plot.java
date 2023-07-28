import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Plot extends JPanel{
    private final Cluster[] clusters;
    private final Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.BLACK, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.LIGHT_GRAY, Color.PINK};

    public Plot(Cluster[] clusters) {
        this.clusters = clusters;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();
        int padding = 30;

        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;

        for(Cluster cluster : clusters) {
            for (Point point : cluster.getPoints()) {
                double[] values = point.getValues();
                double x = values[0];
                double y = values[1];
                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);
            }
        }

        double scaleX = (width - 2 * padding) / (maxX - minX);
        double scaleY = (height - 2 * padding) / (maxY - minY);

        for(int i = 0; i < clusters.length; i++) {
            Point[] points = clusters[i].getPoints().toArray(new Point[0]);
            Color color = colors[i];

            g2d.setColor(color);

            for (Point point : points) {
                double x = (point.getValues()[0] - minX) * scaleX + padding;
                double y = height - ((point.getValues()[1] - minY) * scaleY + padding);

                Ellipse2D.Double pointGraph = new Ellipse2D.Double(x, y, 5, 5);
                g2d.fill(pointGraph);
            }
        }

        g2d.setColor(Color.BLACK);
        g2d.drawString("X", width - padding, height - padding / 2);
        g2d.drawString("Y", padding / 2, padding);

    }

    public static void plotPoints(Cluster[] clusters) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        Plot plot = new Plot(clusters);
        frame.add(plot);

        frame.setVisible(true);
    }
}  