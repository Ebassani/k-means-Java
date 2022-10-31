import java.util.ArrayList;
import java.util.List;

public class Group {
    private List<Point> points;

    public Group(Point[] points) {
        List<Point> list = List.of(points);
        this.points = new ArrayList<>(list);
    }

    public Group(List<Point> points) {
        this.points = points;
    }

    public void delete(int index) {
        points.remove(index);
    }

    public void delete(Point point) {
        points.remove(point);
    }

    @Override
    public String toString() {
        String group = "{";
        for (Point p: points) {
            group += p;
        }
        group += "}";
        return group;
    }
}
