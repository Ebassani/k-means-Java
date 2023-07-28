import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CSVReader {

    static List<Point> redToPoints(String path) {

        List<Point> dataPoints = new ArrayList<>();

        try {

            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String firstLine = br.readLine();
                parseCSVLine(firstLine);


                String line;
                while ((line = br.readLine()) != null) {
                    List<String> values = parseCSVLine(line);
                    double[] pointValues = new double[values.size()];
                    for (int i = 0; i< values.size(); i++) {
                        pointValues[i] = Double.parseDouble(values.get(i));
                    }
                    Point point = new Point(pointValues);
                    dataPoints.add(point);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataPoints;
    }

    // Helper method to parse a CSV line and split values using the comma separator
    private static List<String> parseCSVLine(String line) {
        List<String> values = new ArrayList<>();
        String[] tokens = line.split(",");
        for (String token : tokens) {
            values.add(token.trim());
        }
        return values;
    }

}
