package vectors;

public class Vector2f extends AbstractVector<Vector2f> {

    protected Vector2f(double[] coords) {
        super(2, coords);
    }

    public double x() {
        return coords[0];
    }

    public double y() {
        return coords[1];
    }
}